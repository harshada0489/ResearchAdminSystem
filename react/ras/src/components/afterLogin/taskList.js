import React from "react"
const TaskList = (props) => {
  return (
    props.taskList.map((val, idx) => {
      let question = `question-${idx}`, questionNumber = `questionNumber-${idx}`, answerType = `answerType-${idx}` , dbColumnName = `dbColumnName-${idx}`
      return (
        <tr key={val.index}>

          <td>
            <input type="text"  name="question" data-id={idx} id={question} className="form-control " />
          </td>
          <td>
            <input type="text"  name="questionNumber" id={questionNumber} data-id={idx} className="form-control " />
          </td>
          {/* <td>
            <textarea  name="taskNotes" id={taskNotes} data-id={idx} className="form-control"></textarea>
          </td> */}
          <td>
            <select name="answerType" id={answerType} data-id={idx} className="form-control" >
              <option value="select">Select</option>
              <option value="text">TextBox</option>
              <option value="textarea">TextArea</option>
              {/* <option value="radio">Radio button</option>
              <option value="Dropdown">Dropdown</option>
              <option value="File Upload">File Upload</option>
              <option value="Image Upload ">Image Upload </option> */}
            </select>
          </td>

          <td>
            <input type="text"  name="dbColumnName" id={dbColumnName} data-id={idx} className="form-control " />
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