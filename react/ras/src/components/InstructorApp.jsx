// import React, { Component } from 'react';
// // import { Route } from 'react-router-dom'; 
// // import { BrowserRouter, Router, Switch, Redirect, Link } from "react-router-dom";
// import { HashRouter as Router, Route } from 'react-router-dom';
// import ListCoursesComponent from './ListCoursesComponent.jsx';
// import CourseComponent from './CourseComponent.jsx';
// import LoginComponent from './LoginComponent.jsx';

// import LogoutComponent from './LogoutComponent';
// import MenuComponent from './MenuComponent';
// import AuthenticationService from '../services/AuthenticationService';
// import AuthenticatedRoute from './AuthenticatedRoute';

// class InstructorApp extends Component {
//     render() {
//         return (
//             <div>
//                 <Router>
//                     <div>
//                         <MenuComponent />
//                             <Route path="/" exact component={LoginComponent} />
//                             <Route path="/login" exact component={LoginComponent} />
//                             <AuthenticatedRoute path="/logout" exact component={LogoutComponent} />
//                             <AuthenticatedRoute path="/courses" exact component={ListCoursesComponent} />
//                     </div>
//                 </Router>
//             </div>
//         )
//     }
// }

// export default InstructorApp