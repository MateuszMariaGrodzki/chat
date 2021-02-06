import React from "react";
import { Button, Box } from "@material-ui/core";
import { useHistory } from "react-router-dom";
import { useSnackbar } from "notistack";

import RegisterAPI from "@api/RegisterAPI";
import { Input } from "@common/Input";
import { Form } from "@common/Form";
import { useInput } from "@hooks/useInput";
import { useStatus } from "@hooks/useStatus";
import { paths } from "@config/paths";

const RegisterForm = () => {
  const [status, setStatus] = useStatus();
  const resetFormStatus = () => {
    setStatus(null);
  };

  const [name, handleUsernameChange] = useInput(resetFormStatus);
  const [email, handleEmailChange] = useInput(resetFormStatus);
  const [password, handlePasswordChange] = useInput(resetFormStatus);
  const history = useHistory();
  const { enqueueSnackbar } = useSnackbar();

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
      response.errors.forEach((error) => {
        enqueueSnackbar(error.title);
      });
    }
  };

  return (
    <>
      <Form onSubmit={handleSubmit}>
        <Input placeholder="Username" type="text" name="name" id="username" onChange={handleUsernameChange} value={name} fullWidth />
        <Input placeholder="E-mail address" type="email" name="email" id="email" onChange={handleEmailChange} value={email} fullWidth />
        <Input placeholder="Password" type="password" name="pwd" id="pwd" onChange={handlePasswordChange} value={password} fullWidth />
        <Box mt={2} display="flex" justifyContent="center">
          <Button variant="contained" size="large" type="submit" color="primary" disabled={status === "pending" || status === "error"}>
            Submit
          </Button>
        </Box>
      </Form>
    </>
  );
};

export default RegisterForm;
