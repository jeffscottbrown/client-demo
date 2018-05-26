import React from 'react';
import {LinkContainer} from 'react-router-bootstrap';
import logo from '../images/logo.png';
import {Nav, Navbar, NavItem} from 'react-bootstrap';

const MainLayout = ({children}) => {
  return (
    <div>
      <Navbar >
        <Navbar.Header>
          <Navbar.Brand>
            <img src={logo} alt="OCI/Micronaut" style={{height: 75, padding: 10}}/>
          </Navbar.Brand>
        </Navbar.Header>
        <Navbar.Collapse>
          <Navbar.Text>&nbsp;</Navbar.Text>
          <Navbar.Text><span style={{fontWeight: 'bold', marginTop: 15, fontSize: '3em', lineHeight: '1.4em'}}>Micronaut Demo!</span></Navbar.Text>


          <Nav pullRight>
            <LinkContainer to={{pathname: '/companies'}}>
              <NavItem>Companies</NavItem>
            </LinkContainer>
          </Nav>
        </Navbar.Collapse>
      </Navbar>

      {children}
    </div>
  );
};

export default MainLayout;
