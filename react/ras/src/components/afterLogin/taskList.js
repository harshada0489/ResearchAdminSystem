import React from "react"
const TaskList = (props) => {
  return (
    props.taskList.map((val, idx) => {
      let labelName = `labelName-${idx}`, seqNum = `seqNum-${idx}`, ipType = `ipType-${idx}`
      return (
        <tr key={val.index}>
          <td>
            <input type="text"  name="labelName" data-id={idx} id={labelName} className="form-control " />
          </td>
          <td>
            <input type="text"  name="seqNum" id={seqNum} data-id={idx} className="form-control " />
          </td>
          {/* <td>
            <textarea  name="taskNotes" id={taskNotes} data-id={idx} className="form-control"></textarea>
          </td> */}
          <td>
            <select name="ipType" id={ipType} data-id={idx} className="form-control" >
              <option value="Select">Select</option>
              <option value="Radio button">Radio button</option>
              <option value="Dropdown">Dropdown</option>
              <option value="File Upload">File Upload</option>
              <option value="Image Upload ">Image Upload </option>
              <option value="TextArea">TextArea</option>
              <option value="Dropdown">Dropdown</option>
              <option value="TextBox">TextBox</option>
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
export default TaskList