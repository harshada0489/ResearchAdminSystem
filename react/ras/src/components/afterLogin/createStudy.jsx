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
import StudySideQuesionPage from "./StudySideQuesionPage";

const API_URL = 'http://localhost:8080/ras/studyForm'
// const API_NEXT_PAGE_URL = 'http://localhost:8080/ras/nextPage'

class CreateStudy extends React.Component {
    constructor(props) {
        super(props);
        this.state ={
            studyTitle : '',
            oneWordIdentifier : '',
            selectedGender : '',
            age : '',
            NoOfHumanRequired : '',
            typeOfAnimal : '',
            NoOfAnimalRequired : '',
            researcherId: '',
            typeOfStudy : '',

            filter1: '',
            filter2:'',

            currentUser: AuthService.getCurrentUser()
          };

        this.studyFormHandler = this.studyFormHandler.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.onChangeGenderValue = this.onChangeGenderValue.bind(this);

        this.onChangeFilter1 = this.onChangeFilter1.bind(this);
        this.onChangeFilter2 = this.onChangeFilter2.bind(this);
        
      }


      onChangeFilter2(e){

        this.setState({
          filter2: e.target.value
      });
      }
  
      onChangeFilter1(e){
        this.setState({
          filter1: e.target.value,
          filter2: ''
      });
      }


      studyFormHandler(event){

        event.preventDefault();
        console.log(this.state.currentUser.id);
        this.state.researcherId = this.state.currentUser.id;
        if(this.state.filter1==="human"){
            console.log("Selected filter1 = human ");
            this.state.typeOfAnimal = "";
            this.state.NoOfAnimalRequired = "";
        
              console.log(this.state);
        }
        else if (this.state.filter2==="animal"){
            console.log("Selected Study option1 = animal ");
            this.state.NoOfHumanRequired = "";
            this.state.age = "";
            this.state.selectedGender = "";

              console.log(this.state);
        }
        else{
            console.log('selected study is not human nor animal.');
        }



        axios.post(API_URL,this.state).then(response =>{
            console.log("response.data =", response.data);
          
            this.props.history.push({
                pathname: "/study/" + response.data.studyId + "/" + response.data.filterFormId+ "/" + response.data.systemFormId + "/" + response.data.page,
                // pathname: "/nextPage",
                state: { detail: response.data }
            })
            window.location.reload();
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

    onChangeGenderValue(event){
        this.setState({
            selectedGender: event.target.value
        });
    }

    render(){
        
        let { currentUser } = this.state;

        console.log("filter1=",this.state.filter1, " filter2 = ", this.state.filter2);

        const showFilter2 = () => {
          if (this.state.filter1 === "human"){
  
              return (
                  <div>
                      <div className="row">
                          <div className="col-25">
                              <label htmlFor="filter2">Filter 2 : </label>
                          </div>
                          <div className="col-75">
                          <input type="radio" name="filter2" value="cancer" checked={this.state.filter2 === "cancer"}
                              onChange={this.onChangeFilter2}/> Cancer
                          <input type="radio" name="filter2" value="diabeties" checked={this.state.filter2 === "diabeties"}
                              onChange={this.onChangeFilter2} /> Diabeties
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
                  
          }else if (this.state.filter1 ==="animal"){
  
              return (
                  <div>
                      <div className="row">
                          <div className="col-25">
                              <label htmlFor="filter2">Filter 2 : </label>
                          </div>
                          <div className="col-75">
                          <input type="radio" name="filter2" value="embryogenesis" checked={this.state.filter2 === "embryogenesis"}
                              onChange={this.onChangeFilter2}/> Embryogenesis
                          <input type="radio" name="filter2" value="developmentalBiology" checked={this.state.filter2 === "developmentalBiology"}
                              onChange={this.onChangeFilter2} /> Developmental Biology
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

                <div className="container">

                 <div className="form">
                    <div className="row">
                        <div className="col-25">
                            <label htmlFor="studyTitle">Study Title</label>
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
                        <label htmlFor="filter1">Filter 1 : </label>
                        </div>
                        <div className="col-75">
                        <input type="radio" name="filter1" value="human" checked={this.state.filter1 === "human"}
                            onChange={this.onChangeFilter1} /> Human
                        <input type="radio" name="filter1" value="animal" checked={this.state.filter1 === "animal"}
                            onChange={this.onChangeFilter1}/> Animal
                        </div>
                    </div>

                    {showFilter2()}
                    
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