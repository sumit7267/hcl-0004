package com.hcl.library.service;

import com.hcl.library.exception.BorrowLimitExceededException;
import com.hcl.library.exception.ItemNotAvailableException;
import com.hcl.library.exception.ItemNotFoundException;
import com.hcl.library.exception.LibraryException;
import com.hcl.library.model.LibraryItem;
import com.hcl.library.model.Member;
import com.hcl.library.repository.Repository;
import com.hcl.library.util.SearchCache;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {

    private static final int FIRST_MEMBER_ID = 1;

    private final Repository<LibraryItem, Integer> itemRepository;
    private final Repository<Member, Integer> memberRepository;

    private final Map<Integer, Queue<Member>> waitlists = new HashMap<>();

    private final SearchCache searchCache = new SearchCache();
    private int nextMemberId = FIRST_MEMBER_ID;

    public LibraryService(Repository<LibraryItem, Integer> itemRepository,
                          Repository<Member, Integer> memberRepository) {
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
    }

    public void addItem(LibraryItem item) {
        itemRepository.save(item);
    }

    public Member registerMember(String name, String email) {
        Member member = new Member(nextMemberId, name, email);
        memberRepository.save(member);
        nextMemberId++;
        return member;
    }

    public LibraryItem findItem(int itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item " + itemId + " does not exist."));
    }

    public Member findMember(int memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new LibraryException("Member " + memberId + " does not exist."));
    }

    public LibraryItem issueItem(int memberId, int itemId) {
        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);

        if (!item.isAvailable()) {
            throw new ItemNotAvailableException("Item " + itemId + " is not available.");
        }
        if (!member.canBorrowMore()) {
            throw new BorrowLimitExceededException("Member " + memberId + " already has "
                    + Member.MAX_BORROW_LIMIT + " items. Return one first.");
        }

        item.borrow();
        member.addBorrowedItem(item);
        leaveWaitlist(itemId, member);
        return item;
    }

    public double returnItem(int itemId, int daysLate) {
        LibraryItem item = findItem(itemId);
        if (item.isAvailable()) {
            throw new LibraryException("Item " + itemId + " was not issued to anyone.");
        }

        Member borrower = findBorrower(item);
        item.giveBack();
        borrower.removeBorrowedItem(item);
        return item.lateFee(daysLate);
    }

    public void addToWaitlist(int memberId, int itemId) {
        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);

        if (item.isAvailable()) {
            throw new LibraryException("Item " + itemId + " is available. Please borrow it directly.");
        }
        if (member.hasBorrowed(item)) {
            throw new LibraryException("Member " + memberId + " already has item " + itemId + ".");
        }

        Queue<Member> queue = waitlists.computeIfAbsent(itemId, id -> new ArrayDeque<>());
        if (queue.contains(member)) {
            throw new LibraryException("Member " + memberId + " is already waiting for item " + itemId + ".");
        }
        queue.add(member);
    }

    public void notifyNextInWaitlist(int itemId) {
        Queue<Member> queue = waitlists.get(itemId);
        if (queue == null || queue.isEmpty()) {
            return;
        }
        Member next = queue.peek();
        System.out.println("Notification: Member " + next.getId() + " (" + next.getName()
                + ") is next in the waitlist for " + itemId + ".");
    }

    public List<Member> getWaitlist(int itemId) {
        Queue<Member> queue = waitlists.get(itemId);
        return queue == null ? List.of() : List.copyOf(queue);
    }

    private void leaveWaitlist(int itemId, Member member) {
        Queue<Member> queue = waitlists.get(itemId);
        if (queue != null) {
            queue.remove(member);
            if (queue.isEmpty()) {
                waitlists.remove(itemId);
            }
        }
    }

    private Member findBorrower(LibraryItem item) {
        for (Member member : memberRepository.findAll()) {
            if (member.hasBorrowed(item)) {
                return member;
            }
        }
        throw new LibraryException("Nobody is holding item " + item.getId() + ".");
    }

    public List<LibraryItem> listItemsByTitle() {
        List<LibraryItem> items = itemRepository.findAll();
        Collections.sort(items);
        return Collections.unmodifiableList(items);
    }

    public List<LibraryItem> listItemsByYearNewestFirst() {
        List<LibraryItem> items = itemRepository.findAll();
        items.sort(LibraryItem.BY_YEAR_NEWEST_FIRST);
        return Collections.unmodifiableList(items);
    }

    public List<LibraryItem> searchByTitle(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("Search text must not be blank");
        }
        String text = keyword.trim().toLowerCase();
        searchCache.recordSearch(text);

        return itemRepository.findAll().stream()
                .filter(item -> item.getTitle().toLowerCase().contains(text))
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getRecentSearches() {
        return searchCache.recentSearches();
    }

    public Map<String, Integer> countItemsByType() {
        Map<String, Integer> counts = new TreeMap<>();
        for (LibraryItem item : itemRepository.findAll()) {
            counts.merge(item.getClass().getSimpleName(), 1, Integer::sum);
        }
        return counts;
    }

    public int removeOldItems(int beforeYear) {
        return itemRepository.removeIf(item -> item.getYear() < beforeYear && item.isAvailable());
    }

    public List<Member> getMembers() {
        return Collections.unmodifiableList(memberRepository.findAll());
    }

    public void exportReport(String path) {
        String report = buildReport();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(report);
        } catch (IOException e) {
            throw new LibraryException("Could not write the report to " + path, e);
        }
    }

    private String buildReport() {
        List<LibraryItem> items = new ArrayList<>(itemRepository.findAll());
        items.sort(Comparator.comparingInt(LibraryItem::getId));

        StringBuilder report = new StringBuilder();
        String newLine = System.lineSeparator();

        report.append("===== LIBRARY REPORT =====").append(newLine);
        for (LibraryItem item : items) {
            report.append(item)
                    .append(" | ")
                    .append(item.isAvailable() ? "Available" : "Issued")
                    .append(newLine);
        }
        report.append("Total items: ").append(items.size()).append(newLine);
        return report.toString();
    }
}