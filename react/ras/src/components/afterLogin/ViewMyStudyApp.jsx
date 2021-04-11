import React from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";
import { NotificationContainer, NotificationManager } from 'react-notifications';

import AuthService from "../../services/auth.service";
import StudyAppView from './StudyAppView';

const API_URL = "http://localhost:8080/ras/viewMyStudyForm"




class ViewMyStudyApp extends React.Component {
    constructor(props){
        super(props);
        this.state={
            studyFormList: [],
            myTasks:[],
            message: null,
            currentPage : 1,
            creatorId: AuthService.getCurrentUser().id,

            errors:""
        }

        this.refreshCourses = this.refreshCourses.bind(this);
    }
 

    refreshCourses(){
        axios.get(API_URL + "/" + this.state.creatorId).then(response => {
            console.log("response= ",response.data.MyStudyApp);
            this.setState({
                studyFormList : response.data.MyStudyApp,
                myTasks : response.data.myTasks
            });
            
            console.log("studyFormList=",this.state.studyFormList);
            console.log("myTasks=",this.state.myTasks);
        });
    }


    componentDidMount(){
        this.refreshCourses();
    }

    render(){

        console.log("this.state.creatorId = " , this.state.creatorId);
        return(
                <div className="container">
                    <h3>My Tasks</h3>
                    {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                    <div className="container">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>Rb Id</th>
                                    <th>Study Title</th>
                                    <th>Task Status</th>
                                    <th>Round No</th> 
                                    <th>Outcome</th> 
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.myTasks.map(
                                        formDetails =>
                                            <tr key={formDetails.id}>
                                                <td>
                                                    <Link to = {"viewMyRbTasksForm/viewPage/"+ this.state.currentPage +"/studyApp/view/" + formDetails.id}>{formDetails.id}</Link>
                                                </td>
                                                <td>{formDetails.studyTitle}</td>
                                                <td>{formDetails.taskStatus}</td>
                                                <td> Round Todo </td>
                                                <td>{formDetails.reviewerOutcome}</td>
                                                {/* <td>{formDetails.reviewerOutcome}</td> */}
                                                
                                                {/* <td><button className="btn btn-success" onClick={() => this.viewFormClicked(formDetails.id)}>View</button></td> */}
                                                {/* <td><button className="btn btn-warning" onClick={() => this.deleteFormClicked(formDetails.id)}>Delete</button></td> */} 
                                            
                                            </tr>
                                    )
                                }
                            </tbody>
                        </table>
                            {/* <div className="row">
                                <button className="btn btn-success" onClick={this.createStudyHandler}> + </button>
                            </div> */}
                    </div>







                <h3>My Study Applications</h3>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Study Title</th>
                                <th>Status</th>
                                <th>Create Date</th> 
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.studyFormList.map(
                                    formDetails =>
                                        <tr key={formDetails.id}>
                                            <td>
                                                <Link to = {"viewMyStudyForm/viewPage/"+ this.state.currentPage +"/studyApp/view/" + formDetails.id}>{formDetails.id}</Link>
                                            </td>
                                            <td>{formDetails.studyTitle}</td>
                                            <td>{formDetails.status}</td>
                                            <td>{formDetails.createdDate}</td>
                                            
                                             {/* <td><button className="btn btn-success" onClick={() => this.viewFormClicked(formDetails.id)}>View</button></td> */}
                                            {/* <td><button className="btn btn-warning" onClick={() => this.deleteFormClicked(formDetails.id)}>Delete</button></td> */} 
                                        
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                        {/* <div className="row">
                            <button className="btn btn-success" onClick={this.createStudyHandler}> + </button>
                        </div> */}
                </div>
            </div>
        );
    }
}

export default ViewMyStudyApp;