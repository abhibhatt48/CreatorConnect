"use client";

import { Button, Grid, TextField } from "@mui/material";
import "./styles.css";
import Link from "next/link";

export const LoginBox = () => {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        width: "60%",
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
        <div
          style={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "center",
          }}
        >
          <p>New here?</p>
          <Link
            href="/signup"
            style={{ color: "#222AEF", marginLeft: "0.5rem" }}
          >
            Signup
          </Link>
        </div>
      </Grid>
    </div>
  );
};
