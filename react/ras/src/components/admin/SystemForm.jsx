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
        this.refreshCourses = this.refreshCourses.bind(this)
        this.editFormClicked = this.editFormClicked.bind(this);
        this.deleteFormClicked = this.deleteFormClicked.bind(this);
        this.createFormClicked = this.createFormClicked.bind(this);
    }

    refreshCourses(){
        axios.get(API_URL).then(response => {
            console.log("response= ",response.data);
            this.setState({
                systemFormList : response.data
            });
            
            console.log("systemFormList=",this.state.systemFormList);
        });
    }

    deleteFormClicked(event, id) {
         axios.delete(`${API_URL}/${id}`)
            .then(
                response => {
                    this.setState({ message: `Delete of course ${id} Successful` })
                    this.refreshCourses()
                }
            )
    
    }

    editFormClicked(id) {
        console.log('edit ' + id)

        axios.get(`${API_URL}/edit/${id}`)
            .then(
                response => {
                    console.log(response.data);
                    // this.props.history.push(`/systemForm/edit/${id}`)
                }
            )  
    }

    createFormClicked(event) {
        console.log('create form')

        console.log(this.state);
        this.props.history.push("/createForm"); 
    }


    componentDidMount(){
        this.refreshCourses();
    //   event.preventDefault();
    // axios.get(API_URL).then(response => {
    //     console.log("response= ",response.data);
    //     this.setState({
    //         systemFormList : response.data
    //     });
    //     console.log("systemFormList=",this.state.systemFormList);
    // });
    }

    render() {
        console.log('render')
        return (
            <div className="container">
                <h3>All System Forms</h3>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Form Name</th>
                                <th>Form Description</th>
                                <th>Actions</th>
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
                                            
                                            <td><button className="btn btn-success" onClick={() => this.editFormClicked(formDetails.id)}>Edit</button></td>
                                            <td><button className="btn btn-warning" onClick={() => this.deleteFormClicked(formDetails.id)}>Delete</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                        <div className="row">
                            <button className="btn btn-success" onClick={this.createFormClicked}>Add</button>
                        </div>
                </div>
            </div>
        )
    }
}

export default SystemForm;