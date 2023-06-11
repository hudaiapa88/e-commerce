import {
  Autocomplete,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Paper,
  Stack,
  TextField,
  Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import { DataGrid, GridColDef, GridValueGetterParams } from "@mui/x-data-grid";
import { Category, Product } from "../../types/Product";
import {
  createProductRequest,
  getProductsRequest,
} from "../../api/controllers/product";
import {
  createCategoryRequest,
  getCategorysRequest,
} from "../../api/controllers/category";
import { uploadPhotoRequest } from "../../api/controllers/photo";
import { toast } from "react-toastify";

function Products() {
  const [data, setData] = useState<Product[]>([]);
  const [categories, setCategories] = useState<Category[]>([]);
  const [image, setImage] = useState(null);

  const [createShow, setCreateShow] = useState(false);
  const [createForm, setCreateForm] = useState({
    categoryId: null,
    price: null,
    quantity: null,
    title: "",
  });

  const handleCreateClose = () => {
    setCreateShow(false);
    setCreateForm({
      categoryId: null,
      price: null,
      quantity: null,
      title: "",
    });
  };

  const handleCreateSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    createProductRequest(createForm).then((res) => {
      if (res) {
        if (image) {
          const formData = new FormData();
          formData.append("data", image);
          uploadPhotoRequest(res.data.id, formData);
        }
        handleCreateClose();
        toast.success("Ürün eklendi");
        getData();
      }
    });
  };

  const [createCategoryShow, setCreateCategoryShow] = useState(false);
  const [createCategoryForm, setCreateCategoryForm] = useState({
    title: "",
  });

  const handleCreateCategoryClose = () => {
    setCreateCategoryShow(false);
    setCreateCategoryForm({
      title: "",
    });
  };

  const handleCreateCategorySubmit = (e: React.FormEvent) => {
    e.preventDefault();
    createCategoryRequest(createCategoryForm).then((res) => {
      if (res) {
        handleCreateCategoryClose();
        toast.success("Kategori eklendi");
        getCategories();
      }
    });
  };

  const columns: GridColDef[] = [
    {
      field: "title",
      headerName: "İsim",
      width: 200,
    },
    {
      field: "quantity",
      headerName: "Miktar",
      type: "number",
      width: 110,
    },
    {
      field: "price",
      headerName: "Fiyat",
      type: "number",
      width: 110,
    },
    {
      field: "category",
      headerName: "Kategori",
      width: 200,
      valueGetter: ({ value }: GridValueGetterParams) => value?.title || "-",
    },
  ];

  const getData = () => {
    getProductsRequest().then((res) => {
      if (res) {
        setData(res.data);
      }
    });
  };

  const getCategories = () => {
    getCategorysRequest().then((res) => {
      if (res) {
        setCategories(res.data);
      }
    });
  };

  useEffect(() => {
    getData();
    getCategories();
  }, []);

  return (
    <>
      <Stack spacing={2}>
        <Stack direction="row" justifyContent="space-between">
          <Typography variant="h4" fontWeight={500}>
            Ürünler
          </Typography>
          <Stack direction="row" spacing={1}>
            <Button
              onClick={() => setCreateCategoryShow(true)}
              variant="outlined"
            >
              Kategori Ekle
            </Button>
            <Button onClick={() => setCreateShow(true)} variant="contained">
              Yeni Ekle
            </Button>
          </Stack>
        </Stack>
        <Paper>
          <DataGrid rows={data} columns={columns} sx={{ minHeight: 400 }} />
        </Paper>
      </Stack>
      <Dialog open={createShow} onClose={handleCreateClose}>
        <form onSubmit={handleCreateSubmit}>
          <DialogTitle>Ürün Ekle</DialogTitle>
          <DialogContent>
            <Stack spacing={3}>
              <TextField
                label="İsim"
                value={createForm.title}
                onChange={(e) =>
                  setCreateForm((prev) => ({
                    ...prev,
                    title: e.target.value,
                  }))
                }
                required
              />
              <TextField
                label="Fiyat"
                value={createForm.price}
                onChange={(e) =>
                  setCreateForm((prev) => ({
                    ...prev,
                    price: Number(e.target.value),
                  }))
                }
                inputProps={{
                  min: 0,
                }}
                type="number"
                required
              />
              <TextField
                label="Adet"
                value={createForm.quantity}
                onChange={(e) =>
                  setCreateForm((prev) => ({
                    ...prev,
                    quantity: Number(e.target.value),
                  }))
                }
                inputProps={{
                  min: 0,
                }}
                type="number"
                required
              />
              <Autocomplete
                disablePortal
                options={categories}
                value={categories.find((v) => v.id === createForm.categoryId)}
                onChange={(event, newValue) => {
                  setCreateForm((prev) => ({
                    ...prev,
                    categoryId: Number(newValue.id),
                  }));
                }}
                getOptionLabel={(option) => option.title}
                renderInput={(params) => (
                  <TextField {...params} label="Kategori" required />
                )}
              />
              <TextField
                label="Ürün Resmi"
                type="file"
                onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                  setImage(e.target.files[0])
                }
                inputProps={{
                  accept: "image/*",
                }}
                required
              />
            </Stack>
          </DialogContent>
          <DialogActions>
            <Button type="button" onClick={handleCreateClose}>
              İptal et
            </Button>
            <Button type="submit">Kaydet</Button>
          </DialogActions>
        </form>
      </Dialog>
      <Dialog open={createCategoryShow} onClose={handleCreateCategoryClose}>
        <form onSubmit={handleCreateCategorySubmit}>
          <DialogTitle>Kategori Ekle</DialogTitle>
          <DialogContent>
            <TextField
              label="İsim"
              value={createCategoryForm.title}
              onChange={(e) =>
                setCreateCategoryForm((prev) => ({
                  ...prev,
                  title: e.target.value,
                }))
              }
            />
          </DialogContent>
          <DialogActions>
            <Button type="button" onClick={handleCreateCategoryClose}>
              İptal et
            </Button>
            <Button type="submit">Kaydet</Button>
          </DialogActions>
        </form>
      </Dialog>
    </>
  );
}

export default Products;
