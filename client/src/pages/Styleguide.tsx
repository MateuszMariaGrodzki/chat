import React from "react";

import { PageTitle } from "@common/PageTitle";
import { Box, Button } from "@material-ui/core";

const Styleguide = () => {
  return (
    <>
      <PageTitle>Styleguide</PageTitle>
      <Box display="flex" flexDirection="column" alignItems="center">
        <Button color="primary">Primary</Button>
        <Button color="primary" variant="contained">
          Primary contained
        </Button>
        <Button color="primary" variant="outlined">
          Primary outlined
        </Button>
        <Button color="primary" variant="text">
          Primary text
        </Button>
        <Button color="secondary">Secondary</Button>
        <Button color="primary" variant="contained">
          Secondary contained
        </Button>
        <Button color="secondary" variant="outlined">
          Secondary outlined
        </Button>
        <Button color="secondary" variant="text">
          Secondary text
        </Button>
      </Box>
    </>
  );
};

export default Styleguide;
