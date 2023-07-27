import { useState } from "react";

export const useInfluencerDetailsForm = () => {
  const influencerNicheList = [
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

  const [selectedNiches, setSelectedNiches] = useState(influencerNicheList);
  const [instagramUrl, setInstagramUrl] = useState("");
  const [twitterUrl, setTwitterUrl] = useState("");
  const [youtubeUrl, setYoutubeUrl] = useState("");
  const [facebookUrl, setFacebookUrl] = useState("");
  const [industry, setIndustry] = useState("");

  const handleFinish = async () => {
    const influencerProfileData = {
      firstName,
      lastName,
      bio,
      gender,
      region,
      birthdate,
    };

    const influencerProfileDataString = localStorage.getItem(
      "influencerProfileData"
    );

    JSON.parse(influencerProfileDataString);

    //   const influencerOnboardingInfo =
    //   {
    //     name: influencerProfileData?.firstName + influencerProfileData.lastName,
    //     gender: influencerProfileData.gender,
    //     influencerName: influencerProfileData?.firstName + influencerProfileData.lastName,
    //     influencerType: Fashion,
    //     influencerNiche: filterSelected(selectedNiches),
    //     minRate: 5000,
    //     previousBrands: Brand A, Brand B,
    //     location: New York, USA,
    //     bio: influencerProfileData?.bio,
    //     birthdate: influencerProfileData?.birthdate,
    //     instagram: http://instagram.com/influencer,
    //     tikTok: http://tiktok.com/influencer,
    //     tweeter: http://twitter.com/influencer,
    //     youtube: http://youtube.com/influencer,
    //     facebook: http://facebook.com/influencer,
    //     twitch: http://twitch.com/influencer,
    //     bestPosts: [http://instagram.com/p/abcdef, http://instagram.com/p/ghijkl]
    // }

    try {
      const res = await fetch(
        "https://asdc-project-group2.onrender.com/api/users/login",
        {
          method: "POST", // *GET, POST, PUT, DELETE, etc.
          mode: "cors", // no-cors, *cors, same-origin
          // cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
          // credentials: "same-origin", // include, *same-origin, omit
          headers: {
            "Content-Type": "application/json",
            // 'Content-Type': 'application/x-www-form-urlencoded',
          },
          // redirect: "follow", // manual, *follow, error
          // referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin,
          body: JSON.stringify(signinData), // body data type must match "Content-Type" header
        }
      );

      localStorage.setItem("userId", 3);

      return res;
    } catch (error) {
      console.log("Error", e);
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
  const filterSelected = (data) => {
    const selectedData = data?.filter((data) => data.selected === true);
    return selectedData;
  };

  return {
    influencerNicheList,
    handleSelect,
    filterSelected,
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
  };
};
