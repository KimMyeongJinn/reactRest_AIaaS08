import React, { useState } from "react";
import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  IconButton,
} from "@mui/material";
import { Button, TextField, Stack } from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";

function EditCar(props) {
  const { data, updateCar } = props;
  const [open, setOpen] = useState(false); // 자동차 정보 입력 대화상자 열림 여부
  const [car, setCar] = useState({
    // 자동차 입력 정보
    brand: "",
    model: "",
    color: "",
    year: "",
    price: "",
  });

  const handleClickOpen = () => {
    console.log(data);
    setCar({
      brand: data.row.brand,
      model: data.row.model,
      color: data.row.color,
      year: data.row.year,
      price: data.row.price,
    });
    setOpen(true);
  };
  const handleClose = () => setOpen(false);
  const handleChange = (e) =>
    setCar({ ...car, [e.target.name]: e.target.value });
  const handleSave = () => {
    updateCar(car, data.id);
    handleClose();
  };

  return (
    <div>
      {/* <button onClick={handleClickOpen}>Edit Car</button> */}
      <IconButton onClick={handleClickOpen}>
        <EditIcon color="primary" />
      </IconButton>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Edit Car</DialogTitle>
        {/* <DialogContent>
          <Stack spacing={2} mt={1}>
            <input
              placeholder="Brand"
              name="brand"
              value={car.brand}
              onChange={handleChange}
            />
            <br />
            <input
              placeholder="Model"
              name="model"
              value={car.model}
              onChange={handleChange}
            />
            <br />
            <input
              placeholder="Color"
              name="color"
              value={car.color}
              onChange={handleChange}
            />
            <br />
            <input
              placeholder="Year"
              name="year"
              value={car.year}
              onChange={handleChange}
            />
            <br />
            <input
              placeholder="Price"
              name="price"
              value={car.price}
              onChange={handleChange}
            />
            <br />
          </Stack>
        </DialogContent> */}
        <DialogContent>
          <Stack spacing={2} mt={1}>
            <TextField
              label="Brand"
              name="brand"
              variant="standard"
              autoFocus
              value={car.brand}
              onChange={handleChange}
            />
            <TextField
              label="Model"
              name="model"
              variant="standard"
              value={car.model}
              onChange={handleChange}
            />
            <TextField
              label="Color"
              name="color"
              variant="standard"
              value={car.color}
              onChange={handleChange}
            />
            <TextField
              label="Year"
              name="year"
              variant="standard"
              value={car.year}
              onChange={handleChange}
            />
            <TextField
              label="Price"
              name="price"
              variant="standard"
              value={car.price}
              onChange={handleChange}
            />
          </Stack>
        </DialogContent>
        <DialogActions>
          {/* <button onClick={handleClose}>Cancel</button>
          <button onClick={handleSave}>Save</button> */}
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleSave}>Save</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}

export default EditCar;
