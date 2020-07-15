import React from "react";
import styled from "styled-components";
import { Typography } from "@material-ui/core";

const StyledTypography = styled(Typography)`
  margin: 10px auto;
  text-align: center;
`;

export const PageTitle: React.FC = ({ children }) => {
  return <StyledTypography variant="h6">{children}</StyledTypography>;
};
