--	delete from areaTbl;
--	delete from sigunTbl;
--	delete from foodTbl;
--	delete from stayTbl;
--	delete from travelTbl;
--	delete from stayInfoTbl	
--	delete from travelInfoTbl
--	delete from foodInfoTbl
--	delete from stayIntroTbl
--	delete from foodIntroTbl
--	delete from TravelIntroTbl

--	drop table areaTbl;
--	drop table sigunTbl;
--	drop table foodTbl;
--	drop table stayTbl;
--	drop table travelTbl;
--	drop table stayInfoTbl;	
--	drop table travelInfoTbl;
--	drop table foodInfoTbl;
--	drop table stayIntroTbl;
--	drop table foodIntroTbl;
--	drop table TravelIntroTbl;

conn /as sysdba
create user trip identified by trip;
GRANT resource, connect TO trip;

--	1.	areaTbl 지역 코드
create table areaTbl (
	areaCode varchar2(500),	-- 지역코드
	areaName varchar2(500)		-- 지역명	
);

--	2.	sigungu	시군구 코드
create table sigunTbl (
	areaCode varchar2(500),	-- 지역코드
	sigunCode varchar2(500),	-- 시군구코드
	sigunName varchar2(500)	-- 시군구명
);

select count(areaCode) from siguntbl;

--	3.	숙박정보조회	
create table stayTbl (
	areaCode varchar2(500),	-- 지역코드
	sigunCode varchar2(500),	-- 시군구코드
	addr1 varchar2(500),		-- 신주소
	cat1 varchar2(500),		-- 카테고리1	
	cat2 varchar2(500),		-- 카테고리2
	cat3 varchar2(500),		-- 카테고리3
	contentid varchar2(500),		-- 컨텐츠id
	contenttypeid varchar2(500),		-- 컨텐츠타입id
	createdtime varchar2(500),		-- 등록일
	firstimage varchar2(2000),		-- 첫번째이미지
	firstimage2 varchar2(2000),		-- 두번째이미지
	mapx varchar2(500),		-- x
	mapy varchar2(500),		-- y
	modifiedtime varchar2(500), -- 수정일
	readcount varchar2(1000),		-- 조회수
	tel varchar2(500), -- 전화번호
	title varchar2(500),		-- 제목 
	zipcode varchar2(500)	-- 우편주소
);


select count(contentid) from stayTbl where areaCode ='1';

--	4.	여행정보조회
create table travelTbl(
	areaCode varchar2(500),	-- 지역코드
	sigunCode varchar2(500),	-- 시군구코드
	addr1 varchar2(500),		-- 신주소
	cat1 varchar2(500),		-- 카테고리1	
	cat2 varchar2(500),		-- 카테고리2
	cat3 varchar2(500),		-- 카테고리3
	contentid varchar2(500),		-- 컨텐츠id
	contenttypeid varchar2(500),		-- 컨텐츠타입id
	createdtime varchar2(500),		-- 등록일
	firstimage varchar2(1000),		-- 첫번째이미지
	firstimage2 varchar2(1000),		-- 두번째이미지
	mapx varchar2(500),		-- x
	mapy varchar2(500),		-- y
	modifiedtime varchar2(500), -- 수정일
	readcount varchar2(1000),		-- 조회수
	tel varchar2(500), -- 전화번호
	title varchar2(500),		-- 제목 
	zipcode varchar2(500)	-- 우편주소
);

select count(readcount) from travelTbl where areaCode = '1';
select title, contentid  from travelTbl where areaCode = '1' and contenttypeid = '12';
select count(title) from travelTbl where areaCode = '1' and contenttypeid='12';
select count(title) from travelTbl where areaCode = '1' and contenttypeid='15';
select count(title) from travelTbl where areaCode = '1';
--	음식정보 조회
create table foodTbl(
	areaCode varchar2(500),	-- 지역코드
	sigunCode varchar2(500),	-- 시군구코드
	addr1 varchar2(500),		-- 신주소
	cat1 varchar2(500),		-- 카테고리1	
	cat2 varchar2(500),		-- 카테고리2
	cat3 varchar2(500),		-- 카테고리3
	contentid varchar2(500),		-- 컨텐츠id
	contenttypeid varchar2(500),		-- 컨텐츠타입id
	createdtime varchar2(500),		-- 등록일
	firstimage varchar2(1000),		-- 첫번째이미지
	firstimage2 varchar2(1000),		-- 두번째이미지
	mapx varchar2(500),		-- x
	mapy varchar2(500),		-- y
	modifiedtime varchar2(500), -- 수정일
	readcount varchar2(1000),		-- 조회수
	tel varchar2(500), -- 전화번호
	title varchar2(500),		-- 제목 
	zipcode varchar2(500)	-- 우편주소
);

select count(areaCode) from foodTbl;
select count(contentid) from foodTbl where areaCode='1';
select title from foodTbl where areaCode='39';
select title, contentid, contenttypeid  from foodTbl where areaCode = '1';

-- 숙박 상세정보 조회
create table stayInfoTbl (
	contentid varchar2(500),		-- 컨텐츠id 와 조인
	contenttypeid varchar2(500), -- 컨텐츠타입아이디 분류코드
	overview clob -- 상세내용
);

-- 여행 상세정보 조회
create table travelInfoTbl (
	contentid varchar2(500),		-- 컨텐츠id 와 조인
	contenttypeid varchar2(500), -- 컨텐츠타입아이디 분류코드
	overview clob -- 상세내용
);



-- 음식 상세정보 조회
create table foodInfoTbl (
	contentid varchar2(500),		-- 컨텐츠id 와 조인
	contenttypeid varchar2(500), -- 컨텐츠타입아이디 분류코드
	overview clob -- 상세내용
);


--	숙박 인트로
create table stayIntroTbl(
	accomcountlodging varchar2(1000),
	barbecue varchar2(1000),
	beauty varchar2(1000),
	benikia varchar2(1000),
	beverage varchar2(800),
	bicycle varchar2(800),
	campfire varchar2(800),
	checkintime varchar2(800),
	checkouttime varchar2(800),
	chkcooking varchar2(800),
	contentid varchar2(800), -- <contentid>1883368</contentid> 조인
	contenttypeid varchar2(800), -- 분류
	fitness varchar2(1000),
	foodplace varchar2(2000),
	goodstay varchar2(1000),
	hanok varchar2(1000),
	infocenterlodging varchar2(2000),
	karaoke varchar2(1000),
	parkinglodging varchar2(2000),
	pickup varchar2(1000),
	publicbath varchar2(1000),
	publicpc varchar2(1000),
	reservationlodging varchar2(2000),
	reservationurl varchar2(2500),
	roomcount varchar2(1000),
	roomtype varchar2(1000),
	sauna varchar2(1000),
	seminar varchar2(1000),
	sports varchar2(1000),
	subfacility varchar2(2500)
);


--	음식 인트로
create table foodIntroTbl(
	chkcreditcardfood varchar2(1000),
	contentid varchar2(800),	-- 조인조건
	contenttypeid varchar2(1000),
	firstmenu varchar2(1000),
	infocenterfood varchar2(1000),
	kidsfacility varchar2(1000),
	opendatefood varchar2(2000),
	opentimefood varchar2(1000),
	packing varchar2(1000),
	parkingfood varchar2(1000),
	reservationfood varchar2(1000),
	restdatefood varchar2(1000),
	seat varchar2(1000),
	smoking varchar2(1000),
	treatmenu clob
);

--	여행
create table TravelIntroTbl(
	chkbabycarriage varchar2(800),
	chkcreditcard varchar2(800),
	chkpet varchar2(800),
	contentid varchar2(1000),	--	조인
	contenttypeid varchar2(1000),
	expguide varchar2(2000),
	heritage1 varchar2(800),
	heritage2 varchar2(800),
	heritage3 varchar2(800),
	infocenter varchar2(2000),
	opendate varchar2(1000),
	parking varchar2(1000),
	restdate varchar2(1000),	
	expagerange varchar2(1000),
	usetime varchar2(1000)
);

select count(contenttypeid) from TravelIntroTbl;
select count(contenttypeid) from travelinfoTbl;
select count(contenttypeid) from  foodInfoTbl;
select count(contenttypeid) from  foodIntroTbl;
select count(contenttypeid) from stayInfoTbl;
select count(contenttypeid) from stayIntroTbl;