import React from 'react';
import axios from 'axios';


const API_URL = 'http://localhost:8080/ras/study/'
class StudySideQuesionPage extends React.Component {
    constructor(props){
        super(props);
        this.state={
            questionList : [],
            countOfPage : [],
            currentPage : "",
            answerMapList  : []
        }

        // this.handleChange = this.state.handleChange.bind(this);
    }

    // handleChange(e){
    //     this.setState({
    //         answerMapList : e.target.value
    //     });
    // }

    getData(){
        console.log(" Inside method getData() ");

            this.setState({questionList : this.props.location.state.detail.questionList,
                countOfPage : this.props.location.state.detail.pageList,
                currentPage : this.props.location.state.detail.questionList[0].page
            });
            // window.location.reload();
      }

    // getQuestionList(){
    //     console.log(" Inside method getQuestionList() ");
    //     console.log("states systemFormId ", this.state.questionPageDetails.systemFormId);
    //     axios.get(API_URL + this.state.questionPageDetails.studyId + "/" + this.state.questionPageDetails.filterFormId+ "/" + this.state.questionPageDetails.systemFormId + "/" + this.state.questionPageDetails.page
    //         ,this.state).then(response =>{
    //         console.log("response.data =", response.data);
          
    //         // this.props.history.push({
    //         //     pathname: "/study/" + questionPageDetails.studyId + "/" + questionPageDetails.filterFormId+ "/" + questionPageDetails.systemFormId + "/" + questionPageDetails.page,
    //         //     state: { detail: response.data }
    //         // })
    //         // window.location.reload();
    //     })
    //     .catch(error =>{
    //       console.log(error)
    //     })
    // }

    componentDidMount(){
        console.log(" Inside method componentMount() start");

        this.getData();
        // this.getQuestionList();

        console.log(" Inside method componentMount() end");
    
      }

    render(){
        console.log("question List in this state = ", this.state.questionList);
        console.log("count of Pages in this state = ", this.state.countOfPage);
        console.log("current Pages in this state = ", this.state.currentPage)
        
       let currPage = this.state.currentPage;
       let totalPages = this.state.countOfPage;
       
        console.log("currPage= ", currPage)

        
       const myExample = () => {
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

        return(
            <div>
  
                <table>
                    <tbody>
                   
                        <tr>
                            <td style={{ border: "2px solid lightgrey "}}>
                                <div className="container" >{myExample()}</div>
                            </td>



                            {/* <div className="row">
                                    <div className="col-25">
                                        <label htmlFor="labelsToShowPerPage">No of labels to show on each page :</label>
                                    </div>
                                    <div className="col-75">
                                        <input type="text" name="labelsToShowPerPage" value={this.state.labelsToShowPerPage}  onChange={this.handleChange} placeholder="Enter Number of labels to be shown on each page"/>
                                    </div>
                            </div> */}
                            
                            
                            
                            <td  style={{width: "60%"}}>
                                <div className="container" >
                                <h4>Please fill the below details</h4>
                                            {this.state.questionList.map(d => (
                                            <div key={d.id}>          
                                                <div className="qform">
                                                    <div className="row">
                                                        <div className="col-25">
                                                            <label htmlFor={d.dbColumnName}>{d.questionText}</label>
                                                        </div>
                                                        <div className="col-75">
                                                            <input type={d.answerType} name={d.dbColumnName} onChange={this.handleChange} placeholder="Enter the value"/>
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
                                <div className="form-submit">
                                    <button type="button" onClick={this.studyFormHandler} className="btn"> Submit</button>
                                </div>
                    {/* </div> */}
            </div>
        )


    }
}

export default StudySideQuesionPage;