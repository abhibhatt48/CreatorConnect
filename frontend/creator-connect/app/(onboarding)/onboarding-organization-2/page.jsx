import "../../../src/styles/globals.css";
import React from "react";
import styles from "./OnboardingOrganizationSecondStep.module.css";
import { InfluencerDetailsForm } from "../components/InfluencerDetailsForm/InfluencerDetailsForm";

const OnboardingOrganizationSecondStep = () => {
  return (
    <div className={styles.container}>
      <div className={styles.heading}>
        <p>Create your profile</p>
      </div>
      <div className={styles.content}>
        <InfluencerDetailsForm />
      </div>
    </div>
  );
};

export default OnboardingOrganizationSecondStep;
