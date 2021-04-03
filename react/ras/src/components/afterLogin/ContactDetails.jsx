import React from 'react';
import axios from 'axios';
import AuthService from "../../services/auth.service";
import StudySideQuesionPage from "./StudySideQuesionPage";

import ContactList from "./contactList"
import { NotificationContainer, NotificationManager } from 'react-notifications';
import { Dropdown } from 'semantic-ui-react'


const API_URL = 'http://localhost:8080/ras/study/'

class ContactDetails extends React.Component {
    constructor(props){
        super(props);
        this.state={
            contactList: [{index: Math.random(), userId: "", type: "", creatorId: AuthService.getCurrentUser().id, userList: [], typeList:[]}],
            userList : [],
            typeList : [],
            currentPage : 0,
            answerMapList : [],
            
            request_user_list:[],
            request_type_list:[],
        }

        this.handleChange = this.handleChange.bind(this);
        this.addNewRow = this.addNewRow.bind(this);
        this.deteteRow = this.deteteRow.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.clickOnDelete = this.clickOnDelete.bind(this);


    }

    handleChange = (e) => {
        if (["userId", "type"].includes(e.target.name)) {
            let contactList = [...this.state.contactList]
            contactList[e.target.dataset.id][e.target.name] = e.target.value;
        }
        else {
            
            this.setState({ [e.target.name]: e.target.value })
        }

    }
    addNewRow = () => {
        this.setState((prevState) => ({
            contactList: [...prevState.contactList, { index: Math.random(), userId: "", type: "", creatorId: AuthService.getCurrentUser().id,}]
        }));
    }

    deteteRow = (index) => {
        this.setState({
            contactList: this.state.contactList.filter((s, sindex) => index !== sindex),
        });

    }

    handleSubmit = (e) => {
        e.preventDefault();
        console.log("this.state.contactList = ",this.state.contactList);
        console.log("this.state.contactList.length = ", this.state.contactList.length)
        console.log("this.state.contactList[0].userId = ", this.state.contactList[0].userId === '');
        console.log("this.state.contactList[0].type = ", this.state.contactList[0].tpe=== '');
        
        for(var i=0; i<this.state.contactList.length; i++)
        {
                if(this.state.contactList[i].userId === '' || this.state.contactList[i].type ==='' )
                {
                    NotificationManager.warning("Please Fill up Required Field.Please Check User name and User Type ");
                    return false;
                }
        }
        let count = 0;

        for(var i=0; i<this.state.contactList.length; i++)
        {
                if(this.state.contactList[i].type ==='Principal Investigator'){
                    count = count + 1;
                }

                if(count > 1)
                {
                    NotificationManager.warning(" Only one Principal Investigator is allowed");
                    return false;
                }

        }

        if(count == 0){
            NotificationManager.warning(" Atleast one Principal Investigator is required");
                    return false;
        }

        console.log("this.state.contactList =", this.state.contactList);

        axios.post(API_URL + this.state.answerMapList.studyId + "/"  + this.state.answerMapList.studyAppDataId + "/"
        + this.state.answerMapList.studyDataFormId + "/contactDetails"
        , this.state.contactList).then(response =>{
        console.log("response.data =", response.data);
    }
        ).catch(error => {
            if(error.response.status && error.response.status===400)
            NotificationManager.error("Bad Request");
            else NotificationManager.error("Something Went Wrong");
            this.setState({ errors: error })
        });


        axios.post(API_URL + this.state.currentPage +"/goToNextPage"
        ,this.state.answerMapList).then(response =>{
        console.log("response.data =", response.data);
        var resdata = response.data.questionList[0];

        this.props.history.push({
            pathname: "/study/" + resdata.studyId + "/" + resdata.studyDataId+ "/" + resdata.systemFormId + "/" + resdata.page,
            state: { detail: response.data }
        })
        window.location.reload();
    }

        ).catch(error => {
            if(error.response.status && error.response.status===400)
            NotificationManager.error("Bad Request");
            else NotificationManager.error("Something Went Wrong");
            this.setState({ errors: error })
        });
    }



    clickOnDelete(record) {
        this.setState({
            contactList: this.state.contactList.filter(r => r !== record)
        });
    }


    getData(){
        console.log(" Inside method getData() ");

            this.setState({userList : this.props.location.state.detail.userList,
                typeList : this.props.location.state.detail.typeList,
            });


            this.setState(prevState => ({
                answerMapList: {
                    "studyId" : this.props.location.state.detail.studyAppId,
                    "studyAppDataId" : this.props.location.state.detail.dynamicTableDataId,
                    "studyDataFormId" : this.props.location.state.detail.studyDataFormId,
                    
                },
            }));


            this.setState({contactList : [{
                userList: [this.props.location.state.detail.userList],
                typeList: [this.props.location.state.detail.typeList],
            }]  
            });
            

      }

    componentDidMount(){
        console.log(" Inside method componentMount() start");
        

        axios
        .get(`http://localhost:8080/ras/study/getUserList`)
        .then(response=>response.data)
        // console.log("response.data =", response.data);
        .then(

            data=>{this.setState({request_user:data.request_user_list});
                  this.setState({request_type:data.request_type_list}); 
                    this.setState({loading:false}); 
                  }
                  
                  )
        .catch(error => {console.log(error);});



        this.getData();

        console.log(" Inside method componentMount() end");
    
      }

    render(){
        console.log("userList in this state = ", this.state.userList);
        console.log("typeList in this state = ", this.state.typeList);
        console.log("this.state.answerMapList = ", this.state.answerMapList);


        let { contactList } = this.state 
        
        return (

            <div className="content">
                
                <NotificationContainer/>
                <form onSubmit={this.handleSubmit} onChange={this.handleChange} >
                    <div className="row" style={{ marginTop: 20 }}>
                    
                        
                        <div className="col-sm-10">
                            <div className="card">
                                <div className="card-header text-center">Add contact details to be show on 
                                   
                                </div>
                                <div className="card-body">
                                    
                                    <table className="table">
                                        <thead>
                                            <tr>
                                                <th className="required"> User Full Name</th>
                                                <th className="required"> User Type</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                        

                                            <ContactList add={this.addNewRow} delete={this.clickOnDelete.bind(this)}  contactList={contactList} />

                                        </tbody>
                                        <tfoot>
                                            <tr><td colSpan="4">
                                                <button onClick={this.addNewRow} type="button" className="btn btn-primary text-center"> + <i className="fa fa-plus-circle" aria-hidden="true"></i></button>
                                            </td></tr>
                                        </tfoot>
                                    </table>
                                </div>
                                <div className="card-footer text-center"> 
                                 &nbsp; &nbsp; <button type="submit" className="btn btn-primary text-center"> Save & go to Next Page </button>
                                </div>
                            </div>
                        </div>
                        <div className="col-sm-1"></div>
                    </div>
                </form>
            </div>
        )
            
            }

}

export default ContactDetails;