package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Product;
import vo.UserAccountVO;



public class UserAccountDao {
	
	// 참고 : vo는 데이터를 저장하는것이 목적 --> 데이터가 달라질때 객체를 생성하여 저장
	//		dao는 sql 실행을 위한 메소드로만 구성되어있음으로 객체는 한번만 생성 (싱글톤패턴)
	
	// 싱글톤으로 정의
	private static UserAccountDao dao = new UserAccountDao();
	private UserAccountDao() { }
	public static UserAccountDao getInstance() {
		return dao;
	}
	
	 // db 연결 정보를 상수로 정의합니다.
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String USERNAME = "c##idev";
    private static final String PASSWORD = "1234";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // jsp 사용하는 웹프로젝트에서는 생략 못함
    // db 연결 객체 만드는 메소드 dao 에서만 사용할 수 있도록 정의
    private Connection getConnection() throws SQLException{
    	try {
    		Class.forName(DRIVER); // jsp 사용하는 웹프로젝트에서는 생략 못함
    	} catch (ClassNotFoundException e) {
    		System.out.println("예외 : " + e.getMessage());
    	}
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
	
	// 회원가입 - db 테이블에 insert
	
	// insert할 값이 UserAccountVO에 있음으로 인자를 받음
    // db테이블의 not null 컬럼 5개 값은 반드시 vo에 저장되어있어야함
    //						ㄴ 값이 null 이면 예외 발생
    //						ㄴ 사용자 입력한 내용을 유효 여부 검증 -> 자바스크립트 (브라우저)
	public int insert(UserAccountVO vo) {
		int result = 0;
		String sql = "INSERT INTO TBL_USER_ACCOUNT(USERID,USERNAME,PASSWORD,BIRTH,GENDER,EMAIL) VALUES(?,?,?,?,?,?)";
		
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			) {
				pstmt.setString(1, vo.getUserid());
				pstmt.setString(2, vo.getUsername());
				//null; 도 가능 
				pstmt.setString(3, vo.getPassword());
				pstmt.setString(4, vo.getBirth());
				pstmt.setString(5, vo.getGender());
				pstmt.setString(6, vo.getEmail());
				
				
				result = pstmt.executeUpdate();

			
		} catch (SQLException e) {
			System.out.println("회원가입 오류 : " + e.getMessage());
		}
	return result;
}
	
	
	public UserAccountVO selectForLogin(String userid , String password) {
		String sql = "SELECT * FROM TBL_USER_ACCOUNT WHERE USERID = ? AND PASSWORD = ?";
		UserAccountVO account = null;
		try (Connection connection = getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		){
			pstmt.setString(1, userid);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			 if(rs.next()){
				 account = new UserAccountVO(rs.getString(1),
                                       rs.getString(2),
                                       rs.getString(3),
                                       rs.getString(4),
                                       rs.getString(5),
                                       rs.getString(6));
			 }
			
		} catch (SQLException e) {
			System.out.println("특정 회원 검색 오류 : " + e.getMessage());
		}
		return account;
	}
}
