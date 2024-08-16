package com.preflame.oneorder.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.preflame.oneorder.model.REModel;
import com.preflame.oneorder.sql.DbManager;

@RestController
@RequestMapping("/api/master/tables")
public class MasterTablesAPI {
    
    @GetMapping
    public ResponseEntity<Object> getTables() {
        JSONArray json = new JSONArray();
        DbManager man = DbManager.getInstance();
        try(Connection cn = man.getConnection()) {
            String sql = "SELECT * FROM tables";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");

                String name = rs.getString("table_name");
                String smoking = rs.getString("smoking"); // 現状 喫煙、禁煙になってるのでHTML上も兼ねて英語にしたい
                String type = rs.getString("seat_type"); // こちらも同様
                boolean isVisible = rs.getBoolean("isVisible");
                int capacity = rs.getInt("capacity");
                int maxCapacity = rs.getInt("max_capacity");


                JSONObject obj = new JSONObject();
                obj.put("id", id);
                obj.put("name", name);
                obj.put("low_capacity", capacity);
                obj.put("capacity", maxCapacity);
                obj.put("smoking", smoking);
                obj.put("type", type);
                obj.put("display", isVisible);
                json.put(obj);
            }
        } catch(SQLException e) {
            System.err.println("error");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        }

        REModel mod = new REModel();
        return mod.getArrayToModel(json);
    }

    @PostMapping
    public ResponseEntity<Object> insertTable(@RequestBody String Jsons) {
        JSONObject json = new JSONObject(Jsons);
        String name = json.getString("name");
        String type = json.getString("type");
        String smoking = json.getString("smoking");
        int capacity = json.getInt("capacity");
        int low_capacity = json.getInt("low_capacity");
        boolean display = json.getBoolean("display");

        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            int res = -1;
            String sql = "INSERT INTO tables(table_name, smoking, seat_type, isVisible, capacity, max_capacity) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, smoking);
            pstmt.setString(3, type);
            pstmt.setBoolean(4, display);
            pstmt.setInt(5, low_capacity);
            pstmt.setInt(6, capacity);

            res = pstmt.executeUpdate();
            if(res < 0) {
                System.err.println("error");
                return new REModel().getModel(HttpStatus.BAD_REQUEST);
            }
        } catch (SQLException e) {
            System.err.println("SQL error");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        }


        return new REModel().getModel();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> toggleVisible(@PathVariable("id") String id, @RequestBody String Jsons) {
        JSONObject json = new JSONObject(Jsons);
        boolean display = json.getBoolean("display");
        
        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            int tid = Integer.parseInt(id);
            String sql = "UPDATE tables SET isVisible = ? WHERE id = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setBoolean(1, !display);
            pstmt.setInt(2, tid);
            int res = -1;
            res = pstmt.executeUpdate();
            if(res < 0) {
                System.err.println("error");
                return new REModel().getModel(HttpStatus.BAD_REQUEST);
            }
        } catch(NumberFormatException e) {
            System.err.println("format error");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        }
        catch (SQLException e) {
            System.err.println("SQL error");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        }
        
        return new REModel().getModel();
    }

    @PutMapping
    public ResponseEntity<Object> updateTable(@RequestBody String Jsons) {
        JSONObject json = new JSONObject(Jsons);
        DbManager man = DbManager.getInstance();
        int id = json.getInt("id");
        String name = json.getString("name");
        String smoking = json.getString("smoking");
        String type = json.getString("type");
        int low_capacity = json.getInt("low_capacity");
        int capacity = json.getInt("capacity");
        boolean display = json.getBoolean("display");


        try (Connection cn = man.getConnection()) {
            String sql = "UPDATE tables SET table_name = ?, smoking = ?, seat_type = ?, isVisible = ?, capacity = ?, max_capacity = ? WHERE id = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, smoking);
            pstmt.setString(3, type);
            pstmt.setBoolean(4, display);
            pstmt.setInt(5, low_capacity);
            pstmt.setInt(6, capacity);
            pstmt.setInt(7, id);
            int res = -1;
            res = pstmt.executeUpdate();
            if(res < 0) {
                System.err.println("error");
                return new REModel().getModel(HttpStatus.BAD_REQUEST);
            }
        } catch (SQLException e) {
            System.err.println("SQL error");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        }

        return new REModel().getModel();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTable(@PathVariable("id") String id) {
        DbManager man = DbManager.getInstance();

        try (Connection cn = man.getConnection()) {
            int tid = Integer.parseInt(id);
            String sql = "DELETE FROM tables WHERE id = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, tid);
            int res = -1;
            res = pstmt.executeUpdate();
            if(res < 0) {
                System.err.println("error");
                return new REModel().getModel(HttpStatus.BAD_REQUEST);
            }
        } catch(NumberFormatException e) {
            System.err.println("format error");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        } catch (SQLException e) {
            System.err.println("SQL error");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        }

        return new REModel().getModel();
    }
}
