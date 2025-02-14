package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vo.Customer;
import vo.FanitemOrderVO;

public class FanItemOrderDao {
	
	 //싱글톤 패턴 정의
    private static FanItemOrderDao dao = new FanItemOrderDao();
    
    private FanItemOrderDao (){

    }
    public static FanItemOrderDao getInstance (){
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
    
    public int insert(FanitemOrderVO vo){
        int result = 0;
        String sql = "INSERT INTO TBL_FANITEM_ORDER(order_seq,userid,seq,count,pay)" + "VALUES (tblorder_seq.nextval,?,?,?,?)";
        try (Connection connection = getConnection(); 
            PreparedStatement pstmt =  connection.prepareStatement(sql)
            ) { 
                pstmt.setString(1, vo.getUserid());
                pstmt.setInt(2, vo.getSeq());
                pstmt.setInt(3, vo.getCount());
                pstmt.setInt(4, vo.getPay());
                
                result = pstmt.executeUpdate();
        } catch (Exception e) {
           System.out.println("예외 : " + e.getMessage());
        }
        return result;
    }

    
    
}
