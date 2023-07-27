import { useRouter } from "next/navigation";
import { useState } from "react";
import { toast } from "react-toastify";

export const useOrganizationProfileForm = () => {
  const router = useRouter();
  const [organizationName, setOrganizationName] = useState("asdf");
  const [description, setDescription] = useState("asdf");
  const [websiteLink, setWebsiteLink] = useState("asdf");
  const [region, setRegion] = useState("asdf");
  const [organizationSize, setOrganizationSize] = useState("asdf");

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
