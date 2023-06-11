import { Button, Chip, Paper, Stack, Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import { DataGrid, GridColDef, GridValueGetterParams } from "@mui/x-data-grid";
import {
  getActiveUserRequest,
  getUsersRequest,
} from "../../api/controllers/user";
import { UserFull } from "../../types/Account";

function Users() {
  const [data, setData] = useState<UserFull[]>([]);

  const columns: GridColDef[] = [
    {
      field: "firstName",
      headerName: "İsim",
      minWidth: 150,
    },
    {
      field: "lastName",
      headerName: "Soyisim",
      minWidth: 150,
    },
    {
      field: "email",
      headerName: "E-Mail",
      minWidth: 150,
    },
    {
      field: "address",
      headerName: "Şehir",
      minWidth: 50,
      valueGetter: ({ value }: GridValueGetterParams) => value?.province || "-",
    },
    {
      field: "isActive",
      headerName: "Durum",
      minWidth: 50,
      renderCell: ({ value }: GridValueGetterParams) => (
        <Chip
          label={value ? "Onaylı" : "Onaysız"}
          color={value ? "success" : "default"}
        />
      ),
    },
    {
      field: "id",
      headerName: "",
      minWidth: 50,
      renderCell: ({ value, row }: GridValueGetterParams) =>
        !row.isActive && (
          <Button
            onClick={() => {
              getActiveUserRequest(value).then((res) => {
                if (res) {
                  getData();
                }
              });
            }}
          >
            Onayla
          </Button>
        ),
    },
  ];

  const getData = () => {
    getUsersRequest().then((res) => {
      if (res) {
        setData(res.data);
      }
    });
  };

  useEffect(() => {
    getData();
  }, []);

  return (
    <Stack spacing={2}>
      <Stack>
        <Typography variant="h4" fontWeight={500}>
          Kullanıcılar
        </Typography>
      </Stack>
      <Paper>
        <DataGrid rows={data} columns={columns} sx={{ minHeight: 400 }} />
      </Paper>
    </Stack>
  );
}

export default Users;
