import React from "react";
import InstagramIcon from "@mui/icons-material/Instagram";
import TwitterIcon from "@mui/icons-material/Twitter";
import YouTubeIcon from "@mui/icons-material/YouTube";
import FacebookIcon from "@mui/icons-material/Facebook";
import { IconButton, Grid } from "@mui/material";
import Link from "next/link";
import Image from "next/image";
import TwitchLogo from "../../icons/twitch.svg";
import TikTokLogo from "../../icons/tiktok.svg";

export default function SocialMediaIcons() {
  return (
    <Grid container justifyContent="center">
      <Grid item xs={2}>
        <Link href="https://instagram.com" passHref>
          <IconButton
            color="primary"
            component="a"
            target="_blank"
            rel="noopener noreferrer"
          >
            <InstagramIcon style={{ color: "#E1306C" }} />
          </IconButton>
        </Link>
      </Grid>
      <Grid item xs={2}>
        <Link href="https://twitter.com" passHref>
          <IconButton
            color="primary"
            component="a"
            target="_blank"
            rel="noopener noreferrer"
          >
            <TwitterIcon style={{ color: "#1DA1F2" }} />
          </IconButton>
        </Link>
      </Grid>
      <Grid item xs={2}>
        <Link href="https://tiktok.com" passHref>
          <IconButton
            color="primary"
            component="a"
            target="_blank"
            rel="noopener noreferrer"
          >
            <Image src={TikTokLogo} width={24} height={24} alt="TikTok Logo" />
          </IconButton>
        </Link>
      </Grid>
      <Grid item xs={2}>
        <Link href="https://youtube.com" passHref>
          <IconButton
            color="primary"
            component="a"
            target="_blank"
            rel="noopener noreferrer"
          >
            <YouTubeIcon style={{ color: "#FF0000" }} />
          </IconButton>
        </Link>
      </Grid>
      <Grid item xs={2}>
        <Link href="https://facebook.com" passHref>
          <IconButton
            color="primary"
            component="a"
            target="_blank"
            rel="noopener noreferrer"
          >
            <FacebookIcon style={{ color: "#4267B2" }} />
          </IconButton>
        </Link>
      </Grid>
      <Grid item xs={2}>
        <Link href="https://twitch.tv" passHref>
          <IconButton
            color="primary"
            component="a"
            target="_blank"
            rel="noopener noreferrer"
          >
            <Image src={TwitchLogo} width={24} height={24} alt="Twitch Logo" />
          </IconButton>
        </Link>
      </Grid>
    </Grid>
  );
}
