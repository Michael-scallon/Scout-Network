import React from 'react';
import PropTypes from 'prop-types';

class SignupForm extends React.Component {
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
    <div>
      <h2>Sign up for an account</h2>
    </div>
    <form onSubmit={e => this.props.handle_signup(e, this.state)}>
      <input type="hidden" name="remember" value="true"/>
      <div>
        <div className="form">
          <label htmlFor="username" className="sr-only">Email address </label>
          <input id="email-address" type="email" autoComplete="email" required placeholder="Email address" name="username" value={this.state.username} onChange={this.handle_change} />
        
        </div>
        <div  className="form">
          <label htmlFor="password" className="sr-only">Password </label>
          <input id="password" type="password" autoComplete="current-password" required className=" py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Password"
          name="password"
          value={this.state.password}
          onChange={this.handle_change}/>
        </div>
      </div>

      <div>

      </div>

      <div>
        <button type="submit" className="">
          <span className="">

            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
              <path fillRule="evenodd" d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z" clipRule="evenodd" />
            </svg>
          </span>
          Sign in
        </button>
      </div>
    </form>
  </div>
</div>
    );
  }
}

export default SignupForm;

SignupForm.propTypes = {
  handle_signup: PropTypes.func.isRequired
};