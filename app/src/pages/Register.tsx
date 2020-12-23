import React from "react";
import { Helmet } from "react-helmet";

import { PageTitle } from "../common";
import RegisterForm from "../components/RegisterForm";

const Register = () => {
  return (
    <>
      <Helmet>
        <title>Chat App - Register</title>
      </Helmet>
      <PageTitle>Join the best chatters!</PageTitle>
      <RegisterForm />
    </>
  );
};

export default Register;
