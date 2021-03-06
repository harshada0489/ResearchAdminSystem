
import React, { Component } from "react";
import axios from 'axios';

const API_URL = "http://localhost:8080/ras/systemForm"

class FormDetailsEdit extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            id : this.props.match.params.id
        }
}


componentDidMount(){
    
    axios.get(API_URL + "/edit/" + this.state.id)
    .then(
        response => {
            console.log(response.data);
            if(response.data ==="Successful"){
                console.log("FormDetails Response =", response.data);
            } 
        }
    )
}

render(){
    return(
    <div>
        System Form Details with Id = {this.props.match.params.id}
    </div>        
    );
}
}

export default FormDetailsEdit;