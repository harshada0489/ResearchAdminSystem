import React from 'react';
import ReactDOM from 'react-dom';
import './index.scss';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter } from "react-router-dom";

import Background from './images/lab.jpeg';

var sectionStyle = {
    width: "100%",
    height: "400%",
    backgroundImage: `url(${Background})`
  };

ReactDOM.render( 
    <BrowserRouter>
    <div style={ sectionStyle }>
        <App />
    </div>
    </BrowserRouter>
, document.getElementById('root'));

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

