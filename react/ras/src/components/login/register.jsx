import React, {useState} from 'react';
import loginImg from "../../login.png";
import axios from 'axios';
import ReactDOM from 'react-dom';
import { Login } from "./login";

export class Register extends React.Component {
    constructor(props) {
      super(props);
      this.state ={
        firstName : '',
        lastName : '',
        email : '',
        pwd : '',
        loginType : '',
        institute : '',
        department : ''
      };

      this.handleChange = this.handleChange.bind(this);
      this.handleClick = this.handleClick.bind(this);
    }

      handleChange(event){
         this.setState({
        [event.target.name]: event.target.value
      });
    }
  
    handleClick(event){
      event.preventDefault();
      console.log(this.state);
      let loginRequest ={firstName: this.state.firstName , lastName: this.state.lastName , email: this.state.email , pwd: this.state.pwd, loginType: this.state.loginType, institute: this.state.institute, department: this.state.department};

      axios.post('http://localhost:8080/ras/loginRequest',this.state).then(respose =>{
        // console.log(respose)
        // if(response.data ==="Successful" && response.status == 200){
               ReactDOM.render(< Login />, document.getElementById('root'));
              //  export{ Login } from "./login";
        // }
      })
      .catch(error =>{
        console.log(error)
      })
    }

    componentDidMount(){

    }

    render() {
      const { items } = this.state;

      return (
        <div className="base-container" ref={this.props.containerRef}>
          <div className="header">Register</div>
          <div className="content">
            <div className="image">
              <img src={loginImg} />
            </div>
            <div className="form">
              <div className="form-group">
                <label htmlFor="firstName">First Name</label>
                <input type="text" name="firstName" value={this.state.firstName}  onChange={this.handleChange} placeholder="firstName"/>
              </div>
              <div className="form-group">
                <label htmlFor="lastName">Last Name</label>
                <input type="text" name="lastName" value={this.state.lastName} onChange={this.handleChange} placeholder="lastName"/>
              </div>
              <div className="form-group">
                <label htmlFor="email">Email</label>
                <input type="text" name="email" value={this.state.email} onChange={this.handleChange} placeholder="Email" />
              </div>
              <div className="form-group">
                <label htmlFor="pwd">Password</label>
                <input type="password" name="pwd" value={this.state.pwd} onChange={this.handleChange} placeholder="Password" />
              </div>
              <div className="form-group">
                <label htmlFor="loginType">Login Type</label>
                <input type="text" name="loginType" value={this.state.loginType} onChange={this.handleChange} placeholder="Login Type" />
              </div>
              <div className="form-group">
                <label htmlFor="institute">Institute</label>
                <input type="text" name="institute" value={this.state.institute} onChange={this.handleChange} placeholder="Institute" />
              </div>
              <div className="form-group">
                <label htmlFor="department">Department</label>
                <input type="text" name="department" value={this.state.department} onChange={this.handleChange} placeholder="Department" />
              </div>
            </div>
          </div>
          <div className="footer">
            <button onClick={this.handleClick} type="button" className="btn">
              Register
            </button>
          </div>
        </div>
      );
    }
  }

  