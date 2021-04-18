import React from "react"
const TaskList = (props) => {
  return (
    props.taskList.map((val, idx) => {
      let questionText = `questionText-${idx}`, questionNumber = `questionNumber-${idx}`, answerType = `answerType-${idx}` , dbColumnName = `dbColumnName-${idx}`
      return (
        <tr key={val.index}>

          <td>
            <input type="text"  name="questionText" data-id={idx} id={questionText} className="form-control " />
          </td>
          {/* <td>
            <input type="text"  name="questionNumber" id={questionNumber} data-id={idx} className="form-control " />
          </td> */}

          <td>
            <select name="questionNumber" id={questionNumber} data-id={idx} className="form-control" >
              <option value="select">Select</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
              <option value="13">13</option>
              <option value="14">14</option>
              <option value="15">15</option>
              <option value="16">16</option>
              <option value="17">17</option>
              <option value="18">18</option>
              <option value="19">19</option>
              <option value="20">20</option>
            </select>
          </td>

          
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