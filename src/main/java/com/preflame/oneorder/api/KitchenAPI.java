package com.preflame.oneorder.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.preflame.oneorder.model.REModel;
import com.preflame.oneorder.sql.DbManager;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/kitchen")
public class KitchenAPI {
    @GetMapping(path = "/orders")
    public ResponseEntity<Object> getOrders() {
        JSONObject json = new JSONObject();
        JSONArray ar = new JSONArray();
        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            String sql = "SELECT a.id, table_name, ordered_time, name, order_quant FROM aboutSlip a JOIN tables t ON a.table_id = t.id JOIN merchandice m ON a.merch_id = m.id WHERE cooked_flag = false";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id"); // aboutSlipのid
                String table = rs.getString("table_name");
                String ordered = rs.getString("ordered_time");
                String merchName = rs.getString("name");
                int amount = rs.getInt("order_quant");

                JSONObject obj = new JSONObject();
                obj.put("item_id", id);
                obj.put("table_name", table);
                obj.put("item_name", merchName);
                obj.put("count", amount);
                obj.put("date", ordered);
                // obj.put("order_time", ordered); //これよくわからん、一応注文時間を入れておく
                ar.put(obj);
            }
        } catch (SQLException e) {
            System.err.println("error");
        }
        json.put("table_id", ar);

        return new REModel().getObjectToModel(json);
    }

    @PutMapping("/cooked/{id}")
    public ResponseEntity<Object> cookedMerch(@PathVariable("id") String id) {
        DbManager man = DbManager.getInstance();
        int result = -1;
        try (Connection cn = man.getConnection()) {
            int cid = Integer.parseInt(id);
            String sql = "UPDATE aboutSlip SET cooked_flag = true WHERE id = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, cid);

            result = pstmt.executeUpdate();
        } catch(NumberFormatException e) {
            System.err.println("format error");
        } catch (SQLException e) {
            System.err.println("SQL Error");
        } catch (Exception e) {
            System.err.println("any error");
        }
        
        if(result > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
