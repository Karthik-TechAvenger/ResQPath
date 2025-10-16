package com.src.jtesting;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.src.assetType.AssetType;
import com.src.daoImpl.AssetDAOImpl;
import com.src.model.Asset;
import com.src.model.OfficeGrid;
import com.src.model.SharedOfficeGrid;
import com.src.util.DBUtil;

class AssetDAO {

    private AssetDAOImpl assetDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private OfficeGrid mockGrid;

    @BeforeEach
    void setUp() throws Exception {
        assetDAO = new AssetDAOImpl();
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        mockGrid = mock(OfficeGrid.class);
    }

    @Test
    void testSaveAssets_Success() throws Exception {
        Asset asset = new Asset(AssetType.EMERGENCY_EXIT, 5, 10);

        try (MockedStatic<DBUtil> dbMock = mockStatic(DBUtil.class);
             MockedStatic<SharedOfficeGrid> gridMock = mockStatic(SharedOfficeGrid.class)) {

            gridMock.when(SharedOfficeGrid::getInstance).thenReturn(mockGrid);
            when(mockGrid.add(asset)).thenReturn(true);

            dbMock.when(DBUtil::getConnection).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeUpdate()).thenReturn(1);

            boolean result = assetDAO.saveAssets(asset);
            assertTrue(result, "Expected asset to be saved successfully");
        }
    }

    @Test
    void testSaveAssets_GridRejects() throws Exception {
        Asset asset = new Asset(AssetType.FIRE_EXTINGUISHER, 3, 4);

        try (MockedStatic<SharedOfficeGrid> gridMock = mockStatic(SharedOfficeGrid.class)) {
            gridMock.when(SharedOfficeGrid::getInstance).thenReturn(mockGrid);
            when(mockGrid.add(asset)).thenReturn(false);

            boolean result = assetDAO.saveAssets(asset);
            assertFalse(result, "Saving should fail when grid rejects placement");
        }
    }

    @Test
    void testGetAllAssets() throws Exception {
        try (MockedStatic<DBUtil> dbMock = mockStatic(DBUtil.class)) {
            dbMock.when(DBUtil::getConnection).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true, false);
            when(mockResultSet.getString("type")).thenReturn("LAPTOP");
            when(mockResultSet.getInt("x")).thenReturn(2);
            when(mockResultSet.getInt("y")).thenReturn(6);

            List<Asset> assets = assetDAO.getAllAssets();
            assertEquals(1, assets.size());
            assertEquals(AssetType.FIRST_AID_KIT, assets.get(0).getType());
        }
    }

    @Test
    void testDeleteAssets() throws Exception {
        Asset asset = new Asset(AssetType.FIRE_EXTINGUISHER, 7, 9);

        try (MockedStatic<DBUtil> dbMock = mockStatic(DBUtil.class)) {
            dbMock.when(DBUtil::getConnection).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeUpdate()).thenReturn(1);

            assetDAO.deleteAssets(asset);

            verify(mockPreparedStatement, times(1)).setInt(1, 7);
            verify(mockPreparedStatement, times(1)).setInt(2, 9);
        }
    }
}
