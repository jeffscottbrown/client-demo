import React, {Component} from 'react';
import {Table, Badge, Label, Alert, Grid, Jumbotron} from 'react-bootstrap';


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
                  <td><Badge>{company.id}</Badge></td>
                  <td>{company.name}</td>
              </tr>
          );
      };

      return (
          <Grid>
              <Jumbotron>
                  <h1>Companies</h1>
              </Jumbotron>
              <Alert bsStyle="success"><Label bsStyle="success">Message</Label> Data received from {this.state.hostName}:{this.state.hostPort}</Alert>
              <Table bordered striped hover id="companiesTable">
                  <thead>
                  <tr>
                      <th>ID</th>
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
