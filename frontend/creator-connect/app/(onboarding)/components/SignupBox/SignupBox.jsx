"use client";

import { Grid } from "@mui/material";
import Link from "next/link";
import React from "react";
import InfluencerIcon from "../../../icons/InfluencerIcon";
import OrganisationIcon from "../../../icons/OrganisationIcon";
import styles from "./SignupBox.module.css";
import { useSignupBox } from "./useSignupBox";

export const SignupBox = () => {
  const { accountType, setAccountType } = useSignupBox();

  return (
    <div className={styles.container}>
      <Grid container direction={"column"} gap={2}>
        <p className={styles.title}>Signup</p>
        <input className={styles.input} placeholder="email" />
        <input
          className={styles.input}
          placeholder="password"
          type="password"
        />
        <div className={styles.accountContainer}>
          <div
            className={`${styles.accountType} ${
              accountType === "Influencer"
                ? styles.influencer
                : styles.organisation
            }`}
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
            className={`${styles.accountType} ${
              accountType === "Organisation"
                ? styles.influencer
                : styles.organisation
            }`}
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
        <button className={styles.button}>Create Account</button>
        <div className={styles.linkContainer}>
          <p>Already a member?</p>
          <Link href="/login" className={styles.linkText}>
            Login
          </Link>
        </div>
      </Grid>
    </div>
  );
};
