package vo;

import java.sql.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
// @AllArgsConstructor 모두 final 키워드로 선언되어있음으로 사용 X
@RequiredArgsConstructor // final 키워드로 선언된 필드변수만 초기화

public class CustomerOrderVo {
    private final String pname;
    private final int price;
    private final int quantity;
    private final Date buy_date;
    private final String category;
}



/*
 SELECT PNAME ,PRICE ,quantity, buy_date ,CATEGORY 
FROM TBL_PRODUCT tp 
JOIN TBL_BUY tb 
ON tp.PCODE = tb.PCODE
WHERE CUSTOM_ID = ?
ORDER BY BUY_DATE DES
 */