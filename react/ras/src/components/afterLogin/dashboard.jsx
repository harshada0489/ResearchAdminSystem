import { render } from "react-dom";
import React from 'react';
import Login from '../login/login.jsx';
import ReactDOM from 'react-dom';
import axios from 'axios';
import  CreateStudy  from './createStudy';
import App from '../../App';
import HorizontaHeaderBar from './horizontalHeaderBar';
import VerticalBar from './verticalBar';
import "./afterLoginCommon.scss";
import "./dashboard.scss";
import ReactSession from 'react-client-session';

 class Dashboard extends React.Component {
    constructor(props) {
        super(props);
        this.state ={
            isAuth: 'true'
          };
        this.createStudyHandler = this.createStudyHandler.bind(this);

        
          }   
      
      createStudyHandler(event){
        event.preventDefault();

                ReactDOM.render(<CreateStudy />, document.getElementById('root'));
                console.log(event);
            }  
  
      componentDidMount(){
                axios.get('http://localhost:8080/ras/loginAuth/setValues')
                .then(function (response){
                console.log("session login params response =  ",response);
            })
            }

    render(){

        return(
            // ReactDOM.render(<Dashboard />, document.getElementById('root'))
            <div>
                <div > <HorizontaHeaderBar /></div>
                <div > <VerticalBar /></div> 
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
        )
    }

    }

export default Dashboard;
