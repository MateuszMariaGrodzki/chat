import React from "react";
import { AppBar, Toolbar, Typography, Button } from "@material-ui/core";

import { StyledLink } from "./styled";

const Header = () => {
  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6">ChatApp</Typography>
        <Button component={StyledLink} to="/" color="secondary">
          Home
        </Button>
        <Button component={StyledLink} to="/register" color="primary">
          Register
        </Button>
        <Button component={StyledLink} to="/login">
          Login
        </Button>
      </Toolbar>
    </AppBar>
  );
};

export default Header;
