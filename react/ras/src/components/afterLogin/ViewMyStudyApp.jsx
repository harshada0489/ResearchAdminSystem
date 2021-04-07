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
            systemFormList: [],
            message: null,
            currentPage : 1,
            creatorId: AuthService.getCurrentUser().id,

            errors:""
        }

        this.refreshCourses = this.refreshCourses.bind(this);
        // this.viewFormClicked = this.viewFormClicked.bind(this);

    }
 
    // viewFormClicked(studyAppId){

    //     console.log("this.state.currentPage = ", this.state.currentPage);
    //     console.log("studyAppId = ", studyAppId);
    
    //     axios.post(API_URL + "/page/" + this.state.currentPage + "/studyApp/view/" + studyAppId)
    //    .then(response =>{
    //     console.log("response.data =", response.data);
    //     var resdata = response.data.questionList[0];
        
    //     console.log("Before history push ");
    //     // <StudyAppView/>
    //     this.props.history.push({
    //         pathname: "/study/viewPage/" + this.state.currentPage + "/studyApp/view/" + studyAppId,
    //         state: { detail: response.data }
    //     });

    //     console.log("After history push ");

    //     window.location.reload();
    // }

    //     ).catch(error => {
            
    //         NotificationManager.error("Bad Request");
            
    //         this.setState({ errors: error })
    //     });



    // }

    refreshCourses(){
        axios.get(API_URL + "/" + this.state.creatorId).then(response => {
            console.log("response= ",response.data);
            this.setState({
                systemFormList : response.data
            });
            
            console.log("systemFormList=",this.state.systemFormList);
        });
    }


    componentDidMount(){
        this.refreshCourses();
    }

    render(){

console.log("this.state.creatorId = " , this.state.creatorId);
        return(
            <div className="container">
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
                                this.state.systemFormList.map(
                                    formDetails =>
                                        <tr key={formDetails.id}>
                                            <td>
                                                <Link to = {"viewMyStudyForm/viewPage/"+ this.state.currentPage +"/studyApp/view/" + formDetails.id}>{formDetails.id}</Link>
                                            </td>
                                            {/* <td>{formDetails.id}</td> */}
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