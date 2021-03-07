// import { render } from "react-dom";
import React from 'react';
// import Login from '../login/login.jsx';
// import ReactDOM from 'react-dom';
import axios from 'axios';
// import HorizontaHeaderBar from './horizontalHeaderBar';
// import VerticalBar from './verticalBar';
import "../afterLogin/afterLoginCommon.scss";
import "../afterLogin/createStudy.scss";
import AuthService from "../../services/auth.service";



import TaskList from "../afterLogin/taskList"
import { NotificationContainer, NotificationManager } from 'react-notifications';

 const API_URL= "http://localhost:8080/ras/questionDetails"
 const camelCase = require('camelcase');

export default class QuestionPage extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            taskList: [{ index: Math.random(), question: "", questionNumber: "", answerType: "", dbColumnName:""}],
            systemFormDetails : [],
            pageNumber : []
        }
        this.handleChange = this.handleChange.bind(this);
        this.addNewRow = this.addNewRow.bind(this);
        this.deteteRow = this.deteteRow.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.clickOnDelete = this.clickOnDelete.bind(this);
        
    }
    // state = {
    //     taskList: [{ index: Math.random(), question: "", questionNumber: "", answerType: "", dbColumnName: ""}]
    // }
  




    handleChange = (e) => {
        if (["question", "questionNumber", "answerType" , "dbColumnName"].includes(e.target.name)) {
            let taskList = [...this.state.taskList]
            taskList[e.target.dataset.id][e.target.name] = e.target.value;
        }
        else {
            this.setState({ [e.target.name]: e.target.value })
        }
    }
    addNewRow = () => {
        this.setState((prevState) => ({
            taskList: [...prevState.taskList, { index: Math.random(), question: "", questionNumber: "", answerType: "", dbColumnName:""}]
        }));
    }


    deteteRow = (index) => {
        this.setState({
            taskList: this.state.taskList.filter((s, sindex) => index !== sindex),
        });

    }

    

     

    handleSubmit = (e) => {
        e.preventDefault();
        console.log("this.state.taskList = ",this.state.taskList);
        console.log("this.state.taskList.length = ", this.state.taskList.length)
        console.log("this.state.taskList[0].question = ", this.state.taskList[0].question === '');
        console.log("this.state.taskList[0].questionNumber = ", this.state.taskList[0].questionNumber=== '');
        console.log("this.state.taskList[0].answerType = ", this.state.taskList[0].answerType=== '');
        // console.log("this.state.taskList[0] has blank value? = ", this.state.taskList[i].question === '' || this.state.taskList[i].questionNumber ==='' || this.state.taskList[i].answerType ==='' || this.state.tasklist[i].dbColumnName ==='');
        
        for(var i=0; i<this.state.taskList.length; i++)
        {
                if(this.state.taskList[i].question === '' || this.state.taskList[i].questionNumber ==='' || this.state.taskList[i].answerType ==='' || this.state.taskList[i].dbColumnName ==='')
                {
                    NotificationManager.warning("Please Fill up Required Field.Please Check Label name , Question Number Field and Input Type Field");
                    return false;
                }
               
                
                // console.log("Is white space present in database column name ? " , /\s/g.test(this.state.tasklist[i].dbColumnName));
                 
                // let str = this.state.tasklist[i].dbColumnName;

                


                // return );

                // if(this.state.tasklist[i].dbColumnName.indexOf(' ') >= 0 )
                // {
                //     NotificationManager.warning(" Please remove spaces in Database Column");
                //     return false;
                // }


        }

        let data = { formData: this.state.taskList }
        console.log("this.state.taskList =", this.state.taskList);
        console.log("this.state.systemFormDetails =", this.state.systemFormDetails);
        // axios.defaults.headers.common["Authorization"] = localStorage.getItem('token');
        axios.post(API_URL +"/" + this.state.systemFormDetails.formId +"/Page/" + this.state.systemFormDetails.pageNumber, this.state.taskList).then(res => {
            console.log(res);
            if(res.data ==="success") NotificationManager.success("Successfully Created Questions");
            else{
                NotificationManager.success("Something went Wrong. Please try to create lbels again.");
            }
        }).catch(error => {
            if(error.response.status && error.response.status===400)
            NotificationManager.error("Bad Request");
            else NotificationManager.error("Something Went Wrong");
            this.setState({ errors: error })
        });
    }
    clickOnDelete(record) {
        this.setState({
            taskList: this.state.taskList.filter(r => r !== record)
        });
    }

getData(){
    this.setState({systemFormDetails : this.props.location.state.detail})
}

componentDidMount(){
    this.getData()
}      
    
    render() {
        let { taskList } = this.state 
         console.log("this.state =",this.state );

        return (
            <div className="content">
                
                <NotificationContainer/>
                <form onSubmit={this.handleSubmit} onChange={this.handleChange} >
                    <div className="row" style={{ marginTop: 20 }}>
                    
                        <div className="col-sm-1">
                        <div className="card-header text-center">Page {this.state.systemFormDetails.pageNumber}</div>
                        </div>
                        <div className="col-sm-10">
                            <div className="card">
                                <div className="card-header text-center">Add question details to be show on 
                                    <h4>Page {this.state.systemFormDetails.pageNumber}</h4>
                                </div>
                                <div className="card-body">
                                    
                                    <table className="table">
                                        <thead>
                                            <tr>
                                                <th className="required"> Question </th>
                                                <th className="required"> Question Number </th>
                                                <th className="required"> Answer Type </th>
                                                <th className="required"> Database Column Name </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <TaskList add={this.addNewRow} delete={this.clickOnDelete.bind(this)} taskList={taskList} />
                                        </tbody>
                                        <tfoot>
                                            <tr><td colSpan="4">
                                                <button onClick={this.addNewRow} type="button" className="btn btn-primary text-center"> + <i className="fa fa-plus-circle" aria-hidden="true"></i></button>
                                            </td></tr>
                                        </tfoot>
                                    </table>
                                </div>
                                <div className="card-footer text-center"> 
                                <button type="button" onClick={this.addNewRow} className="btn btn-primary text-center"> Go Back </button>
                                <button type="button" onClick={this.addNewRow} className="btn btn-primary text-center">End Form </button>
                                <button type="submit" className="btn btn-primary text-center"> Save & go to Next Page </button>
                                </div>
                            </div>
                        </div>
                        <div className="col-sm-1"></div>
                    </div>
                </form>
            </div>
        )
    }
}