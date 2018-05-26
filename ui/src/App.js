import React, {Component} from 'react';
import {hashHistory, Route, Router} from 'react-router';
import MainLayout from './layouts/MainLayout';
import Companies from './Companies';
import './App.css';

class App extends Component {
    render() {
        return (
            <Router history={hashHistory}>
                <Route component={MainLayout}>
                    <Route path="/companies" component={Companies}/>
                </Route>
            </Router>
        );
    }
}

export default App;
