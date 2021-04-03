import React from "react";
import { Dropdown } from 'semantic-ui-react'


const ContactList = (props) => {
  console.log("props= ", props);

  let uList;
  let tList;

  if(props.contactList[0].userList[0]){
    console.log("============================ userList is present =========================");
    console.log("props.contactList[0].userList[0].length =", props.contactList[0].userList[0].length);

     uList = props.contactList[0].userList[0].length
                && props.contactList[0].userList[0].map((item, i) => {
                  return (
                    <option value={item.id}>{item.firstName}</option>
                  )
                  },this);
    
     console.log("uList = " + uList);
  }else{
     uList = null;
  }
  

  if(props.contactList[0].userList[0]){
      console.log("============================ typeList is present =========================");
      console.log("props.contactList[0].typeList[0].length =", props.contactList[0].typeList[0].length);

       tList = props.contactList[0].typeList[0].length
      && props.contactList[0].typeList[0].map((item, i) => {
        return (
          <option value={item.type}>{item.type}</option>
        )
        },this);
  
        console.log("tList = " + tList);
  
  }else{
    tList = null;
  }
   



  return (
    props.contactList.map((val, idx) => {
      let userId = `userId-${idx}`, type = `type-${idx}`;
      return (
        <tr key={val.index}>
          <td>
            <select name="userId" id={userId} data-id={idx} className="form-control" >
            <option value="">Select</option>
              {uList}
            </select>
          </td>

          <td>
            <select name="type" id={type} data-id={idx} className="form-control" >
            <option value="">Select</option>
              {tList}
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



// let uList = props.contactList.userList.length > 0
// 		&& props.contactList.userList.map((item, i) => {
// 		return (
// 			<option key={i} value={item.firstName}>{item.firstName}</option>
// 		)
//     }, this);

