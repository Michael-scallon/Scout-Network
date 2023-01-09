import axios from "axios";
import './Get.css';
import { useState, useEffect } from "react";
import { func } from "prop-types";

function Get() {
  const [data, setData] = useState([]);
  const [userInput, setUserInput] = useState([]);
  const [userInput1, setUserInput1] = useState([]);
  const [userInput2, setUserInput2] = useState([]);
  const [userInput3, setUserInput3] = useState([]);
  const [userInput4, setUserInput4] = useState([]);
  console.log(userInput2)
  
  function reset(){
    var params = new URLSearchParams();
  }
  const handleSubmit = (event) => {
    console.log("running")
    event.preventDefault();
    var params = new URLSearchParams();
    
    if (userInput.length >= 1){
      params.append("position",userInput);}
    
    if (userInput1.length >= 1){
    params.append("age",userInput1);}
    if (userInput2.length >= 1){
    params.append("goalsPer",userInput2);}
    if (userInput3.length >= 1){
      params.append("assistsPer",userInput3);}
    if (userInput4.length >= 1){
      params.append("concessionsPer",userInput4);}

      
    console.log(userInput2)
    var request = {
      params: params
    };
    // axios({
    //   url: "https://api.unsplash.com/search/photos",
    //   method: "GET",
    //   dataResponse: "json",
    //   params: {
    //     client_id: "d54vE8fu6bjJ_JgkBqugaZOt4bwFHGkmiKDGHunnXxc",
    //     query: userInput2,
        
    //   },
    // })
    axios.get('http://localhost:8080/players', request)
    .then((response) => {
        console.log(response.data)
        console.log(response.data.results);
        setData(response.data);
        console.log(data[0])
        reset();
    });
  };

  return (
    <div>
      <div class="UInput">
      <form action="" onSubmit={handleSubmit}>
        <h3>Postion</h3>
        <input
          id="search"
          type="text"
          value={userInput}
          onChange={(event) => setUserInput(event.target.value)}
        />
        {/* <select value={userInput}
          onChange={(event) => setUserInput(event.target.value)}>
          <option value="Goalkeeper">GoalKeeper</option>
          <option value="Defender">Defender</option>
          <option selected value="Midfield">Midfield</option>
          <option value="Forward">Forward</option>
        </select> */}
        <h3>Max Age</h3>
        <input
          id="search"
          type="text"
          value={userInput1}
          onChange={(event) => setUserInput1(event.target.value)}
        />
        <h3>Goals per game | 0.0 - 5.0</h3>
        <input
          id="search"
          type="text"
          value={userInput2}
          onChange={(event) => setUserInput2(event.target.value)}
        />
        <h3>Assists per game | 0.0 - 5.0</h3>
        <input
          id="search"
          type="text"
          value={userInput3}
          onChange={(event) => setUserInput3(event.target.value)}
        />
        <h3>concessions per game | 0.0 - 5.0</h3>
        <input
          id="search"
          type="text"
          value={userInput4}
          onChange={(event) => setUserInput4(event.target.value)}
        />
      </form>
      <br></br>
      <hr></hr>
      <br></br>
      <button class="GetPlayer" onClick={handleSubmit}>
  Get players
</button>
</div>
      
      <div className="player-container">
        {data.map((data, key) => {
          return (
            <div key={key}>
              <center>
              <table>
              {/* <thead>Player</thead>
              <tr>
              {data.name}
              </tr>
              <tr>
                {data.playerInfo.age} </tr>
                <tr>
                {data.playerInfo.assistsPer}</tr>
                <tr>
                {data.playerInfo.goalsPer}
                </tr> */}

                <tr>
                <th>Name</th>
                <th>Position</th>
                <th>appearances</th>
                <th>assists</th>
                <th>concessions</th>
                <th>goals</th>
                <th>minutes</th>
                <th>Nationality</th>
                <th>region</th>
                <th>Age</th>
                <th>APG</th>
                <th>GPG</th>
                <th>CPG</th>
              </tr>
              <tr>
                <td>{data.name}</td>
                <td>{data.playerInfo.position}</td>
                <td>{data.playerInfo.appearances}</td>
                <td>{data.playerData.assists}</td>
                <td>{data.playerData.concessions}</td>
                <td>{data.playerData.goals}</td>
                <td>{data.playerData.minutes}</td>
                <td>{data.playerData.nationality}</td>
                <td>{data.playerData.region}</td>
                <td>{data.playerInfo.age}</td>
                <td>{data.playerInfo.assistsPer}</td>
                <td>{data.playerInfo.goalsPer}</td>
                <td>{data.playerInfo.concessionsPer}</td>
              </tr>
              </table>
              </center>

              {/* <table>
      <tbody>
        <tr>
          <td>
            <h5>{data.name}</h5>
          </td>
          <td>
            <h5>{data.name}</h5>
          </td>
          <td>
            <h4>{data.name}</h4>
          </td>
          <td>
            <p>{data.name}</p>
          </td>
        </tr>
      </tbody>
    </table> */}
            </div>
          );
        })}
      </div>


    </div>
  );
}

export default Get;