import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Helmet } from "react-helmet";
import { Container } from "@material-ui/core";

import Header from "./Header";
import Register from "./Register";
import Home from "./Home";

const App = () => (
  <>
    <Helmet>
      <title>Chat App</title>
      <link
        rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
      />
    </Helmet>
    <Router>
      <Header />
      <Container>
        <Switch>
          <Route path="/register">
            <Register />
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
