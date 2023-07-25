"use client";
import { Box, Container, Grid, Typography, Tab, Tabs } from "@mui/material";
import RequestCard from "../components/RequestCard/RequestCard";
import { useState } from "react";

export default function OrganizationDashboard() {
  const [value, setValue] = useState("1");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  return (
    <Box height="100vh">
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
                <Tabs
                  value={value}
                  onChange={handleChange}
                  variant="scrollable"
                  scrollButtons="auto"
                >
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
            <Typography variant="h5" color="#222AEF" fontWeight="600" mt={3}>
              Recommended Influencers
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <Typography variant="h5" color="#222AEF" fontWeight="600">
              Influencer List
            </Typography>
          </Grid>
        </Container>
      </Grid>
    </Box>
  );
}
