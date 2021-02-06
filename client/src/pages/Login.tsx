import React from "react";
import { Helmet } from "react-helmet";

import { PageTitle } from "@common/PageTitle";
import LoginForm from "@components/LoginForm";

const Login = () => {
  return (
    <>
      <Helmet>
        <title>Chat App - Login</title>
      </Helmet>
      <PageTitle>Log in to your chatter account</PageTitle>
      <LoginForm />
    </>
  );
};

export default Login;
