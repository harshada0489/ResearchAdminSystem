import React from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";
import { NotificationContainer, NotificationManager } from 'react-notifications';

import AuthService from "../../services/auth.service";
import StudyAppView from './StudyAppView';

// import { PieChart } from 'react-minimal-pie-chart';
import Chart from "react-google-charts";



const API_URL = "http://localhost:8080/ras/viewMyStudyForm"




class ViewMyStudyApp extends React.Component {
    constructor(props){
        super(props);
        this.state={
            studyFormList: [],
            myTasks:[],
            unReadCount: '',
            inProgressCount: '',
            completeCount: '',

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
                myTasks : response.data.myTasks,
                unReadCount : response.data.countTaskStatus.unReadCount,
                inProgressCount : response.data.countTaskStatus.inProgressCount,
                completeCount : response.data.countTaskStatus.completeCount,
            });
            
            console.log("studyFormList=",this.state.studyFormList);
            console.log("myTasks=",this.state.myTasks);
        });
    }


    componentDidMount(){
        this.refreshCourses();
    }

    render(){


       
        







        let unReadCount = this.state.unReadCount;
        let inProgressCount = this.state.inProgressCount;
        let completeCount = this.state.completeCount;
        console.log("unReadCount =" + unReadCount);
        console.log("inProgressCount =" + inProgressCount);
        console.log("completeCount =" + completeCount);


        console.log("this.state.creatorId = " , this.state.creatorId);


        const mystyle = {
            height: "500px",
            overflow : "scroll"
        }

        return(


            


                <div className="container" >


                        <Chart
                        width={'500px'}
                        height={'300px'}
                        chartType="PieChart"
                        loader={<div>Loading Chart</div>}
                        data={[
                            ['Task', 'Hours per Day'],
                            ['UnRead', unReadCount],
                            ['In Progress', inProgressCount],
                            ['Completed', completeCount],
                        ]}
                        options={{
                            title: 'My Review Task',
                            is3D: true,
                            slices: {
                                0: { color: 'red' },
                                1: { color: 'brown' },
                                2: { color: 'green' },
                            },
                            pieSliceText: 'label',
                        }}
                        rootProps={{ 'data-testid': '1' }}
                        />



                    <h3>My Review Tasks</h3>
                    {this.state.message && <div class="alert alert-success">{this.state.message}</div>}

                    <div className="container"  >
                        <table className="table task" > 
                                <thead >
                                    <tr>
                                    <th>Total Unread = {unReadCount} </th>
                                    <th>Total In Progress = {inProgressCount} </th>
                                    <th>Total Complete = {completeCount}</th> 
                                    </tr>
                                </thead>
                        </table>
                    </div>

                    <div className="container" style = { mystyle}>
                        <table className="table task">
                            <thead>
                                <tr>
                                    <th>Rb Id</th>
                                    <th>Study Title</th>
                                    <th>PI</th>
                                    <th>Study Author</th>
                                    <th>Task Status</th>
                                    <th>Round No</th> 
                                    <th>Outcome</th> 
                                </tr>
                            </thead>
                            <tbody className= "highlight" >
                                {
                                    this.state.myTasks.map(
                                        formDetails =>
                                        
                                            <tr key={formDetails.id} >
                                               
                                                <td>
                                                    <Link to = {"viewMyRbTasksForm/viewPage/"+ this.state.currentPage +"/studyApp/view/" + formDetails.id}>{formDetails.id}</Link>
                                                </td>
                                                <td>{formDetails.studyTitle}</td>

                                                <td>{formDetails.piforFrontEnd}</td>
                                                <td>{formDetails.studyAuthorForFrontEnd} </td>


                                                <td>{formDetails.taskStausForFrontEnd}</td>
                                                <td>{formDetails.roundForFrontEnd} </td>
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
                <div className="container" style = { mystyle}>
                    <table className="table task">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Study Title</th>
                                <th>Status</th>
                                <th>Create Date</th> 
                                <th>Comments</th>
                                
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
                                            {/* <td>TODO</td> */}
                                            <td>
                                                <Link to = {"/viewMyStudyForm/viewComments/"+ formDetails.id}>View</Link>
                                            </td>

                                
                                             {/* <td><button className="btn btn-success" onClick={() => this.viewComments(formDetails.id)}>View</button></td> */}
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


<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>


export default ViewMyStudyApp;