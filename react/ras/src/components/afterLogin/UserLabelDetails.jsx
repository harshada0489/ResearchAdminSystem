import React from 'react';

class UserLabelDetails extends React.Component {
    constructor(props){
        super(props);
        this.state={
            labelDetails : []
        }
    }


    getData(){
            this.setState({labelDetails : this.props.location.state.detail})
      }

    componentDidMount(){
        this.getData()
      }

    render(){
        console.log("label Details in this state = ", this.state)
        // console.log("Rendered Page = ",this.props.location.state.detail);
        return(
            <div>
                <h4>Please fill the below details</h4>
            <div className="container">

                {this.state.labelDetails.map(d => (
                <div key={d.id}>          
                    <div className="form">
                        <div className="row">
                            <div className="col-25">
                                <label htmlFor={d.labelName}>{d.labelName}</label>
                            </div>
                            <div className="col-75">
                                <input type={d.ipType} name={d.labelName} value={d.labelName}  onChange={this.handleChange} placeholder="Enter the value"/>
                            </div>
                        </div>

                    </div>
                    
                </div>
                    ))} 
                        <div className="form-submit">
                            <button type="button" onClick={this.studyFormHandler} className="btn"> Submit</button>
                        </div>
            </div>
            {/* <ul>
            {this.state.labelDetails.map(d => (<li key={d.id}>
            
            
                <div>
                InputType ={d.ipType} 
                </div>

                <div>
                LabelName= {d.labelName} 
                </div>

                <div>
                SeqNum = {d.seqNum} 
                </div>


                </li>))} 
                </ul> */}
            </div>
        )
    }
}

export default UserLabelDetails;