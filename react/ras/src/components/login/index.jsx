import "./style.scss";
import { Route } from 'react-router-dom'; 
// import ReactSession from 'react-client-session';

// ReactSession.setStoreType("localStorage");
export{ Login } from "./login";
export { Register } from "./register";
export { default as Dashboard } from "../afterLogin/dashboard";
export { default as CreateStudy } from "../afterLogin/createStudy";
export { default as HorizontaHeaderBar } from "../afterLogin/horizontalHeaderBar";
export { default as VerticalBar } from "../afterLogin/verticalBar";
