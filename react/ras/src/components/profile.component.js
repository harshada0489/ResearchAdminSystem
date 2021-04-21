import React, { Component } from "react";
import AuthService from "../services/auth.service";
import ReactDOM from 'react-dom';
import  CreateStudy  from './afterLogin/createStudy';

import {SideBar} from './SideBar/SideBar';

import ViewMyStudyApp from './afterLogin/ViewMyStudyApp';
import axios from 'axios';

const API_URL = "http://localhost:8080/api/auth"


export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      currentUser: AuthService.getCurrentUser()
    };
    this.createStudyHandler = this.createStudyHandler.bind(this);
    this.adminCreateStudyFormHandler = this.adminCreateStudyFormHandler.bind(this);
    this.systemFormHandler = this.systemFormHandler.bind(this);
    this.studyStatusHandler = this.studyStatusHandler.bind(this);
  }

  createStudyHandler(event){
      event.preventDefault();
      this.props.history.push("/createStudy");
       window.location.reload();
  }  

  adminCreateStudyFormHandler(event){
      event.preventDefault();
      this.props.history.push("/adminCreateStudyForm");
  }

  systemFormHandler(event){
    event.preventDefault();
      this.props.history.push("/systemForm");
  }

  studyStatusHandler(event){
    event.preventDefault();
      this.props.history.push("/viewSystemForm");
  }




  render() {
    const { currentUser } = this.state;
    console.log("Role = " , currentUser.roles);
    
    let lastLogin = this.state.currentUser.lastLoginTime;
    console.log ("render lastLoggedIn =  ", this.state.currentUser.lastLoginTime);


    if (currentUser.roles.includes('ROLE_ADMIN')) {
      return (
        <div className="container">
          

          <header className="jumbotron">
            <h3>
              Welcome <strong> {currentUser.username}</strong> 
            </h3>
            <div className="lastLoginTime">Last Logged In {lastLogin} </div>
          </header>
  

              <div>
                  {/* <div className="diff-action"> */}
                  <button className="btn btn-success" onClick={this.systemFormHandler}> View All System Form </button>
                     {/* <button type="button" onClick={this.systemFormHandler} className="btn">
                     System Form
                     </button> */}
                 {/* </div> */}

                 
              </div> 
        </div>
      );

    }
    if (this.state.currentUser.roles.includes('ROLE_REVIEWER')) {
      return (
        <div className="container">
          <header className="jumbotron">
            <h3>
              Welcome <strong> {currentUser.username}</strong> 
            </h3>
            <div className="lastLoginTime">Last Logged In {lastLogin} </div>
          </header>
  
          <ViewMyStudyApp />
                 {/* <div>
                  <div className="diff-action">
                     <button type="button" onClick={null} className="btn">
                     Review Study
                     </button>
                 </div>
  
                 <div className="diff-action">
                     <button type="button" onClick={null} className="btn">
                     Reviewed Study Status
                     </button>
                 </div>
                 
              </div>  */}
        </div>
      );
    }
    if (this.state.currentUser.roles.includes('ROLE_USER')) {
      return (
        
<div>


    <div className="container">
  
          <header className="jumbotron">
            <h3>
              Welcome <strong> {currentUser.username}</strong> 
            </h3>
            <div className="lastLoginTime">Last Logged In {lastLogin} </div>
          </header>

          {/* <SideBar/> */}

                 <div>
                      <button className="btn btn-success" onClick={this.createStudyHandler}> Create Study </button>
                  <div>
                    <ViewMyStudyApp />
                  </div>
                 
              </div> 
        </div>

        </div>
      );
    }
  }
  
}