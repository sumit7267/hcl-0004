import React, { useEffect, useState } from 'react'

function UseFetchAPI() {

    const [serverData, setServerData] = useState([]);

    useEffect(() => {
        async function getData(){
            try{
                const serverData = await fetch("http://localhost:8080/");
                const jsonData = await serverData.text();
                setServerData(jsonData);
                console.log(jsonData);
            }
            catch(e){
                console.log(e);
            }
        }
        getData();
    }, [])
    


  return (
    <div>
      <h2>
        {
            JSON.stringify(serverData)
        }
      </h2>
    </div>
  )
}

export default UseFetchAPI