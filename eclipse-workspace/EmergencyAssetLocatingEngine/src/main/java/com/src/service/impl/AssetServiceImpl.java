package com.src.service.impl;


import java.util.List;


import com.src.assetType.AssetType;
import com.src.client.EALEClient;
import com.src.dao.AssetDAO;
import com.src.daoImpl.AssetDAOImpl;
import com.src.model.Asset;
import com.src.service.AssetService;

public class AssetServiceImpl implements AssetService {

    private AssetDAO assetDAO;
    private EALEClient ealeClient;

    public AssetServiceImpl() {
        this.assetDAO = new AssetDAOImpl();
        this.ealeClient = new EALEClient("http://localhost:8081/EALEServer");
    }

    @Override
    public boolean addAsset(Asset asset) {
        return assetDAO.saveAssets(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
    	String json;
		try {
			json = ealeClient.getAllAssetsJson();
			return parseAssets(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return java.util.Collections.emptyList();
		}
    	
       
    }

    @Override
    public void removeAsset(Asset asset) {
        assetDAO.deleteAssets(asset);
    }
    private List<Asset> parseAssets(String json) {
        List<Asset> assets = new java.util.ArrayList<>();
        json = json.trim();
        if (!json.startsWith("[")) return assets;

        String trimmed = json.substring(1, json.length() - 1).trim();
        if (trimmed.isEmpty()) return assets;

        String[] items = trimmed.split("\\},\\s*\\{");
        for (int i = 0; i < items.length; i++) {
            String it = items[i];
            if (i == 0) it = it.startsWith("{") ? it.substring(1) : it;
            if (i == items.length - 1) it = it.endsWith("}") ? it.substring(0, it.length() - 1) : it;

            String type = null;
            Integer x = null, y = null;

            String[] props = it.split(",");
            for (String p : props) {
                String[] kv = p.split(":", 2);
                if (kv.length < 2) continue;
                String key = kv[0].replace("\"", "").trim();
                String val = kv[1].replace("\"", "").trim();
                if (key.equalsIgnoreCase("type")) type = val;
                if (key.equalsIgnoreCase("x")) x = Integer.valueOf(val);
                if (key.equalsIgnoreCase("y")) y = Integer.valueOf(val);
            }

            if (type != null && x != null && y != null) {
                Asset asset = new Asset(AssetType.valueOf(type.trim()), x, y);
                assets.add(asset);
            }
        }
        return assets;
    }
}
