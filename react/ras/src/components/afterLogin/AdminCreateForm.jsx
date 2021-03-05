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


const API_URL = 'http://localhost:8080/ras/AdminCreateForm'

class AdminCreateForm extends React.Component {
    constructor(props) {
        super(props);
        this.state ={
            selectedStudyOption : '',
            selectedStudyField : '',
            pagesRequired : '',
            labelsToShowPerPage : '',

            currentUser: AuthService.getCurrentUser()
          };

        this.adminCreateStudyFormHandler = this.adminCreateStudyFormHandler.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.onChangeValue = this.onChangeValue.bind(this);
        this.onChangeStudyFieldValue = this.onChangeStudyFieldValue.bind(this);
        
        
      }

      adminCreateStudyFormHandler(event){
        console.log(this.state);
        this.props.history.push("/createAttribute");
        // axios.post(API_URL,this.state).then(response =>{
        //   console.log(response)
        // })
        // .catch(error =>{
        //   console.log(error)
        // })

            }   
    
    
    handleChange(event){
          
        this.setState({
           [event.target.name]: event.target.value
         });
       }

    onChangeValue(event){
        this.setState({
            selectedStudyOption: event.target.value
        });
    }


    onChangeStudyFieldValue(event){
        this.setState({
            selectedStudyField : event.target.value
        });
    }

    render(){
        let { currentUser } = this.state;


        let {radioOption} = this.state.selectedStudyOption;
        console.log("selectedStudyOption==",radioOption);


        const typeOfStudy = () => {
            if (this.state.selectedStudyOption ==="Human"){

                return (
                    <div>
                        <div className="row">
                            <div className="col-25">
                                <label htmlFor="gender">FieldOfStudy : </label>
                            </div>
                            <div className="col-75">
                            <input type="radio" name="cancer" value="Cancer" checked={this.state.selectedStudyField === "Cancer"}
                                onChange={this.onChangeStudyFieldValue}/> Cancer
                            <input type="radio" name="diabeties" value="Diabeties" checked={this.state.selectedStudyField === "Diabeties"}
                                onChange={this.onChangeStudyFieldValue} /> Diabeties
                            </div>
                        </div>
                        </div>
                    );

                    }

                    if (this.state.selectedStudyOption ==="Animal"){

                        return (
                            <div>
                                <div className="row">
                                    <div className="col-25">
                                        <label htmlFor="gender">FieldOfStudy : </label>
                                    </div>
                                    <div className="col-75">
                                    <input type="radio" name="embryogenesis" value="Embryogenesis" checked={this.state.selectedStudyField === "Embryogenesis"}
                                        onChange={this.onChangeStudyFieldValue}/> Embryogenesis
                                    <input type="radio" name="developmentalBiology" value="developmentalBiology" checked={this.state.selectedStudyField === "DevelopmentalBiology"}
                                        onChange={this.onChangeStudyFieldValue} /> Developmental Biology
                                    </div>
                                </div>
                            </div>
                            );
        
                            }

                    }
        return(
            <div className="container">
                <header className="jumbotron">
                    <h3>
                        Welcome <strong> {currentUser.username}</strong> 
                    </h3>
                </header>
                <div>
                    <h4>
                    Welcome to create form
                    </h4>
                </div>
            
                <div className="row">
                         <div className="col-25">
                            <label htmlFor="typeOfStudy">Type Of Study : </label>
                         </div>
                         <div className="col-75">
                            <input type="radio" name="typeOfStudy" value="Human" checked={this.state.selectedStudyOption === "Human"}
                                onChange={this.onChangeValue}/> Human
                            <input type="radio" name="typeOfStudy" value="Animal" checked={this.state.selectedStudyOption === "Animal"}
                                onChange={this.onChangeValue} /> Animal
                        </div>
                </div>

                    {typeOfStudy()}

                <div className="row">
                        <div className="col-25">
                            <label htmlFor="pagesRequired">No of Pages Required :</label>
                        </div>
                        <div className="col-75">
                            <input type="text" name="pagesRequired" value={this.state.pagesRequired}  onChange={this.handleChange} placeholder="Enter Number of Pages Required"/>
                        </div>
                </div>

                <div className="row">
                        <div className="col-25">
                            <label htmlFor="labelsToShowPerPage">No of labels to show on each page :</label>
                        </div>
                        <div className="col-75">
                            <input type="text" name="labelsToShowPerPage" value={this.state.labelsToShowPerPage}  onChange={this.handleChange} placeholder="Enter Number of labels to be shown on each page"/>
                        </div>
                </div>

                

            <div className="form-submit">
                <button type="button" onClick={this.adminCreateStudyFormHandler} className="btn">Start Creating Attributes</button>
            </div>

            </div>

        );
        
    }

}

    


export default AdminCreateForm;