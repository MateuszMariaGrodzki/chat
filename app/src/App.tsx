import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Header from "./Header";
import Register from "./Register";
import Home from "./Home";

const App = () => (
  <Router>
    <Header />
    <Switch>
      <Route path="/register">
        <Register />
      </Route>
      <Route path="/">
        <Home />
      </Route>
    </Switch>
  </Router>
);

export default App;
