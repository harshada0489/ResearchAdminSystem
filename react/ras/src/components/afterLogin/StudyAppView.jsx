
import React, { Component } from "react";
import axios from 'axios';

import AuthService from "../../services/auth.service";

var API_URL = "http://localhost:8080/ras/viewMyStudyForm"


class StudyAppView extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            id : this.props.match.params.id,
            qAndAList : [], 
            message:"",

            questionList : [],
            countOfPage : [],
            currentPage : this.props.match.params.currentPage,
            answerMapList  : [],
             items : [{test: "testing"}],
             returnItemsList:[],

             disabled: true 


             
        }

        this.endOfForm = this.endOfForm.bind(this);
        this.nextForm = this.nextForm.bind(this);


}


endOfForm(){
console.log("end form answerMapList in this state = ", this.state.answerMapList);

this.props.history.push({
    pathname: "/profile"
})

// axios.post(API_URL + this.state.currentPage +"/endPage"
//     ,this.state.answerMapList).then(response =>{
//     console.log("response.data =", response.data);

//     if(response.data === 'Successful'){
//         console.log("Data inserted Successfully");
        // this.props.history.push({
        //     pathname: "/profile"
        // })
//         window.location.reload();
//     }
   

    
// })
// .catch(error =>{
//   console.log(error)
// })


}


nextForm(){

console.log("next form button answerMapList in this state = ", this.state.answerMapList);

var nextPage = parseInt(this.state.currentPage) + 1;
//  var nextPage = this.state.currentPage + 1;
 console.log("nextPage value ==================== ", nextPage);


//  this.getData();


 
 axios.post(API_URL + "/page/" + nextPage + "/studyApp/view/" + this.state.id)
    .then(
        response => {
            console.log(response.data);
            this.setState({
                qAndAList : response.data
            });
            this.setState({questionList : response.data.questionList,
                countOfPage : response.data.pageList,
                currentPage : response.data.questionList[0].page,
            });
        
            this.setState(prevState => ({
                answerMapList: {
                    ...prevState.answerMapList,
                    "studyId" : response.data.questionList[0].studyId,
                    "studyAppDataId" : response.data.questionList[0].studyAppDataId,
                    "studyDataFormId" : response.data.questionList[0].studyDataFormId,
                    "creatorId" : AuthService.getCurrentUser().id
                },
            }));
            // window.location.reload();
            
            console.log("qAndAList=",this.state.qAndAList);
        }
    )



    console.log("after to API call in componentDidMount()");

this.props.history.push({

    pathname: "/viewMyStudyForm/viewPage/" + nextPage + "/studyApp/view/" + this.state.id,
    // state: { detail: response.data }
})


}

getData(){

    console.log(" Inside method getData() ");

    var nextPage = parseInt(this.state.currentPage) + 1;

    this.setState({
        currentPage: nextPage
     })

            // this.setState({questionList : this.props.location.state.detail.questionList,
            //     countOfPage : this.props.location.state.detail.pageList,
            //     currentPage : this.props.location.state.detail.questionList[0].page,
            // });

            // this.setState(prevState => ({
            //     answerMapList: {
            //         ...prevState.answerMapList,
            //         "studyId" : this.props.location.state.detail.questionList[0].studyId,
            //         "studyAppDataId" : this.props.location.state.detail.questionList[0].studyAppDataId,
            //         "studyDataFormId" : this.props.location.state.detail.questionList[0].studyDataFormId,
            //         "creatorId" : AuthService.getCurrentUser().id
                    
            //         // "page" : this.props.location.state.detail.questionList[0].page,
            //     },
            // }));

  }


componentDidMount(){
//     console.log(" Inside method componentMount() start");
//     this.getData();
//     console.log(" Inside method componentMount() end");

//   }


    axios.post(API_URL + "/page/" + this.state.currentPage + "/studyApp/view/" + this.state.id)
    .then(
        response => {
            console.log(response.data);
            this.setState({
                qAndAList : response.data
            });
            this.setState({questionList : response.data.questionList,
                countOfPage : response.data.pageList,
                currentPage : response.data.questionList[0].page,
            });
        
            this.setState(prevState => ({
                answerMapList: {
                    ...prevState.answerMapList,
                    "studyId" : response.data.questionList[0].studyId,
                    "studyAppDataId" : response.data.questionList[0].studyAppDataId,
                    "studyDataFormId" : response.data.questionList[0].studyDataFormId,
                    "creatorId" : AuthService.getCurrentUser().id
                },
            }));
            // window.location.reload();
            
            console.log("qAndAList=",this.state.qAndAList);
        }
    )

    // this.getData();

    console.log("after to API call in componentDidMount()");


}



render(){
console.log("question List in this state = ", this.state.questionList);

let currPage = this.state.currentPage;
let totalPages = this.state.countOfPage;

console.log("currPage= ", currPage)


const currPageComponent = () => {
let myArray = []
for(let i = 0; i<totalPages;i++) {
    let curr_i = i + 1 ;

    if(currPage == curr_i){
        myArray.push( <h4><li>Page {i + 1}</li></h4>) 
    }else{
        myArray.push(<li> Page {i + 1}</li>) 
    }
        
}
return myArray
} 


const showSubmitOrNextButton = () => {

    if(currPage == totalPages){
        console.log("Inside if condition")
        return (

            <div className="card-footer text-center">          
                         <button type="button" onClick={this.endOfForm} className="btn btn-primary text-center">Go to Profile Page </button>                              
            </div>
        )


    }else{
        console.log("Inside else condition")
        return (

            <div className="card-footer text-center">          
                         <button type="submit" onClick={this.nextForm} className="btn btn-primary text-center"> View Next Page </button>
            </div>

            
        )
    }
} 






return(
    <div>

        <table>
            <tbody>
           
                <tr>
                    <td style={{ border: "2px solid lightgrey "}}>
                        <div className="container" >{currPageComponent()}</div>
                    </td>                          


                    <td  style={{width: "60%"}}>
                        <div className="container" >
                        <h4>Please fill the below details</h4>
                                    {this.state.questionList.map(d => (
                                    <div key={d.questionNumber}>          
                                        <div className="qform">
                                            <div className="row">                                                
                                                <div className="col-25">
                                                    <label htmlFor={d.dbColumnName}>{d.questionText}</label>
                                                </div>
                                                <div className="col-75">
                                                    <input type={d.answerType} name={d.dbColumnName}  id={d.dbColumnName} 
                                                    onChange={this.handleChange} value = {d.answer} 
                                                    disabled = {(this.state.disabled)? "disabled" : ""}/>
                                                </div>
                                            </div>

                                        </div>
                                        
                                    </div>

                                        ))}
                        </div>
                    </td>
                </tr> 
            </tbody>
        </table>
            <div>
            {showSubmitOrNextButton()}
            </div>
            
                        {/* <div className="form-submit">
                            <button type="button" onClick={this.studyFormHandler} className="btn"> Submit</button>
                        </div> */}
            {/* </div> */}
    </div>
)


}
}








export default StudyAppView;