INSERT INTO roles (role_id, role) VALUES ("1", "ROLE_ADMIN");
INSERT INTO roles (role_id, role) VALUES ("2", "ROLE_USER");

INSERT INTO empleados (username, apellidos, create_time, direccion, email, enabled, nombre, password, telefono, update_time) VALUES ("admin", "Reyes", "2019-06-09", "Calle Cobo", "felix@felix,com", 1, "Felix", "$2a$10$9BYQVmdJujf.yOLlEnHu0el2aPoj8lL85UnZPkASfcXXyAMq1lLee", "604567454", "2019-06-09");

INSERT INTO empleados_roles (username, role_id) VALUES ("admin", 1);

INSERT INTO proveedores (cantidad, create_time, descripcion, direccion, email, empresa, estado_pago, frecuencia, telefono, update_time) VALUES ("20", "2019-06-09", "Es de prueba", "Calle Cobo", "zara@zara.com", "zara", "pendiente", "anual", "561256464", "2019-06-09");

INSERT INTO productos (color, create_time, descripcion, imagen, material, nombre, precio, stock, talla, update_time, proveedores_id) VALUES ("blanco", "2019-06-09", "Es de prueba", "", "algodon", "camiseta basica", "10", "30", "M", "2019-06-09", "1");
INSERT INTO productos (color, create_time, descripcion, imagen, material, nombre, precio, stock, talla, update_time, proveedores_id) VALUES ("azul", "2019-06-09", "Es de prueba", "", "vaquero", "pantalon tejano roto", "24.99", "40", "L", "2019-06-09", "1");

INSERT INTO categorias (nombre) VALUES ("Mujer");
INSERT INTO categorias (nombre) VALUES ("Hombre");
INSERT INTO categorias (nombre) VALUES ("Camisetas");
INSERT INTO categorias (nombre) VALUES ("Pantalones");
INSERT INTO categorias (nombre) VALUES ("Zapatos");

INSERT INTO clientes (apellidos, ciudad, codigo_postal, create_time, direccion, email, movil, nombre, sexo, update_time) VALUES ("Guzman Lopez", "San Lucar", "11922", "2019-06-09", "Calle Nueva", "quino@quino.com", "23423232", "Quino", "hombre", "2019-06-09");
