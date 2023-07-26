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

  const [selectedNiches, setSelectedNiches] = useState(influencerNicheList);
  console.log(
    "🚀 ~ file: useInfluencerDetailsForm.js:69 ~ useInfluencerDetailsForm ~ selectedNiches:",
    selectedNiches
  );
  const [instagramUrl, setInstagramUrl] = useState("");
  console.log(
    "🚀 ~ file: useInfluencerDetailsForm.js:71 ~ useInfluencerDetailsForm ~ instagramUrl:",
    instagramUrl
  );
  const [twitterUrl, setTwitterUrl] = useState("");
  console.log(
    "🚀 ~ file: useInfluencerDetailsForm.js:73 ~ useInfluencerDetailsForm ~ twitterUrl:",
    twitterUrl
  );
  const [youtubeUrl, setYoutubeUrl] = useState("");
  console.log(
    "🚀 ~ file: useInfluencerDetailsForm.js:75 ~ useInfluencerDetailsForm ~ youtubeUrl:",
    youtubeUrl
  );
  const [facebookUrl, setFacebookUrl] = useState("");
  console.log(
    "🚀 ~ file: useInfluencerDetailsForm.js:77 ~ useInfluencerDetailsForm ~ facebookUrl:",
    facebookUrl
  );
  const [industry, setIndustry] = useState("");
  console.log(
    "🚀 ~ file: useInfluencerDetailsForm.js:79 ~ useInfluencerDetailsForm ~ industry:",
    industry
  );

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
