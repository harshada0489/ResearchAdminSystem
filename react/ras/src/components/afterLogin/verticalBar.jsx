import { render } from "react-dom";
import React from 'react';
import Login from '../login/login.jsx';
import ReactDOM from 'react-dom';
import axios from 'axios';
import  CreateStudy  from './createStudy';
import App from '../../App';
import "./afterLoginCommon.scss";
import headerLogoImg from "../../images/headerLogo.jpeg";


class VerticalBar extends React.Component {
    constructor(props) {
        super(props);
      }
      render(){
          return(
            <div className="vertical-bar">
                <ul>
                <li>Page 1</li>
              </ul>
            </div>
          )
        
      }
      
    }

    export default VerticalBar;