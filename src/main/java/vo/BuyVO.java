package vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 구매테이블과 매핑할 VO 클래스
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BuyVO {
 private int buyseq;
 private String custom_id;
 private String pcode;
 private int quantity;
 private Date buy_date;

}
/*
 * @ToString(exclude = "price") // price 필드는 toString()에서 제외
public class BuyyVO {
    private String name;
    private int price;

    public BuyyVO(String name, int price) { // 생성자 수정
        this.name = name;
        this.price = price;
    }
}

public class Main {
    public static void main(String[] args) {
        BuyyVO product = new BuyyVO("Apple", 1000); // BuyyVO 클래스 사용
        System.out.println(product); // toString() 결과 출력
    }
}
 */