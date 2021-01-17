import React, { useEffect, useState, useCallback } from "react";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";

import { Text } from "@common/Text";
import API from "@api/Api";
import { isValidUsersResponse } from "@api/types";

import { StyledTableContainer } from "./styled";

const UsersList = () => {
  const [users, setUsers] = useState<ListedUser[]>([]);

  const fetchUsers = useCallback(async () => {
    const response = await API.getUsers();
    if (isValidUsersResponse(response)) {
      setUsers(response.data);
    }
  }, []);

  useEffect(() => {
    fetchUsers();
  }, []);

  if (users.length <= 0) {
    return <Text>Loading users...</Text>;
  }

  return (
    <StyledTableContainer>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>User name</TableCell>
            <TableCell>Slug</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {users.map((user) => (
            <TableRow key={user.name}>
              <TableCell component="th" scope="row">
                {user.name}
              </TableCell>
              <TableCell>{user.slug}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </StyledTableContainer>
  );
};

export default UsersList;
