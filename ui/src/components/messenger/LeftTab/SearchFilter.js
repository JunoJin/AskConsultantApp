import React, { Component } from 'react';
import SelectField from 'react-md/lib/SelectFields/SelectField'
import TextField from 'react-md/lib/TextFields/TextField'
import Style from './SearchFilter.css'

class SearchFilter extends Component {
    render(){
        return (
            <div id="search_filter">
                <div>
                    <TextField
                        id="searchField"
                        label="Search"
                        lineDirection="center"
                        placeholder="Search term goes here"
                        className="md-cell md-cell--bottom"
                        />
                </div>
                <div>
                    <SelectField
                        id="filter-dropdown"
                        label="Sort by..."
                    />
                </div>
            </div>
        )
    }
}

export default SearchFilter;