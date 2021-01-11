import React from "react";
import { Helmet } from "react-helmet";

import { PageTitle } from "@common/PageTitle";
import { Text } from "@common/Text";
import LoginForm from "@components/LoginForm";

const RegisterSuccess = () => {
  return (
    <>
      <Helmet>
        <title>Chat App - Register Successfull!</title>
      </Helmet>
      <PageTitle>You have successfully joined the best chatters!</PageTitle>
      <Text>You can log into you account now</Text>
      <LoginForm />
    </>
  );
};

export default RegisterSuccess;
