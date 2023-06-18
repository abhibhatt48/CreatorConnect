"use client";

import { Button, Grid, TextField } from "@mui/material";
import "./styles.css";
import { useSignupBox } from "./useSignupBox";
import InfluencerIcon from "../../../icons/InfluencerIcon";
import OrganisationIcon from "../../../icons/OrganisationIcon";
import Link from "next/link";

export const SignupBox = () => {
  const { accountType, setAccountType } = useSignupBox();

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
                    height: "6.4rem",
                    color: "white",
                    cursor: "pointer",
                    borderRadius: "4px",
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center",
                    alignItems: "center",
                  }
                : {
                    width: "48%",
                    backgroundColor: "#D9D9D9",
                    height: "6.4rem",
                    cursor: "pointer",
                    borderRadius: "4px",
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center",
                    alignItems: "center",
                  }
            }
            onClick={() => setAccountType("Influencer")}
          >
            <InfluencerIcon
              fill={accountType === "Influencer" ? "white" : "black"}
              height="3.9rem"
              width="3.9rem"
            />
            <p>Influencer</p>
          </div>
          <div
            style={
              accountType === "Organisation"
                ? {
                    width: "48%",
                    backgroundColor: "#6F74E0",
                    height: "6.4rem",
                    color: "white",
                    cursor: "pointer",
                    borderRadius: "4px",
                    padding: "1rem",
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center",
                    alignItems: "center",
                  }
                : {
                    width: "48%",
                    backgroundColor: "#D9D9D9",
                    height: "6.4rem",
                    cursor: "pointer",
                    borderRadius: "4px",
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center",
                    alignItems: "center",
                  }
            }
            onClick={() => setAccountType("Organisation")}
          >
            <OrganisationIcon
              fill={accountType === "Organisation" ? "white" : "black"}
              height="3.9rem"
              width="3.9rem"
            />
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
        <div
          style={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "center",
          }}
        >
          <p>Already a member?</p>
          <Link
            href="/login"
            style={{ color: "#222AEF", marginLeft: "0.5rem" }}
          >
            Login
          </Link>
        </div>
      </Grid>
    </div>
  );
};
