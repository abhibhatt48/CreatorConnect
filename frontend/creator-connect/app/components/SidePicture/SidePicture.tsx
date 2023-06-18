const SidePicture = () => {
  return (
    <div
      style={{
        width: "50%",
        backgroundImage:
          "url(https://www.oberlo.com/media/1627657017-influencer.jpeg?fit=max&fm=jpg&w=1824)",
        backgroundSize: "cover",
        backgroundPosition: "center",
        height: "100%",
      }}
    >
      <div
        style={{
          position: "absolute",
          top: "0",
          left: "0",
          width: "50%",
          height: "100%",
          backgroundColor: "rgba(0, 0, 0, 0.5)",
          padding: "2rem",
        }}
      >
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "space-between",
            height: "100%",
          }}
        >
          <p
            style={{
              color: "white",
              fontSize: "2rem",
            }}
          >
            Your one stop shop to find the perfect influencer for your marketing
            strategy
          </p>
          <p
            style={{
              color: "white",
              fontSize: "2rem",
              textShadow: "0px 4px 4px rgba(0, 0, 0, 0.25)",
            }}
          >
            The best way to advertise your promotional services as an influencer
          </p>
        </div>
      </div>
    </div>
  );
};

export default SidePicture;
