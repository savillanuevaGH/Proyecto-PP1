-- Active: 1723471438634@@127.0.0.1@3306@order_scheduler
-- Crear la tabla usuario
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    fecha_baja DATE,
    rol ENUM(
        'Empleado',
        'AdminRestaurante'
    ),
    contrasena VARCHAR(100),
    dni INT
);

-- Crear la tabla pedido
CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    semana INT NOT NULL,
    fecha_baja DATE,
    usuario_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

-- Crear la tabla categoria
CREATE TABLE categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Clave primaria numérica
    categoria ENUM(
        'Vegetariano',
        'Carne',
        'Postres',
        'Bebidas',
        'Ensaladas',
        'Complementos',
        'Sopas',
        'Pastas',
        'Mariscos',
        'Comida_Rápida',
        'Snacks'
    ) NOT NULL
);

-- Crear la tabla producto
CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    comida VARCHAR(255) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    descripcion VARCHAR(255),
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id) ON DELETE SET NULL
);

-- Crear la tabla dia
CREATE TABLE dia (
    id ENUM(
        'Lunes',
        'Martes',
        'Miercoles',
        'Jueves',
        'Viernes'
    ),
    producto_id INT,
    FOREIGN KEY (producto_id) REFERENCES producto (id)
);

-- Crear la tabla pedido_producto
CREATE TABLE pedido_producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dia INT NOT NULL,
    cantidad INT NOT NULL,
    pedido_id INT NOT NULL,
    producto_id INT NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedido (id),
    FOREIGN KEY (producto_id) REFERENCES producto (id)
);

-- Crear la tabla menu
CREATE TABLE menu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    semana INT NOT NULL,
    fecha_baja DATE,
    usuario_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

-- Crear la tabla menu_producto
CREATE TABLE menu_producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT NOT NULL,
    menu_id INT NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES producto (id),
    FOREIGN KEY (menu_id) REFERENCES menu (id)
);

-- Inserciones en la tabla usuario
INSERT INTO usuario (email, nombre, rol, contrasena, dni) VALUES 
    ('admin@restaurant.com', 'Admin Restaurante', 'AdminRestaurante', 'adminRestaurante', 33221100),
    ('empleado1@restaurant.com', 'Juan Pérez', 'Empleado', 'laburoduro', 44786290),
    ('empleado2@restaurant.com', 'María López', 'Empleado', 'restopass', 45823666),
    ('empleado3@restaurant.com', 'Carlos García', 'Empleado', 'mesaratona', 40266510),
    ('empleado4@restaurant.com', 'Ana Torres', 'Empleado', 'ana123', 12345678),
    ('empleado5@restaurant.com', 'Luis Fernández', 'Empleado', 'luispass', 87654321);

-- Inserciones en la tabla categoria
INSERT INTO categoria (categoria) VALUES
    ('Vegetariano'),
    ('Carne'),
    ('Postres'),
    ('Bebidas'),
    ('Ensaladas'),
    ('Complementos'),
    ('Sopas'),
    ('Pastas'),
    ('Mariscos'),
    ('Comida_Rápida'),
    ('Snacks');

-- Inserciones en la tabla producto
INSERT INTO producto (comida, stock, descripcion, categoria_id) VALUES
    ('Pizza Margherita', 20, 'Pizza con tomate y mozzarella', 1),  -- Vegetariano
    ('Ensalada César', 15, 'Ensalada con pollo y aderezo César', 2),  -- Carne
    ('Sushi Variado', 30, 'Sushi de atún, salmón y vegetales', 2),  -- Carne
    ('Hamburguesa Clásica', 25, 'Hamburguesa con queso y lechuga', 2),  -- Carne
    ('Tacos al Pastor', 40, 'Tacos con carne de cerdo y piña', 2),  -- Carne
    ('Sopa de Lentejas', 15, 'Sopa nutritiva de lentejas', 1),  -- Vegetariano
    ('Bebida de Limonada', 25, 'Limonada refrescante', 4),  -- Bebidas
    ('Brownie de Chocolate', 10, 'Postre de chocolate', 3),  -- Postres
    ('Wrap de Pollo', 20, 'Wrap con pollo y vegetales', 5),  -- Ensaladas
    ('Pasta al Pesto', 18, 'Pasta con salsa de pesto', 8),  -- Pastas
    ('Taco de Mariscos', 22, 'Tacos rellenos de mariscos frescos', 9),  -- Mariscos
    ('Ensalada de Frutas', 14, 'Ensalada fresca de frutas variadas', 6),  -- Complementos
    ('Sopa de Pollo', 12, 'Sopa caliente de pollo con verduras', 7),  -- Sopas
    ('Pizza de Pepperoni', 30, 'Pizza con pepperoni y queso', 2),  -- Carne
    ('Nachos con Queso', 25, 'Nachos crujientes con queso derretido', 10),  -- Comida Rápida
    ('Batido de Fresa', 20, 'Batido de fresa y yogur', 4),  -- Bebidas
    ('Snacks de Frutos Secos', 15, 'Mezcla de frutos secos', 11);  -- Snacks


-- Inserciones en la tabla pedido
INSERT INTO pedido (semana, usuario_id) VALUES
    (1, 2),  -- Pedido de Juan Pérez para la semana 1
    (2, 3),  -- Pedido de María López para la semana 2
    (3, 4),  -- Pedido de Carlos García para la semana 3
    (1, 4),  -- Pedido de Ana Torres para la semana 1
    (2, 5),  -- Pedido de Luis Fernández para la semana 2
    (3, 1);  -- Pedido de Admin para la semana 3

-- Inserciones en la tabla pedido_producto
INSERT INTO pedido_producto (dia, cantidad, pedido_id, producto_id) VALUES
    (1, 1, 1, 1),  -- Juan pide 1 Pizza Margherita el lunes
    (3, 1, 1, 2),  -- Juan pide 1 Ensalada César el miércoles
    (4, 2, 1, 3),  -- Juan pide 2 Sushi Variado el jueves
    (5, 1, 1, 4),  -- Juan pide 1 Hamburguesa Clásica el viernes
    (2, 1, 1, 5),  -- Juan pide 1 Tacos al Pastor el martes
    (1, 1, 2, 1),  -- María pide 1 Pizza Margherita el lunes
    (2, 1, 2, 3),  -- María pide 1 Sushi Variado el martes
    (3, 2, 2, 4),  -- María pide 2 Hamburguesa Clásica el miércoles
    (4, 1, 2, 5),  -- María pide 1 Tacos al Pastor el jueves
    (5, 1, 2, 2),  -- María pide 1 Ensalada César el viernes
    (1, 2, 3, 5),  -- Carlos pide 2 Tacos al Pastor el lunes
    (2, 1, 3, 1),  -- Carlos pide 1 Pizza Margherita el martes
    (3, 1, 3, 2),  -- Carlos pide 1 Ensalada César el miércoles
    (4, 1, 3, 3),  -- Carlos pide 1 Sushi Variado el jueves
    (5, 2, 3, 4);  -- Carlos pide 2 Hamburguesa Clásica el viernes

-- Inserciones en la tabla menu
INSERT INTO menu (semana, usuario_id) VALUES
    (1, 1),  -- Semana 1 creado por el admin
    (2, 1),  -- Semana 2
    (3, 1);  -- Semana 3

-- Inserciones en la tabla menu_producto
INSERT INTO menu_producto (producto_id, menu_id) VALUES
    (1, 1),  -- Pizza Margherita en menú semana 1
    (2, 1),  -- Ensalada César en menú semana 1
    (3, 1),  -- Sushi Variado en menú semana 1
    (4, 2),  -- Hamburguesa Clásica en menú semana 2
    (5, 2),  -- Tacos al Pastor en menú semana 2
    (1, 3),  -- Pizza Margherita en menú semana 3
    (2, 3),  -- Ensalada César en menú semana 3
    (4, 3);  -- Hamburguesa Clásica en menú semana 3

-- Disponibilidad de items por día
INSERT INTO dia (id, producto_id) VALUES
    ('Lunes', 1),  -- Pizza Margherita disponible los lunes
    ('Jueves', 1),  -- Pizza Margherita disponible los jueves
    ('Viernes', 1),  -- Pizza Margherita disponible los viernes
    ('Martes', 2),  -- Ensalada César disponible los martes
    ('Miercoles', 2),  -- Ensalada César disponible los miércoles
    ('Viernes', 2),  -- Ensalada César disponible los viernes
    ('Lunes', 3),  -- Sushi Variado disponible los lunes
    ('Miercoles', 3),  -- Sushi Variado disponible los miércoles
    ('Jueves', 4),  -- Hamburguesa Clásica disponible los jueves
    ('Viernes', 5);  -- Tacos al Pastor disponible los viernes