import React, {Component} from 'react';
import {Alert, Badge, Button, Col, FormControl, Grid, Jumbotron, Label, Row, Table} from 'react-bootstrap';


import {SERVER_URL} from './conf/config';

class Companies extends Component {

    constructor() {
        super();

        this.state = {
            hostPort: '',
            hostName: '',
            companies: [],
            newCompanyName: ''
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

    submitCompany = () => {
        fetch(`${SERVER_URL}/companies`,
            {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify({name: this.state.newCompanyName})
            })
            .then(r => r.json())
            .then(json => this.addCompany(json))
            .catch(error => console.error("Error connecting to server: " + error));
    }

    deleteCompany = (id) => {
        fetch(`${SERVER_URL}/companies/${id}`,
            {
                method: 'DELETE',
                headers: {"Accept": "application/json"}
            })
            .then(r => this.load())
            .catch(error => console.error("Error connecting to server: " + error));
    }


    addCompany = (company) => {
        let companies = this.state.companies;
        companies.push(company);
        this.setState({companies});
    }

    render() {
        const renderCompanyRow = company => {
            return (
                <tr key={company.id}>
                    <td><Badge>{company.id}</Badge></td>
                    <td>{company.name}</td>
                    <td><Button bsStyle='danger' onClick={(e) => this.deleteCompany(company.id)}>Delete</Button></td>
                </tr>
            );
        };

        return (
            <Grid>
                <Jumbotron>
                    <h1>Companies</h1>
                </Jumbotron>
                <Alert bsStyle="success"><Label bsStyle="success">Message</Label> Data received
                    from {this.state.hostName}:{this.state.hostPort}</Alert>

                <Row>
                    <Col md={8}>
                        <FormControl type="text" value={this.state.newCompanyName}
                                     onChange={(e) => this.setState({newCompanyName: e.target.value})}/>
                    </Col>
                    <Col md={4}>
                        <Button bsStyle='primary' onClick={this.submitCompany}>Submit</Button>
                    </Col>
                </Row>
                <Row>
                    <Table bordered striped hover id="companiesTable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.companies.map(renderCompanyRow)}
                        </tbody>
                    </Table>
                </Row>
            </Grid>
        );
    }
}

export default Companies;
