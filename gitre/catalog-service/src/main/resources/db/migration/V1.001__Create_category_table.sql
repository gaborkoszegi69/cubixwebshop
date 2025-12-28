create sequence category_seq start with 1 increment by 50;
create table category ( id integer not null,  categoryname varchar(255), primary key (id));