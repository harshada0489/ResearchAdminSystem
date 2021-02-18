import { render } from "react-dom";
import React from 'react';
import Login from '../login/login.jsx';
import ReactDOM from 'react-dom';
import axios from 'axios';
import  CreateStudy  from './createStudy';
import App from '../../App';



 class Dashboard extends React.Component {
    constructor(props) {
        super(props);

        this.createStudyHandler = this.createStudyHandler.bind(this);
        this.logoutHandler = this.logoutHandler.bind(this);
      }
      
      createStudyHandler(event){
        event.preventDefault();
                ReactDOM.render(<CreateStudy />, document.getElementById('root'));
                console.log(event);
            }  

        logoutHandler(event){
        event.preventDefault();
                ReactDOM.render(<App />, document.getElementById('root'));

            }  

    render(){
        
        return(
            // ReactDOM.render(<Dashboard />, document.getElementById('root'))
            <div>
                 <h1>Welcome to Dasboard</h1>
                 <div className="footer">
                    <button type="button" onClick={this.createStudyHandler} className="btn">
                    Create Study
                    </button>
                </div>
                <div className="footer">
                    <button type="button" onClick={this.logoutHandler} className="btn">
                    Logout
                    </button>
                </div>
             </div>
        )
    }

    }

export default Dashboard;
