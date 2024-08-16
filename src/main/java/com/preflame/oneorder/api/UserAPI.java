package com.preflame.oneorder.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.preflame.oneorder.TempJson;
import com.preflame.oneorder.model.REModel;
import com.preflame.oneorder.sql.DbManager;
import com.preflame.oneorder.sql.Recommend;

import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/order")
public class UserAPI {

	/**
	 * 商品一覧を返す処理
	 * isVisible が true であるものを返すようにはなってる
	 * @return 商品一覧
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOrderList() {
		JSONArray json = new JSONArray();
		DbManager manager = DbManager.getInstance();
		JSONObject recommend = Recommend.getRecommend();
		if(recommend != null) {
			json.put(recommend);
		}
		try(Connection conn = manager.getConnection()) {
			PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM category WHERE isVisible = true");
			ResultSet rs1 = pstmt1.executeQuery();
			
			while(rs1.next()) {
				JSONObject cat = new JSONObject();
				int catid = rs1.getInt("id");
				String category_name = rs1.getString("category_name");
				
				cat.put("category_id", catid);
				cat.put("category_name", category_name);
				
				// 商品
				JSONArray merchArray = new JSONArray();
				PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM Merchandice WHERE isVisible = true AND category_id = ?");
				pstmt2.setInt(1, catid);
				ResultSet rs2 = pstmt2.executeQuery();
				
				while(rs2.next()) {
					JSONObject merch = new JSONObject();
					
					int merch_id = rs2.getInt("id");
					String merch_name = rs2.getString("name");
					int price = rs2.getInt("price");
					String image = rs2.getString("image");
					String desc = rs2.getString("description");
					
					merch.put("merch_id", merch_id);
					merch.put("merch_name", merch_name);
					merch.put("price", price);
					merch.put("image", image);
					merch.put("desc", desc);
					merchArray.put(merch);
				}
				
				cat.put("items", merchArray);
				json.put(cat);
			}
			
			return new ResponseEntity<Object>(json.toList(), HttpStatus.OK);
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
//		return OrderList.toString();
	}
	
	/**
	 * カテゴリ番号からそのカテゴリの商品情報を取得する処理
	 * でも多分使わないので優先順位低めで書きます
	 * @param catid
	 * @return
	 */
	@GetMapping(path = "/{catId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOrderItem(@PathVariable("catId") String catId) {
		JSONArray json = new JSONArray();
		// JSONObject tmpJson = new JSONObject();
		TempJson tmpJson = new TempJson();
		try {
			json.put(tmpJson);
			// int cid = Integer.parseInt(catId);
		} catch(NumberFormatException e) {
			tmpJson.put("error", "The value entered is invalid.");
			System.err.println("error.");
		}
		
		return new ResponseEntity<Object>(tmpJson.toMap(), HttpStatus.NOT_IMPLEMENTED);
	}
	
	/**
	 * テーブル番号から現在の注文履歴を返す処理
	 * データベース上の領収書番号がnullであることを用いるため必ず思った通りに出るとは限らないと思います
	 * @param tableId
	 * @return
	 */
	@GetMapping(path = "/history/table/{tableid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOrderHistory(@PathVariable("tableid") String tableid) {
		JSONArray json = new JSONArray();
		TempJson tmpJson = new TempJson();
		DbManager man = DbManager.getInstance();
		try (Connection cn = man.getConnection()) {
			int tableId = Integer.parseInt(tableid);

			String sql = "SELECT * FROM aboutSlip a JOIN merchandice m ON a.merch_id = m.id WHERE table_id = ? AND slip_id IS NULL";
			PreparedStatement pstmt = cn.prepareStatement(sql);
			pstmt.setInt(1, tableId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int amount = rs.getInt("order_quant");
				String time = rs.getString("ordered_time");

				obj.put("name", name);
				obj.put("price", price);
				obj.put("amount", amount);
				obj.put("time", time);

				json.put(obj);
			}
		} catch(NumberFormatException e) {
			tmpJson.put("error", "The value entered is invalid.");
		} catch(SQLException e) {
			System.err.println("error");
		}
		
		return new REModel().getArrayToModel(json);
	}

	@GetMapping("/accounting/table/{table}")
	public ResponseEntity<Object> getAccountingHistory(@PathVariable("table") String tableId) {
		JSONArray json = new JSONArray();
		DbManager man = DbManager.getInstance();
		try (Connection cn = man.getConnection()) {
			int tid = Integer.parseInt(tableId);
			String sql = "SELECT SUM(order_quant) sum, name, merch_id, m.price * SUM(order_quant) total FROM aboutSlip a JOIN merchandice m ON a.merch_id = m.id WHERE table_id = ? AND slip_id IS NULL GROUP BY merch_id, m.name";
			PreparedStatement pstmt = cn.prepareStatement(sql);
			pstmt.setInt(1, tid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int amount = rs.getInt("sum");
				String name = rs.getString("name");
				int mid = rs.getInt("merch_id");
				int total = rs.getInt("total");

				JSONObject obj = new JSONObject();
				obj.put("merch_id", mid);
				obj.put("name", name);
				obj.put("amount", amount);
				obj.put("price", total);
				json.put(obj);
			}
		} catch (NumberFormatException e) {
			System.err.println("format error");
			return new REModel().getModel(HttpStatus.BAD_REQUEST);
		} catch (SQLException e) {
			System.err.println("SQL error");
			return new REModel().getModel(HttpStatus.BAD_REQUEST);
		}

		return new REModel().getArrayToModel(json);
		// return new TempJson().getJson();
	}

	@PutMapping("/accounting/{id}")
	public ResponseEntity<Object> accounting(@PathVariable("id") String id) {

		DbManager man = DbManager.getInstance();
		try (Connection cn = man.getConnection()) {
			int tid = Integer.parseInt(id);
			String sql = "UPDATE tables SET status = 'お会計待ち' WHERE id = ?";
			PreparedStatement pstmt = cn.prepareStatement(sql);
			pstmt.setInt(1, tid);
			int res = pstmt.executeUpdate();

			if(res < 0) {
				System.err.println("error?");
			}
		} catch(NumberFormatException e) {
			System.err.println("format error");
			return new REModel().getModel(HttpStatus.BAD_REQUEST);
		} catch (SQLException e) {
			System.err.println("SQL error");
			return new REModel().getModel(HttpStatus.BAD_REQUEST);
		}
		
		return new REModel().getModel();
		// return new TempJson().getJson();
	}
	


	/**
	 * 注文された商品をデータベースに登録する処理
	 * 
	 * @param data jsonデータ
	 * @return ただのHTTPステータス
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> postOrderItem(@RequestBody String data) {
		JSONObject json = new JSONObject(data);
		int table_id = json.getInt("table");

		DbManager man = DbManager.getInstance();

		try (Connection cn = man.getConnection()) {
			String sql = "INSERT INTO aboutSlip(table_id, merch_id, order_quant) VALUES(?, ?, ?)";
			PreparedStatement pstmt = cn.prepareStatement(sql);
			pstmt.setInt(1, table_id);

			JSONArray ar = json.getJSONArray("cart");
			for(int i = 0;i < ar.length();i++) {
				JSONObject item = ar.getJSONObject(i);
				int mid = item.getJSONObject("item").getInt("merch_id");
				int amount = item.getInt("amount");
				pstmt.setInt(2, mid);
				pstmt.setInt(3, amount);
				int result = pstmt.executeUpdate();
				if(result < 0) {
					System.err.println("error?");
				}
			}
		} catch (SQLException e) {
			System.err.println("error");
			return new REModel().getModel(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
