# BizBox 
SSAFY 서울 2기 7조 

## backend

기술 스택  
Spring, Swagger, Mysql, AWS

### API 


-----
DATA INDEX   
   

사용 DATA / API  -   

1) 상권별 거주인구 연령정보  
2) 동 단위 시간별 생활인구   
3) 행정번호, 동이름 변환





----
> 1. 상권별 거주인구 연령정보

데이터 출처 url: https://data.seoul.go.kr/dataList/OA-15584/S/1/datasetView.do   
함수 url:  http://IP주소:8080/population/getPopulationByLocation/도로명주소

input : 도로명 주소  
output : JSON
| 변수 이름 (데이터 타입) |         변수 의미          |
| :---------------------: | :------------------------: |
|       A (String)        |        기준년 코드         |
|       B (String)        |       기준분기 코드        |
|       C (String)        |       상권구분 코드        |
|       D (String)        |      상권구분 코드명       |
|       E (String)        |         상권 코드          |
|       F (String)        |        상권 코드 명        |
|       G (String)        |       총 상주인구 수       |
|       H (String)        |      남성 상주인구 수      |
|       I (String)        |      여성 상주인구 수      |
|       J (String)        |      10대 상주인구 수      |
|       K (String)        |      20대 상주인구 수      |
|       L (String)        |      30대 상주인구 수      |
|       M (String)        |      40대 상주인구 수      |
|       N (String)        |      50대 상주인구 수      |
|       O (String)        |   60대 이상 상주인구 수    |
|       P (String)        |   남성 10대 상주인구 수    |
|       Q (String)        |   남성 20대 상주인구 수    |
|       R (String)        |   남성 30대 상주인구 수    |
|       S (String)        |   남성 40대 상주인구 수    |
|       T (String)        |   남성 50대 상주인구 수    |
|       U (String)        | 남성 60대 이상 상주인구 수 |
|       V (String)        |   여성 10대 상주인구 수    |
|       W (String)        |   여성 20대 상주인구 수    |
|       X (String)        |   여성 30대 상주인구 수    |
|       Y (String)        |   여성 40대 상주인구 수    |
|       Z (String)        |   여성 50대 상주인구 수    |
|       AA (String)       | 여성 60대 이상 상주인구 수 |
|       AB (String)       |         총 가구 수         |
|       AC (String)       |       아파트 가구 수       |
|       AD (String)       |     비 아파트 가구 수      |     




----   

> 2. 동 단위 시간별 생활인구

데이터 출처 url: https://data.seoul.go.kr/dataVisual/seoul/seoulLivingPopulation.do   
함수 url:  http://70.12.247.78:8080/population/getPopulationByDong/동이름

input :  동 이름   
output : JSON
| 변수 이름 (데이터 타입) |         변수 의미          |
| :---------------------: | :------------------------: |
|       A (Date)        |        기준일         |
|       B (int)        |       시간대 구분        |
|       C (String)        |       행정동 코드        |
|       D (String)        |      총 생활 인구 수      |
|       E (String)        |        남자 0~9세           |
|       F (String)        |        남자 10~14세        |
|       G (String)        |      남자 15~19세      |
|       H (String)        |      남자 20~24세      |
|       I (String)        |      남자 25~29세      |
|       J (String)        |      남자 30~34세      |
|       K (String)        |      남자 35~39세      |
|       L (String)        |      남자 40~44세      |
|       M (String)        |      남자 45~49세      |
|       N (String)        |   남자 50~54세    |
|       O (String)        |   남자 55~59세    |
|       P (String)        |   남자 60~64세    |
|       Q (String)        |   남자 65~69세    |
|       R (String)        |   남자 70세 이상    |
|       S (String)        |   여자 0~9세    |
|       T (String)        |   여자 10~14세     |
|       U (String)        |   여자 15~19세    |
|       V (String)        |   여자 20~24세    |
|       W (String)        |   여자 25~29세    |
|       X (String)        |   여자 30~34세    |
|       Y (String)        |   여자 35~39세    |
|       Z (String)       |   여자 40~44세    |
|       AA (String)       |   여자 45~49세         |
|       AB (String)       |    여자 50~54세       |
|       AC (String)       |     여자 55~59세      |
|       AD (String)       |     여자 60~64세      |
|       AE (String)       |     여자 65~69세      |
|       AF (String)       |     여자 70세 이상      |   


----   

> 3. 행정번호, 동 이름 변환

데이터 출처 :  통계청 통계정책국 통계기준과   

input :  행정 구역 코드     
output : JSON (동이름 포함)
| 변수 이름 (데이터 타입) |         변수 의미          |
| :---------------------: | :------------------------: |
|       A (Date)        |        기준일         |
|       B (int)        |       시간대 구분        |
|       C (String)        |       행정동 코드        |
|       D (String)        |      총 생활 인구 수      |
|       E (String)        |        남자 0~9세           |
|       F (String)        |        남자 10~14세        |
|       G (String)        |      남자 15~19세      |
|       H (String)        |      남자 20~24세      |
|       I (String)        |      남자 25~29세      |
|       J (String)        |      남자 30~34세      |
