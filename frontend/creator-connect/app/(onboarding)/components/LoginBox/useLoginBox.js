import { useRouter } from "next/navigation";
import { useState } from "react";
import { toast } from "react-toastify";

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
      let res = await fetch("http://localhost:8080/api/users/login", {
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
      });

      res = await res.json();

      if (res?.ok) {
        toast.success(res.message);
        localStorage.setItem("userData", JSON.stringify(res.data[0]));
        localStorage.setItem("token", res.jwt);
        if (res.data[0].user_type === "Influencer")
          router.push("onboarding-influencer-1");
        else router.push("onboarding-organization-1");
      } else {
        toast.error(res.message);
      }
    } catch (error) {
      toast.error("Error logging in");
      console.log("Error", error);
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
