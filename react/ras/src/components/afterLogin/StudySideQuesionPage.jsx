import React from 'react';
import axios from 'axios';
import AuthService from "../../services/auth.service";

const API_URL = 'http://localhost:8080/ras/study/'
class StudySideQuesionPage extends React.Component {
    constructor(props){
        super(props);
        this.state={
            questionList : [],
            countOfPage : [],
            currentPage : "",
            answerMapList  : [],
             items : [{test: "testing"}],
             returnItemsList:[],

             
        }
        this.handleChange = this.handleChange.bind(this);
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
            // console.log("answerMapList in this state = ", this.state.answerMapList);
    }

    endOfForm(){
        console.log("end form answerMapList in this state = ", this.state.answerMapList);

        axios.post(API_URL + this.state.currentPage +"/endPage"
            ,this.state.answerMapList).then(response =>{
            console.log("response.data =", response.data);

            if(response.data === 'Successful'){
                console.log("Data inserted Successfully");
                this.props.history.push({
                    pathname: "/profile"
                })
                window.location.reload();
            }
            // var resdata = response.data.questionList[0];

            
        })
        .catch(error =>{
          console.log(error)
        })


    }

    nextForm(){
        console.log("next form button answerMapList in this state = ", this.state.answerMapList);

        axios.post(API_URL + this.state.currentPage +"/goToNextPage"
            ,this.state.answerMapList).then(response =>{
            console.log("response.data =", response.data);
            var resdata = response.data.questionList[0];

            this.props.history.push({
                pathname: "/study/" + resdata.studyId + "/" + resdata.studyDataId+ "/" + resdata.systemFormId + "/" + resdata.page,
                state: { detail: response.data }
            })
            window.location.reload();
        })
        .catch(error =>{
          console.log(error)
        })


    }
    getData(){
        console.log(" Inside method getData() ");

            this.setState({questionList : this.props.location.state.detail.questionList,
                countOfPage : this.props.location.state.detail.pageList,
                currentPage : this.props.location.state.detail.questionList[0].page,
            });

            this.setState(prevState => ({
                answerMapList: {
                    ...prevState.answerMapList,
                    "studyId" : this.props.location.state.detail.questionList[0].studyId,
                    "studyAppDataId" : this.props.location.state.detail.questionList[0].studyAppDataId,
                    "studyDataFormId" : this.props.location.state.detail.questionList[0].studyDataFormId,
                    "creatorId": AuthService.getCurrentUser().id
                    
                    // "page" : this.props.location.state.detail.questionList[0].page,
                },
            }));

      }

    // getQuestionList(){
    //     console.log(" Inside method getQuestionList() ");
    //     console.log("states systemFormId ", this.state.questionPageDetails.systemFormId);
        // axios.get(API_URL + this.state.questionPageDetails.studyId + "/" + this.state.questionPageDetails.filterFormId+ "/" + this.state.questionPageDetails.systemFormId + "/" + this.state.questionPageDetails.page
        //     ,this.state).then(response =>{
        //     console.log("response.data =", response.data);
          
        //     // this.props.history.push({
        //     //     pathname: "/study/" + questionPageDetails.studyId + "/" + questionPageDetails.filterFormId+ "/" + questionPageDetails.systemFormId + "/" + questionPageDetails.page,
        //     //     state: { detail: response.data }
        //     // })
        //     // window.location.reload();
        // })
        // .catch(error =>{
        //   console.log(error)
        // })
    // }

    componentDidMount(){
        console.log(" Inside method componentMount() start");

        this.getData();
        // this.getQuestionList();

        console.log(" Inside method componentMount() end");
    
      }

    render(){
        console.log("question List in this state = ", this.state.questionList);
        // console.log("count of Pages in this state = ", this.state.countOfPage);
        // console.log("current Pages in this state = ", this.state.currentPage);
        
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
                                 <button type="button" onClick={this.endOfForm} className="btn btn-primary text-center">End Form </button>                              
                    </div>
                )


            }else{
                console.log("Inside else condition")
                return (

                    <div className="card-footer text-center">          
                                 <button type="submit" onClick={this.nextForm} className="btn btn-primary text-center"> Save & go to Next Page </button>
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
                                                            <input type={d.answerType} name={d.dbColumnName}  id={d.dbColumnName} onChange={this.handleChange} placeholder="Enter the value"/>
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

export default StudySideQuesionPage;