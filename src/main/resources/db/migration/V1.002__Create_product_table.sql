create sequence product_seq start with 1 increment by 50;
create table product ( id integer not null,  productname varchar(255), primary key (id));