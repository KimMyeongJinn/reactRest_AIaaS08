import React, { useState } from "react";
import { SERVER_URL } from "./constants";
import { Button, TextField, Stack, Snackbar } from "@mui/material";
import CarList from "./CarList";

function Login() {
  const [open, setOpen] = useState(false);
  const [user, setUser] = useState({
    username: "",
    password: "",
  });

  const bAuth = sessionStorage.getItem("jwt") !== null ? true : false;
  const [isAuthenticated, setAuth] = useState(bAuth);

  const handleChange = (e) =>
    setUser({ ...user, [e.target.name]: e.target.value });

  const login = () => {
    fetch(SERVER_URL + "login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(user),
    })
      .then((response) => {
        const jwtToken = response.headers.get("Authorization");
        if (jwtToken !== null) {
          sessionStorage.setItem("jwt", jwtToken);
          setAuth(true);
        }else{
          setOpen(true);
        }
      })
      .catch((err) => console.error(err));
  };

  const notAuth = () => setAuth(false);

  if (isAuthenticated) {
    return <CarList notAuth={notAuth} />;
  } else {
    return (
      <div>
        <Stack spacing={2} alignItems="center" mt={2}>
          <TextField name="username" label="Username" onChange={handleChange} />
          <TextField
            name="password"
            label="Password"
            type="password"
            onChange={handleChange}
          />
          <Button variant="outlined" color="primary" onClick={login}>
            Login
          </Button>
        </Stack>
        <Snackbar open={open}
                  autoHideDuration={3000}
                  onClose={() => setOpen(false)}
                  message = "Login Failed: Check your username and password"
                  />
      </div>
    );
  }
}

export default Login;
