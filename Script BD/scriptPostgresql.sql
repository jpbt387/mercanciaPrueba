CREATE TABLE IF NOT EXISTS "Taller".cargo_usuario
(
    id_cargo integer NOT NULL,
    descripcion_cargo character varying(500) NOT NULL,
    PRIMARY KEY (id_cargo)
)

INSERT INTO "taller".cargo_usuario(id_cargo, descripcion_cargo)
	VALUES (1, 'administrador'),
		   (2,'soporte'),
		   (3,'asesor de ventas');

create table IF NOT EXISTS "taller".usuario(
	id_usuario serial,
	nombre_usuario character varying(150) not null,
	edad integer not null,
	cargo_id_cargo integer not null,
	primary key (id_usuario),
	foreign key (cargo_id_cargo) references "taller".cargo_usuario(id_cargo)
)

INSERT INTO "taller".usuario(nombre_usuario,edad,cargo_id_cargo)
	VALUES ('juan torres',28,1),
		   ('jose sosa',23,2),
		   ('ana rojas',30,3);

create table "taller".mercancia(
	id_producto serial not null,
	nombre_producto character varying(100) not null,
	cantidad integer not null,
	fecha_registro date not null,
	usuario integer not null,
	fecha_modificacion date not null,
	editor integer not null,
	primary key (id_producto),
	foreign key (usuario) references "taller".usuario(id_usuario),
	foreign key (editor) references "taller".usuario(id_usuario)
);


