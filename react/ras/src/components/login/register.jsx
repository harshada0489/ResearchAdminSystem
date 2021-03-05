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
        confirm_pwd : '',
        loginType : '',
        institute : '',
        department : '',
        input: {},
        errors: {}
      };

      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }

      handleChange(event){

        let input = this.state.input;
        input[event.target.name] = event.target.value;
  
        this.setState({
          input
        });

         this.setState({
        [event.target.name]: event.target.value
      });
    }
  
    handleSubmit(event){
      event.preventDefault();

      if(this.validate()){
        console.log(this.state);
  
        let input = {};
        input["email"] = "";
        input["pwd"] = "";
        input["confirm_pwd"] = "";
        this.setState({input:input});
  
        
    }

      console.log(this.state);
      let loginRequest ={firstName: this.state.firstName , lastName: this.state.lastName , email: this.state.email , pwd: this.state.pwd, loginType: this.state.loginType, institute: this.state.institute, department: this.state.department};

      axios.post('http://localhost:8080/ras/loginRequest',this.state).then(response =>{
        // alert('Demo Form is submitted');
              //  ReactDOM.render(< Login />, document.getElementById('root'));
      })
      .catch(error =>{
        console.log(error)
      })
    }

    validate(){
      let input = this.state.input;
      let errors = {};
      let isValid = true;
  
      if (!input["email"]) {
        isValid = false;
        errors["email"] = "Please enter your email Address.";
      }
  
      if (typeof input["email"] !== "undefined") {
          
        var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
        if (!pattern.test(input["email"])) {
          isValid = false;
          errors["email"] = "Please enter valid email address.";
        }
      }
  
      if (!input["pwd"]) {
        isValid = false;
        errors["pwd"] = "Please enter your password.";
      }
  
      if (!input["confirm_pwd"]) {
        isValid = false;
        errors["confirm_pwd"] = "Please enter your confirm password.";
      }
  
      if (typeof input["pwd"] !== "undefined") {
        if(input["pwd"].length < 6){
            isValid = false;
            errors["pwd"] = "Please add at least 6 charachter.";
        }
      }
  
      if (typeof input["pwd"] !== "undefined" && typeof input["confirm_pwd"] !== "undefined") {
          
        if (input["pwd"] != input["confirm_pwd"]) {
          isValid = false;
          errors["pwd"] = "Passwords don't match.";
        }
      }
  
      this.setState({
        errors: errors
      });
  
      return isValid;
  }
    componentDidMount(){

    }

    render() {
      const { items } = this.state;

      return (
        <div className="base-container" id="register" ref={this.props.containerRef}>
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
                <input type="text" name="email" value={this.state.email} onChange={this.handleChange} 
                class="form-control"  placeholder="Enter your Email"  id="email"/>
                <div className="text-danger">{this.state.errors.email}</div>
              </div>

              <div className="form-group">
                <label htmlFor="pwd">Password</label>
                <input type="password" name="pwd" value={this.state.pwd}  onChange={this.handleChange} 
                class="form-control"  placeholder="Enter Password" id="pwd" />
                <div className="text-danger">{this.state.errors.pwd}</div>
              </div>

              <div className="form-group">
                <label htmlFor="confirm_pwd">Confirm Password</label>
                <input type="password" name="confirm_pwd" value={this.state.confirm_pwd} onChange={this.handleChange} 
                class="form-control"  placeholder="Enter Confirm Password" id="confirm_pwd" />
                <div className="text-danger">{this.state.errors.confirm_pwd}</div>
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
            <button onClick={this.handleSubmit} type="button" className="btn">
              Register
            </button>
          </div>
        </div>
      );
    }
  }

  