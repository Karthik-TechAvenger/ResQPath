package com.src.dao;

import com.src.model.Asset;
import com.src.model.UserHistory;

import java.util.List;

public interface HistoryDAO {

    void saveUserQuery(String username, int x, int y, Asset newAsset);

    List<UserHistory> getUserHistory(String username);

    List<UserHistory> getAllHistory();
}
