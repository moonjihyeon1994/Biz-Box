package com.bizbox.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 *  2017년 골목상권 결제정보
  `A` = '분기',
  `B` = '상권 구분 코드 명',
  `C` = '상권코드',
  `D` = '도로명 주소',
  `E` = '서비스 업종 코드',
  `F` = '서비스 업종 명',
  `G` = '분기 매출 금액',
  `H` = '분기 매출 건수',
  `I` = '주중 매출 금액',
  `J` = '주말 매출 금액',
  `K` = '월요일 매출 금액',
  `L` = '화요일 매출 금액',
  `M` = '수요일 매출 금액',
  `N` = '목요일 매출 금액',
  `O` = '금요일 매출 금액',
  `P` = '토요일 매출 금액',
  `Q` = '일요일 매출 금액',
  `R` = '00시~06시 매출 금액',
  `S` = '06시~11시 매출 금액',
  `T` = '11시~14시 매출 금액',
  `U` = '14시~17시 매출 금액',
  `V` = '17시~21시 매출 금액',
  `W` = '21시~24시 매출 금액',
  `X` = '남성 매출 금액',
  `Y` = '여성 매출 금액',
  `Z` = '10대 매출 금액',
  `AA` = '20대 매출 금액',
  `AB` = '30대 매출 금액',
  `AC` = '40대 매출 금액',
  `AD` = '50대 매출 금액',
  `AE` = '60대 이상 매출 금액',
  `AF` = '점포수'
  'AG' = '점포별 평균 매출'
  'AH' = '결제 1회당 평균 매출'
 *
 */
public class SalesInformation {
	private String A;
	private String B;
	private String C;
	private String D;
	private String E;
	private String F;
	private String G;
	private String H;
	private String I;
	private String J;
	private String K;
	private String L;
	private String M;
	private String N;
	private String O;
	private String P;
	private String Q;
	private String R;
	private String S;
	private String T;
	private String U;
	private String V;
	private String W;
	private String X;
	private String Y;
	private String Z;
	private String AA;
	private String AB;
	private String AC;
	private String AD;
	private String AE;
	private String AF;
	private String AG;
	private String AH;
}
