import React, { Component } from 'react';
import './EmployeeRegistrationPage.css';
import Button from 'react-md/lib/Buttons/Button';
import {
    BrowserRouter as Router,
    Route,
    Link
} from 'react-router-dom'

class EmployeeRegistrationPage extends Component {
    render() {
        return (
            <div class="canvas">
                Employee registration
                <div>
                    <Link to="/consultants_messenger"><Button raised label="Go to messenger" /></Link><br />
                </div>
                <div>
                    <Link to="/consultants"><Button raised label="Go back to login" /></Link><br />
                </div>


            </div>
        );
    }
}

export default EmployeeRegistrationPage;