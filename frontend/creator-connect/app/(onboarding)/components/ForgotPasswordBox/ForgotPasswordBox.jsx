"use client";
import { Grid } from "@mui/material";
import Link from "next/link";
import React from "react";
import styles from "./ForgotPasswordBox.module.css";

export const ForgotPasswordBox = () => {
  return (
    <div className={styles.container}>
      <Grid container direction={"column"} gap={2}>
        <p className={styles.title}>Account Recovery</p>
        <input className={styles.input} placeholder="Please Enter Your Email" />
        <button className={styles.button}>Get Link</button>
        <div className={styles.linkContainer}>
          <p>Back to </p>
          <Link href="/login" className={styles.linkText}>
            Login
          </Link>
          <span style={{ marginLeft: 8 }}>|</span>
          <Link href="/signup" className={styles.linkText}>
            Signup
          </Link>
        </div>
      </Grid>
    </div>
  );
};
