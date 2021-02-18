import "./style.scss";
import { Route } from 'react-router-dom'; 

export{ Login } from "./login";
export { Register } from "./register";
export { default as Dashboard } from "../afterLogin/dashboard";
export { default as CreateStudy } from "../afterLogin/createStudy";
