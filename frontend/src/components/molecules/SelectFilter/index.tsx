import {
  Card,
  Checkbox,
  FormControl,
  FormControlLabel,
  FormGroup,
  InputAdornment,
  Stack,
  TextField,
  Typography,
} from "@mui/material";
import React, { useState } from "react";
import { Search } from "@mui/icons-material";

interface Props {
  title: string;
  options: string[];
  checked: string[];
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}
function SelectFilter({ title, options, checked, onChange }: Props) {
  const [filter, setFilter] = useState("");
  const data = options.filter((v) =>
    v.toLocaleUpperCase().includes(filter.toLocaleUpperCase())
  );
  return (
    <Stack spacing={1}>
      <Typography variant="caption">{title}</Typography>
      <Card sx={{ p: 2 }}>
        <Stack spacing={1}>
          <TextField
            size="small"
            placeholder="Search"
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <Search />
                </InputAdornment>
              ),
              sx: (theme) => ({
                backgroundColor: theme.palette.background.default,
              }),
            }}
            value={filter}
            onChange={(e) => setFilter(e.target.value)}
          />
          <FormControl sx={{ maxHeight: 200, overflow: "hidden auto" }}>
            <FormGroup>
              {data.map((v, i) => (
                <FormControlLabel
                  key={i}
                  control={
                    <Checkbox
                      checked={checked.some((c) => v === c)}
                      onChange={onChange}
                      name={v}
                    />
                  }
                  label={v}
                />
              ))}
            </FormGroup>
          </FormControl>
        </Stack>
      </Card>
    </Stack>
  );
}

export default SelectFilter;
