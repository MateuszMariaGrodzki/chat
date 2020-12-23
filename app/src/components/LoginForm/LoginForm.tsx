import React, { useState } from "react";
import { Button, Box, Snackbar } from "@material-ui/core";
import { Alert } from "@material-ui/lab";

import { useInput } from "../../hooks";
import { Input, Form } from "../../common";
import API from "../../api";
import { LoginError, isValidLoginResponse } from "../../api/types";
import { getErrorText } from "../../api/errorMaps";

const LoginForm = () => {
  const [username, handleUsernameChange] = useInput();
  const [password, handlePasswordChange] = useInput();
  const [status, setStatus] = useState<null | "success" | "error">(null);
  const [error, setError] = useState<Maybe<LoginError>>(null);

  // temporary - will be replaced with global app state
  const [userLogin, setUserLogin] = useState<Maybe<string>>(null);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const response = await API.login({ name: username, password });

    if (isValidLoginResponse(response)) {
      setStatus("success");
      setUserLogin(response.name);
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
        <Input placeholder="Username" type="text" name="username" id="username" onChange={handleUsernameChange} value={username} fullWidth />
        <Input placeholder="Password" type="password" name="pwd" id="pwd" onChange={handlePasswordChange} value={password} fullWidth />
        <Box mt={2} display="flex" justifyContent="center">
          <Button variant="contained" size="large" type="submit" color="primary">
            Submit
          </Button>
        </Box>
      </Form>
      <Snackbar open={status === "success"} autoHideDuration={3000} onClose={handleSnackbarClose}>
        <Alert severity="success">Logged in as {userLogin}!</Alert>
      </Snackbar>
      <Snackbar open={status === "error"} autoHideDuration={3000} onClose={handleSnackbarClose}>
        <Alert severity="error">{getErrorText(error)}</Alert>
      </Snackbar>
    </>
  );
};

export default LoginForm;
