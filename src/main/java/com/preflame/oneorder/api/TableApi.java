package com.preflame.oneorder.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.preflame.oneorder.model.REModel;
import com.preflame.oneorder.sql.DbManager;

@RestController
@RequestMapping("/api")
public class TableApi {

    @GetMapping(path = "/table/status/{tableId}")
    public ResponseEntity<Object> getStatus(@PathVariable("tableId") String tableId) {
        JSONObject json = new JSONObject();
        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            int tid = Integer.parseInt(tableId);
            String sql = "SELECT * FROM tables WHERE id = ? AND isVisible = true";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, tid);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                // String table = rs.getString("table_name");
                String status = rs.getString("status");
                // json.put("id", id);
                json.put("table", id);
                json.put("status", status);
            } else {
                json.put("error", "存在しないか、使用できないテーブルです。");
            }
        } catch(NumberFormatException e) {
            System.err.println("format error");
        } catch(SQLException e) {
            System.err.println("SQLError");
        }
        catch (Exception e) {
            System.err.println("any error");
        }

        return new REModel().getObjectToModel(json);
    }
}
