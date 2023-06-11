import * as React from "react";
import Box from "@mui/material/Box";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { useTranslation } from "react-i18next";
import {
  Avatar,
  Checkbox,
  Divider,
  FormControlLabel,
  FormGroup,
  Grid,
  List,
  ListItem,
  ListItemAvatar,
  ListItemText,
  Paper,
  Stack,
  TextField,
} from "@mui/material";
import { useAppSelector } from "../../redux/store";
import { BASE_URL } from "../../api/ApiProvider";
import { getUserRequest } from "../../api/controllers/user";
import { useAuth } from "../../context/Auth";
import { UserFull } from "../../types/Account";
import { createOrderRequest } from "../../api/controllers/order";
import { PatternFormat } from "react-number-format";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { clearCart } from "../../redux/slices/cart";

function Checkout() {
  const { t } = useTranslation();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { currentUser } = useAuth();
  const steps = [t("Cart Detail"), t("Payment")];
  const cart = useAppSelector((state) => {
    return state.cart.items;
  });
  const total = cart.reduce((a, b) => Number(a) + Number(b.price), 0);
  const noDuplicate = cart.filter(
    (value, index, self) => index === self.findIndex((t) => t.id === value.id)
  );
  const [isSave, setIsSave] = React.useState(true);
  const [activeStep, setActiveStep] = React.useState(0);
  const [card, setCard] = React.useState({
    cvv2: "",
    date: "",
    no: "",
  });
  const [user, setUser] = React.useState<UserFull | null>(null);

  const handleNext = () => {
    if (activeStep === 1) {
      createOrderRequest(isSave, {
        address: user.address,
        orderLines: noDuplicate.map((v) => ({
          productId: v.id,
          quantity: cart.filter((c) => c.id === v.id).length,
        })),
        saveCreditCardRequest: card,
      }).then((res) => {
        if (res) {
          toast.success(t("Order is successfull"));
          navigate("/");
          dispatch(clearCart());
        }
      });
    } else if (activeStep === 0) {
      setActiveStep((prevActiveStep) => prevActiveStep + 1);
    }
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  React.useEffect(() => {
    if (currentUser?.id) {
      getUserRequest(currentUser?.id).then((res) => setUser(res.data));
    }
  }, []);

  return (
    <Box>
      <Stepper activeStep={activeStep}>
        {steps.map((label) => {
          const stepProps: { completed?: boolean } = {};
          const labelProps: {
            optional?: React.ReactNode;
          } = {};

          return (
            <Step key={label} {...stepProps}>
              <StepLabel {...labelProps}>{label}</StepLabel>
            </Step>
          );
        })}
      </Stepper>
      <React.Fragment>
        <Grid container sx={{ mt: 2 }} spacing={4}>
          <Grid item xs={12} md={6}>
            <Paper sx={{ p: 4 }}>
              <Stack spacing={2}>
                <Typography fontWeight={600}>{t("Order Detail")}</Typography>
                <Stack>
                  <Typography variant="overline">{t("Total Price")}</Typography>
                  <Typography>
                    {new Intl.NumberFormat("tr-TR", {
                      style: "currency",
                      currency: "TRY",
                    }).format(total || 0)}
                  </Typography>
                </Stack>
                {user?.address && (
                  <>
                    <Divider />
                    <Stack>
                      <Typography variant="overline">
                        {t("First Name") + " " + t("Last Name")}
                      </Typography>
                      <Typography>
                        {user.firstName + " " + user.lastName}
                      </Typography>
                    </Stack>
                    <Divider />
                    <Stack>
                      <Typography variant="overline">{t("Address")}</Typography>
                      <Typography>
                        {user.address.fullAddress +
                          " " +
                          user.address.district +
                          "/" +
                          user.address.province}
                      </Typography>
                    </Stack>
                  </>
                )}
              </Stack>
            </Paper>
          </Grid>
          <Grid item xs={12} md={6}>
            {activeStep === 0 && cart && (
              <Paper sx={{ p: 4 }}>
                <Stack spacing={2}>
                  <Typography fontWeight={600}>{t("Products")}</Typography>
                  <List
                    sx={{
                      width: "100%",
                      bgcolor: "background.paper",
                    }}
                  >
                    {noDuplicate.map((v, i) => (
                      <ListItem key={i}>
                        <ListItemAvatar>
                          <Avatar src={`${BASE_URL}/photo/product/${v.id}`} />
                        </ListItemAvatar>
                        <ListItemText primary={v.title} secondary={v.price} />
                      </ListItem>
                    ))}
                  </List>
                </Stack>
              </Paper>
            )}
            {activeStep === 1 && cart && (
              <Paper sx={{ p: 4 }}>
                <Stack spacing={2}>
                  <Typography fontWeight={600}>
                    {t("Payment Detail")}
                  </Typography>
                  <PatternFormat
                    customInput={TextField}
                    format="#### #### #### ####"
                    mask="_"
                    label={t("Card Number")}
                    value={card.no}
                    onValueChange={({ value }) =>
                      setCard((prev) => ({ ...prev, no: value }))
                    }
                    fullWidth
                    placeholder="XXXX XXXX XXXX XXXX"
                  />
                  <PatternFormat
                    customInput={TextField}
                    format="###"
                    mask="_"
                    label="CVV"
                    value={card.cvv2}
                    onValueChange={({ value }) =>
                      setCard((prev) => ({ ...prev, cvv2: value }))
                    }
                    fullWidth
                    placeholder="XXX"
                  />
                  <PatternFormat
                    customInput={TextField}
                    format="##/##"
                    mask="_"
                    label={t("Last Date")}
                    value={card.date}
                    onValueChange={({ formattedValue }) =>
                      setCard((prev) => ({ ...prev, date: formattedValue }))
                    }
                    fullWidth
                    placeholder="XX/XX"
                  />
                  <FormGroup>
                    <FormControlLabel
                      control={
                        <Checkbox
                          value={isSave}
                          onChange={(e) => setIsSave(e.target.checked)}
                        />
                      }
                      label={t("Save Card")}
                    />
                  </FormGroup>
                </Stack>
              </Paper>
            )}
          </Grid>
        </Grid>

        <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
          <Button
            color="inherit"
            disabled={activeStep === 0}
            onClick={handleBack}
            sx={{ mr: 1 }}
          >
            {t("Back")}
          </Button>
          <Box sx={{ flex: "1 1 auto" }} />
          <Button
            disabled={activeStep === 1 && Object.values(card).includes("")}
            variant="contained"
            onClick={handleNext}
          >
            {activeStep === steps.length - 1 ? t("Complete") : t("Next")}
          </Button>
        </Box>
      </React.Fragment>
    </Box>
  );
}

export default Checkout;
