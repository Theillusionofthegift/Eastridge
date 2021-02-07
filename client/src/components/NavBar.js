import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import Form from 'react-bootstrap/Form'
import FormControl from 'react-bootstrap/FormControl'
import Button from 'react-bootstrap/Button'
import './NavBar.css'

function NavBar() {
    return (
        <>
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="/">
                    <img
                        alt=""
                        src="../Illusion_Transparent.png"
                        width="30"
                        height="30"
                        className="d-inline-block align-top"
                    />{'Illusion'}

                </Navbar.Brand>
                <Form inline className="mx-auto">
                    <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                    <Button variant="outline-danger">Search</Button>
                </Form>
                <Nav className="link1">
                    <Nav.Link href="/">Home</Nav.Link>
                    <Nav.Link href="/">About</Nav.Link>
                    <Nav.Link href="/">???</Nav.Link>
                </Nav>
            </Navbar>
        </>)
}

export default NavBar