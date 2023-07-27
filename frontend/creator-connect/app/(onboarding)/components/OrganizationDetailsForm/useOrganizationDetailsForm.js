import { useState } from "react";
import { toast } from "react-toastify";
import { useRouter } from "next/navigation";

export const useOrganizationDetailsForm = () => {
  const router = useRouter();
  const organizationNicheList = [
    {
      name: "Fashion",
      selected: true,
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
    console.log("hello");
    const organizationProfileDataString = localStorage.getItem(
      "organizationProfileData"
    );

    const organizationProfileData = JSON.parse(organizationProfileDataString);
    console.log(
      "🚀 ~ file: useOrganizationDetailsForm.js:67 ~ handleFinish ~ organizationProfileData:",
      organizationProfileData
    );

    // {
    //   "orgName": "McDonald",
    //   "profileImage": "https://example.com/profile.jpg",
    //   "companyType": "Food",
    //   "size": 1000,
    //   "websiteLink": "https://www.mcdonalds.com/ca/en-ca.html",
    //   "targetInfluencerNiche": ["Food", "Wellness"],
    //   "location": "New York",
    //   "bio": "We are a food company specializing in healthy snacks.",
    //   "instagram": "https://www.instagram.com/examplecompany",
    //   "facebook": "https://www.facebook.com/examplecompany",
    //   "twitter": "https://twitter.com/examplecompany",
    //   "tiktok": "https://www.tiktok.com/@examplecompany",
    //   "youtube": "https://www.youtube.com/user/examplecompany",
    //   "twitch": "https://www.twitch.tv/examplecompany"
    // }

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
    console.log(
      "🚀 ~ file: useOrganizationDetailsForm.js:102 ~ handleFinish ~ organizationOnboardingInfo:",
      organizationOnboardingInfo
    );

    try {
      const res = await fetch(
        // "https://asdc-project-group2.onrender.com/api/organizations/register/75",
        "http://localhost:8080/api/organizations/register/76",
        {
          method: "POST", // *GET, POST, PUT, DELETE, etc.
          // mode: "cors", // no-cors, *cors, same-origin
          mode: "cors",
          // cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
          // credentials: "same-origin", // include, *same-origin, omit
          headers: {
            "Content-Type": "application/json",
            // 'Content-Type': 'application/x-www-form-urlencoded',
          },
          // redirect: "follow", // manual, *follow, error
          // referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin,
          body: JSON.stringify(organizationOnboardingInfo), // body data type must match "Content-Type" header
        }
      );
      console.log(
        "🚀 ~ file: useOrganizationDetailsForm.js:131 ~ handleFinish ~ res:",
        res
      );

      if (!res.error) router.push("organization-dashboard");
      else toast.error(error);
      return res;
    } catch (error) {
      toast.error(error);
      console.log("Error", error);
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
