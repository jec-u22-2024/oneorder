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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.preflame.oneorder.model.REModel;
import com.preflame.oneorder.sql.DbManager;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/master/categorys")
public class CategoryAPI {

    @GetMapping
    public ResponseEntity<Object> getCategorys() {
        DbManager man = DbManager.getInstance();
        JSONArray json = new JSONArray();
        try(Connection cn = man.getConnection()) {
            String sql = "SELECT * FROM category";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("category_name");
                String color = rs.getString("category_color");
                boolean isVisible = rs.getBoolean("isVisible");
                JSONObject obj = new JSONObject();
                obj.put("id", id);
                obj.put("name", name);
                obj.put("color", color);
                obj.put("display", isVisible);
                json.put(obj);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        REModel mod = new REModel();
        return mod.getArrayToModel(json);
    }

    @PostMapping
    public ResponseEntity<Object> insertCategory(@RequestBody String Jsons) {
        JSONObject json = new JSONObject(Jsons);
        String name = json.getString("name");
        String color = json.getString("color");
        boolean display = json.getBoolean("display");

        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            int res = -1;
            String sql = "INSERT INTO category(category_name, category_color, isVisible) VALUES(?, ?, ?)";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, color);
            pstmt.setBoolean(3, display);

            res = pstmt.executeUpdate();
            if(res < 0) {
                System.err.println("error");
            }
            
        } catch (SQLException e) {
            System.err.println("SQL error");
            REModel bad = new REModel();
            bad.setStatus(HttpStatus.BAD_REQUEST);
            return bad.getModel();
        }

        return new REModel().getModel();
        // return new TempJson().getJson();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> toggleVisible(@PathVariable("id") String id, @RequestBody String Jsons) {
        JSONObject json = new JSONObject(Jsons);
        boolean display = json.getBoolean("display");
        DbManager man = DbManager.getInstance();

        try (Connection cn = man.getConnection()) {
            int tid = Integer.parseInt(id);
            String sql = "UPDATE category SET isVisible = ? WHERE id = ?";
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
        } catch (SQLException e) {
            System.err.println("SQL error");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        }

        return new REModel().getModel();
    }

    @PutMapping
    public ResponseEntity<Object> updateCategory(@RequestBody String Jsons) {
        JSONObject json = new JSONObject(Jsons);
        int id = json.getInt("id");
        String name = json.getString("name");
        String color = json.getString("color");
        boolean display = json.getBoolean("display");

        DbManager man = DbManager.getInstance();

        try (Connection cn = man.getConnection()) {
            String sql = "UPDATE category SET category_name = ?, category_color = ?, isVisible = ? WHERE id = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, color);
            pstmt.setBoolean(3, display);
            pstmt.setInt(4, id);
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
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") String id) {
        DbManager man = DbManager.getInstance();

        try (Connection cn = man.getConnection()) {
            int tid = Integer.parseInt(id);
            String sql = "DELETE FROM category WHERE id = ?";
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
