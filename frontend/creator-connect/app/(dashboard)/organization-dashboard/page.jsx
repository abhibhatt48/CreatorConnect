"use client";
import { Box, Container, Grid, Typography, Tab, Tabs } from "@mui/material";
import { useState, useEffect } from "react";
import dynamic from "next/dynamic";
import axios from "axios";
import InfluencerListTable from "../components/InfluencerListTable/InfluencerListTable";
import { useRouter } from "next/navigation";

export default function OrganizationDashboard() {
  const [influencers, setInfluencers] = useState(null);
  const [requests, setRequests] = useState(null);
  let userData = localStorage.getItem("userData");
  userData = JSON.parse(userData);
  let orgID = userData.userID;
  const [acceptedRequests, setAcceptedRequests] = useState();
  const [pendingRequests, setPendingRequests] = useState();
  const router = useRouter();

  useEffect(() => {
    if (requests?.length) {
      setAcceptedRequests(
        requests.filter((request) => request.requestStatus === "Accepted")
      );

      setPendingRequests(
        requests.filter(
          (request) =>
            request.requestStatus === "Pending" ||
            request.requestStatus === "Denied"
        )
      );
    }
  }, [requests]);

  useEffect(() => {
    // Retrieve JWT token from local storage
    const tempToken = localStorage.getItem("token");
    // If token does not exist, redirect to login page
    if (!tempToken) {
      alert("Please login to continue");
      router.push("/login");
      return;
    }
    const fetchRequests = async () => {
      try {
        const token = localStorage.getItem("token");

        const res = await fetch(
          `/api/proxy?url=connectionReq/organization/getByID/${orgID}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (res.status < 400) {
          const result = await res.json();
          setRequests(result);
        } else {
          throw new Error("An error occurred.");
        }
      } catch (error) {
        console.log("Error:", error);
      }
    };

    fetchRequests();

    const fetchInfluencers = async () => {
      try {
        const token = localStorage.getItem("token");
        const res = await fetch("/api/proxy?url=influencers", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (res.status < 400) {
          const result = await res.json();
          setInfluencers(result);
        } else {
          throw new Error("An error occurred.");
        }
      } catch (error) {
        console.log("Error:", error);
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
          {pendingRequests?.length ? (
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
                      {pendingRequests.map((request, index) => (
                        <Tab key={index} label={<RequestCard {...request} />} />
                      ))}
                    </Tabs>
                  </Container>
                </Grid>
              </Grid>
            </Grid>
          ) : null}

          {acceptedRequests?.length ? (
            <Grid item xs={12}>
              <Grid container spacing={2} direction="column">
                <Grid item xs={12}>
                  <Typography
                    variant="h5"
                    color="#222AEF"
                    fontWeight="600"
                    mt={3}
                  >
                    Accepted requests
                  </Typography>
                </Grid>
                <Grid item xs={12}>
                  <Container maxWidth="lg">
                    <Tabs variant="scrollable" scrollButtons="auto">
                      {acceptedRequests.map((request, index) => (
                        <Tab key={index} label={<RequestCard {...request} />} />
                      ))}
                    </Tabs>
                  </Container>
                </Grid>
              </Grid>
            </Grid>
          ) : null}
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
