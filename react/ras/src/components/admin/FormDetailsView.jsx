
import React, { Component } from "react";
import axios from 'axios';

import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import AuthService from "../../services/auth.service";

const API_URL = "http://localhost:8080/ras/systemForm"


const required = value => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
  };
  

const formName = value => {
    if (value.length < 3 ) {
      return (
        <div className="alert alert-danger" role="alert">
          The Form Name must more than 3 characters.
        </div>
      );
    }
  };

  const formDescription = value => {
    if (value.length < 3 ) {
      return (
        <div className="alert alert-danger" role="alert">
          The Form Description must more than 3 characters.
        </div>
      );
    }
  };


class FormDetailsView extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            id : this.props.match.params.id,

            systemForm : [],
            formName : '',
            formDescription : '',
            filter1: '',
            filter2:'',
            disabled: '',
            edit : this.props.match.params.edit
        }
}


componentDidMount(){
    // console.log("this.props.match.params.edit =================", this.props.match.params.edit);

    if(this.props.match.params.edit === 'true'){
        console.log("this.props.match.params.edit true =================", this.props.match.params.edit);

        axios.get(API_URL + "/edit/" + this.state.id)
        .then(
            response => {
                console.log(response.data);
                // console.log("response.data.systemForm.filter1================", response.data.systemForm.filterList);
                
                
                this.setState({
                    systemForm : response.data.systemForm,
    
                    formName : response.data.systemForm.formName,
                    formDescription : response.data.systemForm.formDescription,
                    disabled : response.data.disabled,
    
                });
    
            let filterList = response.data.systemForm.filterList;
    
                if(filterList.includes('human')){
                    this.setState({
                        filter1: 'human'
                    });
                        if(filterList.includes('cancer')){
                            this.setState({
                                filter2: 'cancer'
                            });
                        }
                        if(filterList.includes('diabeties')){
                            this.setState({
                                filter2: 'diabeties'
                            }); 
                        }   
                }
                if(filterList.includes('animal')){
                    this.setState({
                        filter1: 'animal'
                    });  
                        if(filterList.includes('embryogenesis')){
                            this.setState({
                                filter2: 'embryogenesis'
                            });
                        } 
                        if(filterList.includes('developmentalBiology')){
                            this.setState({
                                filter2: 'developmentalBiology'
                            }); 
                        }   
                }
    
    
    
            }
        )







    }if(this.props.match.params.edit === 'false'){

        console.log("this.props.match.params.edit false      =================", this.props.match.params.edit);
        axios.get(API_URL + "/view/" + this.state.id)
    .then(
        response => {
            console.log(response.data);
            // console.log("response.data.systemForm.filter1================", response.data.systemForm.filterList);
            
            
            this.setState({
                systemForm : response.data.systemForm,

                formName : response.data.systemForm.formName,
                formDescription : response.data.systemForm.formDescription,
                disabled : response.data.disabled,

            });

        let filterList = response.data.systemForm.filterList;

            if(filterList.includes('human')){
                this.setState({
                    filter1: 'human'
                });
                    if(filterList.includes('cancer')){
                        this.setState({
                            filter2: 'cancer'
                        });
                    }
                    if(filterList.includes('diabeties')){
                        this.setState({
                            filter2: 'diabeties'
                        }); 
                    }   
            }
            if(filterList.includes('animal')){
                this.setState({
                    filter1: 'animal'
                });  
                    if(filterList.includes('embryogenesis')){
                        this.setState({
                            filter2: 'embryogenesis'
                        });
                    } 
                    if(filterList.includes('developmentalBiology')){
                        this.setState({
                            filter2: 'developmentalBiology'
                        }); 
                    }   
            }



        }
    )

    
    }
    
}


    render(){

        console.log("this.state = ", this.state);
        console.log("filter1=",this.state.filter1, " filter2 = ", this.state.filter2);
        

        let showViewOrEdit = this.state.edit;

        console.log("showViewOrEdit =" , showViewOrEdit);


        const viewOrEdit = () => {
            if(this.state.edit === 'false'){
                return(<div className="form-group">
                <button className="btn btn-primary btn-block">View System Form</button>
              </div>)
                
            }
            if(showViewOrEdit === 'true'){
                return(<div className="form-group">
                <button className="btn btn-primary btn-block">Edit System Form</button>
              </div>)
            }
        }


        const showFilter2 = () => {
          if (this.state.filter1 === "human"){
  
              return (
                  <div>
                      <div className="row">
                          <div className="col-25">
                              <label htmlFor="filter2">Filter 2 : </label>
                          {/* </div>
                          <div className="col-75"> */}
                          <input type="radio" name="filter2" value="cancer" checked={this.state.filter2 === "cancer"}
                              onChange={this.onChangeFilter2}
                              disabled = {(this.state.disabled)? "disabled" : ""}/> Cancer
                          <input type="radio" name="filter2" value="diabeties" checked={this.state.filter2 === "diabeties"}
                              onChange={this.onChangeFilter2} 
                              disabled = {(this.state.disabled)? "disabled" : ""}/> Diabeties
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
                          {/* </div>
                          <div className="col-75"> */}
                          <input type="radio" name="filter2" value="embryogenesis" checked={this.state.filter2 === "embryogenesis"}
                              onChange={this.onChangeFilter2} 
                              disabled = {(this.state.disabled)? "disabled" : ""}/> Embryogenesis
                          <input type="radio" name="filter2" value="developmentalBiology" checked={this.state.filter2 === "developmentalBiology"}
                              onChange={this.onChangeFilter2} 
                              disabled = {(this.state.disabled)? "disabled" : ""}/> Developmental Biology
                          </div>
                      </div>
                  </div>
              )
          }
      }
  
          return(
          <div className="col-md-12">
              <Form
              onSubmit={this.handleRegister}
              ref={c => {
                this.form = c;
              }}
            >
              {!this.state.successful && (
  
              <div>
                  <header> <h4> System Form Page </h4> </header>
                  <div className="form-group">
                    <label htmlFor="formName">Form Name</label>
                    <Input
                      type="text"
                      className="form-control"
                      name="formName"
                      value={this.state.formName}
                      onChange={this.onChangeFormName}
                      validations={[required, formName]}
                      disabled = {(this.state.disabled)? "disabled" : ""}
                    />
                  </div>
  
                  <div className="form-group">
                    <label htmlFor="formDescription">Form Description</label>
                    <Input
                      type="textArea"
                      className="form-control"
                      name="formDescription"
                      value={this.state.formDescription}
                      onChange={this.onChangeFormDescription}
                      validations={[required, formDescription]}
                      disabled = {(this.state.disabled)? "disabled" : ""}
                    />
                  </div>
  
                  <div className="form-group">
                  <div className="row">
                          <div className="col-25">
                          <label htmlFor="filter1">Filter 1 : </label>
                          {/* </div>
                          <div className="col-75"> */}
                          <input type="radio" name="filter1" value="human" checked={this.state.filter1 === "human"}
                              onChange={this.onChangeFilter1}
                              disabled = {(this.state.disabled)? "disabled" : ""}/> Human
                          <input type="radio" name="filter1" value="animal" checked={this.state.filter1 === "animal"}
                              onChange={this.onChangeFilter1}
                              disabled = {(this.state.disabled)? "disabled" : ""}/> Animal
                          </div>
                      </div>
  
                      </div>
  
                      <div className="form-group">
                      {showFilter2()}
                      </div>
                      
                  {/* <div className="form-group">
                    <button className="btn btn-primary btn-block">View System Form</button>
                  </div> */}

                  {viewOrEdit()}

              </div>
              )}
  
              {this.state.message && (
                  <div className="form-group">
                  <div
                      className={
                      this.state.successful
                          ? "alert alert-success"
                          : "alert alert-danger"
                      }
                      role="alert"
                  >
                      {this.state.message}
                  </div>
                  </div>
              )}
              <CheckButton
                  style={{ display: "none" }}
                  ref={c => {
                  this.checkBtn = c;
                  }}
              />
              </Form>
              </div>
          );


}
}

export default FormDetailsView;