"use client";

import { Button, Grid, TextField } from "@mui/material";
import "./styles.css";
import { useSignupBox } from "./useSignupBox";

export const SignupBox = () => {
  const { accountType, setAccountType } = useSignupBox();

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
          Signup
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
        <div style={{ display: "flex", justifyContent: "space-between" }}>
          <div
            style={
              accountType === "Influencer"
                ? {
                    width: "48%",
                    backgroundColor: "#6F74E0",
                    height: "5.8rem",
                    color: "white",
                    cursor: "pointer",
                    borderRadius: "4px",
                  }
                : {
                    width: "48%",
                    backgroundColor: "#D9D9D9",
                    height: "5.8rem",
                    cursor: "pointer",
                    borderRadius: "4px",
                  }
            }
            onClick={() => setAccountType("Influencer")}
          >
            <p>Influencer</p>
          </div>
          <div
            style={
              accountType === "Organisation"
                ? {
                    width: "48%",
                    backgroundColor: "#6F74E0",
                    height: "5.8rem",
                    color: "white",
                    cursor: "pointer",
                    borderRadius: "4px",
                  }
                : {
                    width: "48%",
                    backgroundColor: "#D9D9D9",
                    height: "5.8rem",
                    cursor: "pointer",
                    borderRadius: "4px",
                  }
            }
            onClick={() => setAccountType("Organisation")}
          >
            <p>Organisation</p>
          </div>
        </div>
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
          Create Account
        </button>
      </Grid>
    </div>
  );
};
