/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  JOSE SANDOVAL
 * Created: 20/04/2024
 */


CREATE TABLE Usuario(

    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    email VARCHAR(50),
    telefono VARCHAR(50),
    direccion VARCHAR(50)


);



CREATE TABLE SemestreAcademico(

    id INT AUTO_INCREMENT PRIMARY KEY,
    codigoAcademico INT NOT NULL,
    año VARCHAR(4),
    semestre CHAR

);

CREATE TABLE ProgramaAcademico(

    id INT AUTO_INCREMENT PRIMARY KEY,
    codigoAcademico INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    facultad VARCHAR(50)


);


CREATE TABLE Pensum(

    id INT AUTO_INCREMENT PRIMARY KEY,
    codigoAcademico INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    id_programa INT,
    semestres VARCHAR(2),
    FOREIGN KEY (id_programa) REFERENCES ProgramaAcademico(id) 

);

CREATE TABLE Asignatura(

    id INT AUTO_INCREMENT PRIMARY KEY,
    codigoAcademico INT NOT NULL,
    nombre VARCHAR(50),
    creditos VARCHAR(2),
    id_pensum INT,
    tipo VARCHAR(50),
    FOREIGN KEY(id_pensum) REFERENCES Pensum(id)
    

);



CREATE TABLE Profesor(

    id INT AUTO_INCREMENT PRIMARY KEY,
    codigoAcademico INT NOT NULL,
    maximoTitulo VARCHAR(50),
    especialidad VARCHAR(50),
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)


);


CREATE TABLE Curso(

    id INT AUTO_INCREMENT PRIMARY KEY,
    codigoAcademico INT NOT NULL,
    nombre VARCHAR(50),
    salonT VARCHAR(50),
    salonP VARCHAR(50),
    id_asignatura INT,
    id_profesor INT,
    FOREIGN KEY (id_asignatura) REFERENCES asignatura(id),
    FOREIGN KEY (id_profesor) REFERENCES Profesor(id)
    





);

CREATE TABLE Administrador(

    id INT AUTO_INCREMENT PRIMARY KEY,
    codigoAcademico INT NOT NULL,
    puesto VARCHAR(50),
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)



);



CREATE TABLE Estudiante(

    id INT AUTO_INCREMENT PRIMARY KEY,
    codigoAcademico INT NOT NULL,
    id_programa INT,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    FOREIGN KEY (id_programa) REFERENCES ProgramaAcademico(id)

);

