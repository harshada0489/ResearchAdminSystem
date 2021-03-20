import React from 'react';
import axios from 'axios';


const API_URL = 'http://localhost:8080/ras/study/'
class StudySideQuesionPage extends React.Component {
    constructor(props){
        super(props);
        this.state={
            questionPageDetails : [],
            questionList : []
        }
    }


    getData(){
            this.setState({questionPageDetails : this.props.location.state.detail})
      }

    getQuestionList(){
        axios.post(API_URL + this.state.questionPageDetails.studyId + "/" + this.state.questionPageDetails.filterFormId+ "/" + this.state.questionPageDetails.systemFormId + "/" + this.state.questionPageDetails.page
            ,this.state).then(response =>{
            console.log("response.data =", response.data);
          
            // this.props.history.push({
            //     pathname: "/study/" + questionPageDetails.studyId + "/" + questionPageDetails.filterFormId+ "/" + questionPageDetails.systemFormId + "/" + questionPageDetails.page,
            //     state: { detail: response.data }
            // })
            // window.location.reload();
        })
        .catch(error =>{
          console.log(error)
        })
    }

    componentDidMount(){
        this.getData();
        this.getQuestionList();
      }

    render(){
        console.log("question Details in this state = ", this.state)
        
        return (
                <div>
                    <h4>Please fill the below details</h4>
                </div>
        )
        // return(
        //     <div>
        //         <h4>Please fill the below details</h4>
        //     <div className="container">

        //         {this.state.questionPageDetails.map(d => (
        //         <div key={d.id}>          
        //             <div className="form">
        //                 <div className="row">
        //                     <div className="col-25">
        //                         <label htmlFor={d.labelName}>{d.labelName}</label>
        //                     </div>
        //                     <div className="col-75">
        //                         <input type={d.ipType} name={d.labelName} value={d.labelName}  onChange={this.handleChange} placeholder="Enter the value"/>
        //                     </div>
        //                 </div>

        //             </div>
                    
        //          </div>
        //             ))} 
        //                 <div className="form-submit">
        //                     <button type="button" onClick={this.studyFormHandler} className="btn"> Submit</button>
        //                 </div>
        //     </div>
        //     </div>
        // )
    }
}

export default StudySideQuesionPage;