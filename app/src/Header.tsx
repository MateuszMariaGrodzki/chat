import React from "react";
import { Link } from "react-router-dom";
import { Container } from "@material-ui/core";

const Header = () => {
  return (
    <Container>
      <header>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/register">Register</Link>
        </li>
      </header>
    </Container>
  );
};

export default Header;
