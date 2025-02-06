package vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok 사용 @
@Getter
@Setter
@ToString
@AllArgsConstructor // 커스텀생성자 어노테이션 (모든 필드변수 초기화)
@RequiredArgsConstructor //final 변수만 초기화시키는 커스텀 생성자  
// @NoArgsConstructor // 기본생성자 어노테이션
                    // 기본생성자 사용하려면 변수언시 값을 정해야함
public class Customer {
    private final String customID;
    private final String name;
    private final String email;
    private final int age;
    private final Date regDate;
    private String postcode;
    
    // 일부 필드변수 초기화 생성자
    // public Customer(String customID, String name, String email, int age, Date regDate) {
    //     this.customID = customID;
    //     this.name = name;
    //     this.email = email;
    //     this.age = age;
    //     this.regDate = regDate;
    // }

}

// ORM 프레임워크 사용하면 setter 필요