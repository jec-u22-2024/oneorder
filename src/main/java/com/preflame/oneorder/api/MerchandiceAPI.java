package com.preflame.oneorder.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.preflame.oneorder.model.REModel;
import com.preflame.oneorder.sql.DbManager;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/master/merchandices")
public class MerchandiceAPI {
    
    @GetMapping
    public ResponseEntity<Object> getItems() {
        JSONArray json = new JSONArray();
        DbManager man = DbManager.getInstance();
        try(Connection cn = man.getConnection()) {
            String sql = "SELECT * FROM merchandice m LEFT OUTER JOIN category c ON m.category_id = c.id";
            // String sql = "SELECT * FROM merchandice";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                boolean isVisible = rs.getBoolean("isVisible");
                int category_id = rs.getInt("category_id");
                String category = rs.getString("category_name");
                String category_color = rs.getString("category_color");

                
                JSONObject obj = new JSONObject();
                
                obj.put("id", id);
                obj.put("itemName", name);
                obj.put("price", price);
                obj.put("itemImage", image);
                obj.put("desc", description);
                obj.put("display", isVisible);
                obj.put("category_id", category_id);
                obj.put("category", category);
                obj.put("colorCode", category_color);
                json.put(obj);
            }
        } catch(SQLException e) {
            System.err.println("error");
        }

        REModel mod = new REModel();
        return mod.getArrayToModel(json);
    }

    @PostMapping
    public ResponseEntity<Object> insertMerch(@RequestBody String Jsons) {
        JSONObject json = new JSONObject(Jsons);
        System.out.println(json);
        String image = json.getString("image");
        int cid = json.getInt("category_id");
        int price = json.getInt("price");
        boolean display = json.getBoolean("display");
        String name = json.getString("name");
        String desc = json.getString("desc");

        DbManager man = DbManager.getInstance();
        int res = -1;
        try (Connection cn = man.getConnection()) {
            String sql = "INSERT INTO merchandice(name, price, image, isVisible, description, category_id) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setString(3, image);
            pstmt.setBoolean(4, display);
            pstmt.setString(5, desc);
            pstmt.setInt(6, cid);
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

    @PutMapping
    public ResponseEntity<Object> updateMerch(@RequestBody String Jsons) {
        JSONObject json = new JSONObject(Jsons);
        System.out.println(json);
        int id = json.getInt("id");
        int cid = json.getInt("category");
        String name = json.getString("name");
        int price = json.getInt("price");
        String image = json.getString("image");
        String desc = "";
        try {
            desc = json.getString("desc");
        } catch (JSONException e) {
            desc = "";
        }
        boolean display = json.getBoolean("display");

        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            String sql = "UPDATE merchandice SET name = ?, price = ?, image = ?, description = ?, isVisible = ?, category_id = ? WHERE id = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setString(3, image);
            pstmt.setString(4, desc);
            pstmt.setBoolean(5, display);
            pstmt.setInt(6, cid);
            pstmt.setInt(7, id);
            int res = -1;
            res = pstmt.executeUpdate();
            if(res < 0) {
                System.err.println("error");
            }
        } catch (SQLException e) {
            System.err.println("SQL error");
        }

        return new REModel().getModel();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> toggleVisible(@PathVariable("id") String id, @RequestBody String Jsons) {
        DbManager man = DbManager.getInstance();
        JSONObject json = new JSONObject(Jsons);
        boolean display = json.getBoolean("display");
        try (Connection cn = man.getConnection()) {
            String sql = "UPDATE merchandice SET isVisible = ? WHERE id = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setBoolean(1, !display);
            int mid = Integer.parseInt(id);
            pstmt.setInt(2, mid);

            int res = -1;
            res = pstmt.executeUpdate();
            if(res < 0) {
                System.err.println("error");
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMerch(@PathVariable("id") String id) {
        System.out.println(id);
        DbManager man = DbManager.getInstance();

        // TODO: 気が向いたら画像も削除する
        try (Connection cn = man.getConnection()) {
            int res = -1;
            int mid = Integer.parseInt(id);
            String sql = "DELETE FROM merchandice WHERE id = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, mid);

            res = pstmt.executeUpdate();
            if(res < 0) {
                System.err.println("error");
            }
        } catch (NumberFormatException e) {
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        } catch (SQLException e) {
            System.err.println("SQL error");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        }
        return new REModel().getModel();
    }
}
