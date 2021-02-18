import { render } from "react-dom";
import React from 'react';
import Login from '../login/login.jsx';
import ReactDOM from 'react-dom';
import axios from 'axios';



class CreateStudy extends React.Component {
    constructor(props) {
        super(props);
        this.state ={
            studyTitle : '',
            oneWordIdentifier : '',
            selectedOption : '',
            gender : '',
            age : '',
            NoOfHumanRequired : ''
          };

        this.studyFormHandler = this.studyFormHandler.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.onChangeValue = this.onChangeValue.bind(this);
      }

      studyFormHandler(event){

        event.preventDefault();
        console.log(this.state);
        let loginRequest ={studySubject: this.state.studySubject};
  
        axios.post('http://localhost:8080/ras/studyForm',this.state).then(respose =>{
          console.log(respose)
          // if(response.data ==="Successful" && response.status == 200){
                //  ReactDOM.render(< Login />, document.getElementById('root'));
                //  export{ Login } from "./login";
          // }
        })
        .catch(error =>{
          console.log(error)
        })

            }   
    
    
    handleChange(event){
          
        this.setState({
           [event.target.name]: event.target.value
         });
       }

    onChangeValue(event){
        this.setState({
            selectedOption: event.target.value
        });

        // console.log(event.taget.value);
    }
    render(){
        
        let {radioOption} = this.state.selectedOption;
        console.log(radioOption);
        const typeOfStudy = () => {
            if (this.state.selectedOption ==="Human"){
                return (
                    <div>
                        <div className="form-group">
                            <label htmlFor="gender">Gender</label>
                            <input type="text" name="gender" value={this.state.gender}  onChange={this.handleChange} placeholder="Gender"/>
                        </div>

                        <div className="form-group">
                            <label htmlFor="age">Age</label>
                            <input type="text" name="Age" value={this.state.age}  onChange={this.handleChange} placeholder="Age"/>
                        </div>

                        <div className="form-group">
                            <label htmlFor="NoOfHumanRequired">No Of Humans Required</label>
                            <input type="text" name="NoOfHumanRequired" value={this.state.NoOfHumanRequired}  onChange={this.handleChange} placeholder="No of Human Required"/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="NoOfHumanRequired">Page 1 Complete</label>
                            <input type="text" name="NoOfHumanRequired" value={this.state.NoOfHumanRequired}  onChange={this.handleChange} placeholder="No of Human Required"/>
                        </div>
                    </div>
                )
                    
            }else if (this.state.selectedOption ==="Animal"){
                return (
                    <div>
                        <div className="form-group">
                            <label htmlFor="typeOfAnimal">Which type of Animal</label>
                            <input type="text" name="typeOfAnimal" value={this.state.typeOfAnimal}  onChange={this.handleChange} placeholder="Type Of Animal"/>
                        </div>

                        <div className="form-group">
                            <label htmlFor="age">Age</label>
                            <input type="text" name="Age" value={this.state.age}  onChange={this.handleChange} placeholder="Age"/>
                        </div>

                        <div className="form-group">
                            <label htmlFor="NoOfHumanRequired">No Of Humans Required</label>
                            <input type="text" name="NoOfHumanRequired" value={this.state.NoOfHumanRequired}  onChange={this.handleChange} placeholder="No of Human Required"/>
                        </div>
                    </div>
                )
            }
        }
        return(
            <div>
                <div>

                </div>
                <div>
                <h1>Page 1</h1>
                </div>
                 

                 <div className="form">
                    <div className="form-group">
                        <label htmlFor="studyTitle">Study Tile</label>
                        <input type="text" name="studyTitle" value={this.state.studyTitle}  onChange={this.handleChange} placeholder="Study Title"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="oneWordIdentifier">One Word Identifier</label>
                        <input type="text" name="oneWordIdentifier" value={this.state.oneWordIdentifier}  onChange={this.handleChange} placeholder="One Word Identifier"/>
                    </div>

                    <div className="form-group" >
                        <label htmlFor="typeOfStudy">Type Of Study : </label>
                        <input type="radio" name="typeOfStudy" value="Human" checked={this.state.selectedOption === "Human"}
                            onChange={this.onChangeValue}/> Human
                        <input type="radio" name="typeOfStudy" value="Animal" checked={this.state.selectedOption === "Animal"}
                            onChange={this.onChangeValue} /> Animal
                    </div>

                    {/* <div>
                        Selected option is : {this.state.selectedOption} 
                    </div>        */}
                        
                    {typeOfStudy()}
                    
                </div>

                <div className="footer">
                    <button type="button" onClick={this.studyFormHandler} className="btn">
                    Submit
                    </button>
                </div>



            </div>
                
        )
    }

    }

export default CreateStudy;