package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BuyVO;
import vo.CustomerOrderVo;

public class TblBuyDao {

  // db연결 정보를 상수로 정의합니다.
  private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
  private static final String USERNAME = "c##idev";
  private static final String PASSWORD = "1234";

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
  }

  public int insertMany(List<BuyVO> cart) { // 장바구니 목록을 인자로 받기
    int count = 0;
    String sql = "INSERT INTO tbl_buy VALUES(tbl_buy_seq.nextval, ?, ?, ?, sysdate)";
    Connection connection = null;
    PreparedStatement pstmt = null;
    try {
      connection = getConnection();
      pstmt = connection.prepareStatement(sql);

      // 수동 커밋으로 변경
      connection.setAutoCommit(false);

      // batch(일괄) 실행
      for (BuyVO vo : cart) { // 장바구니 목록의 항목 갯수 만큼 반복
        pstmt.setString(1, vo.getCustom_id());
        pstmt.setString(2, vo.getPcode());
        pstmt.setInt(3, vo.getQuantity());
        pstmt.addBatch(); // * 일괄처리할 파라미터 값을 추가. 모아두었다가 한번에 처리
        count++;
      }
      pstmt.executeBatch(); // * 모아둔 파라미터 값 목록으로 일괄 실행
      // 저장할 행 데이터는 n개, 오라클서버에 처리 요청은 한번만한다.

      // result = pstmt.executeUpdate(); // 이 명령어 반복은 요청을 n번
      connection.commit(); // 데이터베이스에 영구 저장

    } catch (SQLException e) {
      // System.out.println("예외 : " + e.getMessage());
      try {
        connection.rollback();
        count = 0;
      } catch (SQLException e1) {
      }
    } finally {
      try {
        pstmt.close();
        connection.close();
      } catch (SQLException e1) {
      }
    }
    return count;
  }

  public int insertOne(BuyVO vo) {
    int result = 0;
    String sql = "INSERT INTO tbl_buy VALUES(tbl_buy_seq.nextval, ?, ?, ?, sysdate)";
    try (
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);) {
      pstmt.setString(1, vo.getCustom_id());
      pstmt.setString(2, vo.getPcode());
      pstmt.setInt(3, vo.getQuantity());
      result = pstmt.executeUpdate();

    } catch (SQLException e) {
      System.out.println("예외 : " + e.getMessage());
    }
    return result;
  }

  public List<BuyVO> selectByCustomerid(String customerid) {
    List<BuyVO> list = null;
    String sql = "SELECT * FROM TBL_BUY WHERE CUSTOM_ID = ? ORDER BY BUY_DATE DESC";
    try (
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);) {
      list = new ArrayList<>();
      pstmt.setString(1, customerid);
      ResultSet rs = pstmt.executeQuery();
      BuyVO product = null;
      while (rs.next()) {
        product = new BuyVO(rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            rs.getDate(5));
        list.add(product);
      }
    } catch (Exception e) {
      System.out.println("구매목록 확인 오류 : " + e.getMessage());
    }
    return list;
  }

  public List<CustomerOrderVo> selectCustomerOrderList (String customerid){
    List<CustomerOrderVo> list = null;
    String sql = "SELECT PNAME, PRICE, QUANTITY, BUY_DATE, CATEGORY FROM TBL_PRODUCT tp JOIN TBL_BUY tb  ON TP.PCODE = TB.PCODE WHERE CUSTOM_ID = ?  ORDER BY BUY_DATE DESC";
    try (
      Connection connection = getConnection();
      PreparedStatement pstmt = connection.prepareStatement(sql);
    ) {
      list = new ArrayList<>();
      pstmt.setString(1, customerid);
      ResultSet rs = pstmt.executeQuery();
      CustomerOrderVo prodcut = null;
      while (rs.next()) {
        prodcut = new CustomerOrderVo(
        rs.getString(1),
        rs.getInt(2),
        rs.getInt(3),
        rs.getDate(4),
        rs.getString(5));
        list.add (prodcut);
      }
    } catch (SQLException e) {
      System.out.println(customerid + " 회원 구매목록 조회 오류 " + e.getMessage());
    }
    return list;
  }
}