"use client";
import { Grid } from "@mui/material";
import Link from "next/link";
import React from "react";
import styles from "./LoginBox.module.css";

export const LoginBox = () => {
  return (
    <div className={styles.container}>
      <Grid container direction={"column"} gap={2}>
        <p className={styles.title}>Login</p>
        <input className={styles.input} placeholder="email" />
        <input
          className={styles.input}
          placeholder="password"
          type="password"
        />
        <button className={styles.button}>Login</button>
        <div className={styles.linkContainer}>
          <p>New here?</p>
          <Link href="/signup" className={styles.linkText}>
            Signup
          </Link>
        </div>
      </Grid>
    </div>
  );
};
