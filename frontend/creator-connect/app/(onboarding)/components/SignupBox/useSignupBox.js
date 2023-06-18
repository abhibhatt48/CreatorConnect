const { useState } = require("react");

export const useSignupBox = () => {
  const [accountType, setAccountType] = useState("Influencer");

  return {
    accountType,
    setAccountType,
  };
};
