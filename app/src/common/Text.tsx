import React from "react";
import styled from "styled-components";
import { Typography } from "@material-ui/core";

const StyledTypography = styled(Typography)`
  margin: 10px auto;
  text-align: center;
`;

export const Text: React.FC = ({ children }) => {
  return <StyledTypography>{children}</StyledTypography>;
};
