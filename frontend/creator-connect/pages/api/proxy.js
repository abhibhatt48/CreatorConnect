// pages/api/proxy.js

import axios from "axios";

export default async function handler(req, res) {
  const { method, body, query } = req;

  // Get the url parameter from the query
  const { url } = query;

  try {
    // Make the request to the external API
    const response = await axios({
      method: method.toLowerCase(),
      url: `http://localhost:8080/api/${url}`,
      headers: {
        Authorization: req.headers.authorization,
        "Content-Type": "application/json",
      },
      data: body,
    });

    // Forward status code and data from the external API to the client
    res.status(response.status).json(response.data);
  } catch (error) {
    // Forward status code and error message
    res.status(error.response?.status || 500).json({ message: error.message });
  }
}
