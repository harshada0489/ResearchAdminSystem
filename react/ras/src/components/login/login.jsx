import React from 'react';
 import loginImg from "../../login.png";
 import axios from 'axios';
import ReactDOM from 'react-dom';
import { render } from '@testing-library/react';
import Dashboard from '../afterLogin/dashboard';
import { Route } from 'react-router-dom'; 
import { BrowserRouter, Router, Switch, Redirect, Link } from "react-router-dom";

 export class Login extends React.Component {
    constructor(props) {
      super(props);
      this.state ={
        email : '',
        pwd : '',
        isAuth: 'false'
      };

      this.handleChange = this.handleChange.bind(this);
      this.clickHandler = this.clickHandler.bind(this);
    }
    
    handleChange(event){
      this.setState({
     [event.target.name]: event.target.value
   });
 }

 clickHandler(event){
    event.preventDefault();
       axios.get('http://localhost:8080/ras/loginRequest?email=' + this.state.email + '&' + 'pwd=' + this.state.pwd)
        .then(function (response){
          console.log(response);


          if(response.data==='Successful'){
            // this.setState ({ 
            //   isAuth: "true" 
            // });
            ReactDOM.render(<Dashboard />, document.getElementById('root'));

          }

          })
        }   

 componentDidMount(){

 }


    render() {
      return (
        <div className="base-container" ref={this.props.containerRef}>
          <div className="header">Login</div>
          <div className="content">
            <div className="image">
              <img src={loginImg} />
            </div>
            <div className="form">
              <div className="form-group">
                <label htmlFor="email">Email</label>
                <input type="text" name="email" value={this.state.email} onChange={this.handleChange} placeholder="Email" />
              </div>
              <div className="form-group">
                <label htmlFor="pwd">Password</label>
                <input type="password" name="pwd" value={this.state.pwd} onChange={this.handleChange} placeholder="password" />
              </div>
            </div>
          </div>
          <div className="footer">
            <button type="button" onClick={this.clickHandler} className="btn">
              Login
            </button>
          </div>
        </div>
      );
    }




  }

