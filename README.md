# OrderScheduler: Sistema de Gestión de Pedidos de Almuerzo [Frontend y Backend]

Este proyecto es una aplicación web que incluye un frontend en HTML, CSS y JavaScript, y un backend desarrollado en Spring. Además, el proyecto está configurado para utilizar una base de datos que se inicializa mediante un archivo `.sql`.

## Contenidos

- [OrderScheduler: Sistema de Gestión de Pedidos de Almuerzo \[Frontend y Backend\]](#orderscheduler-sistema-de-gestión-de-pedidos-de-almuerzo-frontend-y-backend)
  - [Contenidos](#contenidos)
  - [Requisitos](#requisitos)
  - [Instalación](#instalación)
  - [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
  - [Ejecución del Proyecto](#ejecución-del-proyecto)
  - [Uso](#uso)
  - [Implementaciones a futuro](#implementaciones-a-futuro)

## Requisitos

Asegúrate de tener instalados los siguientes programas:

- **Java 17**
- **Maven** para gestionar las dependencias y construir el proyecto de Spring
- **MySQL** (o cualquier otra base de datos que prefieras)
- **IntelliJ IDEA** o cualquier IDE compatible con Java (opcional)
- **Navegador web** para visualizar el frontend

## Instalación

1. **Clona el repositorio del proyecto**:

    ```bash
    git clone https://github.com/savillanuevaGH/Proyecto-PP1
    ```

2. **Configura el backend**:

   - Navega hasta la carpeta del backend (`backend`).
   - Asegúrate de tener todas las dependencias descargadas ejecutando:
     
     ```bash
     mvn clean install
     ```

3. **Configura el frontend**:

   - Dirígete a la carpeta `frontend` donde encontrarás el código HTML, CSS y JavaScript.
   - No se requiere una configuración adicional para el frontend, puedes abrir los archivos HTML en tu navegador.

## Configuración de la Base de Datos

1. **Crea la base de datos**:
   - Inicia sesión en MySQL (u otra base de datos de tu elección) y crea una base de datos para el proyecto. Por ejemplo:

     ```sql
     CREATE DATABASE order_scheduler;
     ```

2. **Ejecuta el archivo SQL**:
   - Dentro de la carpeta del proyecto, encontrarás un archivo `database.sql` que contiene la estructura y datos iniciales de la base de datos, eso creeara las tablas y cargara los datos dentro de cada una.

3. **Configura la conexión de la base de datos en Spring**:
   - En el archivo `application.properties` o `application.yml` del proyecto Spring, actualiza los datos de conexión:

     ```properties
        spring.application.name=orderSheduler
        spring.datasource.url=jdbc:mysql://localhost:3306/order_scheduler?useSSL=false&serverTimezone=UTC
        spring.datasource.username=root
        spring.datasource.password=

        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true
        spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

        logging.level.org.springframework=DEBUG
        logging.level.com.orderScheduler=DEBUG

        spring.mvc.static-path-pattern=/static/**
     ```

## Ejecución del Proyecto

1. **Ejecuta el backend**:
   - Desde la carpeta del backend, inicia el proyecto de Spring usando Maven o desde tu IDE:

     ```bash
     mvn spring-boot:run
     ```

   - El backend se iniciará en `http://localhost:8080`.

2. **Ejecuta el frontend**:
   - Abre el archivo principal `index.html` ubicado en la carpeta `frontend` directamente en tu navegador. También puedes servirlo usando un servidor local como [Live Server en VS Code](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer).

## Uso

Una vez que el backend y el frontend estén en ejecución:

- Accede a la aplicación desde `http://localhost:8080` o donde esté configurado el frontend.
- La aplicación se comunicará con el backend a través de peticiones HTTP (por ejemplo, usando `fetch` en el JavaScript del frontend).

## Implementaciones a futuro
* Cambiar el producto de un dia por otro (UPDATE pedido)
* Editar perfil de Usuario
* Cambiar atributos de un Producto
* Dar de baja a un usuario
