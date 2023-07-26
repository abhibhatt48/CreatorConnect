"use client";

import React from "react";
import styles from "./OrganizationDetailsForm.module.css";
import { Grid } from "@mui/material";
import { useOrganizationDetailsForm } from "./useOrganizationDetailsForm";
import InstagramIcon from "@mui/icons-material/Instagram";
import TwitterIcon from "@mui/icons-material/Twitter";
import YouTubeIcon from "@mui/icons-material/YouTube";
import FacebookIcon from "@mui/icons-material/Facebook";

export const OrganizationDetailsForm = () => {
  const {
    influencerNicheList,
    handleSelect,
    filterSelected,
    selectedNiches,
    setSelectedNiches,
  } = useOrganizationDetailsForm();

  return (
    <Grid
      rowGap={2}
      style={{
        display: "flex",
        flexDirection: "column",
        width: "60%",
        overflow: "scroll",
      }}
    >
      <div style={{ display: "flex", flexDirection: "column" }}>
        <div style={{ alignSelf: "flex-start" }}>
          <p
            style={{
              fontSize: "20px",
              color: "#222aef",
              fontWeight: "bold",
              textAlign: "left",
            }}
          >
            Select your niche
          </p>
          <p style={{ textAlign: "left", marginTop: "5px" }}>
            Select all that apply
          </p>
        </div>
        <div
          style={{ display: "flex", flexDirection: "row", flexWrap: "wrap" }}
        >
          {selectedNiches.map((niche, index) => {
            return (
              <div
                style={
                  niche.selected
                    ? {
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                        padding: "10px",
                        height: "40px",
                        marginTop: "20px",
                        marginRight: "20px",
                        borderRadius: "15px",
                        color: "white",
                        backgroundColor: "#222aef",
                      }
                    : {
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                        padding: "10px",
                        height: "40px",
                        marginTop: "20px",
                        marginRight: "20px",
                        borderRadius: "15px",
                        backgroundColor: "#d2d3d1",
                      }
                }
                onClick={() =>
                  handleSelect(selectedNiches, setSelectedNiches, index)
                }
              >
                <span>{niche.name}</span>
              </div>
            );
          })}
        </div>
      </div>

      <div
        style={{ display: "flex", flexDirection: "column", overflow: "scroll" }}
      >
        <div style={{ alignSelf: "flex-start" }}>
          <p
            style={{
              fontSize: "20px",
              color: "#222aef",
              fontWeight: "bold",
              textAlign: "left",
            }}
          >
            Add your socials
          </p>
          <p style={{ textAlign: "left", marginTop: "5px" }}>
            Add all that apply
          </p>
        </div>
        <Grid container rowGap={2}>
          <div
            style={{
              display: "flex",
              width: "100%",
              alignItems: "center",
              justifyContent: "space-between",
              marginTop: "20px",
            }}
          >
            <InstagramIcon />
            <input
              className={styles.input}
              placeholder="URL"
              style={{ width: "90%" }}
            />
          </div>
          <div
            style={{
              display: "flex",
              width: "100%",
              alignItems: "center",
              justifyContent: "space-between",
            }}
          >
            <TwitterIcon />
            <input
              className={styles.input}
              placeholder="URL"
              style={{ width: "90%" }}
            />
          </div>
          <div
            style={{
              display: "flex",
              width: "100%",
              alignItems: "center",
              justifyContent: "space-between",
            }}
          >
            <YouTubeIcon />
            <input
              className={styles.input}
              placeholder="URL"
              style={{ width: "90%" }}
            />
          </div>
          <div
            style={{
              display: "flex",
              width: "100%",
              alignItems: "center",
              justifyContent: "space-between",
            }}
          >
            <FacebookIcon />
            <input
              className={styles.input}
              placeholder="URL"
              style={{ width: "90%" }}
            />
          </div>
        </Grid>
        <Grid style={{ display: "flex", marginTop: "20px" }}>
          <div>
            <div style={{ alignSelf: "flex-start" }}>
              <p
                style={{
                  fontSize: "20px",
                  color: "#222aef",
                  fontWeight: "bold",
                  textAlign: "left",
                }}
              >
                Minimum rate (Optional)
              </p>
              <p style={{ textAlign: "left", marginTop: "5px" }}>
                Enter the minimum compensation you are willing to accept for
                your promotional services
              </p>
            </div>
          </div>
          <input
            className={styles.input}
            type={"number"}
            placeholder="$"
            style={{ width: "100%", height: "80px", fontSize: "40px" }}
          />
        </Grid>
      </div>
      <button className={styles.button}>Next</button>
    </Grid>
  );
};
