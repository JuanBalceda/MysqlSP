CREATE TABLE productos(
id INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(35),
cantidad INT,
categoria VARCHAR(20)
)

delimiter $
CREATE PROCEDURE reg_prod(
	IN nombre VARCHAR(35),
	IN cantidad INT,
	IN categoria VARCHAR(20))
BEGIN
	INSERT INTO productos(nombre, cantidad, categoria)
	VALUES(nombre, cantidad, categoria);
END $

delimiter $
CREATE PROCEDURE cons_prod()
BEGIN
	SELECT * FROM productos;
END $

delimiter $
CREATE PROCEDURE busq_prod(
	IN categ VARCHAR(20)	
)
BEGIN
	SELECT * FROM productos WHERE categoria=categ;
END $