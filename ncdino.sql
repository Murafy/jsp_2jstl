create table tbl_fanitem (
seq number(8) primary key,
title varchar2(100) not null,
price number(8) not null,
newitem number(8),
soldout number(8),
filename varchar2(100)
);

create sequence fanitem_seq;

create sequence tblorder_seq;

select*from TBL_FANITEM WHERE SEQ=5;

insert into tbl_fanitem (seq,title,price,newitem,soldout,filename)
values (fanitem_seq.nextval,'조구만 브라키오 인형 머리띠',19000,1,0,'1.jpg');

insert into tbl_fanitem (seq,title,price,newitem,soldout,filename)
values (fanitem_seq.nextval,'2025 CAMP 2 기념구',15000,1,1,'2.jpg');

insert into tbl_fanitem (seq,title,price,newitem,soldout,filename)
values (fanitem_seq.nextval,'2025 CAMP 2 기념 열접착 패치',9000,0,0,'3.jpg');

insert into tbl_fanitem (seq,title,price,newitem,soldout,filename)
values (fanitem_seq.nextval,'2025년 일반 달력',13000,0,1,'4.jpg');

insert into tbl_fanitem (seq,title,price,newitem,soldout,filename)
values (fanitem_seq.nextval,'조구만 브라키오 봉제 키링',19000,1,1,'5.jpg');

insert into tbl_fanitem (seq,title,price,newitem,soldout,filename)
values (fanitem_seq.nextval,'2025 드래프트 기념구',15000,0,0,'6.jpg');

insert into tbl_fanitem (seq,title,price,newitem,soldout,filename)
values (fanitem_seq.nextval,'김형준 응원 타월',12000,0,0,'7.jpg');

insert into tbl_fanitem (seq,title,price,newitem,soldout,filename)
values (fanitem_seq.nextval,'김재열 응원 타월',12000,1,1,'8.jpg');

insert into tbl_fanitem (seq,title,price,newitem,soldout,filename)
values (fanitem_seq.nextval,'서호철 응원 타월',12000,1,1,'9.jpg');

insert into tbl_fanitem (seq,title,price,newitem,soldout,filename)
values (fanitem_seq.nextval,'2024 클러치 홈 유니폼',89000,0,0,'10.jpg');

insert into tbl_fanitem (seq,title,price,newitem,soldout,filename)
values (fanitem_seq.nextval,'클러치 민트 유니폼',89000,1,0,'11.jpg');

insert into tbl_fanitem (seq,title,price,newitem,soldout,filename)
values (fanitem_seq.nextval,'2024 어센틱 원정 유니폼',144000,0,0,'12.jpg');

create table tbl_fanitem_order(
	order_seq number(8) primary key,
	userid varchar2(200) not null,
	seq number(8) not null,
	count number(8) not null,
	pay number(8) not null,
	orderDate Date Default sysdate
);



drop table nc_dinos;