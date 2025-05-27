import React, { useEffect, useState } from "react";
import {
  Card,
  CardContent,
  Typography,
  Grid,
  CircularProgress,
} from "@mui/material";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
  CartesianGrid,
} from "recharts";

const StockDashboard = () => {
  const [priceData, setPriceData] = useState([]);
  const [correlationData, setCorrelationData] = useState([]);
  const [loading, setLoading] = useState(true);

  const selectedTickers = ["NVIDIA", "PYPL"];
  const minutes = 30;

  // Fetch price history for NVIDIA
  const fetchPriceHistory = async () => {
    try {
      const res = await fetch(
        `http://localhost:3001/stocks/NVIDIA?minutes=${minutes}&aggregation=average`
      );
      const data = await res.json();
      setPriceData(data); // Make sure the backend returns [{ time, price }]
    } catch (error) {
      console.error("Error fetching price data:", error);
    }
  };

  // Fetch correlation heatmap
  const fetchCorrelation = async () => {
    try {
      const query = new URLSearchParams();
      query.append("minutes", minutes);
      selectedTickers.forEach((ticker) => query.append("ticker", ticker));
      const res = await fetch(
        `http://localhost:3001/stockcorrelation?${query.toString()}`
      );
      const data = await res.json();
      setCorrelationData(data); // Expecting [{ ticker1, ticker2, correlation }]
    } catch (error) {
      console.error("Error fetching correlation data:", error);
    }
  };

  useEffect(() => {
    setLoading(true);
    Promise.all([fetchPriceHistory(), fetchCorrelation()]).then(() =>
      setLoading(false)
    );
  }, []);

  return (
    <Card sx={{ margin: 4, padding: 3 }}>
      <CardContent>
        <Typography variant="h4" gutterBottom>
          Stock Analytics Dashboard
        </Typography>

        {loading ? (
          <CircularProgress />
        ) : (
          <Grid container spacing={4}>
            {/* Price Chart */}
            <Grid item xs={12} md={6}>
              <Typography variant="h6">
                NVIDIA Price History (Avg, {minutes} mins)
              </Typography>
              <ResponsiveContainer width="100%" height={300}>
                <LineChart data={priceData}>
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="time" />
                  <YAxis />
                  <Tooltip />
                  <Line
                    type="monotone"
                    dataKey="price"
                    stroke="#1976d2"
                    strokeWidth={2}
                  />
                </LineChart>
              </ResponsiveContainer>
            </Grid>

            {/* Correlation Heatmap */}
            <Grid item xs={12} md={6}>
              <Typography variant="h6">Stock Correlation Heatmap</Typography>
              <Grid container spacing={1}>
                {correlationData.map((row, index) => (
                  <Grid item xs={12} key={index}>
                    <Typography>
                      {row.ticker1} - {row.ticker2}:{" "}
                      <strong>{(row.correlation * 100).toFixed(2)}%</strong>
                    </Typography>
                  </Grid>
                ))}
              </Grid>
            </Grid>
          </Grid>
        )}
      </CardContent>
    </Card>
  );
};

export default StockDashboard;
