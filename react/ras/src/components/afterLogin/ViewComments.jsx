import React from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";
import { NotificationContainer, NotificationManager } from 'react-notifications';

import AuthService from "../../services/auth.service";
import StudyAppView from './StudyAppView';

const API_URL = "http://localhost:8080/ras/viewMyStudyForm"




class ViewComments extends React.Component {
    constructor(props){
        super(props);
        this.state={
            id: this.props.match.params.id,
            message: null,
            comments : "",
            creatorId: AuthService.getCurrentUser().id,

            errors:""
        }

        
        
    }

    refreshCourses(){
        axios.get(API_URL + "/studyView/comments/" + this.props.match.params.id ).then(response => {
            console.log("response= ",response.data.comments);
            this.setState({
                comments : response.data.comments,
                
            });
            
           
        });
    }


    componentDidMount(){
        this.refreshCourses();
    }


    render(){
        let comment = this.state.comments;


        if(comment != ""){
            return(
                <div>
    
                    Correction Comment  =  {comment}
    
                </div>
    
            );
        }
        else{
            return(
                <div>
    
                    No comments
    
                </div>
    
            );
        } 
        
    }

}

export default ViewComments;