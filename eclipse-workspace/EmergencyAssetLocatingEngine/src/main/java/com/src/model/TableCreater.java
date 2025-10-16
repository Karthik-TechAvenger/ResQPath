package com.src.model;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.src.anotations.Column;
import com.src.anotations.Table;
import com.src.util.DBUtil;

public class TableCreater {

	public static void createTable(Class<?> cls) {
		if (!cls.isAnnotationPresent(Table.class))
			return;

		Table table = cls.getAnnotation(Table.class);
		StringBuilder sb = new StringBuilder();

		sb.append("CREATE TABLE IF NOT EXISTS ").append(table.name()).append(" (");

		Field[] fields = cls.getDeclaredFields();

		for (Field f : fields) {
			if (f.isAnnotationPresent(Column.class)) {
				Column c = f.getAnnotation(Column.class);
				sb.append(c.name()).append(" ").append(c.type()).append(" ");
				if (c.primaryKey()) {
					if (c.type().toUpperCase().contains("INT")) {
						sb.append("PRIMARY KEY AUTO_INCREMENT");
					} else {
						sb.append("PRIMARY KEY");
					}
				}
				sb.append(", ");
			}
		}

		// Remove last comma
		sb.setLength(sb.length() - 2);
		sb.append(")");

		try (Connection con = DBUtil.getConnection()) {

			// ✅ Removed the risky DROP logic
			// if (isTableEmpty(con, table.name())) {
			// try (PreparedStatement ps = con.prepareStatement("DROP TABLE " +
			// table.name())) {
			// ps.executeUpdate();
			// System.out.println("Table `" + table.name() + "` was empty and dropped.");
			// } catch (Exception e) {
			// System.out.println("Cannot drop table `" + table.name() + "`: " +
			// e.getMessage());
			// }
			// }

			PreparedStatement ps = con.prepareStatement(sb.toString());
			int res = ps.executeUpdate();

			// ✅ Corrected success/error messages
			if (res == 0) {
				System.out.println("Table `" + table.name() + "` created or already exists.");
			} else {
				System.out.println("Error creating table `" + table.name() + "`");
			}

		} catch (Exception e) {
			System.err.println("Error creating table `" + table.name() + "`: " + e.getMessage());
		}
	}

	private static boolean isTableEmpty(Connection con, String tableName) {
		try {
			ResultSet rs = con.getMetaData().getTables(null, null, tableName, null);
			if (!rs.next())
				return false;

			String sql = "SELECT COUNT(*) FROM " + tableName;
			try (PreparedStatement ps = con.prepareStatement(sql); ResultSet countRs = ps.executeQuery()) {
				if (countRs.next())
					return countRs.getInt(1) == 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void createTables(Class<?>... cls) {
		for (Class<?> c : cls) {
			createTable(c);
		}
	}
}
