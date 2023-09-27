package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.travel.db.MySQLConnector;
import com.travel.db.Querys;
import com.travel.vo.ItemVO;
import com.travel.vo.PurchaseListVO;

public class PurchaseListDAO extends MySQLConnector implements Querys {

	public PurchaseListDAO() {
		super();
	}
	
	public ArrayList<PurchaseListVO> get_temp_purchaselist(int useridx) {
		//1. purchase에서 가져온 데이터를 임시결제함 리스트에 뿌리되 trvpay=F,tempapprove=F(결제전 여행 상품)들만 리스트에 뿌린다.
		//1-1. trvpay = T,tempapprove=F => 결제 완료 했으나 관리자 승인 전인상태./이것도 리스트에 뿌리되 결제 로직은 없어야 한다.
		//2. 현재시간을 측정하여 출발시간이 지난 여행 상품은 제외해야한다.
				
		//3. 정원과 현재인원을 비교하여 현재인원>T.O인 경우에는 리스트에서 제외해야한다.
		
		ArrayList<PurchaseListVO> items = new ArrayList<PurchaseListVO>();
		System.out.println("get_temp_purchaselist");
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		PurchaseListVO item = null;
	
		try {
			ptmt = conn.prepareStatement(select_temp_purchase_query);
			ptmt.setInt(1, useridx);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				item = new PurchaseListVO();		
				item.setUser_idx(rs.getInt("useridx"));
				item.setTrv_idx(rs.getInt("trvidx"));
				item.setTrv_name(rs.getString("trvname"));
				item.setTrv_depart(rs.getString("trvdepart"));
				item.setTrv_dest(rs.getString("trvdest"));
				item.setTrv_price(rs.getInt("trvprice"));
				item.setTrv_tcnt(rs.getInt("trvtcnt"));
				item.setTrv_ccnt(rs.getInt("trvccnt"));
				item.setTrv_deptdate(rs.getDate("trvdepdate"));
				item.setTrv_destdate(rs.getDate("TRVDESTDATE"));
				items.add(item);
			}
		} catch (SQLException e) {
			System.out.println("SQLException in get_temp_purchaselist() : "+e.getMessage());
		}finally {
			close(ptmt,rs);
		}
		
		return items;
	}
	
	public ArrayList<PurchaseListVO> get_purchaselist(int useridx) {
		//1. purchase에서 가져온 데이터를 임시결제함 리스트에 뿌리되,
		//trvpay=1,tempapprove=1(결제 및 승인완료)들만 리스트에 뿌린다.
		
		ArrayList<PurchaseListVO> items = new ArrayList<PurchaseListVO>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		try {
			ptmt = conn.prepareStatement(select_purchase_query);
			ptmt.setInt(1, useridx);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				PurchaseListVO item = new PurchaseListVO();
				
				item.setTrv_idx(rs.getInt("trvidx"));
				item.setUser_idx(rs.getInt("useridx"));
				item.setTrv_name(rs.getString("trvname"));
				item.setTrv_depart(rs.getString("trvdepart"));
				item.setTrv_dest(rs.getString("trvdest"));
				item.setTrv_price(rs.getInt("trvprice"));
				item.setTrv_ccnt(rs.getInt("trvccnt"));
				item.setTrv_tcnt(rs.getInt("trvtcnt"));
				item.setTrv_deptdate(rs.getDate("trvdepdate"));
				item.setTrv_destdate(rs.getDate("TRVDESTDATE"));
				
				items.add(item);
			}
		} catch (SQLException e) {
			System.out.println("SQLException in get_purchaselist : "+e.getMessage());
		}finally {
			close(ptmt,rs);
		}
		return items;
	}
	public ItemVO get_item(int Trv_idx) {
		
		//임시결제함의 상품 제목을 클릭하면 볼 수 있는 관광 상품 상세 정보
		//Trv_idx에 맞는 정보 가져와서 아이템 다오에 담아 리턴
		ItemVO item = new ItemVO();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		try {
			ptmt = conn.prepareStatement(get_item_query);
			ptmt.setInt(1, Trv_idx);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				int trv_idx = rs.getInt(1);
				String trv_name = rs.getString(2);
				String trv_depart = rs.getString(3);
				String trv_dest = rs.getString(4);
				int trv_price = rs.getInt(5);
				int trv_tcnt = rs.getInt(6); //T.O
				int trv_ccnt = rs.getInt(7); //current T.O
				Date trv_deptdate = rs.getDate(8);
				Date trv_destdate = rs.getDate(9);
				
				item.set(trv_idx, trv_name, trv_depart, trv_dest, trv_price, trv_tcnt, trv_ccnt, trv_deptdate, trv_destdate);
			}
		} catch (SQLException e) {
		System.out.println("SQLException in get_item() : " +e.getMessage());
		}finally {
			close(ptmt,rs);
		}
		return item;
	}
	public boolean get_TRVPAY_from_itemidx(PurchaseListVO item,int useridx) {
		//임시결제함 페이지에서 결제를 완료했으나, 관리자가 승인하지 않아 승인 대기중인 관광상품을 파악하기위한 메소드.
		
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		boolean TRVPAY = false;
		
		try {
			ptmt = conn.prepareStatement(get_TRVPAY_from_itemidx);
			ptmt.setInt(1, item.getTrv_idx());
			ptmt.setInt(2, useridx);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				TRVPAY = rs.getBoolean(1);
			}
					
		} catch (SQLException e) {
			System.out.println("SQLException in get_TRVPAY_from_itemidx : "+e.getMessage());
		}finally {
			close(ptmt,rs);
		}
		
		return TRVPAY;
	}
	
	public void temppay_to_pay(int trvidx,int useridx) {
		//임시 저장한 관광 상품을 결제로 처리.그러나 승인은 아직.(승인 대기 상태)
		//1. purchase 테이블에서 정해진 관광 상품의 trvpay를 참으로 바꿈.
	
		PreparedStatement ptmt1 = null;
		
		//"update purchase set trvpay=1 where trvidx = ? and usridx = ?"
		try {
			ptmt1 = conn.prepareStatement(temppay_to_pay_query1);
			ptmt1.setInt(1, trvidx);
			ptmt1.setInt(2, useridx);
			ptmt1.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException in temppay_to_pay : " + e.getMessage());
		}finally {
			close(ptmt1);
		}
	}//temppay_to_pay()
	
	public void delete_temp_pay(int trvidx,int useridx) {
	 //1. 결제 대기 중인 관광 상품을 purchase 테이블에서 삭제
	
	PreparedStatement ptmt = null;
	
	try {
		ptmt = conn.prepareStatement(delete_temp_pay_query);
		ptmt.setInt(1, trvidx);
		ptmt.setInt(2, useridx);
		ptmt.executeUpdate();
	} catch (SQLException e) {
		System.out.println("SQLException in delete_temp_pay : "+e.getMessage());
	}finally {
		close(ptmt);
	}
		
	}//delete_temp_pay()
	
	public void delete_pay(int trvidx, int useridx) {
		// 결제 완료 된 관광 상품 환불 처리 - purchase 테이블에서 삭제
		//travel_agency 테이블의 check_value 변수를 업데이트함.(check_value = checkvalue - trvprice)
		//해당 관광 상품의 현재 인원을 1명 감소시킨다.
		PreparedStatement ptmt1 = null;
		PreparedStatement ptmt2 = null;
		PreparedStatement ptmt3 = null;
		
		try {
			ptmt1 = conn.prepareStatement(delete_pay_query1);
			ptmt1.setInt(1, trvidx);
			ptmt1.setInt(2, useridx);
			ptmt1.executeUpdate();
			
			ptmt2 = conn.prepareStatement(delete_pay_query2);
			ptmt2.setInt(1, trvidx);
			ptmt2.executeUpdate();
			
			ptmt3 = conn.prepareStatement(delete_pay_query3);
			ptmt3.setInt(1, trvidx);
			ptmt3.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException in temppay_to_pay : " + e.getMessage());
		}finally {
			close(ptmt1);
			close(ptmt2);
			close(ptmt3);
		}
	}

}
