import { useRouter } from "next/navigation";
import { useState } from "react";
import { toast } from "react-toastify";

export const useInfluencerProfileForm = () => {
  const router = useRouter();
  const [firstName, setFirstName] = useState("asdf");
  const [lastName, setLastName] = useState("asdf");
  const [bio, setBio] = useState("asdf");
  const [gender, setGender] = useState("asdf");
  const [region, setRegion] = useState("asdf");
  const [birthdate, setBirthdate] = useState("asdf");

  const handleNext = () => {
    if (firstName && lastName && bio && gender && region && birthdate) {
      const influencerProfileData = {
        firstName,
        lastName,
        bio,
        gender,
        region,
        birthdate,
      };
      localStorage.setItem(
        "influencerProfileData",
        JSON.stringify(influencerProfileData)
      );

      router.push("onboarding-influencer-2");
    } else {
      toast.error("Please fill out all fields");
    }
  };

  return {
    firstName,
    setFirstName,
    lastName,
    setLastName,
    bio,
    setBio,
    gender,
    setGender,
    region,
    setRegion,
    birthdate,
    setBirthdate,
    handleNext,
  };
};
