import React from "react";
import { Helmet } from "react-helmet";

import { PageTitle } from "@common/PageTitle";
import UsersList from "@components/UsersList";

const Users = () => {
  return (
    <>
      <Helmet>
        <title>Chat App - Users</title>
      </Helmet>
      <PageTitle>Our chatters</PageTitle>
      <UsersList />
    </>
  );
};

export default Users;
