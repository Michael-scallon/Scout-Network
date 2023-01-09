import React, { Component } from "react";
import Nav from "./Components/Nav";
import LoginForm from "./Components/LoginForm";
import SignupForm from "./Components/SignupForm";
import "./App.css";
import { useState } from "react";
import Upload from "./Components/Upload";

import Get from "./Components/Get";
class App extends Component {
  state = {
    title: "",
    content: "",
  };

  handle_change = (e) => {
    const name = e.target.name;
    const value = e.target.value;
    this.setState((prevstate) => {
      const newState = { ...prevstate };
      newState[name] = value;
      return newState;
    });
  };

  constructor(props) {
    super(props);
    this.state = {
      displayed_form: "",
      logged_in: localStorage.getItem("token") ? true : false,
      username: "User",
      file: null,
    };
  }
  componentDidMount() {
    if (this.state.logged_in) {
      fetch("http://localhost:8080/players", {})
        .then((res) => res.json())
        .then((json) => {
          console.log(json);
        });
    }

    if (this.state.logged_in) {
      fetch("http://localhost:8000/core/current_user/", {
        headers: {
          Authorization: `JWT ${localStorage.getItem("token")}`,
        },
      })
        .then((res) => res.json())
        .then((json) => {
          this.setState({ username: json.username });
        });
    }
  }

  handle_login = (e, data) => {
    e.preventDefault();
    fetch("http://localhost:8000/token-auth/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((res) => res.json())
      .then((json) => {
        console.log(json);
        localStorage.setItem("token", json.token);
        console.log(json.token);
        if (json.username === undefined) {
          json.username = "User";
        }

        if (json.token === undefined) {
          alert("Unable to login, Please try again");
          this.setState({
            logged_in: false,
            displayed_form: "signup",
            username: json.username,
          });
        } else {
          this.setState({
            logged_in: true,
            displayed_form: "",
            username: json.username,
          });
        }
      });
  };

  handle_signup = (e, data) => {
    fetch("http://localhost:8080/players")
      .then((res) => res.json())
      .then((json) => {
        console.log(json);
      });
    e.preventDefault();
    fetch("http://localhost:8000/core/users/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((res) => res.json())
      .then((json) => {
        localStorage.setItem("token", json.token);
        console.log(json.token);
        if (json.token === undefined) {
          alert("Unable to login, Please try again");

          this.setState({
            logged_in: false,
            displayed_form: "signup",
          });
        } else {
          this.setState({
            logged_in: true,
            displayed_form: "",
            username: json.username,
          });
        }
      });
  };

  handle_logout = () => {
    localStorage.removeItem("token");
    this.setState({ logged_in: false, username: "" });
  };

  display_form = (form) => {
    this.setState({
      displayed_form: form,
    });
  };

  render() {
    const isLoggedIn = this.state.logged_in;
    let form;
    switch (this.state.displayed_form) {
      case "login":
        form = <LoginForm handle_login={this.handle_login} />;
        break;
      case "signup":
        form = <SignupForm handle_signup={this.handle_signup} />;
        break;
      default:
        form = null;
    }

    return (
      <div className="App">
        <Nav
          logged_in={this.state.logged_in}
          display_form={this.display_form}
          handle_logout={this.handle_logout}
        />

        <div class="logo"></div>
        {form}
        <h3>
          {this.state.logged_in ? (
            `Hello, ${this.state.username}`
          ) : (
            <h2>Please Log In</h2>
          )}
        </h3>

        <div>{isLoggedIn ? <Hidden /> : ""}</div>
      </div>
    );
  }
}

function activateLasers(url) {
  fetch("http://localhost:8080/players")
    .then((res) => res.json())
    .then((json) => {
      console.log(json);
    });
}

function Hidden() {
  const [name, setName] = useState("ALL");
  const [age, setAge] = useState("/ALL");
  const [Positon, setPositon] = useState("/ALL");
  console.log(name);
  console.log(Positon);
  console.log(age);

  var url = Positon + age + "/" + name;

  console.log(url);

  return (
    <div>
      <body>
        <h1>PUT IN QUERY HERE</h1>
        <Get></Get>
        {/* <h3>
          Positon example
        </h3>
        <select value={Positon}
          onChange={(e) => setPositon(e.target.value)}>
          <option value="/Goal">GoalKeeper</option>
          <option value="/DEF">Defender</option>
          <option selected value="/Mid">Midfield</option>
          <option value="/For">Forward</option>
        </select>

        <select value={age}
          onChange={(e) => setAge(e.target.value)}>
          <option value="/<20">Under 20</option>
          <option value="/21-25">21 - 25</option>
          <option selected value="/25-30">25 - 30</option>
          <option value="/30+">30+</option>
        </select>

        <form>
      <label>Enter your name:
        <input
          type="text" 
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </label>
      </form> */}
        {/* <button onClick={activateLasers}> Activate Lasers </button>
      <button onClick={() => activateLasers(url)}>Greet</button>; */}

        {/* <Upload></Upload> */}
      </body>
    </div>
  );
}

export default App;
