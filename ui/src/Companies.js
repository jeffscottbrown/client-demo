import React, {Component} from 'react';
import {Table, Button, Grid} from 'react-bootstrap';
import {LinkContainer} from 'react-router-bootstrap';
// import ConferenceDetailsForm from './ConferenceDetailsForm';
import FontAwesome from 'react-fontawesome';
import Time from 'react-time';

import {SERVER_URL} from './conf/config';

class Companies extends Component {

  constructor() {
    super();

    this.state = {
      hostPort: '',
        hostName: '',
        companies: []
    };
  }


  load = () => {
    fetch(`${SERVER_URL}/companies`)
        .then(r => r.json())
        .then(json => this.setState(json))
        .catch(error => console.error("Error connecting to server: " + error));
  };

  componentDidMount() {
    this.load();
  }

  render() {
      const renderCompanyRow = company => {
          return (
              <tr key={company.id}>
                  <td>{company.name}</td>
              </tr>
          );
      };

      return (
          <Grid>
              <h1 className="h2">Companies</h1>
              <h2>Data received from {this.state.hostName}:{this.state.hostPort}</h2>
              <Table bordered hover id="companiesTable">
                  <thead>
                  <tr>
                      <th>Name</th>
                  </tr>
                  </thead>
                  <tbody>
                  {this.state.companies.map(renderCompanyRow)}
                  </tbody>
              </Table>
          </Grid>
      );
  }
}

export default Companies;
