use bizbox;

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
  
drop table population_location;
select * from population_location;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/populationByLocaion.csv'
INTO TABLE population_location CHARACTER SET UTF8
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

select * from populationAge where F like '천호옛길'  order by a DESC  limit 1;