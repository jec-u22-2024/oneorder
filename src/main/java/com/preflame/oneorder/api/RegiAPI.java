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
import org.springframework.web.bind.annotation.DeleteMapping;
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

                PreparedStatement totalstmt = cn.prepareStatement("SELECT SUM(a.order_quant * m.price) sum FROM aboutSlip a JOIN merchandice m ON a.merch_id = m.id WHERE table_id = ? AND slip_id IS NULL AND accounted = false");
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

    @GetMapping("/regi/order/detail/{table}")
    public ResponseEntity<Object> getDetail(@PathVariable("table") String table) {
        JSONObject json = new JSONObject();
        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()){
            int total = 0;
            // String sql = "SELECT a.id, order_quant, merch_id, name, price, table_id, table_name FROM aboutSlip a JOIN merchandice m ON a.merch_id = m.id JOIN tables t ON a.table_id = t.id WHERE slip_id IS NULL AND accounted = false HAVING table_name = ?"; // table_name用
            String sql = "SELECT a.id, order_quant, merch_id, name, price, table_id, table_name FROM aboutSlip a JOIN merchandice m ON a.merch_id = m.id JOIN tables t ON a.table_id = t.id WHERE slip_id IS NULL AND accounted = false HAVING table_id = ?"; // table_id用
            // String sql = "SELECT a.id, order_quant, merch_id, name, price, table_id, table_name FROM aboutSlip a JOIN merchandice m ON a.merch_id = m.id JOIN tables t ON a.table_id = t.id WHERE slip_id IS NULL AND accounted = false GROUP BY merch_id HAVING table_id = ?"; // table_id用
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, table);
            ResultSet rs = pstmt.executeQuery();
            
            JSONArray ar = new JSONArray();
            while(rs.next()) {
                JSONObject obj = new JSONObject();
                int abid = rs.getInt("id");
                // int mid = rs.getInt("merch_id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int amount = rs.getInt("order_quant");
                String tName = rs.getString("table_name");

                total += amount * price;
                obj.put("id", abid);
                obj.put("name", name);
                obj.put("price", price);
                obj.put("amount", amount);
                json.put("table", tName);
                ar.put(obj);
            }
            // json.put("table", table);
            json.put("items", ar);
            json.put("total", total);
            // String sql = "SELECT a.id, order_quant, merch_id, name, price, table_id, table_name FROM aboutSlip a JOIN merchandice m ON a.merch_id = m.id JOIN tables t ON a.table_id = t.id HAVING table_id = ?"; // table_id用
        } catch (SQLException e) {
            // e.printStackTrace();
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


            // String sql = "SELECT order_quant, merch_id, name, price FROM aboutSlip ab JOIN merchandice m ON ab.merch_id = m.id WHERE slip_id = ?";
            String sql = "SELECT SUM(order_quant) sum, name, merch_id, price, m.price * SUM(order_quant) total FROM aboutSlip a LEFT OUTER JOIN merchandice m ON a.merch_id = m.id WHERE slip_id = ? GROUP BY merch_id, m.name";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, argSlipId);
            ResultSet rs = pstmt.executeQuery();


            while(rs.next()) {
                JSONObject obj = new JSONObject();
                int id = rs.getInt("merch_id");
                // int amount = rs.getInt("order_quant");
                int amount = rs.getInt("sum");
                String name = rs.getString("name");
                String total = rs.getString("total");
                int price = rs.getInt("price");
                
                // int total = price * amount;

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
            REModel bad = new REModel();
            bad.setStatus(HttpStatus.BAD_REQUEST);
            return bad.getModel();
        } catch (Exception e) {
            // any
        }

        return new REModel().getArrayToModel(json);
        // return new TempJson().getJson();
    }

    @PostMapping("/regi/accounting")
    public ResponseEntity<Object> postAccounting(@RequestBody String Jsons) {
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
                    String sql3 = "UPDATE aboutSlip SET slip_id = ?, accounted = true WHERE table_id = ? AND slip_id IS NULL AND accounted = false"; //sid tid
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
            REModel bad = new REModel();
            bad.setStatus(HttpStatus.BAD_REQUEST);
            return bad.getModel();
        }
        
        return new REModel().getModel();
    }
    
    /**
     * 画像アップロードの処理
     * @param file
     * @return
     */
    @PostMapping("/merchImage")
    public ResponseEntity<Object> uploadMerch(@RequestPart("images") MultipartFile file) {
        JSONObject json = new JSONObject();
        HttpStatus stat = null;

        // String path = "./src/main/resources/static/upload/img" + file.getOriginalFilename();
        Path dst = Path.of("./src/main/resources/static/upload/img", file.getOriginalFilename());
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

    @PutMapping("/merchImage")
    public ResponseEntity<Object> updateImage(@RequestPart("images") MultipartFile file, @RequestPart("oldImage") String oldPath) {
        Path dst = Path.of("./src/main/resources/static/upload/img", file.getOriginalFilename());
        try {

            // 一応古い画像を削除する処理
            if(!oldPath.equals("assets/img/noimage.png")) {
                Path old = Path.of("./src/main/resources/static", oldPath);
                File oldImg = new File(old.toString());
                if(oldImg.delete()) {
                    System.out.println("success");
                }
            }


            Files.copy(file.getInputStream(), dst);
            
        } catch(FileAlreadyExistsException e) {
            try {
                Files.delete(dst);
                Files.copy(file.getInputStream(), dst);
            } catch(IOException ex) {
                System.err.println("I/O例外");
            }
        } catch(Exception e) {
            System.err.println("any error");
            return new REModel().getModel(HttpStatus.BAD_REQUEST);
        }

        return new REModel().getModel();
    }
    
    @DeleteMapping("/regi/items/{order}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("order") String order_id) {
        DbManager man = DbManager.getInstance();
        try (Connection cn = man.getConnection()) {
            int oid = Integer.parseInt(order_id);
            String sql = "DELETE FROM aboutSlip WHERE id = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, oid);
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
    
    @PutMapping("/regi/accounting/{table}")
    public ResponseEntity<Object> stopAccounting(@PathVariable("table") String table_id) {
        DbManager man = DbManager.getInstance();

        try (Connection cn = man.getConnection()) {
            int tid = Integer.parseInt(table_id);
            String sql = "UPDATE tables SET status = '空き状態' WHERE id = ?";
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
