import React from "react";
import { Link, LinkProps } from "react-router-dom";
import { AppBar, Toolbar, IconButton, Typography, Button } from "@material-ui/core";
import styled from "styled-components";

const StyledLink = styled(Link)`
  margin-left: 10px;
`;

const Header = () => {
  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6">ChatApp</Typography>
        <Button component={StyledLink} to="/">
          Home
        </Button>
        <Button component={StyledLink} to="/register">
          Register
        </Button>
      </Toolbar>
    </AppBar>
  );
};

export default Header;
