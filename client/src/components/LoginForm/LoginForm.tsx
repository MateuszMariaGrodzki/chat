import React, { useState } from "react";
import { Button, Box, Snackbar } from "@material-ui/core";
import { Alert } from "@material-ui/lab";
import { useHistory } from "react-router-dom";

import { useInput } from "@hooks/useInput";
import { Input } from "@common/Input";
import { Form } from "@common/Form";
import LoginAPI from "@api/LoginAPI";
import { useUserContext } from "@providers/UserProvider";
import { paths } from "@config/paths";

const LoginForm = () => {
  const [username, handleUsernameChange] = useInput();
  const [password, handlePasswordChange] = useInput();
  const [status, setStatus] = useState<null | "success" | "error">(null);
  const [errors, setErrors] = useState<string[]>([]);

  const { setUser, user } = useUserContext();
  const history = useHistory();

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const response = await LoginAPI.post({ name: username, password });

    if (LoginAPI.validate(response)) {
      const { name, email } = response.data;
      setStatus("success");
      setUser({ name, email });
      history.push(paths.home);
    } else {
      setStatus("error");
      setErrors(response.errors.map((error) => error.title));
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
        <Alert severity="success">Logged in as {user?.name}!</Alert>
      </Snackbar>
      {errors.map((error) => (
        <Snackbar open={status === "error"} autoHideDuration={3000} onClose={handleSnackbarClose} key={error}>
          <Alert severity="error">{error}</Alert>
        </Snackbar>
      ))}
    </>
  );
};

export default LoginForm;
