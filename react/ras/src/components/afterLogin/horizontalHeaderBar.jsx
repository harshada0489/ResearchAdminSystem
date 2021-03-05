import { render } from "react-dom";
import React from 'react';
import Login from '../login/login.jsx';
import ReactDOM from 'react-dom';
import axios from 'axios';
import  CreateStudy  from './createStudy';
import App from '../../App';
import "./afterLoginCommon.scss";
import headerLogoImg from "../../images/headerLogo.jpeg";
import AuthService from "../../services/auth.service";


class HorizontaHeaderBar extends React.Component {
    constructor(props) {
        super(props);
        this.state ={
          isAuth: 'true',
          currentUser: AuthService.getCurrentUser()
        };

        this.logoutHandler = this.logoutHandler.bind(this);
      }

      logoutHandler(event){
        event.preventDefault();
          this.state ={
            isAuth: 'false'
          };
          console.log("Auth value = ",this.state.isAuth);
        ReactDOM.render(<App />, document.getElementById('root'));

            }  
      render(){
        let { currentUser } = this.state;

          return(
            <div className="horizontal-bar">
              <div className="headerimage">
                <img src={headerLogoImg} />
              </div>
              
              {/* <div className="welcome"><h1>Welcome to Dasboard </h1></div> */}
              <header className="welcome">
                <h3>
                  Welcome <strong> {currentUser.username}</strong> 
                </h3>
              </header>

              <ul>
                <li>
                <button type="button" onClick={this.logoutHandler} className="btn">
                    Logout
                </button>
                </li>
              </ul>
                
            </div>
          )
        
      }
      
    }

    export default HorizontaHeaderBar;