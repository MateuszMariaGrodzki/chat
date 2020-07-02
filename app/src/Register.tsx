import React, { useState } from "react";
import { Helmet } from "react-helmet";
import axios from "axios";
import styled from "styled-components";
import { Input, Button, Box, Snackbar } from "@material-ui/core";
import { Alert } from "@material-ui/lab";

import PageTitle from "./common/PageTitle";

const Form = styled.form`
  max-width: 400px;
  margin: 0 auto;
`;

const StyledInput = styled(Input)`
  margin-top: 10px;
`;

const Register = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [status, setStatus] = useState<null | "success" | "error">(null);

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
      .post("http://localhost:2222/api/register", {
        name,
        email,
        password,
      })
      .then((response) => {
        if (response.status === 200) {
          // TODO: check also backend response when provided; handle errors
          setStatus("success");
        } else {
          setStatus("error");
        }
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
        setStatus("error");
      });
  };

  const handleSnackbarClose = () => {
    setStatus(null);
  };

  return (
    <>
      <Helmet>
        <title>Chat App - Register</title>
      </Helmet>
      <PageTitle>Join the best chatters!</PageTitle>
      <Form onSubmit={handleSubmit}>
        <StyledInput
          placeholder="Name"
          type="text"
          name="name"
          id="name"
          onChange={handleNameChange}
          value={name}
          fullWidth
          required
        />
        <StyledInput
          placeholder="E-mail address"
          type="email"
          name="email"
          id="email"
          onChange={handleEmailChange}
          value={email}
          fullWidth
          required
        />
        <StyledInput
          placeholder="Password"
          type="password"
          name="pwd"
          id="pwd"
          onChange={handlePasswordChange}
          value={password}
          fullWidth
          required
        />
        <Box mt={2} display="flex" justifyContent="center">
          <Button variant="contained" size="large" type="submit" color="primary">
            Submit
          </Button>
        </Box>
      </Form>
      <Snackbar open={status === "success"} autoHideDuration={3000} onClose={handleSnackbarClose}>
        <Alert severity="success">Registered successfully!</Alert>
      </Snackbar>
      <Snackbar open={status === "error"} autoHideDuration={3000} onClose={handleSnackbarClose}>
        <Alert severity="error">Oops! Something went wrong...</Alert>
      </Snackbar>
    </>
  );
};

export default Register;
