import React, { useState } from "react";
import { Helmet } from "react-helmet";
import axios from "axios";
import { Button, Box, Snackbar } from "@material-ui/core";
import { Alert } from "@material-ui/lab";

import useInput from "../hooks";
import { PageTitle, Input, Form } from "../common";

const Register = () => {
  const [name, handleUsernameChange] = useInput();
  const [email, handleEmailChange] = useInput();
  const [password, handlePasswordChange] = useInput();
  const [status, setStatus] = useState<null | "success" | "error">(null);

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/register", {
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
        <Input
          placeholder="Username"
          type="text"
          name="name"
          id="username"
          onChange={handleUsernameChange}
          value={name}
          fullWidth
        />
        <Input
          placeholder="E-mail address"
          type="email"
          name="email"
          id="email"
          onChange={handleEmailChange}
          value={email}
          fullWidth
        />
        <Input
          placeholder="Password"
          type="password"
          name="pwd"
          id="pwd"
          onChange={handlePasswordChange}
          value={password}
          fullWidth
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
