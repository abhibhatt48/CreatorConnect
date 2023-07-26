"use client";
import { Box, Container, Grid, Typography, Tab, Tabs } from "@mui/material";
import { useState } from "react";
import dynamic from "next/dynamic";
import InfluencerListTable from "../components/InfluencerListTable/InfluencerListTable";
export default function OrganizationDashboard() {
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
                <Tabs variant="scrollable" scrollButtons="auto">
                  <Tab label={<RequestCard />} />
                  <Tab label={<RequestCard />} />
                  <Tab label={<RequestCard />} />
                  <Tab label={<RequestCard />} />
                  <Tab label={<RequestCard />} />
                  <Tab label={<RequestCard />} />
                  <Tab label={<RequestCard />} />
                </Tabs>
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
                  <Tabs variant="scrollable" scrollButtons="auto">
                    <Tab label={<RecommendedInfluencerCard />} />
                    <Tab label={<RecommendedInfluencerCard />} />
                    <Tab label={<RecommendedInfluencerCard />} />
                    <Tab label={<RecommendedInfluencerCard />} />
                    <Tab label={<RecommendedInfluencerCard />} />
                    <Tab label={<RecommendedInfluencerCard />} />
                  </Tabs>
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
                <InfluencerListTable />
              </Grid>
            </Grid>
          </Grid>
        </Container>
      </Grid>
    </Box>
  );
}
