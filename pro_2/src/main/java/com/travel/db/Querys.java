package com.travel.db;

public interface Querys {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String db_name = "team1_travel";
	String url = "jdbc:mysql://localhost:3306/" + db_name
			+ "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	String id_mysql = "root";
	String pw_mysql = "0000";
	
	String select_temp_purchase_query = "SELECT p.useridx,i.* "
	        + "FROM purchase p "
	        + "LEFT JOIN item i ON p.trvidx = i.trvidx "
	        + "WHERE i.trvdepdate > NOW() "
	        + "AND i.trvtcnt > i.trvccnt "	
	        + "AND p.trvpay = 0 "
	        + "AND p.useridx = ?";
	
	String select_purchase_query = "select p.useridx,i.* "
			+ "FROM purchase p "
			+ "LEFT JOIN item i ON p.trvidx = i.trvidx "
			+ "WHERE p.trvpay = 1 "
			+ "AND p.TRVAPPROVE = 1 "
			+ "AND p.useridx = ?";
			
	String get_item_query = "select * from item where trvidx = ?";
	
	String get_TRVPAY_from_itemidx = "select TRVPAY from purchase where trvidx = ? and useridx = ?";
	
	String temppay_to_pay_query1 = "update purchase set trvpay=1 where trvidx = ? and useridx = ?";
	//String temppay_to_pay_query2 = "update travel_agency set check_value = check_value + ?";
	//String temppay_to_pay_query3 = "update item set trvccnt = trvccnt+1 where trvidx=?";
	
	String delete_temp_pay_query = "delete from purchase where trvidx = ? and useridx = ?";
	
	String delete_pay_query1 = "delete from purchase where trvidx = ? and useridx = ?";
	String delete_pay_query2 = "update travel_agency set check_value = check_value - ?";
	String delete_pay_query3 = "UPDATE item SET trvccnt = CASE WHEN trvccnt <= 0 THEN 0 ELSE trvccnt - 1 END WHERE trvidx = ?";
}
