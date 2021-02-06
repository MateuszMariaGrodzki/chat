import React, { useState } from "react";
import { Button, Box, Snackbar } from "@material-ui/core";
import { Alert } from "@material-ui/lab";
import { useHistory } from "react-router-dom";

import { Input } from "@common/Input";
import { Form } from "@common/Form";
import { useInput } from "@hooks/useInput";
import { useStatus } from "@hooks/useStatus";
import { paths } from "@config/paths";
import RegisterAPI from "@api/RegisterAPI";

const RegisterForm = () => {
  const [name, handleUsernameChange] = useInput();
  const [email, handleEmailChange] = useInput();
  const [password, handlePasswordChange] = useInput();
  const [status, setStatus] = useStatus();
  const [errors, setErrors] = useState<string[]>([]);

  const history = useHistory();

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    setStatus("pending");
    e.preventDefault();
    const response = await RegisterAPI.post({ name, email, password });
    // TODO: save user in app state, when backend does it
    if (RegisterAPI.validate(response)) {
      setStatus("success");
      history.push(paths.registerSuccess);
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
        <Input placeholder="Username" type="text" name="name" id="username" onChange={handleUsernameChange} value={name} fullWidth />
        <Input placeholder="E-mail address" type="email" name="email" id="email" onChange={handleEmailChange} value={email} fullWidth />
        <Input placeholder="Password" type="password" name="pwd" id="pwd" onChange={handlePasswordChange} value={password} fullWidth />
        <Box mt={2} display="flex" justifyContent="center">
          <Button variant="contained" size="large" type="submit" color="primary" disabled={status === "pending"}>
            Submit
          </Button>
        </Box>
      </Form>
      {errors.map((error) => (
        <Snackbar open={status === "error"} autoHideDuration={3000} onClose={handleSnackbarClose} key={error}>
          <Alert severity="error">{error}</Alert>
        </Snackbar>
      ))}
    </>
  );
};

export default RegisterForm;
