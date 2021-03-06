import { render } from "react-dom";
import React from 'react';
import Login from '../login/login.jsx';
import ReactDOM from 'react-dom';
import axios from 'axios';
import HorizontaHeaderBar from './horizontalHeaderBar';
import VerticalBar from './verticalBar';
import "./afterLoginCommon.scss";
import "./createStudy.scss";
import AuthService from "../../services/auth.service";
import UserLabelDetails from "./UserLabelDetails";

const API_URL = 'http://localhost:8080/ras/studyForm'
const API_NEXT_PAGE_URL = 'http://localhost:8080/ras/nextPage'

class CreateStudy extends React.Component {
    constructor(props) {
        super(props);
        this.state ={
            studyTitle : '',
            oneWordIdentifier : '',
            selectedStudyOption : '',
            selectedStudyField : '',
            selectedGender : '',
            age : '',
            NoOfHumanRequired : '',
            typeOfAnimal : '',
            NoOfAnimalRequired : '',
            researcherId: '',
            typeOfStudy : '',
            currentUser: AuthService.getCurrentUser()
          };

        this.studyFormHandler = this.studyFormHandler.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.onChangeValue = this.onChangeValue.bind(this);
        this.onChangeGenderValue = this.onChangeGenderValue.bind(this);
        this.onChangeStudyFieldValue = this.onChangeStudyFieldValue.bind(this);
        
        
      }

      studyFormHandler(event){

        event.preventDefault();
        // let loginRequest ={studySubject: this.state.studySubject};
        console.log(this.state.currentUser.id);
        this.state.researcherId = this.state.currentUser.id;
        if(this.state.selectedStudyOption==="Human"){
            console.log("Selected Study option1 = Human ");
            this.state.typeOfAnimal = "";
            this.state.NoOfAnimalRequired = "";
        
              console.log(this.state);
        }
        else if (this.state.selectedStudyOption==="Animal"){
            console.log("Selected Study option1 = Animal ");
            this.state.NoOfHumanRequired = "";
            this.state.age = "";
            this.state.selectedGender = "";

              console.log(this.state);
        }
        else{
            console.log('selected study is not Human nor Animal.');
        }



        axios.post(API_URL,this.state).then(response =>{

        // const {labelRes } = this.state.labelRes;
          
            this.props.history.push({
                pathname: "/nextPage",
                state: { detail: response.data }
            })
            
          
        })
        .catch(error =>{
          console.log(error)
        })

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

    onChangeGenderValue(event){
        this.setState({
            selectedGender: event.target.value
        });

        // console.log(event.taget.value);
    }

    onChangeStudyFieldValue(event){
        this.setState({
            selectedStudyField : event.target.value
        });
    }
    render(){
        
        let { currentUser } = this.state;

        let {radioOption} = this.state.selectedStudyOption;
        console.log(radioOption);

        

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

                        <div className="row">
                            <div className="col-25">
                                <label htmlFor="gender">Gender : </label>
                            </div>
                            <div className="col-75">
                            <input type="radio" name="gender" value="Male" checked={this.state.selectedGender === "Male"}
                                onChange={this.onChangeGenderValue}/> Male
                            <input type="radio" name="gender" value="Female" checked={this.state.selectedGender === "Female"}
                                onChange={this.onChangeGenderValue} /> Female
                            </div>
                        </div>


                        <div className="row">
                            <div className="col-25">
                                <label htmlFor="age">Age</label>
                            </div>
                            <div className="col-75">
                                <input type="text" name="age" value={this.state.age}  onChange={this.handleChange} placeholder="Age"/>
                            </div>
                        </div>

                        <div className="row">
                            <div className="col-25">
                            <label htmlFor="NoOfHumanRequired">No of Humans Required</label>   
                        </div> 
                        <div className="col-75">
                            <input type="text" name="NoOfHumanRequired" value={this.state.NoOfHumanRequired}  onChange={this.handleChange} placeholder="No of Human Required"/>
                            </div>
                        </div>
    
                    </div>
                )
                    
            }else if (this.state.selectedStudyOption ==="Animal"){

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

                        <div className="row">
                            <div className="col-25">
                                <label htmlFor="typeOfAnimal">Which type of Animal</label>
                            </div> 
                            <div className="col-75">
                                <input type="text" name="typeOfAnimal" value={this.state.typeOfAnimal}  onChange={this.handleChange} placeholder="Type Of Animal"/>
                            </div>   
                        </div>

                        <div className="row">
                            <div className="col-25">
                                <label htmlFor="NoOfAnimalRequired">No Of Animals Required</label>
                            </div> 
                            <div className="col-75">
                            <input type="text" name="NoOfAnimalRequired" value={this.state.NoOfAnimalRequired}  onChange={this.handleChange} placeholder="No of Animals Required"/>
                            </div>   
                        </div>
                    </div>
                )
            }
        }

        return(
            

             <div>
                {/* <div > <HorizontaHeaderBar /></div> */}
                {/* <div > <VerticalBar /></div>  */}
               
                


                <div className="container">

                  
                 <div className="form">
                    <div className="row">
                        <div className="col-25">
                            <label htmlFor="studyTitle">Study Tile</label>
                        </div>
                        <div className="col-75">
                            <input type="text" name="studyTitle" value={this.state.studyTitle}  onChange={this.handleChange} placeholder="Study Title"/>
                        </div>
                    </div>
                    

                    <div className="row">
                        <div className="col-25">
                            <label htmlFor="oneWordIdentifier">One Word Identifier</label>
                        </div>
                        <div className="col-75">
                            <input type="text" name="oneWordIdentifier" value={this.state.oneWordIdentifier}  onChange={this.handleChange} placeholder="One Word Identifier"/>
                        </div>
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
                    
                    <div className="form-submit">
                        <button type="button" onClick={this.studyFormHandler} className="btn"> Next / Submit</button>
                    </div>
                </div>
                </div>
            </div> 
                
        )
    }

    }

export default CreateStudy;