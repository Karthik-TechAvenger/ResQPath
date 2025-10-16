package com.src.service;

import com.src.model.Asset;
import com.src.model.UserHistory;

import java.util.List;

public interface HistoryService {

	void logUserQuery(String username, int x, int y, Asset newAsset);

	List<UserHistory> getUserHistory(String username);

	List<UserHistory> getAllHistory();
}
