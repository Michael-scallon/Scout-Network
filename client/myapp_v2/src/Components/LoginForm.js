import React from 'react';
import PropTypes from 'prop-types';

class LoginForm extends React.Component {
  state = {
    username: '',
    password: ''
  };

  handle_change = e => {
    const name = e.target.name;
    const value = e.target.value;
    this.setState(prevstate => {
      const newState = { ...prevstate };
      newState[name] = value;
      return newState;
    });
  };

  render() {
    return (
  <div>
    <div>
      <h2 className="">Sign into your account</h2>
    </div>
    <form className="mt-8 space-y-6" onSubmit={e => this.props.handle_login(e, this.state)}>
      <input type="hidden" name="remember" value="true"/>
      
      <div>
        <div  className="form">
          <label htmlFor="username" className="">Email address </label>
          <input id="email-address" type="email" autoComplete="email" required placeholder="Email address" name="username" value={this.state.username} onChange={this.handle_change} />
        
        </div>
        <div  className="form">
          <label htmlFor="password" className="">Password </label>
          <input id="password" type="password" autoComplete="current-password" required className="" placeholder="Password"
          name="password"
          value={this.state.password}
          onChange={this.handle_change}/>
        </div>
      </div>
      <div>
        <button type="submit" className="">
          <span>

            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
              <path fillRule="evenodd" d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z" clipRule="evenodd" />
            </svg>
          </span>
          Sign in
        </button>
      </div>
    </form>
  </div>

















    //   <form >
    //     <h4>Log In</h4>
    //     <label htmlFor="username">Username</label>
    //     <input
    //       type="text"
    //       name="username"
    //       value={this.state.username}
    //       onChange={this.handle_change}
    //     />
    //     <label htmlFor="password">Password</label>
    //     <input
    //       type="password"
    //       name="password"
    //       value={this.state.password}
    //       onChange={this.handle_change}
    //     />
    //     <input type="submit" />
    //   </form>
    );
  }
}

export default LoginForm;

LoginForm.propTypes = {
  handle_login: PropTypes.func.isRequired
};