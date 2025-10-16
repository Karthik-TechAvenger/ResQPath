package General.EALEServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class AssetDAOImpl implements AssetDAO {
	@Override
	public List<Asset> getAllAssets() {
		List<Asset> assets = new ArrayList<>();
		String sql = "SELECT * FROM assets";

		try (Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Asset asset = new Asset(AssetType.valueOf(rs.getString("type").trim()), rs.getInt("x"), rs.getInt("y"));
				assets.add(asset);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return assets;
	}

}
