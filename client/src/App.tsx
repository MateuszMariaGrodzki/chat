import React from "react";
import { Helmet } from "react-helmet";
import { CssBaseline, ThemeProvider as MUIThemeProvider } from "@material-ui/core";
import { SnackbarProvider } from "notistack";
import { ThemeProvider } from "styled-components";

import AppRouter from "@components/Router";
import { UserContextProvider } from "@providers/UserProvider";
import theme from "@config/theme";

const App = () => (
  <>
    <Helmet>
      <title>Chat App</title>
      <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
      <meta name="viewport" content="minimum-scale=1, initial-scale=1, width=device-width" />
    </Helmet>
    <CssBaseline />
    <UserContextProvider>
      <MUIThemeProvider theme={theme}>
        <ThemeProvider theme={theme}>
          <SnackbarProvider variant="error" maxSnack={5}>
            <AppRouter />
          </SnackbarProvider>
        </ThemeProvider>
      </MUIThemeProvider>
    </UserContextProvider>
  </>
);

export default App;
