use bizbox;

#골목상권 거주인구
CREATE TABLE `bizbox`.`population_location` (
	  `A` VARCHAR(255) NULL COMMENT '기준_년_코드',
	  `B` VARCHAR(255) NULL COMMENT '기준_분기_코드',
	  `C` VARCHAR(255) NULL COMMENT '상권_구분_코드',
	  `D` VARCHAR(255) NULL COMMENT '상권_구분_코드_명',
	  `E` VARCHAR(255) NULL COMMENT '상권_코드',
	  `F` VARCHAR(255) NULL COMMENT '상권_코드_명',
	  `G` VARCHAR(255) NULL COMMENT '총_상주인구_수',
	  `H` VARCHAR(255) NULL COMMENT '남성_상주인구_수',
	  `I` VARCHAR(255) NULL COMMENT '여성_상주인구_수',
	  `J` VARCHAR(255) NULL COMMENT '연령대_10_상주인구_수',
	  `K` VARCHAR(255) NULL COMMENT '연령대_20_상주인구_수',
	  `L` VARCHAR(255) NULL COMMENT '연령대_30_상주인구_수',
	  `M` VARCHAR(255) NULL COMMENT '연령대_40_상주인구_수',
	  `N` VARCHAR(255) NULL COMMENT '연령대_50_상주인구_수',
	  `O` VARCHAR(255) NULL COMMENT '연령대_60_이상_상주인구_수',
	  `P` VARCHAR(255) NULL COMMENT '남성연령대_10_상주인구_수',
	  `Q` VARCHAR(255) NULL COMMENT '남성연령대_20_상주인구_수',
	  `R` VARCHAR(255) NULL COMMENT '남성연령대_30_상주인구_수',
	  `S` VARCHAR(255) NULL COMMENT '남성연령대_40_상주인구_수',
	  `T` VARCHAR(255) NULL COMMENT '남성연령대_50_상주인구_수',
	  `U` VARCHAR(255) NULL COMMENT '남성연령대_60_이상_상주인구_수',
	  `V` VARCHAR(255) NULL COMMENT '여성연령대_10_상주인구_수',
	  `W` VARCHAR(255) NULL COMMENT '여성연령대_20_상주인구_수',
	  `X` VARCHAR(255) NULL COMMENT '여성연령대_30_상주인구_수',
	  `Y` VARCHAR(255) NULL COMMENT '여성연령대_40_상주인구_수',
	  `Z` VARCHAR(255) NULL COMMENT '여성연령대_50_상주인구_수',
	  `AA` VARCHAR(255) NULL COMMENT '여성연령대_60_이상_상주인구_수',
	  `AB` VARCHAR(255) NULL COMMENT '총_가구_수',
	  `AC` VARCHAR(255) NULL COMMENT '아파트_가구_수',
	  `AD` VARCHAR(255) NULL COMMENT '비_아파트_가구_수');
  
  select @@GLOBAL.secure_file_priv;
  
select * from population_location;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/populationByLocaion.csv'
INTO TABLE population_location CHARACTER SET UTF8
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;


# 서울시 생활인구
CREATE TABLE `bizbox`.`local_people_dong` (
	  `A` date NULL COMMENT '기준일 ID',
	  `B` integer NULL COMMENT '시간대 구분',
	  `C` VARCHAR(255) NULL COMMENT '행정동코드',
	  `D` VARCHAR(255) NULL COMMENT '총생활인구수',
	  `E` VARCHAR(255) NULL COMMENT '남자 0~9세',
	  `F` VARCHAR(255) NULL COMMENT '남자 10~14세',
	  `G` VARCHAR(255) NULL COMMENT '남자 15~19세',
	  `H` VARCHAR(255) NULL COMMENT '남자 20~24세',
	  `I` VARCHAR(255) NULL COMMENT '남자 25~29세',
	  `J` VARCHAR(255) NULL COMMENT '남자 30~34세',
	  `K` VARCHAR(255) NULL COMMENT '남자 35~39세',
	  `L` VARCHAR(255) NULL COMMENT '남자 40~44세',
	  `M` VARCHAR(255) NULL COMMENT '남자 45~49세',
	  `N` VARCHAR(255) NULL COMMENT '남자 50~54세',
	  `O` VARCHAR(255) NULL COMMENT '남자 55~59세',
	  `P` VARCHAR(255) NULL COMMENT '남자 60~64세',
	  `Q` VARCHAR(255) NULL COMMENT '남자 65~69세',
	  `R` VARCHAR(255) NULL COMMENT '남자 70세 이상',
	  `S` VARCHAR(255) NULL COMMENT '여자 0~9세',
	  `T` VARCHAR(255) NULL COMMENT '여자 10~14세',
	  `U` VARCHAR(255) NULL COMMENT '여자 15~19세',
	  `V` VARCHAR(255) NULL COMMENT '여자 20~24세',
	  `W` VARCHAR(255) NULL COMMENT '여자 25~29세',
	  `X` VARCHAR(255) NULL COMMENT '여자 30~34세',
	  `Y` VARCHAR(255) NULL COMMENT '여자 35~39세',
	  `Z` VARCHAR(255) NULL COMMENT '여자 40~44세',
	  `AA` VARCHAR(255) NULL COMMENT '여자 45~49세',
	  `AB` VARCHAR(255) NULL COMMENT '여자 50~54세',
	  `AC` VARCHAR(255) NULL COMMENT '여자 55~59세',
	  `AD` VARCHAR(255) NULL COMMENT '여자 60~64세',
	  `AE` VARCHAR(255) NULL COMMENT '여자 65~69세',
      `AF` VARCHAR(255) NULL COMMENT '여자 70세 이상'
      );
	
drop table local_people_dong;
select * from local_people_dong;
select * from local_people_dong where C like "%11740700%";

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/LOCAL_PEOPLE_DONG_201701.csv'
INTO TABLE local_people_dong CHARACTER SET UTF8
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

SET SQL_SAFE_UPDATES = 0;

DELETE FROM local_people_dong
where not A in ('20170101','20170102');

# 동 코드 변환
CREATE TABLE `bizbox`.`address_code` (
	  `A` VARCHAR(255) NULL COMMENT '시도',
	  `B` VARCHAR(255) NULL COMMENT '시군구',
	  `C` VARCHAR(255) NULL COMMENT '행정구역명',
	  `D` VARCHAR(255) NULL COMMENT '행정동(행정기관명)',
	  `E` VARCHAR(255) NULL COMMENT '법정동',
	  `F` VARCHAR(255) NULL COMMENT '행정구역코드',
	  `G` VARCHAR(255) NULL COMMENT '행정기관코드',
	  `H` VARCHAR(255) NULL COMMENT '행정기관 생성일',
	  `I` VARCHAR(255) NULL COMMENT '법정동코드',
	  `J` VARCHAR(255) NULL COMMENT '관할지역'
      );


LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/addressCode.csv'
INTO TABLE address_code CHARACTER SET UTF8
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 0 LINES;

select * from address_code;
select left(G, 8) as Num from address_code where C like '%둔촌2동%' group by Num;

select * from local_people_dong where C in ( select left(G, 8) as Num from address_code where C like '%원효로%' group by Num )
order by C, A, B ASC;




CREATE TABLE `bizbox`.`salesinformation` (
  `A` VARCHAR(255) NULL DEFAULT NULL comment '분기',
  `B` VARCHAR(255) NULL DEFAULT NULL comment '상권 구분 코드 명',
  `C` VARCHAR(255) NULL DEFAULT NULL comment '상권코드',
  `D` VARCHAR(255) NULL DEFAULT NULL comment '도로명 주소',
  `E` VARCHAR(255) NULL DEFAULT NULL comment '서비스 업종 코드',
  `F` VARCHAR(255) NULL DEFAULT NULL comment '서비스 업종 명',
  `G` VARCHAR(255) NULL DEFAULT NULL comment '분기 매출 금액',
  `H` VARCHAR(255) NULL DEFAULT NULL comment '분기 매출 건수',
  `I` VARCHAR(255) NULL DEFAULT NULL comment '주중 매출 금액',
  `J` VARCHAR(255) NULL DEFAULT NULL comment '주말 매출 금액',
  `K` VARCHAR(255) NULL DEFAULT NULL comment '월요일 매출 금액',
  `L` VARCHAR(255) NULL DEFAULT NULL comment '화요일 매출 금액',
  `M` VARCHAR(255) NULL DEFAULT NULL comment '수요일 매출 금액',
  `N` VARCHAR(255) NULL DEFAULT NULL comment '목요일 매출 금액',
  `O` VARCHAR(255) NULL DEFAULT NULL comment '금요일 매출 금액',
  `P` VARCHAR(255) NULL DEFAULT NULL comment '토요일 매출 금액',
  `Q` VARCHAR(255) NULL DEFAULT NULL comment '일요일 매출 금액',
  `R` VARCHAR(255) NULL DEFAULT NULL comment '00시~06시 매출 금액',
  `S` VARCHAR(255) NULL DEFAULT NULL comment '06시~11시 매출 금액',
  `T` VARCHAR(255) NULL DEFAULT NULL comment '11시~14시 매출 금액',
  `U` VARCHAR(255) NULL DEFAULT NULL comment '14시~17시 매출 금액',
  `V` VARCHAR(255) NULL DEFAULT NULL comment '17시~21시 매출 금액',
  `W` VARCHAR(255) NULL DEFAULT NULL comment '21시~24시 매출 금액',
  `X` VARCHAR(255) NULL DEFAULT NULL comment '남성 매출 금액',
  `Y` VARCHAR(255) NULL DEFAULT NULL comment '여성 매출 금액',
  `Z` VARCHAR(255) NULL DEFAULT NULL comment '10대 매출 금액',
  `AA` VARCHAR(255) NULL DEFAULT NULL comment '20대 매출 금액',
  `AB` VARCHAR(255) NULL DEFAULT NULL comment '30대 매출 금액',
  `AC` VARCHAR(255) NULL DEFAULT NULL comment '40대 매출 금액',
  `AD` VARCHAR(255) NULL DEFAULT NULL comment '50대 매출 금액',
  `AE` VARCHAR(255) NULL DEFAULT NULL comment '60대 이상 매출 금액',
  `AF` VARCHAR(255) NULL DEFAULT NULL comment '점포수')
COMMENT = '2017년 골목상권 결제정보' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/salesInformation.csv'
INTO TABLE salesinformation CHARACTER SET UTF8
FIELDS TERMINATED BY ','	
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

select * from salesinformation;

