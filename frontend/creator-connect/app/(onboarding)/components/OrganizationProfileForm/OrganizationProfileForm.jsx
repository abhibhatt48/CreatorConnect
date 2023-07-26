"use client";

import React from "react";
import styles from "./OrganizationProfileForm.module.css";
import { Grid } from "@mui/material";
import { useOrganizationProfileForm } from "./useOrganizationProfileForm";

export const OrganizationProfileForm = () => {
  const {
    fileInputRef,
    selectedImage,
    handleImageSelect,
    handleCircularDivClick,
  } = useOrganizationProfileForm();

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
      <input className={styles.input} placeholder="Organization name" />
      <input
        className={styles.input}
        placeholder="Organization description"
        style={{ height: "9rem", textAlign: "top" }}
      />
      <input className={styles.input} placeholder="Website link" />

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
      <select className={styles.input}>
        <option value="" selected disabled hidden>
          Choose Organization Size
        </option>
        <option value="Small: 10-49">Small: 10-49 employees</option>
        <option value="Medium: 50-249">Medium: 50-249 10-49 employees</option>
        <option value="Large 249+">Large: 249+ 10-49 employees</option>
      </select>
      <button className={styles.button}>Next</button>
    </Grid>
  );
};
