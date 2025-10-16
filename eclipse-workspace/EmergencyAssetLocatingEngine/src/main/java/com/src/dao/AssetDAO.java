package com.src.dao;

import java.util.List;
import com.src.model.Asset;

public interface AssetDAO {
	boolean saveAssets(Asset asset);

	void deleteAssets(Asset asset);
}
