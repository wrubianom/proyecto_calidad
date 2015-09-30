


/*LA BASE DE DATOS SE DEBE LLAMAR " Sistema_Notas "*/


create table usuario
(
id_usuario serial primary key,
login varchar(50) not null,
nombre varchar(50) not null,
clave varchar(50) not null,
tipo varchar(15)
);


create table materia
(
id_materia serial primary key,
codigo varchar(50) not null,
nombre varchar(50) not null,
tipo varchar(2) not null,
creditos varchar(1) not null,
ih varchar(1) not null,
semestre varchar(1) not null
);

create table profesor
(
id_profesor serial primary key,
codigo varchar(12) not null,
nombre varchar(40) not null,
tipo_contrato varchar(15) not null,
disponibilidad varchar(1) not null
);

create table perfil 
(
id_perfil serial primary key,
id_profesor integer references profesor(id_profesor),
id_materia integer references materia(id_materia)
);


create table horario_profesor
(
id_horario_profesor serial primary key,
id_profesor integer references profesor(id_profesor),
dia varchar(1),
hora varchar(2),
estado varchar(1) default '0',
manual varchar(1) default '0'
);

ALTER TABLE horario_profesor
	ADD CONSTRAINT UQ_cod_dia_hora UNIQUE (id_profesor,dia,hora);


create table horario_materia
(
id_horario_materia serial primary key,
id_materia integer references materia(id_materia),
grupo varchar(3) ,
dia varchar(1),
hora varchar(2) not null,
duracion varchar(1) not null,
semestre varchar(1) not null,
jornada varchar(1) not null
);

ALTER TABLE horario_materia
	ADD CONSTRAINT UQ_id_materia_grupo_dia UNIQUE (id_materia,grupo,dia);





