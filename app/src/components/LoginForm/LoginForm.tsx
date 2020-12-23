import React, { useState } from "react";
import { Button, Box, Snackbar } from "@material-ui/core";
import { Alert } from "@material-ui/lab";

import { useInput } from "../../hooks";
import { Input, Form } from "../../common";
import API from "../../api";
import { LoginError } from "../../api/types";
import { getErrorText } from "../../api/errorMaps";

const LoginForm = () => {
  const [username, handleUsernameChange] = useInput();
  const [password, handlePasswordChange] = useInput();
  const [status, setStatus] = useState<null | "success" | "error">(null);
  const [error, setError] = useState<Maybe<LoginError>>(null);

  const [userLogin, setUserLogin] = useState<Maybe<string>>(null);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const { errorCode, ...data } = await API.login({ name: username, password });

    if (errorCode !== null) {
      setStatus("error");
      setError(errorCode);
    } else {
      setStatus("success");
      // TODO: fix typing
      // @ts-ignore
      setUserLogin(data.name);
      // TODO: save user data in app state
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
        <Alert severity="success">Logged in successfully!</Alert>
      </Snackbar>
      <Snackbar open={status === "error"} autoHideDuration={3000} onClose={handleSnackbarClose}>
        <Alert severity="error">{getErrorText(error)}</Alert>
      </Snackbar>
    </>
  );
};

export default LoginForm;
