CREATE DATABASE ColeDB;
USE ColeDB;

CREATE TABLE Acceso(
idAcceso char(5) NOT NULL,
nom varchar(20) NOT NULL,
contra varchar(8) NOT NULL,
CONSTRAINT pk_Acceso PRIMARY KEY (idAcceso)
);

CREATE TABLE Nivel(
idNivel int(1) AUTO_INCREMENT NOT NULL,
descripcion varchar(20) NOT NULL,
CONSTRAINT pk_Nivel PRIMARY KEY(idNivel)
);

CREATE TABLE Grado(
idGrado int(2) NOT NULL,
idNivel int(1) NOT NULL,
descripcion varchar(20),
CONSTRAINT pk_Grado PRIMARY KEY (idNivel,idGrado),
CONSTRAINT fk_GradoXNivel FOREIGN KEY (idNivel) 
REFERENCES Nivel(idNivel)
);

CREATE TABLE Alumno(
idAlumno int(3) AUTO_INCREMENT NOT NULL,
nom varchar(20) NOT NULL,
ape varchar(20) NOT NULL,
fechNac date NOT NULL,
idNivel int(1) NOT NULL,
idGrado int(2) NOT NULL,
CONSTRAINT pk_Alumno PRIMARY KEY (idAlumno),
CONSTRAINT fk_AlunmoXGrado FOREIGN KEY (idNivel, idGrado)
REFERENCES Grado(idNivel,idGrado)
);



CREATE TABLE Profesor(
idProfesor int(3) AUTO_INCREMENT NOT NULL,
nom varchar(20) NOT NULL,
ape varchar(20) NOT NULL,
sueldo float(6,2) NOT NULL,
CONSTRAINT pk_Profesor PRIMARY KEY (idProfesor)
);

insert into Profesor(nom, ape,sueldo) values('No Asignado', ' ', 0.0);
update Profesor set idProfesor=0;

ALTER TABLE Grado ADD idProfesor int(3) NOT NULL;
ALTER TABLE Grado ADD CONSTRAINT fk_GradoXProfesor FOREIGN KEY(idProfesor) 
REFERENCES Profesor(idProfesor);


insert into Acceso(idAcceso, nom, contra) values('admin', 'Administrador', 'clave123');

insert into Nivel(descripcion) values('Primaria');
insert into Nivel(descripcion) values('Secundaria');

USE ColeDB;
insert into Grado(idNivel, idGrado, descripcion, idProfesor) values(1,1,'1ro',0);
insert into Grado(idNivel, idGrado, descripcion, idProfesor) values(1,2,'2do',0);	
insert into Grado(idNivel, idGrado, descripcion, idProfesor) values(1,3,'3ro',0);
insert into Grado(idNivel, idGrado, descripcion, idProfesor) values(1,4,'4to',0);
insert into Grado(idNivel, idGrado, descripcion, idProfesor) values(1,5,'5to',0);
insert into Grado(idNivel, idGrado, descripcion, idProfesor) values(1,6,'6to',0);
USE ColeDB;
insert into Grado(idNivel, idGrado, descripcion, idProfesor) values(2,1,'1ro',0);
insert into Grado(idNivel, idGrado, descripcion, idProfesor) values(2,2,'2do',0);	
insert into Grado(idNivel, idGrado, descripcion, idProfesor) values(2,3,'3ro',0);
insert into Grado(idNivel, idGrado, descripcion, idProfesor) values(2,4,'4to',0);
insert into Grado(idNivel, idGrado, descripcion, idProfesor) values(2,5,'5to',0);
USE ColeDB;
insert into Alumno(nom, ape, fechNac, idNivel, idGrado)	values('Nombre01','Apellido01', '2000-10-15', 2, 2);

