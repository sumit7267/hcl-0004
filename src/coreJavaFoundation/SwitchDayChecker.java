package coreJavaFoundation;

public class SwitchDayChecker {
    public static void main(String[] args) {
        String day = "SATURDAY";

        // Modern switch expression
        String dayType = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> throw new IllegalArgumentException("Invalid day: " + day);
        };

        System.out.println(day + " is a " + dayType);
    }
}