import React, { useState } from "react";
import { Helmet } from "react-helmet";
import axios from "axios";

const Register = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setName(e.target.value);
  };

  const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/register", {
        name,
        email,
        password,
      })
      .then(function (response) {
        console.log(response);
      });
  };
  return (
    <>
      <Helmet>
        <title>Chat App - Register</title>
      </Helmet>
      <div>Register page</div>
      <form onSubmit={handleSubmit}>
        <input
          placeholder="Name"
          type="text"
          name="name"
          id="name"
          onChange={handleNameChange}
          value={name}
        />
        <input
          placeholder="E-mail address"
          type="email"
          name="email"
          id="email"
          onChange={handleEmailChange}
          value={email}
        />
        <input
          placeholder="Password"
          type="password"
          name="pwd"
          id="pwd"
          onChange={handlePasswordChange}
          value={password}
        />
        <button type="submit">Submit</button>
      </form>
    </>
  );
};

export default Register;
