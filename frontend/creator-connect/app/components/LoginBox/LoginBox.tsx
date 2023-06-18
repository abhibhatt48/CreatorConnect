"use client";

import { Button, Grid, TextField } from "@mui/material";
import "./styles.css";

export const LoginBox = () => {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        width: "70%",
      }}
    >
      <Grid container direction={"column"} gap={2}>
        <p style={{ color: "#222AEF", fontSize: "3.2rem", fontWeight: "600" }}>
          Login
        </p>
        <input
          style={{
            borderColor: "#222AEF",
            height: "3rem",
            borderRadius: "5px",
            paddingLeft: "1rem",
            paddingRight: "1rem",
          }}
          placeholder="email"
        ></input>
        <input
          style={{
            borderColor: "#222AEF",
            height: "3rem",
            borderRadius: "5px",
            paddingLeft: "1rem",
            paddingRight: "1rem",
          }}
          placeholder="password"
          type="password"
        ></input>
        <button
          style={{
            backgroundColor: "#222AEF",
            color: "white",
            borderColor: "#222AEF",
            borderRadius: "5px",
            height: "3rem",
            cursor: "pointer",
          }}
        >
          Login
        </button>
      </Grid>
    </div>
  );
};
