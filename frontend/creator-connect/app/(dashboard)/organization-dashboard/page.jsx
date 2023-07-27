"use client";
import { Box, Container, Grid, Typography, Tab, Tabs } from "@mui/material";
import { useState, useEffect } from "react";
import dynamic from "next/dynamic";
import axios from "axios";
import InfluencerListTable from "../components/InfluencerListTable/InfluencerListTable";

export default function OrganizationDashboard() {
  const [influencers, setInfluencers] = useState(null);
  const [requests, setRequests] = useState(null);
  const orgID = 2;

  useEffect(() => {
    const fetchRequests = async () => {
      try {
        const res = await axios.get(
          "http://localhost:8080/api/connectionReq/organization/getByID/" +
            orgID
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

    const fetchInfluencers = async () => {
      try {
        const res = await axios.get("http://localhost:8080/api/influencers");
        setInfluencers(res.data);
      } catch (error) {
        console.log("Error:");
        console.error(error);
      }
    };

    fetchInfluencers();
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
          <Grid item xs={12}>
            <Grid container spacing={2} direction="column">
              <Grid item xs={12}>
                <Typography variant="h5" color="#222AEF" fontWeight="600">
                  Requests
                </Typography>
              </Grid>
              <Grid item xs={12}>
                <Container maxWidth="lg">
                  <Tabs variant="scrollable" scrollButtons="auto">
                    {requests &&
                      requests.map((request, index) => (
                        <Tab key={index} label={<RequestCard {...request} />} />
                      ))}
                  </Tabs>
                </Container>
              </Grid>
            </Grid>
          </Grid>
          <Grid item xs={12}>
            <Grid container spacing={2} direction="column">
              <Grid item xs={12}>
                <Typography
                  variant="h5"
                  color="#222AEF"
                  fontWeight="600"
                  mt={3}
                >
                  Recommended Influencers
                </Typography>
              </Grid>
              <Grid item xs={12}>
                <Grid container spacing={2} direction="row">
                  <Container maxWidth="lg">
                    <Tabs variant="scrollable" scrollButtons="auto">
                      {influencers &&
                        influencers.map((influencer, index) => (
                          <Tab
                            key={index}
                            label={
                              <RecommendedInfluencerCard
                                influencer={influencer}
                              />
                            }
                          />
                        ))}
                    </Tabs>
                  </Container>
                </Grid>
              </Grid>
            </Grid>
          </Grid>
          <Grid item xs={12}>
            <Grid container spacing={2} direction="column">
              <Grid item xs={12}>
                <Typography
                  variant="h5"
                  color="#222AEF"
                  fontWeight="600"
                  mt={3}
                >
                  Influencer List
                </Typography>
              </Grid>
              <Grid item xs={12}>
                <InfluencerListTable influencers={influencers} />
              </Grid>
            </Grid>
          </Grid>
        </Container>
      </Grid>
    </Box>
  );
}
