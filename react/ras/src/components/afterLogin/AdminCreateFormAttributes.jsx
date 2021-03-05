// import { render } from "react-dom";
import React from 'react';
// import Login from '../login/login.jsx';
// import ReactDOM from 'react-dom';
import axios from 'axios';
// import HorizontaHeaderBar from './horizontalHeaderBar';
// import VerticalBar from './verticalBar';
import "./afterLoginCommon.scss";
import "./createStudy.scss";
import AuthService from "../../services/auth.service";



import TaskList from "./taskList"
import { NotificationContainer, NotificationManager } from 'react-notifications';

 const API_URL= "http://localhost:8080/ras/labelDetails"

export default class AdminCreateFormAttributes extends React.Component {
    state = {
        taskList: [{ index: Math.random(), labelName: "", seqNum: "", ipType: ""}]
    }
  
    handleChange = (e) => {
        if (["labelName", "seqNum", "ipType"].includes(e.target.name)) {
            let taskList = [...this.state.taskList]
            taskList[e.target.dataset.id][e.target.name] = e.target.value;
        } else {
            this.setState({ [e.target.name]: e.target.value })
        }
    }
    addNewRow = () => {
        this.setState((prevState) => ({
            taskList: [...prevState.taskList, { index: Math.random(), labelName: "", seqNum: "", ipType: ""}]
        }));
    }


    deteteRow = (index) => {
        this.setState({
            taskList: this.state.taskList.filter((s, sindex) => index !== sindex),
        });

    }
    handleSubmit = (e) => {
        e.preventDefault();
        console.log(this.state.taskList);
        for(var i=0;i<this.state.taskList.length;i++)
        {
                if(this.state.taskList[i].labelName==='' || this.state.taskList[i].seqNum==='' ||
                this.state.taskList[i].ipType==='' )
                {
                    NotificationManager.warning("Please Fill up Required Field.Please Check Label name , Sequence Field and Input Type Field");
                    return false;
                }
        }

        let data = { formData: this.state.taskList }
        // console.log("data =", data);
        // console.log("localStorage accessToken =", data.getItem('accessToken'));
        // axios.defaults.headers.common["Authorization"] = localStorage.getItem('token');
        axios.post(API_URL, this.state.taskList).then(res => {
            console.log(res);
            if(res.data ==="success") NotificationManager.success("Successfully Created Labels");
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
    render() {
        let { taskList } = this.state 
        return (
            <div className="content">
                <NotificationContainer/>
                <form onSubmit={this.handleSubmit} onChange={this.handleChange} >
                    <div className="row" style={{ marginTop: 20 }}>
                        <div className="col-sm-1"></div>
                        <div className="col-sm-10">
                            <div className="card">
                                <div className="card-header text-center">Add Your Labels Details</div>
                                <div className="card-body">
                                    
                                    <table className="table">
                                        <thead>
                                            <tr>
                                                <th className="required" >Label Name</th>
                                                <th className="required" >Sequence</th>
                                                <th>Input Type</th>
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
                                <div className="card-footer text-center"> <button type="submit" className="btn btn-primary text-center">Submit</button></div>
                            </div>
                        </div>
                        <div className="col-sm-1"></div>
                    </div>
                </form>
            </div>
        )
    }
}