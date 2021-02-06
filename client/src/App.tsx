import React from "react";
import { Helmet } from "react-helmet";
import { CssBaseline } from "@material-ui/core";
import { SnackbarProvider } from "notistack";

import AppRouter from "@components/Router";
import { UserContextProvider } from "@providers/UserProvider";

const App = () => (
  <>
    <Helmet>
      <title>Chat App</title>
      <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
      <meta name="viewport" content="minimum-scale=1, initial-scale=1, width=device-width" />
    </Helmet>
    <CssBaseline />
    <UserContextProvider>
      <SnackbarProvider variant="error">
        <AppRouter />
      </SnackbarProvider>
    </UserContextProvider>
  </>
);

export default App;
