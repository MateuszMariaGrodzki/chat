import React from "react";
import { HashRouter as Router, Switch, Route } from "react-router-dom";
import { Container } from "@material-ui/core";

import { useUserContext } from "@providers/UserProvider";
import { paths } from "@config/paths";
import Header from "@components/Header";
import Register from "@pages/Register";
import Login from "@pages/Login";
import Home from "@pages/Home";
import RegisterSuccess from "@pages/RegisterSuccess";
import Users from "@pages/Users";
import Styleguide from "@pages/Styleguide";

const AppRouter: React.FC = () => {
  const { user } = useUserContext();
  return (
    <Router>
      <Header />
      <Container>
        <Switch>
          <Route exact path={paths.home}>
            <Home />
          </Route>
          <Route path={paths.registerSuccess}>
            <RegisterSuccess />
          </Route>
          <Route path={paths.register}>
            <Register />
          </Route>
          <Route path={paths.login}>
            <Login />
          </Route>
          <Route path={paths.styleguide}>
            <Styleguide />
          </Route>
          {user && (
            <>
              <Route path={paths.users}>
                <Users />
              </Route>
            </>
          )}
        </Switch>
      </Container>
    </Router>
  );
};

export default AppRouter;
