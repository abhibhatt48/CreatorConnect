import { useRouter } from "next/navigation";
import { useState } from "react";
import { toast } from "react-toastify";

export const useOrganizationProfileForm = () => {
  const router = useRouter();
  const [organizationName, setOrganizationName] = useState("asdf");
  console.log(
    "🚀 ~ file: useOrganizationProfileForm.js:8 ~ useOrganizationProfileForm ~ organizationName:",
    organizationName
  );
  const [description, setDescription] = useState("asdf");
  console.log(
    "🚀 ~ file: useOrganizationProfileForm.js:10 ~ useOrganizationProfileForm ~ description:",
    description
  );
  const [websiteLink, setWebsiteLink] = useState("asdf");
  console.log(
    "🚀 ~ file: useOrganizationProfileForm.js:12 ~ useOrganizationProfileForm ~ websiteLink:",
    websiteLink
  );
  const [region, setRegion] = useState("asdf");
  console.log(
    "🚀 ~ file: useOrganizationProfileForm.js:14 ~ useOrganizationProfileForm ~ region:",
    region
  );
  const [organizationSize, setOrganizationSize] = useState("asdf");
  console.log(
    "🚀 ~ file: useOrganizationProfileForm.js:16 ~ useOrganizationProfileForm ~ organizationSize:",
    organizationSize
  );

  const handleNext = () => {
    if (
      organizationName &&
      description &&
      websiteLink &&
      region &&
      organizationSize
    ) {
      const organizationProfileData = {
        organizationName,
        description,
        websiteLink,
        region,
        organizationSize,
      };
      localStorage.setItem(
        "organizationProfileData",
        JSON.stringify(organizationProfileData)
      );

      router.push("onboarding-organization-2");
    } else {
      toast.error("Please fill out all fields");
    }
  };

  return {
    organizationName,
    setOrganizationName,
    description,
    setDescription,
    websiteLink,
    setWebsiteLink,
    region,
    setRegion,
    organizationSize,
    setOrganizationSize,
    handleNext,
  };
};
