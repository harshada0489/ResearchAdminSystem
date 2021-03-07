import React, { Component } from "react";
import axios from 'axios';

import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

const API_URL = "http://localhost:8080/ras/systemForm/create"

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

class CreateForm extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            systemFormList: [],
            message: null,
            questionDetail: [],
            formName : '',
            formDescription : '',
            filter1: '',
            filter2:'',
            filterList:'undefined'
        }

        this.onChangeFormName = this.onChangeFormName.bind(this);
        this.onChangeFormDescription = this.onChangeFormDescription.bind(this);
        this.handleRegister = this.handleRegister.bind(this);
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

    onChangeFormName(e) {
        this.setState({
            formName: e.target.value
        });
      }

      onChangeFormDescription(e) {
        this.setState({
            formDescription: e.target.value
        });
      }

      handleRegister(e){
        e.preventDefault();

        var sortFilter = this.state.filter1 + "," + this.state.filter2;
        // var sortedFilter = sortFilter.split(",").sort().join(",");
       
        console.log("sortFilter = ", sortFilter.split(",").sort().join(","));

        this.setState({
          message: "",
          successful: false,
          filterList : sortFilter.split(",").sort().join(",")
        });
        

        this.form.validateAll();
        if (this.checkBtn.context._errors.length === 0) {
            console.log(this.state);
            axios.post(API_URL, this.state)
            .then(
              response => {
                this.setState({
                  // message: response.data,
                  questionDetail :response.data,
                  successful: true
                });
                console.log("this.state.questionDetail = ",this.state.questionDetail);
                console.log("this.state.questionDetail.formId = ",this.state.questionDetail.formId);
                console.log("this.state.questionDetail.pageId = ",this.state.questionDetail.pageId);
                console.log("this.state.questionDetail.pageNumber = ",this.state.questionDetail.pageNumber);
                // console.log("response.data====> id=", this.state.questionDetail[0], "page =",this.state.questionDetail[1]);
                let {formId} = this.state.questionDetail.formId;
                let {pageId} = this.state.questionDetail.pageId;
                let {pageNumber} = this.state.questionDetail.pageNumber;

                console.log("formId = " , formId, " pageId = ",  pageId, "  pageNumber=" , pageNumber );
                this.props.history.push({
                  pathname: "/"+ this.state.questionDetail.formId + "/createQuestion/Page/" + this.state.questionDetail.pageNumber ,
                  state: { detail : this.state.questionDetail}
                })
              },
              error => {
                const resMessage =
                  (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                  error.message ||
                  error.toString();
      
                this.setState({
                  successful: false,
                  message: resMessage
                });
              }
            );
          }



      }
    render(){

      console.log("filter1=",this.state.filter1, " filter2 = ", this.state.filter2);

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
                            onChange={this.onChangeFilter2}/> Cancer
                        <input type="radio" name="filter2" value="diabeties" checked={this.state.filter2 === "diabeties"}
                            onChange={this.onChangeFilter2} /> Diabeties
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
                            onChange={this.onChangeFilter2}/> Embryogenesis
                        <input type="radio" name="filter2" value="developmentalBiology" checked={this.state.filter2 === "developmentalBiology"}
                            onChange={this.onChangeFilter2} /> Developmental Biology
                        </div>
                    </div>
                </div>
            )
        }
    }

        return(
        <div className="col-md-12">
        {/* <div className="card card-container"> */}
        {/* <img src={loginImg} alt="profile-img"
            className="profile-img-card"/> */}

            <Form
            onSubmit={this.handleRegister}
            ref={c => {
              this.form = c;
            }}
          >
            {!this.state.successful && (

            <div>
                <header> Create Form Page </header>
                <div className="form-group">
                  <label htmlFor="formName">Form Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="formName"
                    value={this.state.formName}
                    onChange={this.onChangeFormName}
                    validations={[required, formName]}
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
                  />
                </div>

                <div className="form-group">
                <div className="row">
                        <div className="col-25">
                        <label htmlFor="filter1">Filter 1 : </label>
                        {/* </div>
                        <div className="col-75"> */}
                        <input type="radio" name="filter1" value="human" checked={this.state.filter1 === "human"}
                            onChange={this.onChangeFilter1} /> Human
                        <input type="radio" name="filter1" value="animal" checked={this.state.filter1 === "animal"}
                            onChange={this.onChangeFilter1}/> Animal
                        </div>
                    </div>

                    </div>

                    <div className="form-group">
                    {showFilter2()}
                    </div>
                    
                <div className="form-group">
                  <button className="btn btn-primary btn-block">Create System Form</button>
                </div>
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
    export default CreateForm