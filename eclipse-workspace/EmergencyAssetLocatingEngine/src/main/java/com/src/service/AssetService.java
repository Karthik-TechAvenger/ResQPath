package com.src.service;

import java.util.List;
import com.src.model.Asset;

public interface AssetService {
    boolean addAsset(Asset asset);
    List<Asset> getAllAssets();
    void removeAsset(Asset asset);
}
