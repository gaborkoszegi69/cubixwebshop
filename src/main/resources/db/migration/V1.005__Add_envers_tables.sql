create table category_aud (id int8 not null, rev int4 not null, revtype int2, categoryname varchar(255), primary key (id, rev));
create table product_aud (id int8 not null, rev int4 not null, revtype int2, productname varchar(255), price numeric(6,3), primary key (id, rev));
create table revinfo (rev int4 not null, revtstmp int8, primary key (rev));
alter table if exists category_aud add constraint FK_category_aud_rev foreign key (rev) references revinfo;
alter table if exists product_aud add constraint F_product_aud_rev foreign key (rev) references revinfo;
