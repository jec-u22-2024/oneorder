package com.preflame.oneorder.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.preflame.oneorder.TempJson;
import com.preflame.oneorder.model.REModel;
import com.preflame.oneorder.sql.DbManager;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin
@RequestMapping("/api/master")
public class RegiAPI {
    
    @GetMapping
    public ResponseEntity<Object> getAny() {
        TempJson tmpJson = new TempJson();

        return tmpJson.getJson();
    }

    @GetMapping(path = "/categorys")
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

    @GetMapping("/regi/tables")
    public ResponseEntity<Object> getRegiTables() {
        JSONArray json = new JSONArray();
        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            // String sql = "SELECT * FROM tables WHERE isVisible = true";
            String sql = "SELECT * FROM tables";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("table_name");
                String status = rs.getString("status");
                JSONObject obj = new JSONObject();

                obj.put("id", id);
                obj.put("number", name);
                obj.put("status", status);

                PreparedStatement totalstmt = cn.prepareStatement("SELECT SUM(a.order_quant * m.price) sum FROM aboutSlip a JOIN merchandice m ON a.merch_id = m.id WHERE table_id = ? && slip_id IS NULL");
                totalstmt.setInt(1, id);
                ResultSet totalrs = totalstmt.executeQuery();
                if(totalrs.next()) {
                    int total = totalrs.getInt("sum");
                    obj.put("total", total);
                }
                json.put(obj);
            }

        } catch(SQLException e) {
            System.err.println("error");
        }
        
        REModel mod = new REModel();

        return mod.getArrayToModel(json);
    }

    @GetMapping("/merchandices")
    public ResponseEntity<Object> getItems() {
        JSONArray json = new JSONArray();
        DbManager man = DbManager.getInstance();
        try(Connection cn = man.getConnection()) {
            String sql = "SELECT * FROM merchandice m JOIN category c ON m.category_id = c.id";
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

    @GetMapping("/tables")
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
        }

        REModel mod = new REModel();
        return mod.getArrayToModel(json);
    }

    @GetMapping("/regi/order/detail/{table}")
    public ResponseEntity<Object> getDetail(@PathVariable("table") String table) {
        JSONObject json = new JSONObject();
        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()){
            int total = 0;
            String sql = "SELECT a.id, order_quant, merch_id, name, price, table_id, table_name FROM aboutSlip a JOIN merchandice m ON a.merch_id = m.id JOIN tables t ON a.table_id = t.id HAVING table_name = ?"; // table_name用
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, table);
            ResultSet rs = pstmt.executeQuery();
            
            JSONArray ar = new JSONArray();
            while(rs.next()) {
                JSONObject obj = new JSONObject();
                int mid = rs.getInt("merch_id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int amount = rs.getInt("order_quant");

                total += amount * price;
                obj.put("id", mid);
                obj.put("name", name);
                obj.put("price", price);
                obj.put("amount", amount);
                ar.put(obj);
            }
            json.put("table", table);
            json.put("items", ar);
            json.put("total", total);
            // String sql = "SELECT a.id, order_quant, merch_id, name, price, table_id, table_name FROM aboutSlip a JOIN merchandice m ON a.merch_id = m.id JOIN tables t ON a.table_id = t.id HAVING table_id = ?"; // table_id用
        } catch (SQLException e) {
            System.err.println("error");
        }

        return new REModel().getObjectToModel(json);
        // return new TempJson().getJson();
    }

    @GetMapping("/historys/{date}")
    public ResponseEntity<Object> getHistory(@PathVariable("date") String date) {
        JSONArray arr = new JSONArray();
        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            String sql = "SELECT DISTINCT s.*, table_name FROM aboutSlip ab JOIN slip s ON ab.slip_id = s.id JOIN tables t ON ab.table_id = t.id WHERE DATE_FORMAT(leave_date, '%Y-%m-%d') = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String d = rs.getString("leave_date");
                int id = rs.getInt("id");
                String tableName = rs.getString("table_name");
                int totalprice = rs.getInt("totalprice");
                int received = rs.getInt("amount_paid");
                JSONObject obj = new JSONObject();


                obj.put("transaction_datetime", d);
                obj.put("slip_id", id);
                obj.put("table_number", tableName);
                obj.put("total_price", totalprice);
                obj.put("received", received);
                arr.put(obj);
            }
        } catch (SQLException e) {
            System.err.println("error");
        }
        return new REModel().getArrayToModel(arr);
        // return new TempJson().getJson();
    }

    @GetMapping("/history/details/{slipId}")
    public ResponseEntity<Object> getHistoryDetail(@PathVariable("slipId") String slipId) {
        JSONArray json = new JSONArray();
        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            int argSlipId = Integer.parseInt(slipId);


            String sql = "SELECT order_quant, merch_id, name, price FROM aboutSlip ab JOIN merchandice m ON ab.merch_id = m.id WHERE slip_id = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, argSlipId);
            ResultSet rs = pstmt.executeQuery();


            while(rs.next()) {
                JSONObject obj = new JSONObject();
                int id = rs.getInt("merch_id");
                int amount = rs.getInt("order_quant");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int total = price * amount;

                obj.put("id", id);
                obj.put("name", name);
                obj.put("amount", amount);
                obj.put("price", price);
                obj.put("total_price", total);
                json.put(obj);
            }
        } catch(NumberFormatException e) {
            System.err.println("format error");
        } catch(SQLException e) {
            System.err.println("SQL Error");
        } catch (Exception e) {
            // TODO: handle exception
        }

        return new REModel().getArrayToModel(json);
        // return new TempJson().getJson();
    }

    @PostMapping("/regi/accounting")
    public ResponseEntity<Object> postMethodName(@RequestBody String Jsons) {
        JSONObject json = new JSONObject(Jsons);
        int total = json.getInt("total"); // totalprice
        int bills = json.getInt("bill"); // amount_paid
        int changeMoney = bills - total; // change_money
        int tid = json.getInt("id");
        int res1 = -1;
        int res2 = -1;
        int res3 = -1;

        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            String sql = "INSERT INTO slip(totalprice, amount_paid, change_money) VALUES(?, ?, ?)"; // total bills changeMoney
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, total);
            pstmt.setInt(2, bills);
            pstmt.setInt(3, changeMoney);
            res1 = pstmt.executeUpdate();
            String sql2 = "SELECT id FROM slip ORDER BY id DESC LIMIT 1;"; // 最新のslip_idを取得する
            pstmt = cn.prepareStatement(sql2);
            ResultSet rs = pstmt.executeQuery();
            int sid;
            if(rs.next()) {
                sid = rs.getInt("id");
                if(sid != 0) {
                    String sql3 = "UPDATE aboutSlip SET slip_id = ? WHERE table_id = ? AND slip_id IS NULL"; //sid tid
                    pstmt = cn.prepareStatement(sql3);
                    pstmt.setInt(1, sid);
                    pstmt.setInt(2, tid);
                    res2 = pstmt.executeUpdate();
                    String sql4 = "UPDATE tables SET status = '空き状態' WHERE id = ?"; // tid
                    pstmt = cn.prepareStatement(sql4);
                    pstmt.setInt(1, tid);
                    res3 = pstmt.executeUpdate();
                }
            }
            if(res2 < 0 || res3 < 0) {
                System.err.println("accounting error");
            } else if(res1 < 0) {
                System.err.println("argument error");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error");
        }
        
        return new REModel().getModel();
    }
    

    @PostMapping("/merchandices")
    public ResponseEntity<Object> insertMerch(@RequestBody String Jsons) {
        JSONObject json = new JSONObject(Jsons);
        System.out.println(json);
        

        return new TempJson().getJson();
    }

    @PutMapping("/merchandices/{id}")
    public ResponseEntity<Object> updateMerch(@PathVariable("id") String id, @RequestBody String Jsons) {
        JSONObject json = new JSONObject(Jsons);
        System.out.println(json);
        System.out.println(id);
        // System.out.println(Jsons);
        // JSONObject obj = new JSONObject(Jsons);
        // System.out.println(obj);
        
        TempJson tmpJson = new TempJson();
        return tmpJson.getJson();
    }
    
    /**
     * 画像アップロードの処理
     * @param file
     * @return
     */
    @PostMapping("/upload/merchImage")
    public ResponseEntity<Object> uploadMerch(@RequestPart("images") MultipartFile file) {
        JSONObject json = new JSONObject();
        HttpStatus stat = null;

        // String path = "./src/main/resources/static/upload/img" + file.getOriginalFilename();
        Path dst = Path.of("./src/main/resources/static/upload/img", file.getOriginalFilename());
        String path = dst.toString();
        File conv = new File(path);
        conv.exists(); // conv が未使用とうるさいのでただただbooleanを出力する処理を置いただけ
        try {
            Files.copy(file.getInputStream(), dst);
            stat = HttpStatus.CREATED;
        } catch(FileAlreadyExistsException e) {
            try {
                Files.delete(dst);
                Files.copy(file.getInputStream(), dst);
            } catch(IOException ex) {
                System.err.println("I/O例外");
            }
            stat = HttpStatus.ACCEPTED;
        } catch(Exception e) {
            stat = HttpStatus.BAD_REQUEST;
            json.put("error", "Any Error");
        }
        // System.out.println(json.toString());
        ResponseEntity<Object> res = new ResponseEntity<Object>(json.toMap(), stat);
        return res;
    }
    
}
