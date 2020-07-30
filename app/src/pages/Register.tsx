import React, { useState } from "react";
import { Helmet } from "react-helmet";
import { Button, Box, Snackbar } from "@material-ui/core";
import { Alert } from "@material-ui/lab";

import API from "../api";
import { PageTitle, Input, Form } from "../common";
import { RegisterError } from "../api/types";
import { registerFeedback } from "../api/errorMaps";
import { useInput, useStatus } from "../hooks";

const Register = () => {
  const [name, handleUsernameChange] = useInput();
  const [email, handleEmailChange] = useInput();
  const [password, handlePasswordChange] = useInput();
  const [status, setStatus] = useStatus();
  const [error, setError] = useState<Maybe<RegisterError>>(null);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    setStatus("pending");
    e.preventDefault();
    const { errorCode } = await API.register({ name, email, password });
    if (errorCode === null) {
      setStatus("success");
    } else {
      setStatus("error");
      setError(errorCode);
    }
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
        <Input placeholder="Username" type="text" name="name" id="username" onChange={handleUsernameChange} value={name} fullWidth />
        <Input placeholder="E-mail address" type="email" name="email" id="email" onChange={handleEmailChange} value={email} fullWidth />
        <Input placeholder="Password" type="password" name="pwd" id="pwd" onChange={handlePasswordChange} value={password} fullWidth />
        <Box mt={2} display="flex" justifyContent="center">
          <Button variant="contained" size="large" type="submit" color="primary" disabled={status === "pending"}>
            Submit
          </Button>
        </Box>
      </Form>
      <Snackbar open={status === "success"} autoHideDuration={3000} onClose={handleSnackbarClose}>
        <Alert severity="success">Registered successfully!</Alert>
      </Snackbar>
      <Snackbar open={status === "error"} autoHideDuration={3000} onClose={handleSnackbarClose}>
        <Alert severity="error">{registerFeedback[error]}</Alert>
      </Snackbar>
    </>
  );
};

export default Register;
