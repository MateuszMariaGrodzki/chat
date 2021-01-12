import React from "react";
import { AppBar, Toolbar, Typography, Button } from "@material-ui/core";

import { useUserContext } from "@providers/UserProvider";
import { paths } from "@config/paths";

import { StyledLink } from "./styled";

const Header = () => {
  const { user } = useUserContext();
  const isUnfetched = user === undefined;
  const isGuest = user === null;
  const isLoggedIn = Boolean(user);
  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6">ChatApp</Typography>
        <Button component={StyledLink} to="/" color="secondary">
          Home
        </Button>
        {isUnfetched && "Loading..."}
        {isGuest && (
          <>
            <Button component={StyledLink} to={paths.register} color="primary">
              Register
            </Button>
            <Button component={StyledLink} to={paths.login}>
              Login
            </Button>
          </>
        )}
        {isLoggedIn && (
          <>
            <Button component={StyledLink} to={paths.users} color="primary">
              Users
            </Button>
            <Button color="secondary">Logout</Button>
          </>
        )}
      </Toolbar>
    </AppBar>
  );
};

export default Header;
