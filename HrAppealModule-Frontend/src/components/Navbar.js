import '../styles/Navbar.scss';
import React from 'react'
import { Link } from "react-router-dom";

const Navbar = () => {

    return (
        <div className='navbar'>

            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div>
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item active">
                            <Link className='nav-link h3' to="/jobs">Jobs</Link>
                        </li>
                        <li className="nav-item active">
                            <Link className='nav-link h3' to="/apply">Apply</Link>
                        </li>
                        <li className="login nav-item active">
                            <Link className='nav-link h3' to="/login">Login</Link>
                        </li>
                    </ul>
                </div>
            </nav>

        </div>
    )
}

export default Navbar