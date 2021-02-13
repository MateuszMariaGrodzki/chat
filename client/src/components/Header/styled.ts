import styled from "styled-components";
import { Button, Typography } from "@material-ui/core";

export const MenuItem = styled(Button)`
  color: ${(props) => props.theme.palette.primary.contrastText};
` as typeof Button;

export const Logo = styled(MenuItem)`
  font-size: ${(props) => props.theme.typography.h6.fontSize};
  text-transform: none;
` as typeof MenuItem;
