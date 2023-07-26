import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import { Grid, Button } from "@mui/material";

export default function RequestCard() {
  return (
    <Card sx={{ maxHeight: 150, backgroundColor: "#D9D9D9" }}>
      <Grid container>
        <Grid item xs={4}>
          <CardMedia
            component="img"
            image="https://img.freepik.com/free-vector/influencer-concept-illustration_114360-681.jpg"
            alt="influencerImage"
            sx={{
              objectFit: "cover",
              height: "100%",
              width: "100%",
            }}
          />
        </Grid>
        <Grid item xs={8}>
          <CardContent>
            <Grid container textAlign="left">
              <Grid item xs={12}>
                <Typography
                  gutterBottom
                  variant="h6"
                  component="div"
                  sx={{ textTransform: "none" }}
                >
                  Swoozie
                </Typography>
              </Grid>
              <Grid item xs={12}>
                <Button
                  variant="contained"
                  color="success"
                  size="small"
                  sx={{ borderRadius: "50px", textTransform: "none" }}
                >
                  Accepted
                </Button>
              </Grid>
              <Grid item xs={12} mt={1}>
                <Button
                  variant="contained"
                  sx={{
                    borderRadius: "50px",
                    textTransform: "none",
                    backgroundColor: "#e8eae0",
                    color: "#000000",
                    "&:hover": {
                      backgroundColor: "rgba(232, 234, 224, 0.4)",
                      color: "#000000",
                    },
                  }}
                  size="small"
                >
                  View Request
                </Button>
              </Grid>
            </Grid>
          </CardContent>
        </Grid>
      </Grid>
    </Card>
  );
}
