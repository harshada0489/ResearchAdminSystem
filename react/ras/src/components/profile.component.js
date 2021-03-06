import React, { Component } from "react";
import AuthService from "../services/auth.service";
import ReactDOM from 'react-dom';
import  CreateStudy  from './afterLogin/createStudy';

import {SideBar} from './SideBar/SideBar';

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      currentUser: AuthService.getCurrentUser()
    };
    this.createStudyHandler = this.createStudyHandler.bind(this);
    this.adminCreateStudyFormHandler = this.adminCreateStudyFormHandler.bind(this);
    this.systemFormHandler = this.systemFormHandler.bind(this);
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

  render() {
    const { currentUser } = this.state;
    console.log("Role = " , currentUser.roles);
    
    if (currentUser.roles.includes('ROLE_ADMIN')) {
      return (
        <div className="container">
          

          <header className="jumbotron">
            <h3>
              Welcome <strong> {currentUser.username}</strong> 
            </h3>
          </header>
  

              <div>
                  <div className="diff-action">
                     <button type="button" onClick={this.systemFormHandler} className="btn">
                     System Form
                     </button>
                 </div>

  

                  <div className="diff-action">
                     <button type="button" onClick={this.createStudyHandler} className="btn">
                     Create Form
                     </button>
                 </div>
  
                 {/* <div className="diff-action">
                     <button type="button" onClick={this.createStudyHandler} className="btn">
                     Check User Status
                     </button>
                 </div> */}
                 
              </div> 
        </div>
      );

    }
    if (this.state.currentUser.roles.includes('ROLE_MODERATOR')) {
      return (
        <div className="container">
          <header className="jumbotron">
            <h3>
              Welcome <strong> {currentUser.username}</strong> 
            </h3>
          </header>
  
  
                 <div>
                  <div className="diff-action">
                     <button type="button" onClick={this.createStudyHandler} className="btn">
                     Review Study
                     </button>
                 </div>
  
                 <div className="diff-action">
                     <button type="button" onClick={this.createStudyHandler} className="btn">
                     Reviewed Study Status
                     </button>
                 </div>
                 
              </div> 
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
          </header>

          {/* <SideBar/> */}

                 <div>
                  <div className="diff-action">
                     <button type="button" onClick={this.createStudyHandler} className="btn">
                     Create Study
                     </button>
                 </div>
  
                 <div className="diff-action">
                     <button type="button" onClick={this.createStudyHandler} className="btn">
                     Submitted Study Status
                     </button>
                 </div>
                 
              </div> 
        </div>

        </div>
      );
    }
  }
  
}