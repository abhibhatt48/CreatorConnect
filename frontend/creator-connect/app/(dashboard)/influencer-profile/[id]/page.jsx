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
} from "@mui/material";
import { useEffect, useState } from "react";
import axios from "axios";

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

  useEffect(() => {
    const fetchInfluencerData = async () => {
      try {
        const res = await axios.get(
          "http://localhost:8080/api/influencers/" + influencerID
        );
        setInfluencerData(res.data);
        console.log(res.data);
      } catch (error) {
        console.log("Error:");
        console.error(error);
      }
    };

    fetchInfluencerData();
  }, []);

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
              src="https://images.pexels.com/photos/8413906/pexels-photo-8413906.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
              className={classes.large}
              variant="square"
            />

            <Button
              variant="contained"
              color="primary"
              className={classes.button}
            >
              Connect
            </Button>
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
                  <Grid item container xs={12} rowGap={2}>
                    <Paper
                      variant="outlined"
                      borderRadius="20px"
                      sx={{
                        backgroundColor: "#F1F3E6",
                        p: 1,
                        border: "1px solid #222AEF",
                        height: "200px",
                        width: "300px",
                        margin: "auto",
                      }}
                    />
                    <Paper
                      variant="outlined"
                      borderRadius="20px"
                      sx={{
                        backgroundColor: "#F1F3E6",
                        p: 1,
                        border: "1px solid #222AEF",
                        height: "200px",
                        width: "300px",
                        margin: "auto",
                      }}
                    />
                    <Paper
                      variant="outlined"
                      borderRadius="20px"
                      sx={{
                        backgroundColor: "#F1F3E6",
                        p: 1,
                        border: "1px solid #222AEF",
                        height: "200px",
                        width: "300px",
                        margin: "auto",
                      }}
                    />
                    <Paper
                      variant="outlined"
                      borderRadius="20px"
                      sx={{
                        backgroundColor: "#F1F3E6",
                        p: 1,
                        border: "1px solid #222AEF",
                        height: "200px",
                        width: "300px",
                        margin: "auto",
                      }}
                    />
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
