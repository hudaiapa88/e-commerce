import React from "react";
import { Product } from "../../../types/Product";
import {
  Button,
  Card,
  CardActions,
  CardContent,
  CardMedia,
  Tooltip,
  Typography,
} from "@mui/material";
import { Link } from "react-router-dom";
import { useAppDispatch } from "../../../redux/store";
import { addToCart } from "../../../redux/slices/cart";
import { BASE_URL } from "../../../api/ApiProvider";
import { useTranslation } from "react-i18next";

function ProductCard(props: Product) {
  const { t } = useTranslation();
  const distpatch = useAppDispatch();
  return (
    <Card
      sx={{
        height: "100%",
      }}
    >
      <CardMedia
        sx={{ aspectRatio: "1" }}
        image={`${BASE_URL}/photo/product/${props.id}`}
        title={props.title}
      />
      <CardContent>
        <Typography color="primary" fontWeight={500}>
          {props.price + " ₺"}
        </Typography>
        <Tooltip title={props.title}>
          <Link
            to={`/product/${props.id}`}
            style={{ color: "inherit", textDecoration: "none" }}
          >
            <Typography
              sx={{
                whiteSpace: "nowrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
                textDecoration: "none",
                fontWeight: 500,
              }}
            >
              {props.title}
            </Typography>
          </Link>
        </Tooltip>
      </CardContent>
      <CardActions>
        <Button
          onClick={() => distpatch(addToCart(props))}
          variant="contained"
          fullWidth
          size="small"
        >
          {t("Add to Cart")}
        </Button>
      </CardActions>
    </Card>
  );
}

export default ProductCard;
