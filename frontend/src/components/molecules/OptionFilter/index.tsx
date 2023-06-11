import {
  Card,
  FormControl,
  FormControlLabel,
  Radio,
  RadioGroup,
  Stack,
  Typography,
} from "@mui/material";
import React from "react";
import { useTranslation } from "react-i18next";

interface Props {
  title: string;
  options: any[];
  value: any;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

function OptionFilter({ title, options, value, onChange }: Props) {
  const { t } = useTranslation();
  return (
    <Stack spacing={1}>
      <Typography variant="caption">{title}</Typography>
      <Card sx={{ p: 2 }}>
        <FormControl>
          <RadioGroup value={value} onChange={onChange}>
            {options.map((v, i) => (
              <FormControlLabel
                key={i}
                value={v}
                control={<Radio />}
                label={t(v)}
              />
            ))}
          </RadioGroup>
        </FormControl>
      </Card>
    </Stack>
  );
}

export default OptionFilter;
