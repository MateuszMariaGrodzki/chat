import React from "react";
import { Helmet } from "react-helmet";
import { CssBaseline } from "@material-ui/core";

import AppRouter from "@components/Router";
import { UserContextProvider } from "@providers/UserProvider";

const App = () => (
  <>
    <Helmet>
      <title>Chat App</title>
      <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
    </Helmet>
    <CssBaseline />
    <UserContextProvider>
      <AppRouter />
    </UserContextProvider>
  </>
);

export default App;
