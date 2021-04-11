import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.scss";

import AuthService from "./services/auth.service";

import Login from "./components/login.component";
import Register from "./components/register.component";
import Home from "./components/home.component";
import Profile from "./components/profile.component";
import BoardUser from "./components/board-user.component";
import BoardModerator from "./components/board-moderator.component";
import BoardAdmin from "./components/board-admin.component";

import  CreateStudy  from './components/afterLogin/createStudy';
import  AdminCreateForm  from './components/afterLogin/AdminCreateForm';
import  QuestionPage  from './components/admin/QuestionPage';
import StudySideQuesionPage from "./components/afterLogin/StudySideQuesionPage";
import SystemForm from "./components/admin/SystemForm";
import CreateForm from "./components/admin/CreateForm";
import FormDetailsEdit from "./components/admin/FormDetailsEdit";
import FormDetailsView from "./components/admin/FormDetailsView";


import StudyAppView from "./components/afterLogin/StudyAppView";


import  ContactDetails  from './components/afterLogin/ContactDetails';
import ViewMyStudyApp from './components/afterLogin/ViewMyStudyApp';

import ViewMyTasksRbStudyApp from './components/afterLogin/ViewMyTasksRbStudyApp';


import {SideBar} from './components/SideBar/SideBar';

class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      showModeratorBoard: false,
      showAdminBoard: false,
      currentUser: undefined,
    };
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();
    console.log("user =" ,user);
    if (user) {
      this.setState({
        currentUser: user,
        showModeratorBoard: user.roles.includes("ROLE_MODERATOR"),
        showAdminBoard: user.roles.includes("ROLE_ADMIN"),
      });
    }
  }

  logOut() {
    AuthService.logout();
  }

  render() {
    const { currentUser, showModeratorBoard, showAdminBoard } = this.state;
    console.log("currentUser = " , currentUser);
    console.log("showModeratorBoard = " , showModeratorBoard);
    console.log("showAdminBoard = ", showAdminBoard);

    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/profile"} className="navbar-brand">
            ReseachAdminSystem
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/home"} className="nav-link">
                Home
              </Link>
            </li>

            {showModeratorBoard && (
              <li className="nav-item">
                <Link to={"/mod"} className="nav-link">
                  Moderator Board
                </Link>
              </li>
            )}

            {showAdminBoard && (
              <li className="nav-item">
                <Link to={"/admin"} className="nav-link">
                  Admin Board
                </Link>
              </li>
            )}

            {currentUser && (
              <li className="nav-item">
                
                <Link to={"/user"} className="nav-link">
                  User
                </Link>
              </li>
            )}
          </div>

          {currentUser ? (
            <div className="navbar-nav ml-auto">
              
              <li className="nav-item">
                <Link to={"/profile"} className="nav-link">
                  {currentUser.username}
                </Link>
              </li>
              <li className="nav-item">
                <a href="/login" className="nav-link" onClick={this.logOut}>
                  LogOut
                </a>
              </li>
            </div>
          ) : (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/login"} className="nav-link">
                  Login
                </Link>
              </li>

              <li className="nav-item">
                <Link to={"/register"} className="nav-link">
                  Sign Up
                </Link>
              </li>
            </div>
          )}
        </nav>



        <div className="container mt-3">
          
          <Switch>
            <Route exact path={["/", "/home"]} component={Home} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/profile" component={Profile} />
            {/* <Route path="/user" component={BoardUser} /> */}
            {/* <Route path="/mod" component={BoardModerator} /> */}
            {/* <Route path="/admin" component={BoardAdmin} /> */}
            <Route path="/createStudy" component={CreateStudy} />
            <Route path="/adminCreateStudyForm" component={AdminCreateForm} />
            <Route path="/:sysId/createQuestion/Page/:pageNum" component={QuestionPage} />
            {/* <Route path="/createAttribute" component={AdminCreateFormAttributes} /> */}
            <Route path="/study/:studyId/:filterFormId/:systemFormId/:page" component={StudySideQuesionPage} />
            <Route path="/study/:studyAppId/:studyDataFormId/contactDetails" component={ContactDetails} />
            <Route path="/systemForm" component={SystemForm} />
            <Route path="/viewMyStudyApp" component={ViewMyStudyApp} />
            <Route path="/createForm" component={CreateForm} />
            <Route path="/form/edit/:id" component={FormDetailsEdit} />
            <Route path="/form/view/:id" component={FormDetailsView} />

            <Route path="/viewMyStudyForm/viewPage/:currentPage/studyApp/view/:id" component={StudyAppView} />
            <Route path="/viewMyRbTasksForm/viewPage/:currentPage/studyApp/view/:id" component={ViewMyTasksRbStudyApp} />            
            
          </Switch>
        </div>

</div>

    );
  }
}

export default App;

