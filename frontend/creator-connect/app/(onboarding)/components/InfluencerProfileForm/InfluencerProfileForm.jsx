"use client";

import React from "react";
import styles from "./InfluencerProfileForm.module.css";
import { Grid } from "@mui/material";
import { useInfluencerProfileForm } from "./useInfluencerProfileForm";

export const InfluencerProfileForm = () => {
  const {
    fileInputRef,
    selectedImage,
    handleImageSelect,
    handleCircularDivClick,
  } = useInfluencerProfileForm();

  return (
    <Grid
      rowGap={2}
      style={{
        display: "flex",
        flexDirection: "column",
        width: "60%",
      }}
    >
      <div
        style={{
          height: "200px",
          width: "200px",
          borderRadius: "100%",
          backgroundColor: "#d2d3d1",
          alignSelf: "center",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          marginBottom: "20px",
        }}
        onClick={handleCircularDivClick}
      >
        <input
          type="file"
          accept="image/*"
          ref={fileInputRef}
          style={{ display: "none" }}
          onChange={handleImageSelect}
        />
        {selectedImage ? (
          <img
            className="selected-image"
            src={selectedImage}
            style={{
              height: "200px",
              width: "200px",
              borderRadius: "100%",
              backgroundColor: "#d2d3d1",
            }}
            alt="Profile"
          />
        ) : (
          <div className="placeholder" style={{ color: "#222aef" }}>
            Select Profile Image
          </div>
        )}
      </div>
      <div style={{ display: "flex", justifyContent: "space-between" }}>
        <input
          className={styles.input}
          style={{ width: "49%" }}
          placeholder="First name"
        />
        <input
          className={styles.input}
          style={{ width: "49%" }}
          placeholder="Last name"
        />
      </div>
      <input className={styles.input} placeholder="Last name" />
      <select className={styles.input}>
        <option value="" selected disabled hidden>
          Choose Gender
        </option>
        <option value="North America">Male</option>
        <option value="volvo">Female</option>
      </select>
      <select className={styles.input}>
        <option value="" selected disabled hidden>
          Choose Region
        </option>
        <option value="North America">North America</option>
        <option value="volvo">South America</option>
        <option value="saab">Caribbean</option>
        <option value="mercedes">Asia</option>
        <option value="audi">Europe</option>
        <option value="audi">Australasia</option>
        <option value="audi">Africa</option>
      </select>
      <input
        className={styles.input}
        type="date"
        value="2018-07-22"
        min="1920-01-01"
        max="2018-12-31"
      />
      <button className={styles.button}>Next</button>
    </Grid>
  );
};
