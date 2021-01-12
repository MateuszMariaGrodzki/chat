import React from "react";
import { Helmet } from "react-helmet";

import { PageTitle } from "@common/PageTitle";
import { Text } from "@common/Text";

const UsersList = () => {
  return (
    <>
      <Helmet>
        <title>Chat App - Users</title>
      </Helmet>
      <PageTitle>Our chatters</PageTitle>
      <Text>...</Text>
    </>
  );
};

export default UsersList;
