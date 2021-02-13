import React from "react";
import { AppBar, Toolbar, Button } from "@material-ui/core";
import { Link } from "react-router-dom";

import { useUserContext } from "@providers/UserProvider";
import { paths } from "@config/paths";

import { MenuItem, Logo } from "./styled";

const Header = () => {
  const { user, logout } = useUserContext();
  const isUnfetched = user === undefined;
  const isGuest = user === null;
  const isLoggedIn = Boolean(user);
  return (
    <AppBar position="static">
      <Toolbar>
        <Logo component={Link} to="/" color="primary">
          ChatApp
        </Logo>
        {isUnfetched && "Loading..."}
        {isGuest && (
          <>
            <MenuItem component={Link} to={paths.register} color="primary">
              Register
            </MenuItem>
            <MenuItem component={Link} to={paths.login} color="primary">
              Login
            </MenuItem>
          </>
        )}
        {isLoggedIn && (
          <>
            <MenuItem component={Link} to={paths.users} color="primary">
              Users
            </MenuItem>
            <MenuItem onClick={logout} color="primary">
              Logout
            </MenuItem>
          </>
        )}
      </Toolbar>
    </AppBar>
  );
};

export default Header;
