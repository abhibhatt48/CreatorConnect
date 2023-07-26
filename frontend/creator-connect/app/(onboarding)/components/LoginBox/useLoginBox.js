import { useRouter } from "next/navigation";
import { useState } from "react";

export const useLoginBox = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const router = useRouter();

  const handleSignin = async () => {
    const signinData = {
      email,
      password,
    };

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

  return {
    email,
    setEmail,
    password,
    setPassword,
    handleSignin,
  };
};
