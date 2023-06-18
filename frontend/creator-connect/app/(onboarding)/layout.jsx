import SidePicture from "./components/SidePicture/SidePicture";

export default function OnboardingLayout({ children }) {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        height: "100vh",
      }}
    >
      <SidePicture />
      {children}
    </div>
  );
}
