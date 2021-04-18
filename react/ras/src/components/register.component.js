import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";

import AuthService from "../services/auth.service";
import loginImg from "../login.png";

const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const email = value => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const vusername = value => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The username must be between 3 and 20 characters.
      </div>
    );
  }
};

const vpassword = value => {
  if (value.length < 6 || value.length > 40) {
    return (
      <div className="alert alert-danger" role="alert">
        The password must be between 6 and 40 characters.
      </div>
    );
  }
};

const firstName = value => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The First Name must be between 3 and 20 characters.
      </div>
    );
  }
};

const lastName = value => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The Last Name must be between 3 and 20 characters.
      </div>
    );
  }
};

const roles = value => {
  if (  !(value === 'admin') && !(value === 'reviewer') && !(value === 'researcher')  ) {
    return (
      <div className="alert alert-danger" role="alert">
        The Login Type must be either 'researcher' or 'reviewer'.
      </div>
    );
  }
};

const institute = value => {
  if (value.length < 3 ) {
    return (
      <div className="alert alert-danger" role="alert">
        The Institute Name must more than 3 characters.
      </div>
    );
  }
};

const department = value => {
  if (value.length < 3 ) {
    return (
      <div className="alert alert-danger" role="alert">
        The Department Name must more than 3 characters.
      </div>
    );
  }
};


export default class Register extends Component {
  constructor(props) {
    super(props);
    this.handleRegister = this.handleRegister.bind(this);
    this.onChangeUsername = this.onChangeUsername.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);


    this.onChangeFirstName = this.onChangeFirstName.bind(this);
    this.onChangeLastName = this.onChangeLastName.bind(this);
    this.onChangeRoles = this.onChangeRoles.bind(this);
    this.onChangeInstitute = this.onChangeInstitute.bind(this);
    this.onChangeDepartment = this.onChangeDepartment.bind(this);

    this.state = {
      username: "",
      email: "",
      password: "",
  
      firstName: "",
      lastName: "",
      roles: "",
      institute: "",
      department: "",

      successful: false,
      message: ""
    };
  }

  onChangeUsername(e) {
    this.setState({
      username: e.target.value
    });
  }

  onChangeEmail(e) {
    this.setState({
      email: e.target.value
    });
  }

  onChangePassword(e) {
    this.setState({
      password: e.target.value
    });
  }

  onChangeFirstName(e) {
    this.setState({
      firstName: e.target.value
    });
  }

  onChangeLastName(e) {
    this.setState({
      lastName: e.target.value
    });
  }

  onChangeRoles(e) {
    this.setState({
      roles: e.target.value
    });
  }

  onChangeInstitute(e) {
    this.setState({
      institute: e.target.value
    });
  }


  onChangeDepartment(e) {
    this.setState({
      department: e.target.value
    });
  }

  handleRegister(e) {
    e.preventDefault();

    this.setState({
      message: "",
      successful: false
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      console.log(this.state);
      AuthService.register(
        this.state.username,
        this.state.email,
        this.state.password,
        
        this.state.firstName,
        this.state.lastName,
        this.state.roles,
        this.state.institute,
        this.state.department
      ).then(
        response => {
          this.setState({
            message: response.data.message,
            successful: true
          });
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

  componentDidMount(){  
    var element = document.getElementById("container");
    element.classList.remove("container");
}

  render() {
    return (
      <div className="col-md-12">
        <div className="card card-container">
        <div className="login-text"  ><h4 > Register </h4></div>

        <img src={loginImg} alt="profile-img"
            className="profile-img-card"/>

          <Form
            onSubmit={this.handleRegister}
            ref={c => {
              this.form = c;
            }}
          >
            {!this.state.successful && (
              <div>
                <div className="form-group">
                  <label htmlFor="username">Username</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="username"
                    value={this.state.username}
                    onChange={this.onChangeUsername}
                    validations={[required, vusername]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="email">Email</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="email"
                    value={this.state.email}
                    onChange={this.onChangeEmail}
                    validations={[required, email]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="password">Password</label>
                  <Input
                    type="password"
                    className="form-control"
                    name="password"
                    value={this.state.password}
                    onChange={this.onChangePassword}
                    validations={[required, vpassword]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="firstName">First Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="firstName"
                    value={this.state.firstName}
                    onChange={this.onChangeFirstName}
                    validations={[required, firstName]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="lastName">Last Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="lastName"
                    value={this.state.lastName}
                    onChange={this.onChangeLastName}
                    validations={[required, lastName]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="roles">Role Type</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="roles"
                    value={this.state.roles}
                    onChange={this.onChangeRoles}
                    validations={[required, roles]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="institute">Institute</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="institute"
                    value={this.state.institute}
                    onChange={this.onChangeInstitute}
                    validations={[required, institute]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="institute">Department</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="institute"
                    value={this.state.department}
                    onChange={this.onChangeDepartment}
                    validations={[required, department]}
                  />
                </div>


                <div className="form-group">
                  <button className="btn btn-primary btn-block">Sign Up</button>
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
      </div>
    );
  }
}