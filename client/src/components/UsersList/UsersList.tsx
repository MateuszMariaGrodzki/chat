import React, { useEffect, useState, useCallback } from "react";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";

import { Text } from "@common/Text";
import UsersAPI from "@api/UsersAPI";
import { useStatus } from "@hooks/useStatus";

import { StyledTableContainer } from "./styled";

const UsersList = () => {
  const [users, setUsers] = useState<ListedUser[]>([]);
  const [status, setStatus] = useStatus("pending");

  const fetchUsers = useCallback(async () => {
    const response = await UsersAPI.get();
    if (UsersAPI.validate(response)) {
      setUsers(response.data.data);
      setStatus("success");
    } else {
      // TODO: error while fetching users
      setStatus("error");
    }
  }, []);

  useEffect(() => {
    fetchUsers();
  }, []);

  if (status === "pending") {
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
