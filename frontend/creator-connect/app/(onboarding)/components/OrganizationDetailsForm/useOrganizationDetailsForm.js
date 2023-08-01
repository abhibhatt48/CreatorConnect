import { useState } from "react";
import { toast } from "react-toastify";
import { useRouter } from "next/navigation";

export const useOrganizationDetailsForm = () => {
  const router = useRouter();
  const organizationNicheList = [
    {
      name: "Fashion",
      selected: false,
    },
    {
      name: "Beauty",
      selected: false,
    },
    {
      name: "Health",
      selected: false,
    },
    {
      name: "Celebrity",
      selected: false,
    },
    {
      name: "Video Game",
      selected: false,
    },
    {
      name: "Brand Ambassador",
      selected: false,
    },
    {
      name: "Wellness",
      selected: false,
    },
    {
      name: "Vlogger",
      selected: false,
    },
    {
      name: "Entrpreneurship",
      selected: false,
    },
    {
      name: "Cooking",
      selected: false,
    },
    {
      name: "Fitness",
      selected: false,
    },
  ];

  const [selectedNiches, setSelectedNiches] = useState(organizationNicheList);
  const [instagramUrl, setInstagramUrl] = useState("");
  const [twitterUrl, setTwitterUrl] = useState("");
  const [youtubeUrl, setYoutubeUrl] = useState("");
  const [facebookUrl, setFacebookUrl] = useState("");
  const [industry, setIndustry] = useState("");

  const handleFinish = async () => {
    if (getSelectedNicheNames(selectedNiches).length && industry) {
      let organizationProfileDataString;
      let userData;
      if (typeof window !== "undefined") {
        organizationProfileDataString = localStorage.getItem(
          "organizationProfileData"
        );

        userData = localStorage.getItem("userData");
      }

      userData = JSON.parse(userData);

      const organizationProfileData = JSON.parse(organizationProfileDataString);

      const organizationOnboardingInfo = {
        orgName: organizationProfileData?.organizationName,
        profileImage: "",
        companyType: industry,
        size: parseInt(organizationProfileData?.organizationSize),
        websiteLink: organizationProfileData?.websiteLink,
        targetInfluencerNiche: getSelectedNicheNames(selectedNiches),
        location: organizationProfileData?.region,
        bio: organizationProfileData?.description,
        previousBrands: "",
        instagram: instagramUrl,
        tweeter: twitterUrl,
        youtube: youtubeUrl,
        facebook: facebookUrl,
        tiktok: "",
        twitch: "",
      };

      try {
        const token = localStorage.getItem("token");
        const res = await fetch(
          `/api/proxy?url=organizations/register/${userData?.userID}`,
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(organizationOnboardingInfo),
          }
        );

        if (res.status < 400) {
          const result = await res.json();

          if (!result.error) {
            router.push("organization-dashboard");
          } else {
            toast.error(result.error);
          }
        } else {
          toast.error("An error occurred.");
        }
      } catch (error) {
        toast.error("Error: " + error);
      }
    } else {
      toast.error("Please fill out all fields, social URL's are optional.");
    }
  };
  const handleSelect = (data, setData, index) => {
    const newNiches = data.map((niche, i) => {
      if (index === i) {
        niche.selected = !niche.selected;
      }
      return niche;
    });

    setData(newNiches);
  };

  //filteres the selected data to return only the selected ones as an array of the fields.
  function getSelectedNicheNames(nicheList) {
    return nicheList
      .filter((niche) => niche.selected) // filter out unselected niches
      .map((niche) => niche.name); // transform remaining objects to just their names
  }

  return {
    organizationNicheList,
    handleSelect,
    selectedNiches,
    setSelectedNiches,
    instagramUrl,
    setInstagramUrl,
    twitterUrl,
    setTwitterUrl,
    youtubeUrl,
    setYoutubeUrl,
    facebookUrl,
    setFacebookUrl,
    industry,
    setIndustry,
    handleFinish,
  };
};
