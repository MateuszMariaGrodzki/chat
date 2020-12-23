import React from "react";
import { HashRouter as Router, Switch, Route } from "react-router-dom";
import { Helmet } from "react-helmet";
import { Container, CssBaseline } from "@material-ui/core";

import { paths } from "./config/paths";
import Header from "./components/Header";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Home from "./pages/Home";
import RegisterSuccess from "./pages/RegisterSuccess";

const App = () => (
  <>
    <Helmet>
      <title>Chat App</title>
      <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
    </Helmet>
    <CssBaseline />
    <Router>
      <Header />
      <Container>
        <Switch>
          <Route path={paths.registerSuccess}>
            <RegisterSuccess />
          </Route>
          <Route path={paths.register}>
            <Register />
          </Route>
          <Route path={paths.login}>
            <Login />
          </Route>
          <Route path={paths.home}>
            <Home />
          </Route>
        </Switch>
      </Container>
    </Router>
  </>
);

export default App;
