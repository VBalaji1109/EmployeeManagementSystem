import React , {useEffect, useState} from 'react'
import { deleteEmployee, listEmployees } from '../services/EmployeeService'
import { useNavigate } from 'react-router-dom'

const ListEmployeeComponent = () => {
    const [employees , setEmployees] = useState([])

    useEffect(()=>{
        getAllEmployees();
    })
    const navigator = useNavigate();

    function getAllEmployees(){
        listEmployees().then((response) => {
            setEmployees(response.data);
        }).catch(err => {
            console.error(err);
        })
    }
    function addNewEmployee(){
        navigator('/add-employee')
    }

    function updateEmployee(id){
        navigator(`/edit-employee/${id}`)
    }
    function removeEmployee(id){
        console.log(id);
        deleteEmployee(id).then(response => {
            getAllEmployees();
        }).catch(error =>{
            console.error(error);
        })
    }
    return(
    <div className="container">
        <h2 className="text-center"> List Of Employees </h2>
        <button className='btn btn-outline-secondary' onClick={addNewEmployee}>Add Employee</button>
        <table className="table table-striped table-bordered">
            <thead>
                <tr>
                    <th className="text-center"> EMPLOYEE ID </th>
                    <th className="text-center"> EMPLOYEE FIRST NAME </th>
                    <th className="text-center"> EMPLOYEE LAST NAME </th>
                    <th className="text-center"> EMPLOYEE EMAIL ID</th>
                    <th className="text-center"> ACTIONS</th>
                </tr>
            </thead>
            <tbody>
                {employees.map(emp =>  
                    <tr key={emp.id}>
                        <td className="text-center">{emp.id}</td>
                        <td className="text-center">{emp.firstName}</td>
                        <td className="text-center">{emp.lastName}</td>
                        <td className="text-center">{emp.email}</td>
                        <td className='text-center'> 
                            <button className='btn btn-info' onClick={()=> updateEmployee(emp.id)}>Update</button>
                            <button className='btn btn-danger' onClick={()=> removeEmployee(emp.id)} style={{marginLeft:"20px"}}>Delete</button>
                        </td>
                    </tr>
                )}
            </tbody>
        </table>
     </div>
     )
}
export default ListEmployeeComponent