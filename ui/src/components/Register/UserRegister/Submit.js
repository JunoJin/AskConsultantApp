import React, { Component } from 'react';
import '../../../containers/dumb/User/RegisterPage.css';

import Checkbox from 'react-md/lib/SelectionControls/Checkbox';
import Button from 'react-md/lib/Buttons/Button';
import {Link} from 'react-router-dom'

class Submit extends Component {
    render() {
        return (
            <div id="canvas">
                <Checkbox
                    id="readDocumentationPage"
                    name="simpleCheckboxes"
                    label="I accept the Terms and Conditions"
                />

                <Button raised label="Submit"  onClick={e => this.props.registrationSubmit(e, this.props.dispatch, this.props.history, this.props.regStatus)}/>

            </div>

        );
    }
}

export default Submit;