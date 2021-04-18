
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

             disabled:""


             
        }

        this.handleChange = this.handleChange.bind(this);

        this.disabled_endOfForm = this.disabled_endOfForm.bind(this);
        this.disabled_nextForm = this.disabled_nextForm.bind(this);

        this.endOfForm = this.endOfForm.bind(this);
        this.nextForm = this.nextForm.bind(this);

}



handleChange = (e) => {
 
    this.setState(prevState => ({
        answerMapList: {
            ...prevState.answerMapList,
            [e.target.id]: e.target.value,
        },
    }));

    console.log('in handle change'+e.target);
    // console.log("answerMapList in this state = ", this.state.answerMapList);
}




disabled_endOfForm(){
console.log("end form answerMapList in this state = ", this.state.answerMapList);

this.props.history.push({
    pathname: "/profile"
})
}

disabled_nextForm(){

console.log("next form button answerMapList in this state = ", this.state.answerMapList);

var nextPage = parseInt(this.state.currentPage) + 1;
 console.log("nextPage value ==================== ", nextPage);

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



endOfForm(){
    console.log("end form answerMapList in this state = ", this.state.answerMapList);
    // this.nextForm();

    axios.post(API_URL + "/endDraftPage/" + this.state.currentPage + "/studyApp/view/" + this.state.id
    , this.state.answerMapList)
       .then(
           response => {
               console.log(response.data);
               if(response.data.load == "Successful"){

                this.props.history.push({
                    pathname: "/profile"
                })
               }
            //    let qsize = response.data.questionList;
            //    this.preFilledanswerList(qsize);

           })

    
    }
    
   
    
nextForm(){
        console.log("next form button answerMapList in this state = ", this.state.answerMapList);
    
        var nextPage = parseInt(this.state.currentPage) + 1;
         console.log("nextPage value ==================== ", nextPage);
        
         axios.post(API_URL + "/saveDraftNextPage/" + this.state.currentPage + "/studyApp/view/" + this.state.id
         , this.state.answerMapList)
            .then(
                response => {
                    console.log(response.data);
                    let qsize = response.data.questionList;
                    this.preFilledanswerList(qsize);

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
                    window.location.reload();
                    
                    console.log("qAndAList=",this.state.qAndAList);
                }
            )
        
        
        
            console.log("after to API call in componentDidMount()");
        
        this.props.history.push({
        
            pathname: "/viewMyStudyForm/viewPage/" + nextPage + "/studyApp/view/" + this.state.id,
            // state: { detail: response.data }
        })
    
    
    }


// nextForm(){
    
//     console.log("next form button answerMapList in this state = ", this.state.answerMapList);
    
//     var nextPage = parseInt(this.state.currentPage) + 1;
//      console.log("nextPage value ==================== ", nextPage);
    
//      axios.post(API_URL + "/page/" + nextPage + "/studyApp/view/" + this.state.id)
//         .then(
//             response => {
//                 console.log(response.data);
//                 this.setState({
//                     qAndAList : response.data
//                 });
//                 this.setState({questionList : response.data.questionList,
//                     countOfPage : response.data.pageList,
//                     currentPage : response.data.questionList[0].page,
                    
//                 });
            
//                 this.setState(prevState => ({
//                     answerMapList: {
//                         ...prevState.answerMapList,
//                         "studyId" : response.data.questionList[0].studyId,
//                         "studyAppDataId" : response.data.questionList[0].studyAppDataId,
//                         "studyDataFormId" : response.data.questionList[0].studyDataFormId,
                        
//                         "creatorId" : AuthService.getCurrentUser().id
//                     },
//                 }));
//                 // window.location.reload();
                
//                 console.log("qAndAList=",this.state.qAndAList);
//             }
//         )
    
    
    
//         console.log("after to API call in componentDidMount()");
    
//     this.props.history.push({
    
//         pathname: "/viewMyStudyForm/viewPage/" + nextPage + "/studyApp/view/" + this.state.id,
//         // state: { detail: response.data }
//     })
    
    
//     }



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

  preFilledanswerList(qsize){
   
    this.setState({ answerMapList: {} });

    console.log("response.data.questionList =========", qsize);
    console.log("response.data.questionList.length =========", qsize.length);
    for(var i = 0; i<qsize.length; i++){
        let question = qsize[i];
        console.log("question ==============" , question);
        console.log("question.dbColumnName ==============" , question.dbColumnName);
        console.log("question.answer ==============" , question.answer);

        let dbColumnName = question.dbColumnName;

        let answer = question.answer;

        this.setState(prevState => ({
            answerMapList: {
                ...prevState.answerMapList,
                [dbColumnName]: answer,
            },
        }));
    }
  }

componentDidMount(){

    axios.post(API_URL + "/page/" + this.state.currentPage + "/studyApp/view/" + this.state.id)
    .then(
        response => {
            console.log("resp.data ==============",response.data);
            let qsize = response.data.questionList;

            this.preFilledanswerList(qsize);

            this.setState({
                qAndAList : response.data
            });
            this.setState({questionList : response.data.questionList,
                countOfPage : response.data.pageList,
                currentPage : response.data.questionList[0].page,
                disabled : response.data.disabled
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

console.log("answerMapList in this state ============== ", this.state.answerMapList);
console.log("question List in this state = ", this.state.questionList);
console.log("disabled = ", this.state.disabled);
let currPage = this.state.currentPage;
let totalPages = this.state.countOfPage;
let disabled = this.state.disabled;

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
if(disabled){
    if(currPage == totalPages){
        console.log("Inside if condition")
        return (

            <div className="card-footer text-center">          
                         <button type="button" onClick={this.disabled_endOfForm} className="btn btn-primary text-center">Go to Profile Page </button>                              
            </div>
        )


    }else{
        console.log("Inside else condition")
        return (

            <div className="card-footer text-center">          
                         <button type="submit" onClick={this.disabled_nextForm} className="btn btn-primary text-center"> View Next Page </button>
            </div>

            
        )
    }
}
else{
    if(currPage == totalPages){
        console.log("Inside if condition")
        return (

            <div className="card-footer text-center">          
                         <button type="button" onClick={this.endOfForm} className="btn btn-primary text-center">End Form </button>                              
            </div>
        )


    }else{
        console.log("Inside else condition")
        return (

            <div className="card-footer text-center">          
                         <button type="submit" onClick={this.nextForm} className="btn btn-primary text-center"> Save and Go To Next Page </button>
            </div>

            
        )
    }
}
    
} 






return(
    <div style={{ width: "100%"}}>

        <table style={{ width: "100%"}}>
            <tbody>
           
                <tr>
                    <td style={{ border: "2px solid lightgrey", width: "20%"}}>
                        <div className="container" >{currPageComponent()}</div>
                    </td>                          


                    <td  style={{width: "80%"}}>
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