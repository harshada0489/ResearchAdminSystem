import React, { Component } from "react";
import axios from 'axios';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import { confirmAlert } from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css';
import { Link } from "react-router-dom";

import AuthService from "../../services/auth.service";

const API_URL = "http://localhost:8080/ras/systemForm"

class SystemForm extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            systemFormList: [],
            message: null,


            currentUser: AuthService.getCurrentUser()
        }
        this.refreshCourses = this.refreshCourses.bind(this);
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

    
    

    deleteFormClicked(id) {

        confirmAlert({
            customUI: ({ onClose }) => {
              return (
                <div className='custom-ui'>
                  <h1>Are you sure?</h1>
                  <p>You want to delete this file?</p>
                  <button onClick={onClose}>No</button>
                  <button
                    onClick={() => {
                      this.handleClickDelete(id);
                      onClose();
                    }}
                  >
                    Yes, Delete it!
                  </button>
                </div>
              );
            }
          });

         
    }

    handleClickDelete(id){
        axios.delete(`${API_URL}/delete/${id}`)
        .then(
            response => {
                this.setState({ message: `Delete of course ${id} Successful` })
                this.refreshCourses()
            }
        )
    }


    editFormClicked(id) {
        console.log('edit ' + id)
      this.props.history.push("/form/view/" + id + "/edit/" + true); 
    }

    createFormClicked(event) {
        console.log('create form')

        console.log(this.state);
        this.props.history.push("/createForm"); 
    }


    componentDidMount(){
        this.refreshCourses();
    }

    render() {
        console.log('render');
        console.log("this.state.currentUser id= " ,this.state.currentUser.id);


        return (
            <div className="container">
                <h3>All System Forms</h3>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table task">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Form Name</th>
                                <th>Form Description</th>
                                <th>Actions </th>
                                {/* <th>Actions 2 </th> */}
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.systemFormList.map(
                                    formDetails =>
                                        <tr key={formDetails.id}>
                                            <td><Link to = {"/form/view/" + formDetails.id +  "/edit/" + false}>{formDetails.id}</Link>
                                            </td>
                                            
                                            <td>{formDetails.formName}</td>
                                            <td>{formDetails.formDescription}</td>
                                            
                                            {/* <td><button className="btn btn-success" onClick={() => this.editFormClicked(formDetails.id)}>Edit</button></td> */}
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