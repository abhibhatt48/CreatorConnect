"use client";
import { Box, Container, Grid, Typography, Tab, Tabs } from "@mui/material";
import { useState, useEffect } from "react";
import dynamic from "next/dynamic";
import axios from "axios";
import RequestsListTable from "../components/RequestsListTable/RequestsListTable";

export default function InfluencerDashboard() {
  const [requests, setRequests] = useState(null);

  let userData = localStorage.getItem("userData");

  userData = JSON.parse(userData);
  let userID = userData.userID;

  useEffect(() => {
    const fetchRequests = async () => {
      try {
        const res = await axios.get(
          "http://localhost:8080/api/connectionReq/influencer/getByID/" + userID
        );
        console.log("Response:");
        setRequests(res.data);
        console.log(res.data);
      } catch (error) {
        console.log("Error:");
        console.error(error);
      }
    };

    fetchRequests();
  }, []);

  const Placeholder = () => <div>Loading...</div>;

  const RequestCard = dynamic(
    () => import("../components/RequestCard/RequestCard"),
    {
      ssr: false,
      loading: () => <Placeholder />,
    }
  );

  const RecommendedInfluencerCard = dynamic(
    () =>
      import(
        "../components/RecommendedInfluencerCard/RecommendedInfluencerCard"
      ),
    {
      ssr: false,
      loading: () => <Placeholder />,
    }
  );

  return (
    <Box height="100%" overflow="auto">
      <Grid container spacing={2} direction="column" mt={7}>
        <Container maxWidth="lg">
          <Grid container spacing={2} direction="column">
            <Grid item xs={12}>
              <Typography variant="h5" color="#222AEF" fontWeight="600" mt={3}>
                Influencer List
              </Typography>
            </Grid>
            <Grid item xs={12}>
              <RequestsListTable requests={requests} />
            </Grid>
          </Grid>
        </Container>
      </Grid>
    </Box>
  );
}
