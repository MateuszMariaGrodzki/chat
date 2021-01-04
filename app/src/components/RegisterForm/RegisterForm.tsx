import React, { useState } from "react";
import { Button, Box, Snackbar } from "@material-ui/core";
import { Alert } from "@material-ui/lab";
import { useHistory } from "react-router-dom";

import { Input, Form } from "../../common";
import API from "../../api";
import { RegisterError, isValidRegisterResponse } from "../../api/types";
import { getErrorText } from "../../api/errorMaps";
import { useInput, useStatus } from "../../hooks";
import { paths } from "../../config/paths";

const RegisterForm = () => {
  const [name, handleUsernameChange] = useInput();
  const [email, handleEmailChange] = useInput();
  const [password, handlePasswordChange] = useInput();
  const [status, setStatus] = useStatus();
  const [error, setError] = useState<Maybe<RegisterError>>(null);

  const history = useHistory();

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    setStatus("pending");
    e.preventDefault();
    const response = await API.register({ name, email, password });
    // TODO: save user in app state, when backend does it
    if (isValidRegisterResponse(response)) {
      setStatus("success");
      history.push(paths.registerSuccess);
    } else {
      setStatus("error");
      setError(response.errorCode);
    }
  };

  const handleSnackbarClose = () => {
    setStatus(null);
  };

  return (
    <>
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
      <Snackbar open={status === "error"} autoHideDuration={3000} onClose={handleSnackbarClose}>
        <Alert severity="error">{getErrorText(error)}</Alert>
      </Snackbar>
    </>
  );
};

export default RegisterForm;
