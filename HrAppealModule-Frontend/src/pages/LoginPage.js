import '../styles/LoginPage.scss'
import loginImg from '../assets/human-resources.png'
import React from 'react'

const LoginPage = () => {
  return (
    <div className='login-page'>
      <div className='login-form-container'>
        <div className='form'>

          <div className='mid-container'>
            <img className='img-hr-admin row' src={loginImg} width="120" alt="Hr Admin Login Icon"></img>
          </div>

          <div className="form-group">
            <label>Email address</label>
            <input type="email" className="form-control" placeholder="Enter email" />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input type="password" className="form-control" placeholder="Password" />
          </div>

          <div className='mid-container'>
            <button type="submit" className="btn btn-success">Login</button>
          </div>

        </div>
      </div>
    </div>
  )
}

export default LoginPage