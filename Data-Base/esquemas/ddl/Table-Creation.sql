CREATE TABLE `Cliente` (
  `id_cliente` integer PRIMARY KEY,
  `nombre` varchar(255),
  `colores` varchar(255),
  `superpoder` varchar(255)
);

CREATE TABLE `Traje` (
  `id_traje` integer PRIMARY KEY,
  `nombre` varchar(255),
  `estado` varchar(255)
);

CREATE TABLE `Taller` (
  `id_taller` integer PRIMARY KEY,
  `tipo_sala` varchar(255),
  `nombre_sala` varchar(255)
);

CREATE TABLE `Empleado` (
  `id_empleado` integer PRIMARY KEY,
  `nombre` varchar(255),
  `apellidos` varchar(255),
  `apodo` varchar(255),
  `categoria` varchar(255),
  `contraseña` varchar(255)
);

CREATE TABLE `Citas` (
  `id_cita` integer PRIMARY KEY,
  `fecha` date,
  `duracion` timestamp,
  `id_cliente` integer,
  `id_empleado` integer,
  `id_traje` integer,
  `id_taller` integer
);

ALTER TABLE `Citas` ADD FOREIGN KEY (`id_cliente`) REFERENCES `Cliente` (`id_cliente`);

ALTER TABLE `Empleado` ADD FOREIGN KEY (`id_empleado`) REFERENCES `Citas` (`id_empleado`);

ALTER TABLE `Citas` ADD FOREIGN KEY (`id_traje`) REFERENCES `Traje` (`id_traje`);

ALTER TABLE `Citas` ADD FOREIGN KEY (`id_taller`) REFERENCES `Taller` (`id_taller`);
