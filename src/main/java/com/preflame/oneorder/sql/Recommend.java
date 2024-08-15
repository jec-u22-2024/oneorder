package com.preflame.oneorder.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public class Recommend {
    public static JSONObject getRecommend() {
        JSONObject json = new JSONObject();
        json.put("category_name", "おすすめ");
        json.put("category_id", 0);
        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            String sql = "SELECT * FROM merchandice WHERE isRecommend = true";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            JSONArray arr = new JSONArray();

            while(rs.next()) {
                int id = rs.getInt("id");
                String merch_name = rs.getString("name");
                int price = rs.getInt("price");
                String image = rs.getString("image");

                JSONObject obj = new JSONObject();

                obj.put("id", id);
                obj.put("merch_name", merch_name);
                obj.put("price", price);
                obj.put("image", image);
                arr.put(obj);
            }
            if(arr.length() <= 0) {
                return null;
            }

            json.put("items", arr);
        } catch (SQLException e) {
            System.err.println("SQL error");
        }

        return json;
    }
}
