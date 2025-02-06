package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Customer;



public class TblCustomerDao {

    //싱글톤 패턴 정의
    private static TblCustomerDao dao = new TblCustomerDao();
    
    private TblCustomerDao (){

    }
    public static TblCustomerDao getInstance (){
        return dao;
    }

    // DB 연결 정보를 상수로 정의
    // 상수(final)은 대문자로 변수명 정의
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String USERNAME = "c##idev";
    private static final String PASSWORD = "1234";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // jsp 사용하는 웹프로젝트에서는 생략 못함
    // db 연결 객체를 만드는 메소드를 dao 에서만 사용할 수 있도록 정의 
    // connect 메서드 정의 (Oracle DB Connect) 
    private Connection getConnection() throws SQLException {
    	try {
    		Class.forName(DRIVER); // jsp 사용하는 웹프로젝트에서는 생략 못함
    	} catch (ClassNotFoundException e) {
    		System.out.println("예외 : " + e.getMessage());
    	}
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    // sql 실행 메소드 정의 -> 각 sql connection 만들어 사용하고 sql 실행 후
    public int insert(Customer vo){
        int result = 0;
        String sql = "INSERT INTO TBL_CUSTOMER(CUSTOM_ID,NAME,EMAIL,AGE,REG_DATE)" + "VALUES (?,?,?,?,SYSDATE)" ;
        try (Connection connection = getConnection(); 
            PreparedStatement pstmt =  connection.prepareStatement(sql)
            ) { 
                pstmt.setString(1, vo.getCustomID());
                pstmt.setString(2, vo.getName());
                pstmt.setString(3, vo.getEmail());
                pstmt.setInt(4, vo.getAge());
                
                result = pstmt.executeUpdate();
        } catch (Exception e) {
           System.out.println("예외 : " + e.getMessage());
        }
        return result;
    }


    public int update(Customer vo){
        int result = 0;
        // 이메일과 우편번호 업데이트
        String sql = "UPDATE TBL_CUSTOMER SET EMAIL = ? ,POSTCODE = ? WHERE CUSTOM_ID = ? ";
        try (Connection connection = getConnection(); 
            PreparedStatement pstmt =  connection.prepareStatement(sql)
            ) { 
                pstmt.setString(1, vo.getEmail());
                pstmt.setString(2, vo.getPostcode());
                pstmt.setString(3,vo.getCustomID());

                result = pstmt.executeUpdate();
        } catch (Exception e) {
           System.out.println("업데이트 오류 : " + e.getMessage());
        }
        return result;
    }


    public int delete(String customid){
        int result = 0;
        String sql="Delete from TBL_CUSTOMER WHERE CUSTOM_ID = ? ";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1,customid);
          
                result = pstmt.executeUpdate();
        } catch (Exception e) {
          System.out.println("삭제 예외 : " + e.getMessage());
        }
        return result;
      }


      // 기본키 값으로 조회
    public Customer selectByPk(String custom_id){
        String sql="SELECT * FROM TBL_CUSTOMER WHERE CUSTOM_ID = ?";
        Customer customer = null;
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, custom_id);
                ResultSet rs = pstmt.executeQuery(); //행집합 리턴

                if (rs.next()) {
                    customer = new Customer(
                    rs.getString(1), // 각각의 컬럼들
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4), 
                    rs.getDate(5),
                    rs.getString(6)
                    );
                }
        } catch (Exception e) {
          System.out.println("예외 : " + e.getMessage());
        }
        return customer;
    }
}


