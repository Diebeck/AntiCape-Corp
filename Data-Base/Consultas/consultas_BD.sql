--  CLIENTE
INSERT INTO cliente (nombre, colores, superpoder) VALUES (?, ?, ?);

-- MODIFICAR CLIENTE
UPDATE Cliente SET nombre = ?, colores = ?, superpoder = ? WHERE id = ?;

-- ELIMINAR CLIENTE
DELETE FROM Cliente WHERE id = ?;

-- EN CREAR CLIENTE, CREAR TRAJE
INSERT INTO Cliente (nombre, estado) VALUES (?, ?);

-- MODIFICAR TRAJE DENTRO DE MODIFICAR CLIENTE
UPDATE Cliente SET nombre = ?, estado = ? WHERE id = ?;


-- CREAR CITA
INSERT INTO Cita values (fecha, duracion, id_cliente, id_encargado, id_taller, id_traje)




