import React from "react";
import { Dropdown } from 'semantic-ui-react'


const ContactList = (props) => {
  console.log("props= ", props);
  
  return (
    props.contactList.map((val, idx) => {
      let userId = `userId-${idx}`, type = `type-${idx}`
      return (
        <tr key={val.index}>
          <td>
            {/* <input type="text"  name="user" data-id={idx} id={user} className="form-control " /> */}

            <select name="userId" id={userId} data-id={idx} className="form-control" >
              <option value="">Select</option>
              <option value="101">Harshada Bhangale</option>
              <option value="102">Rahul Bhole</option>
              <option value="103">Yuga</option>
            </select>


          </td>

          <td>
          <select name="type" id={type} data-id={idx} className="form-control" >
              <option value="">Select</option>
              <option value="Principal Investigator">Principal Investigator</option>
              <option value="Study Author">Study Author</option>
              <option value="Study Contact">Study Contact</option>
              <option value="Reviewer">Reviewer</option>
            </select>
          </td>

          <td>
            {
            idx===0?<button onClick={()=>props.add()} type="button" className="btn btn-primary text-center"> + <i className="fa fa-plus-circle" aria-hidden="true"></i></button>
            : <button className="btn btn-danger" onClick={(() => props.delete(val))} > - <i className="fa fa-minus" aria-hidden="true"></i></button>
            }
          </td>
          
        </tr >
      )
    })
  )
}







export default ContactList


