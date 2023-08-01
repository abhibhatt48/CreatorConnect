"use client";
import {
  Container,
  Grid,
  Paper,
  Avatar,
  Button,
  Typography,
  Chip,
  Box,
  TextField,
} from "@mui/material";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import { useEffect, useState } from "react";
import axios from "axios";
import { useRouter } from "next/navigation";
import InfluencerFeaturedPosts from "../../components/InfluencerFeaturedPosts/InfluencerFeaturedPosts";
import { makeStyles } from "@mui/styles";
import SocialMediaIcons from "../../components/SocialMediaIcons/SocialMediaIcons";
const useStyles = makeStyles({
  large: {
    width: "100%",
    height: "400px",
    borderRadius: 0,
    objectFit: "cover",
  },
  button: {
    position: "absolute",
    bottom: "20px",
    right: "25px",
    width: "150px",
    backgroundColor: "#222AEF",
  },
  name: {
    position: "absolute",
    bottom: "20px",
    left: "25px",
    fontWeight: "bold",
    color: "white",
    backgroundColor: "rgba(0, 0, 0, 0.3)",
    padding: "5px",
  },
  coverContainer: {
    position: "relative",
    margin: "0 0",
  },
});
export default function InfluencerProfile({ params }) {
  const classes = useStyles();
  const influencerID = params.id;
  const [influencerData, setInfluencerData] = useState(null);
  const [open, setOpen] = useState(false);
  const [requestMessage, setRequestMessage] = useState("");
  const [requests, setRequests] = useState(null);
  const router = useRouter();

  let userData;
  let userID;
  if (typeof window !== "undefined") {
    userData = localStorage.getItem("userData");
    userData = JSON.parse(userData);
    userID = userData.userID;
  }

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const isConnected = () => {
    if (!requests) return false;

    for (let request of requests) {
      if (
        request.influencerID == influencerID &&
        (request.requestStatus === "Accepted" ||
          request.requestStatus === "Pending")
      ) {
        return true;
      }
    }

    return false;
  };

  useEffect(() => {
    // Retrieve JWT token from local storage
    const tempToken = localStorage.getItem("token");
    // If token does not exist, redirect to login page
    if (!tempToken) {
      alert("Please login to continue");
      router.push("/login");
      return;
    }
    const fetchInfluencerData = async () => {
      try {
        const res = await fetch(`/api/proxy?url=influencers/${influencerID}`, {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${tempToken}`,
          },
        });

        if (res.status < 400) {
          setInfluencerData(await res.json());
        } else {
          throw new Error("An error occurred");
        }
      } catch (error) {
        console.error(error);
      }
    };

    const fetchRequests = async () => {
      try {
        const token = localStorage.getItem("token");
        const res = await fetch(
          `/api/proxy?url=connectionReq/organization/getByID/${userData?.userID}`,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (res.status < 400) {
          setRequests(await res.json());
        } else {
          throw new Error("An error occurred");
        }
      } catch (error) {
        console.error(error);
      }
    };

    fetchRequests();
    const addProfileView = async () => {
      try {
        const token = localStorage.getItem("token");
        const viewObject = {
          influencerId: parseInt(influencerID),
          orgId: userID,
          date: new Date(),
        };

        const res = await fetch(`/api/proxy?url=viewCounters/addView`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify(viewObject),
        });

        if (res.status >= 400) {
          throw new Error("An error occurred");
        }
      } catch (error) {
        console.error(error);
      }
    };

    addProfileView();

    fetchInfluencerData();
  }, []);

  const handleConnect = async (requestMessage) => {
    try {
      const token = localStorage.getItem("token");
      const connectObj = {
        orgID: userID,
        influencerID: influencerID,
        requestMessage: requestMessage,
        requestStatus: "Pending",
      };

      const res = await fetch(`/api/proxy?url=connectionReq/create`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(connectObj),
      });

      if (res.status < 400) {
        alert("Connection Request Sent!");
      } else {
        throw new Error("An error occurred");
      }
    } catch (error) {
      console.error(
        "There was an error sending the connection request:",
        error
      );
    }
  };

  function calculateAge(birthdate) {
    const dob = new Date(birthdate);
    const today = new Date();

    let age = today.getFullYear() - dob.getFullYear();
    const monthDifference = today.getMonth() - dob.getMonth();

    if (
      monthDifference < 0 ||
      (monthDifference === 0 && today.getDate() < dob.getDate())
    ) {
      age--;
    }
    return age;
  }

  return (
    <Container maxWidth="lg">
      <Paper
        sx={{
          margin: "auto",
          flexGrow: 1,
          backgroundColor: "#e8eae0",
          textTransform: "none",
        }}
      >
        <Grid container direction="column">
          <Grid item xs={12} className={classes.coverContainer}>
            <Avatar
              alt="Cover Image"
              src="https://ca-times.brightspotcdn.com/dims4/default/25e1c0c/2147483647/strip/true/crop/5184x3456+0+0/resize/1200x800!/quality/80/?url=https%3A%2F%2Fcalifornia-times-brightspot.s3.amazonaws.com%2F9e%2Faf%2F0d8ec73b408ba1fde362ef73570d%2F459362-et-0717-johnson-mwy-0066.JPG"
              className={classes.large}
              variant="square"
            />

            {!isConnected() && (
              <Button
                variant="contained"
                color="primary"
                className={classes.button}
                onClick={handleClickOpen}
              >
                Connect
              </Button>
            )}
            <Dialog open={open} onClose={handleClose}>
              <DialogTitle>Message</DialogTitle>
              <DialogContent>
                <DialogContentText>
                  Kindly provide a brief message below.Your message will be
                  directly shared with the influencer.
                </DialogContentText>
                <TextField
                  autoFocus
                  margin="dense"
                  id="message"
                  label="Message"
                  type="text"
                  fullWidth
                  variant="standard"
                  value={requestMessage}
                  onChange={(e) => setRequestMessage(e.target.value)}
                />
              </DialogContent>
              <DialogActions>
                <Button onClick={handleClose}>Cancel</Button>
                <Button onClick={() => handleConnect(requestMessage)}>
                  Connect
                </Button>
              </DialogActions>
            </Dialog>
            <Typography variant="h6" className={classes.name}>
              {influencerData?.name}
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <Grid container direction="row">
              <Grid item xs={12} md={4}>
                <Grid container direction="column" spacing={1}>
                  <Grid item xs={12} mt={2}>
                    <SocialMediaIcons
                      links={{
                        instagram: influencerData?.instagram,
                        youtube: influencerData?.youtube,
                        twitter: influencerData?.tweeter,
                        facebook: influencerData?.facebook,
                        tiktok: influencerData?.tikTok,
                        twitch: influencerData?.twitch,
                      }}
                    />
                  </Grid>
                  <Grid item xs={12} ml={1}>
                    <Typography variant="body1">
                      {influencerData?.gender} (
                      {calculateAge(influencerData?.birthdate)})
                    </Typography>
                  </Grid>
                  <Grid item xs={12} ml={1}>
                    <Typography variant="body1">
                      {influencerData?.location}
                    </Typography>
                  </Grid>
                  <Grid item xs={12} ml={1}>
                    <Typography variant="body2">
                      Minimum Amount: ${influencerData?.minRate}
                    </Typography>
                  </Grid>
                  <Grid item xs={12} margin={"auto"}>
                    {influencerData?.influencerNiche.map((niche) => (
                      <Chip
                        key={niche}
                        label={niche}
                        sx={{
                          ml: 1,
                          mb: 1,
                          backgroundColor: "#rgba(232, 234, 224, 0.4)",
                        }}
                      />
                    ))}
                  </Grid>
                  <Grid item xs={12}>
                    <Paper
                      variant="outlined"
                      borderRadius="20px"
                      sx={{
                        backgroundColor: "#F1F3E6",
                        p: 1,
                        border: "1px solid #222AEF",
                      }}
                    >
                      <Box p={2}>
                        <Typography variant="body1">
                          {influencerData?.bio}
                        </Typography>
                      </Box>
                    </Paper>
                  </Grid>
                </Grid>
              </Grid>
              <Grid item xs={12} md={8}>
                <Grid container direction="column">
                  <Grid item xs={12} mt={2} textAlign={"center"}>
                    <Typography variant="h4">Featured Posts</Typography>
                  </Grid>
                  <Grid
                    item
                    container
                    xs={12}
                    rowGap={2}
                    justifyContent={"center"}
                  >
                    <InfluencerFeaturedPosts />
                  </Grid>
                </Grid>
              </Grid>
            </Grid>
          </Grid>
        </Grid>
      </Paper>
    </Container>
  );
}
