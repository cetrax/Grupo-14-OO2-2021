USE `Grupo-14-bdd-OO2-2020`;

INSERT INTO `local` (id_local,direccion,latitud,longitud,telefono) VALUES(1,"Alto Avellaneda Shopping, Gral. Güemes 897",3.15,1,"1111111111");
INSERT INTO `local` (direccion,latitud,longitud,telefono) VALUES("Abasto Shopping, Av. Corrientes 3247",-3.15,1,"2222222222");
INSERT INTO `local` (direccion,latitud,longitud,telefono) VALUES("Nike Factory Store, Av. Pres. Hipólito Yrigoyen 9272",6.35,2.423,"3333333333");
INSERT INTO `local` (direccion,latitud,longitud,telefono) VALUES("Paseo Alcorta Shopping, Jerónimo Salguero 3172",3.145,2.1,"4444444444");
INSERT INTO `local` (direccion,latitud,longitud,telefono) VALUES("Nike Soho, Gurruchaga 1615",3.23,1.9,"5555555555");

INSERT INTO persona (id_persona,nombre,apellido,fecha_de_nacimiento,dni) VALUES(1,"Santiago","Gonzalez",'1990-09-01',11111111);
INSERT INTO persona (nombre,apellido,fecha_de_nacimiento,dni) VALUES("Mateo","Rodriguez",'1990-09-11',12222222);
INSERT INTO persona (nombre,apellido,fecha_de_nacimiento,dni) VALUES("Sergio","Avalos",'1982-04-04',11111122);
INSERT INTO persona (nombre,apellido,fecha_de_nacimiento,dni) VALUES("Juana","Meneses",'1962-01-31',22222222);

INSERT INTO persona (nombre,apellido,fecha_de_nacimiento,dni) VALUES("Emmanuel","Dorado",'2000-08-25',22221111);
INSERT INTO persona (nombre,apellido,fecha_de_nacimiento,dni) VALUES("Gonzalo","Cetraro",'1997-05-22',22223333);
INSERT INTO persona (nombre,apellido,fecha_de_nacimiento,dni) VALUES("Franco","Astorga",'2001-02-18',22224444);
INSERT INTO persona (nombre,apellido,fecha_de_nacimiento,dni) VALUES("Mia","Molina",'2002-07-15',22225555);


INSERT INTO empleado (id_persona,franja_horaria,es_gerente,id_local) VALUES(1,"de 09:30 a 17:30",false,2);
INSERT INTO cliente (id_persona,email) VALUES(2,"nlopez@gmail.com");
INSERT INTO empleado (id_persona,franja_horaria,es_gerente,id_local) VALUES(3,"de 09:30 a 17:30",true,1);
INSERT INTO cliente (id_persona,email) VALUES(4,"nlopez@gmail.com");
INSERT INTO empleado (id_persona,franja_horaria,es_gerente,id_local) VALUES(5,"de 08:30 a 16:30",false,2);
INSERT INTO empleado (id_persona,franja_horaria,es_gerente,id_local) VALUES(6,"de 08:30 a 16:30",false,1);
INSERT INTO empleado (id_persona,franja_horaria,es_gerente,id_local) VALUES(7,"de 09:30 a 17:30",false,3);
INSERT INTO empleado (id_persona,franja_horaria,es_gerente,id_local) VALUES(8,"de 08:30 a 16:30",false,3);

INSERT INTO producto (id_producto,descripcion,fecha_alta,nombre,precio) VALUES(1,"Extra resistente",'2020-05-31',"Zapatillas blancas",3230);
INSERT INTO producto (descripcion,fecha_alta,nombre,precio) VALUES("Aptos para trabajar",'2020-04-20',"Botines negros",4500);
INSERT INTO producto (descripcion,fecha_alta,nombre,precio) VALUES("Deportivas",'2020-03-17',"Zapatillas multicolor",2456);
INSERT INTO producto (descripcion,fecha_alta,nombre,precio) VALUES("Impermeables",'2020-05-31',"Botas",3890);

INSERT INTO lote (id_lote,cantidad_actual,cantidad_inicial,estado,fecha_ingreso,id_local,producto_id_producto) VALUES(1,20,20,1,'2020-06-09',1,1);
INSERT INTO lote (cantidad_actual,cantidad_inicial,estado,fecha_ingreso,id_local,producto_id_producto) VALUES(9,9,1,'2020-06-09',1,2);
INSERT INTO lote (cantidad_actual,cantidad_inicial,estado,fecha_ingreso,id_local,producto_id_producto) VALUES(24,24,1,'2020-06-09',2,4);
INSERT INTO lote (cantidad_actual,cantidad_inicial,estado,fecha_ingreso,id_local,producto_id_producto) VALUES(21,21,1,'2020-06-09',2,2);
INSERT INTO lote (cantidad_actual,cantidad_inicial,estado,fecha_ingreso,id_local,producto_id_producto) VALUES(31,31,1,'2020-06-09',3,2);
INSERT INTO lote (cantidad_actual,cantidad_inicial,estado,fecha_ingreso,id_local,producto_id_producto) VALUES(15,12,1,'2020-06-09',4,2);