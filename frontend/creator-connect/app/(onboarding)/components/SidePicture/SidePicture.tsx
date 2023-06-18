import React from "react";
import styles from "./SidePicture.module.css";

const SidePicture = () => {
  console.log("side picture rendering");
  return (
    <div className={styles.sidepicture}>
      <div className={styles.sidepictureOverlay}>
        <div className={styles.sidepictureContent}>
          <p>
            Your one stop shop to find the perfect influencer for your marketing
            strategy
          </p>
          <p>
            The best way to advertise your promotional services as an influencer
          </p>
        </div>
      </div>
    </div>
  );
};

export default SidePicture;
