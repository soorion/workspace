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

--	1.	areaTbl ���� �ڵ�
create table areaTbl (
	areaCode varchar2(500),	-- �����ڵ�
	areaName varchar2(500)		-- ������	
);

--	2.	sigungu	�ñ��� �ڵ�
create table sigunTbl (
	areaCode varchar2(500),	-- �����ڵ�
	sigunCode varchar2(500),	-- �ñ����ڵ�
	sigunName varchar2(500)	-- �ñ�����
);

select count(areaCode) from siguntbl;

--	3.	����������ȸ	
create table stayTbl (
	areaCode varchar2(500),	-- �����ڵ�
	sigunCode varchar2(500),	-- �ñ����ڵ�
	addr1 varchar2(500),		-- ���ּ�
	cat1 varchar2(500),		-- ī�װ�1	
	cat2 varchar2(500),		-- ī�װ�2
	cat3 varchar2(500),		-- ī�װ�3
	contentid varchar2(500),		-- ������id
	contenttypeid varchar2(500),		-- ������Ÿ��id
	createdtime varchar2(500),		-- �����
	firstimage varchar2(2000),		-- ù��°�̹���
	firstimage2 varchar2(2000),		-- �ι�°�̹���
	mapx varchar2(500),		-- x
	mapy varchar2(500),		-- y
	modifiedtime varchar2(500), -- ������
	readcount varchar2(1000),		-- ��ȸ��
	tel varchar2(500), -- ��ȭ��ȣ
	title varchar2(500),		-- ���� 
	zipcode varchar2(500)	-- �����ּ�
);


select count(contentid) from stayTbl where areaCode ='1';

--	4.	����������ȸ
create table travelTbl(
	areaCode varchar2(500),	-- �����ڵ�
	sigunCode varchar2(500),	-- �ñ����ڵ�
	addr1 varchar2(500),		-- ���ּ�
	cat1 varchar2(500),		-- ī�װ�1	
	cat2 varchar2(500),		-- ī�װ�2
	cat3 varchar2(500),		-- ī�װ�3
	contentid varchar2(500),		-- ������id
	contenttypeid varchar2(500),		-- ������Ÿ��id
	createdtime varchar2(500),		-- �����
	firstimage varchar2(1000),		-- ù��°�̹���
	firstimage2 varchar2(1000),		-- �ι�°�̹���
	mapx varchar2(500),		-- x
	mapy varchar2(500),		-- y
	modifiedtime varchar2(500), -- ������
	readcount varchar2(1000),		-- ��ȸ��
	tel varchar2(500), -- ��ȭ��ȣ
	title varchar2(500),		-- ���� 
	zipcode varchar2(500)	-- �����ּ�
);

select count(readcount) from travelTbl where areaCode = '1';
select title, contentid  from travelTbl where areaCode = '1' and contenttypeid = '12';
select count(title) from travelTbl where areaCode = '1' and contenttypeid='12';
select count(title) from travelTbl where areaCode = '1' and contenttypeid='15';
select count(title) from travelTbl where areaCode = '1';
--	�������� ��ȸ
create table foodTbl(
	areaCode varchar2(500),	-- �����ڵ�
	sigunCode varchar2(500),	-- �ñ����ڵ�
	addr1 varchar2(500),		-- ���ּ�
	cat1 varchar2(500),		-- ī�װ�1	
	cat2 varchar2(500),		-- ī�װ�2
	cat3 varchar2(500),		-- ī�װ�3
	contentid varchar2(500),		-- ������id
	contenttypeid varchar2(500),		-- ������Ÿ��id
	createdtime varchar2(500),		-- �����
	firstimage varchar2(1000),		-- ù��°�̹���
	firstimage2 varchar2(1000),		-- �ι�°�̹���
	mapx varchar2(500),		-- x
	mapy varchar2(500),		-- y
	modifiedtime varchar2(500), -- ������
	readcount varchar2(1000),		-- ��ȸ��
	tel varchar2(500), -- ��ȭ��ȣ
	title varchar2(500),		-- ���� 
	zipcode varchar2(500)	-- �����ּ�
);

select count(areaCode) from foodTbl;
select count(contentid) from foodTbl where areaCode='1';
select title from foodTbl where areaCode='39';
select title, contentid, contenttypeid  from foodTbl where areaCode = '1';

-- ���� ������ ��ȸ
create table stayInfoTbl (
	contentid varchar2(500),		-- ������id �� ����
	contenttypeid varchar2(500), -- ������Ÿ�Ծ��̵� �з��ڵ�
	overview clob -- �󼼳���
);

-- ���� ������ ��ȸ
create table travelInfoTbl (
	contentid varchar2(500),		-- ������id �� ����
	contenttypeid varchar2(500), -- ������Ÿ�Ծ��̵� �з��ڵ�
	overview clob -- �󼼳���
);



-- ���� ������ ��ȸ
create table foodInfoTbl (
	contentid varchar2(500),		-- ������id �� ����
	contenttypeid varchar2(500), -- ������Ÿ�Ծ��̵� �з��ڵ�
	overview clob -- �󼼳���
);


--	���� ��Ʈ��
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
	contentid varchar2(800), -- <contentid>1883368</contentid> ����
	contenttypeid varchar2(800), -- �з�
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


--	���� ��Ʈ��
create table foodIntroTbl(
	chkcreditcardfood varchar2(1000),
	contentid varchar2(800),	-- ��������
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

--	����
create table TravelIntroTbl(
	chkbabycarriage varchar2(800),
	chkcreditcard varchar2(800),
	chkpet varchar2(800),
	contentid varchar2(1000),	--	����
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