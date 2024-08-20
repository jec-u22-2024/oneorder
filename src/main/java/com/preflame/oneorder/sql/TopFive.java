package com.preflame.oneorder.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 注文された上位5つを取得するクラス
 * 
 * 未使用
 */
public class TopFive {
    public static JSONObject getTops() {
        JSONObject json = new JSONObject();
        // JSONArray json = new JSONArray();
        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            int totals = 1; //平均を求めるための合計
            String sql2 = "SELECT SUM(order_quant) total FROM aboutSlip";
            PreparedStatement pstmt = cn.prepareStatement(sql2);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                int total = rs.getInt("total");
                json.put("total", total);
                totals = total;
            }
            

            String sql1 = "SELECT SUM(order_quant) total, name, merch_id FROM aboutSlip ab JOIN merchandice m ON ab.merch_id = m.id WHERE isVisible = true GROUP BY name, merch_id ORDER BY total DESC LIMIT 5";
            pstmt = cn.prepareStatement(sql1);
            rs = pstmt.executeQuery();
            JSONArray ar = new JSONArray();


            int pcts = 100;
            while (rs.next()) {
                int mid = rs.getInt("merch_id");
                int total = rs.getInt("total");
                String name = rs.getString("name");

                double pct = (double)total / totals * 100; // 注文の割合を切り捨てで計算
                pcts -= (int) pct;


                JSONObject obj = new JSONObject();

                obj.put("id", mid);
                obj.put("name", name);
                obj.put("total", total);
                obj.put("pct", (int) pct);
                ar.put(obj);
            }
            json.put("topfive", ar);
            json.put("other", pcts);

        } catch (SQLException e) {
            System.err.println("SQL error");
            return null;
        }

        if(json.getInt("pct") == 100) {
            return null;
        } else {
            return json;
        }
    }
}
