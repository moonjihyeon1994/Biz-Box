CREATE TABLE `population_time` (
  `a` varchar(45) DEFAULT NULL COMMENT '기준_년_코드',
  `b` varchar(45) DEFAULT NULL COMMENT '기준_분기_코드',
  `c` varchar(45) DEFAULT NULL COMMENT '상권_구분_코드',
  `d` varchar(45) DEFAULT NULL COMMENT '상권_구분_코드_명',
  `e` varchar(45) DEFAULT NULL COMMENT '상권_코드',
  `f` varchar(45) DEFAULT NULL COMMENT '상권_코드_명',
  `g` varchar(45) DEFAULT NULL COMMENT '총_유동인구_수',
  `h` varchar(45) DEFAULT NULL COMMENT '남성_유동인구_수',
  `i` varchar(45) DEFAULT NULL COMMENT '여성_유동인구_수',
  `j` varchar(45) DEFAULT NULL COMMENT '시간대_1_유동인구_수',
  `k` varchar(45) DEFAULT NULL COMMENT '시간대_2_유동인구_수',
  `l` varchar(45) DEFAULT NULL COMMENT '시간대_3_유동인구_수',
  `m` varchar(45) DEFAULT NULL COMMENT '시간대_4_유동인구_수',
  `n` varchar(45) DEFAULT NULL COMMENT '시간대_5_유동인구_수',
  `o` varchar(45) DEFAULT NULL COMMENT '시간대_6_유동인구_수',
  `p` varchar(45) DEFAULT NULL COMMENT '월요일_유동인구_수',
  `q` varchar(45) DEFAULT NULL COMMENT '화요일_유동인구_수',
  `r` varchar(45) DEFAULT NULL COMMENT '수요일_유동인구_수',
  `s` varchar(45) DEFAULT NULL COMMENT '목요일_유동인구_수',
  `t` varchar(45) DEFAULT NULL COMMENT '금요일_유동인구_수',
  `u` varchar(45) DEFAULT NULL COMMENT '토요일_유동인구_수',
  `v` varchar(45) DEFAULT NULL COMMENT '일요일_유동인구_수'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/populationByTime.csv'
INTO TABLE populationage CHARACTER SET UTF8
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

-- businesschange --
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci