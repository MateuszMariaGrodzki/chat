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
import UsersList from "@pages/UsersList";

const AppRouter: React.FC = () => {
  const { user } = useUserContext();
  return (
    <Router>
      <Header />
      <Container>
        <Switch>
          {user && (
            <>
              <Route path={paths.users}>
                <UsersList />
              </Route>
            </>
          )}
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
  );
};

export default AppRouter;
