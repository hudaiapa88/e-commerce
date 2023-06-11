import { Button, Paper, Stack, Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import {
  DataGrid,
  GridColDef,
  GridRenderCellParams,
  GridValueGetterParams,
} from "@mui/x-data-grid";
import {
  getOrdersRequest,
  updateOrderShipRequest,
} from "../../api/controllers/order";
import { Order } from "../../types/Order";
import { toast } from "react-toastify";

function Orders() {
  const [data, setData] = useState<Order[]>([]);

  const columns: GridColDef[] = [
    {
      field: "id",
      headerName: "ID",
      width: 50,
    },
    {
      field: "user",
      headerName: "Müşteri",
      type: "number",
      width: 200,
      valueGetter: ({ value }: GridValueGetterParams) =>
        value?.firstName + " " + value?.lastName,
    },
    {
      field: "totalPrice",
      headerName: "Fiyat",
      type: "number",
      width: 200,
    },
    {
      field: "",
      headerName: "",
      type: "number",
      width: 200,
      renderCell: ({ row }: GridRenderCellParams) => (
        <Button
          variant="outlined"
          onClick={() => {
            updateOrderShipRequest(row.id).then((res) => {
              if (res) {
                getData();
                toast.success("Kargolandı");
              }
            });
          }}
        >
          Kargola
        </Button>
      ),
    },
  ];

  const getData = () => {
    getOrdersRequest().then((res) => {
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
          Siparişler
        </Typography>
      </Stack>
      <Paper>
        <DataGrid rows={data} columns={columns} sx={{ minHeight: 400 }} />
      </Paper>
    </Stack>
  );
}

export default Orders;
