package com.src.service.impl;

import com.src.dao.HistoryDAO;
import com.src.daoImpl.HistoryDAOImpl;
import com.src.model.Asset;
import com.src.model.UserHistory;
import com.src.service.HistoryService;

import java.util.List;

public class HistoryServiceImpl implements HistoryService {

    private final HistoryDAO historyDAO;

    // No-arg constructor, DAO instantiated internally
    public HistoryServiceImpl() {
        this.historyDAO = new HistoryDAOImpl(); // Can switch to DI later
    }

    @Override
    public void logUserQuery(String username, int x, int y, Asset newAsset) {
        historyDAO.saveUserQuery(username, x, y, newAsset);
    }

    @Override
    public List<UserHistory> getUserHistory(String username) {
        return historyDAO.getUserHistory(username);
    }

    @Override
    public List<UserHistory> getAllHistory() {
        return historyDAO.getAllHistory();
    }
}
