import { useState } from "react";

export const useOrganizationDetailsForm = () => {
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

  return {
    influencerNicheList,
    handleSelect,
    filterSelected,
    selectedNiches,
    setSelectedNiches,
  };
};
