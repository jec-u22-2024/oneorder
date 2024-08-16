package com.preflame.oneorder.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.preflame.oneorder.model.REModel;
import com.preflame.oneorder.sql.DbManager;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/table")
public class TableAPI {

    @GetMapping(path = "/status/{tableId}")
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
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        } catch(SQLException e) {
            System.err.println("SQLError");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.err.println("any error");
            return new REModel().getModel(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new REModel().getObjectToModel(json);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Object> changeStatus(@PathVariable("id") String id, @RequestBody String status) {
        JSONObject json = new JSONObject(status);
        String stat = json.getString("status");
        int res = -1;
        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            int tid = Integer.parseInt(id);
            String sql = "UPDATE tables SET status = ? WHERE id = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, stat);;
            pstmt.setInt(2, tid);
            res = pstmt.executeUpdate();
            if (res < 0) {
                System.err.println("error");
                return new REModel().getModel(HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            System.err.println("format error");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        } catch(SQLException e) {
            System.err.println("SQL error");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println();
            System.err.println("any error");
            return new REModel().getModel(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
