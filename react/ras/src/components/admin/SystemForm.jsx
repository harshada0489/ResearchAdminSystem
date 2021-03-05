import React, { Component } from "react";
import axios from 'axios';

const API_URL = "http://localhost:8080/ras/systemForm"
class SystemForm extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            systemFormList: [],
            message: null
        }
    }

    componentDidMount(){
    //   event.preventDefault();
    axios.get(API_URL).then(response => {
        console.log("response= ",response.data);
        this.setState({
            systemFormList : response.data
        });
        console.log("systemFormList=",this.state.systemFormList);
    });
    }

    render() {
        console.log('render')
        return (
            <div className="container">
                <h3>All System Form </h3>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Form Name</th>
                                <th>Form Description</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.systemFormList.map(
                                    formDetails =>
                                        <tr key={formDetails.id}>
                                            <td>{formDetails.id}</td>
                                            <td>{formDetails.formName}</td>
                                            <td>{formDetails.formDescription}</td>
                                            {/* <th>Update</th> */}
                                            {/* <td><button className="btn btn-success" onClick={() => this.updateCourseClicked(course.id)}>Update</button></td> */}
                                            {/* <td><button className="btn btn-warning" onClick={() => this.deleteCourseClicked(course.id)}>Delete</button></td> */}
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                        {/* <div className="row">
                            <button className="btn btn-success" onClick={this.addCourseClicked}>Add</button>
                        </div> */}
                </div>
            </div>
        )
    }
}

export default SystemForm;