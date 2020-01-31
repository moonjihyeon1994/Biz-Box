CREATE TABLE `address_code` (
  `A` varchar(255) DEFAULT NULL COMMENT '시도',
  `B` varchar(255) DEFAULT NULL COMMENT '시군구',
  `C` varchar(255) DEFAULT NULL COMMENT '행정구역명',
  `D` varchar(255) DEFAULT NULL COMMENT '행정동(행정기관명)',
  `E` varchar(255) DEFAULT NULL COMMENT '법정동',
  `F` varchar(255) DEFAULT NULL COMMENT '행정구역코드',
  `G` varchar(255) DEFAULT NULL COMMENT '행정기관코드',
  `H` varchar(255) DEFAULT NULL COMMENT '행정기관 생성일',
  `I` varchar(255) DEFAULT NULL COMMENT '법정동코드',
  `J` varchar(255) DEFAULT NULL COMMENT '관할지역'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `businesschange` (
  `a` varchar(45) DEFAULT NULL,
  `b` varchar(45) DEFAULT NULL,
  `c` varchar(45) DEFAULT NULL,
  `d` varchar(45) DEFAULT NULL,
  `e` varchar(45) DEFAULT NULL,
  `f` varchar(45) DEFAULT NULL,
  `g` varchar(45) DEFAULT NULL,
  `h` varchar(45) DEFAULT NULL,
  `i` varchar(45) DEFAULT NULL,
  `j` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `local_people_dong` (
  `A` date DEFAULT NULL COMMENT '기준일 ID',
  `B` int DEFAULT NULL COMMENT '시간대 구분',
  `C` varchar(255) DEFAULT NULL COMMENT '행정동코드',
  `D` varchar(255) DEFAULT NULL COMMENT '총생활인구수',
  `E` varchar(255) DEFAULT NULL COMMENT '남자 0~9세',
  `F` varchar(255) DEFAULT NULL COMMENT '남자 10~14세',
  `G` varchar(255) DEFAULT NULL COMMENT '남자 15~19세',
  `H` varchar(255) DEFAULT NULL COMMENT '남자 20~24세',
  `I` varchar(255) DEFAULT NULL COMMENT '남자 25~29세',
  `J` varchar(255) DEFAULT NULL COMMENT '남자 30~34세',
  `K` varchar(255) DEFAULT NULL COMMENT '남자 35~39세',
  `L` varchar(255) DEFAULT NULL COMMENT '남자 40~44세',
  `M` varchar(255) DEFAULT NULL COMMENT '남자 45~49세',
  `N` varchar(255) DEFAULT NULL COMMENT '남자 50~54세',
  `O` varchar(255) DEFAULT NULL COMMENT '남자 55~59세',
  `P` varchar(255) DEFAULT NULL COMMENT '남자 60~64세',
  `Q` varchar(255) DEFAULT NULL COMMENT '남자 65~69세',
  `R` varchar(255) DEFAULT NULL COMMENT '남자 70세 이상',
  `S` varchar(255) DEFAULT NULL COMMENT '여자 0~9세',
  `T` varchar(255) DEFAULT NULL COMMENT '여자 10~14세',
  `U` varchar(255) DEFAULT NULL COMMENT '여자 15~19세',
  `V` varchar(255) DEFAULT NULL COMMENT '여자 20~24세',
  `W` varchar(255) DEFAULT NULL COMMENT '여자 25~29세',
  `X` varchar(255) DEFAULT NULL COMMENT '여자 30~34세',
  `Y` varchar(255) DEFAULT NULL COMMENT '여자 35~39세',
  `Z` varchar(255) DEFAULT NULL COMMENT '여자 40~44세',
  `AA` varchar(255) DEFAULT NULL COMMENT '여자 45~49세',
  `AB` varchar(255) DEFAULT NULL COMMENT '여자 50~54세',
  `AC` varchar(255) DEFAULT NULL COMMENT '여자 55~59세',
  `AD` varchar(255) DEFAULT NULL COMMENT '여자 60~64세',
  `AE` varchar(255) DEFAULT NULL COMMENT '여자 65~69세',
  `AF` varchar(255) DEFAULT NULL COMMENT '여자 70세 이상'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `population_location` (
  `A` varchar(255) DEFAULT NULL COMMENT '기준_년_코드',
  `B` varchar(255) DEFAULT NULL COMMENT '기준_분기_코드',
  `C` varchar(255) DEFAULT NULL COMMENT '상권_구분_코드',
  `D` varchar(255) DEFAULT NULL COMMENT '상권_구분_코드_명',
  `E` varchar(255) DEFAULT NULL COMMENT '상권_코드',
  `F` varchar(255) DEFAULT NULL COMMENT '상권_코드_명',
  `G` varchar(255) DEFAULT NULL COMMENT '총_상주인구_수',
  `H` varchar(255) DEFAULT NULL COMMENT '남성_상주인구_수',
  `I` varchar(255) DEFAULT NULL COMMENT '여성_상주인구_수',
  `J` varchar(255) DEFAULT NULL COMMENT '연령대_10_상주인구_수',
  `K` varchar(255) DEFAULT NULL COMMENT '연령대_20_상주인구_수',
  `L` varchar(255) DEFAULT NULL COMMENT '연령대_30_상주인구_수',
  `M` varchar(255) DEFAULT NULL COMMENT '연령대_40_상주인구_수',
  `N` varchar(255) DEFAULT NULL COMMENT '연령대_50_상주인구_수',
  `O` varchar(255) DEFAULT NULL COMMENT '연령대_60_이상_상주인구_수',
  `P` varchar(255) DEFAULT NULL COMMENT '남성연령대_10_상주인구_수',
  `Q` varchar(255) DEFAULT NULL COMMENT '남성연령대_20_상주인구_수',
  `R` varchar(255) DEFAULT NULL COMMENT '남성연령대_30_상주인구_수',
  `S` varchar(255) DEFAULT NULL COMMENT '남성연령대_40_상주인구_수',
  `T` varchar(255) DEFAULT NULL COMMENT '남성연령대_50_상주인구_수',
  `U` varchar(255) DEFAULT NULL COMMENT '남성연령대_60_이상_상주인구_수',
  `V` varchar(255) DEFAULT NULL COMMENT '여성연령대_10_상주인구_수',
  `W` varchar(255) DEFAULT NULL COMMENT '여성연령대_20_상주인구_수',
  `X` varchar(255) DEFAULT NULL COMMENT '여성연령대_30_상주인구_수',
  `Y` varchar(255) DEFAULT NULL COMMENT '여성연령대_40_상주인구_수',
  `Z` varchar(255) DEFAULT NULL COMMENT '여성연령대_50_상주인구_수',
  `AA` varchar(255) DEFAULT NULL COMMENT '여성연령대_60_이상_상주인구_수',
  `AB` varchar(255) DEFAULT NULL COMMENT '총_가구_수',
  `AC` varchar(255) DEFAULT NULL COMMENT '아파트_가구_수',
  `AD` varchar(255) DEFAULT NULL COMMENT '비_아파트_가구_수'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `population_time` (
  `a` varchar(45) DEFAULT NULL,
  `b` varchar(45) DEFAULT NULL,
  `c` varchar(45) DEFAULT NULL,
  `d` varchar(45) DEFAULT NULL,
  `e` varchar(45) DEFAULT NULL,
  `f` varchar(45) DEFAULT NULL,
  `g` varchar(45) DEFAULT NULL,
  `h` varchar(45) DEFAULT NULL,
  `i` varchar(45) DEFAULT NULL,
  `j` varchar(45) DEFAULT NULL,
  `k` varchar(45) DEFAULT NULL,
  `l` varchar(45) DEFAULT NULL,
  `m` varchar(45) DEFAULT NULL,
  `n` varchar(45) DEFAULT NULL,
  `o` varchar(45) DEFAULT NULL,
  `p` varchar(45) DEFAULT NULL,
  `q` varchar(45) DEFAULT NULL,
  `r` varchar(45) DEFAULT NULL,
  `s` varchar(45) DEFAULT NULL,
  `t` varchar(45) DEFAULT NULL,
  `u` varchar(45) DEFAULT NULL,
  `v` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


select * from address_code;
select * from businesschange;
select * from local_people_dong;
select * from population_location;
select * from population_time;



CREATE TABLE `salesinformation` (
  `A` varchar(255) DEFAULT NULL COMMENT '분기',
  `B` varchar(255) DEFAULT NULL COMMENT '상권 구분 코드 명',
  `C` varchar(255) DEFAULT NULL COMMENT '상권코드',
  `D` varchar(255) DEFAULT NULL COMMENT '도로명 주소',
  `E` varchar(255) DEFAULT NULL COMMENT '서비스 업종 코드',
  `F` varchar(255) DEFAULT NULL COMMENT '서비스 업종 명',
  `G` varchar(255) DEFAULT NULL COMMENT '분기 매출 금액',
  `H` varchar(255) DEFAULT NULL COMMENT '분기 매출 건수',
  `I` varchar(255) DEFAULT NULL COMMENT '주중 매출 금액',
  `J` varchar(255) DEFAULT NULL COMMENT '주말 매출 금액',
  `K` varchar(255) DEFAULT NULL COMMENT '월요일 매출 금액',
  `L` varchar(255) DEFAULT NULL COMMENT '화요일 매출 금액',
  `M` varchar(255) DEFAULT NULL COMMENT '수요일 매출 금액',
  `N` varchar(255) DEFAULT NULL COMMENT '목요일 매출 금액',
  `O` varchar(255) DEFAULT NULL COMMENT '금요일 매출 금액',
  `P` varchar(255) DEFAULT NULL COMMENT '토요일 매출 금액',
  `Q` varchar(255) DEFAULT NULL COMMENT '일요일 매출 금액',
  `R` varchar(255) DEFAULT NULL COMMENT '00시~06시 매출 금액',
  `S` varchar(255) DEFAULT NULL COMMENT '06시~11시 매출 금액',
  `T` varchar(255) DEFAULT NULL COMMENT '11시~14시 매출 금액',
  `U` varchar(255) DEFAULT NULL COMMENT '14시~17시 매출 금액',
  `V` varchar(255) DEFAULT NULL COMMENT '17시~21시 매출 금액',
  `W` varchar(255) DEFAULT NULL COMMENT '21시~24시 매출 금액',
  `X` varchar(255) DEFAULT NULL COMMENT '남성 매출 금액',
  `Y` varchar(255) DEFAULT NULL COMMENT '여성 매출 금액',
  `Z` varchar(255) DEFAULT NULL COMMENT '10대 매출 금액',
  `AA` varchar(255) DEFAULT NULL COMMENT '20대 매출 금액',
  `AB` varchar(255) DEFAULT NULL COMMENT '30대 매출 금액',
  `AC` varchar(255) DEFAULT NULL COMMENT '40대 매출 금액',
  `AD` varchar(255) DEFAULT NULL COMMENT '50대 매출 금액',
  `AE` varchar(255) DEFAULT NULL COMMENT '60대 이상 매출 금액',
  `AF` varchar(255) DEFAULT NULL COMMENT '점포수'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='2017년 골목상권 결제정보'


select * from salesinformation;