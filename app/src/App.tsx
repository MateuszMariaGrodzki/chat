import React from "react";
import { HashRouter as Router, Switch, Route } from "react-router-dom";
import { Helmet } from "react-helmet";
import { Container, CssBaseline } from "@material-ui/core";

import Header from "./Header";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Home from "./pages/Home";

const App = () => (
  <>
    <Helmet>
      <title>Chat App</title>
      <link
        rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
      />
    </Helmet>
    <CssBaseline />
    <Router>
      <Header />
      <Container>
        <Switch>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
      </Container>
    </Router>
  </>
);

export default App;
