create database clientedb;
use clientedb;
create table tbcliente(
id int auto_increment primary key,
nome varchar(50) not null,
email varchar(50) not null,
telefone varchar(15) not null,
idade int not null
);
insert into tbcliente(nome,email,telefone,idade)
values('guilherme','guiiilherme@gmail.com','11-95178-55555',20);


select * from tbcliente;