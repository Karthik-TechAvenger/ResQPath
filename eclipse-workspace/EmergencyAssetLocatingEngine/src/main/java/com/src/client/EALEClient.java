package com.src.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EALEClient {

    private final String baseUrl;

    public EALEClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getAllAssetsJson() throws Exception {
        String endpoint = baseUrl + "/webresources/myresource";
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        int status = conn.getResponseCode();
        InputStream is = (status >= 200 && status < 300) ? conn.getInputStream() : conn.getErrorStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            if (status < 200 || status >= 300) {
                throw new RuntimeException("HTTP " + status + " - " + sb.toString());
            }
            return sb.toString();
        } finally {
            conn.disconnect();
        }
    }
}
