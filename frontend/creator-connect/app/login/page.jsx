import "../../src/styles/globals.css";
import { LoginBox } from "../components/LoginBox/LoginBox";
import SidePicture from "../components/SidePicture/SidePicture";

const LoginPage = () => {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        height: "100vh",
      }}
    >
      <SidePicture />
      <div
        style={{
          width: "50%",
          height: "100%",
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          textAlign: "center",
        }}
      >
        <div
          style={{
            height: "20%",
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          <p
            style={{
              color: "#222AEF",
              fontSize: "3.75rem",
              fontWeight: "600",
            }}
          >
            Creator Connect
          </p>
        </div>
        <div
          style={{
            height: "100%",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            textAlign: "center",
            alignItems: "center",
          }}
        >
          <LoginBox />
        </div>
      </div>
    </div>
  );
};

export default LoginPage;