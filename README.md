
# Gestión de Franquicias

Este proyecto es una API para gestionar franquicias, sucursales y productos utilizando **Spring Boot**. Permite realizar operaciones CRUD sobre las franquicias, sus sucursales y los productos disponibles en cada una, además de funcionalidades específicas como consultar el producto con mayor stock por sucursal.

## Características Principales

- **Gestión de Franquicias**: Agregar, actualizar y listar franquicias.
- **Gestión de Sucursales**: Asignar sucursales a una franquicia, listar y administrar las sucursales existentes.
- **Gestión de Productos**: Agregar, actualizar stock, cambiar nombre del producto y eliminar productos en una sucursal.
- **Consultas Específicas**: Consultar el producto con mayor stock por sucursal para una franquicia específica.

## Estructura del Proyecto

El proyecto sigue una arquitectura hexagonal, separando las responsabilidades en capas claras:

- **Domain**: Contiene los modelos y servicios principales.
- **Application**: Incluye los DTOs y lógica específica para la API.
- **Adapter**: Contiene los controladores y mapeadores para interactuar con los clientes.
- **Infrastructure**: Contiene los adaptadores y la configuración de persistencia (repositorios JPA).

## Requisitos Previos

- **Java 17** o superior.
- **Gradle 8.4** o superior.
- Base de datos compatible con MySQL (o utiliza H2 en memoria para pruebas).

## Configuración de Base de Datos

El proyecto está configurado para usar **H2** en memoria durante el desarrollo y pruebas. Para usar MySQL, actualiza el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/franchises_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

## Ejecución del Proyecto

1. Clona el repositorio en tu máquina local.
2. Ejecuta el siguiente comando para iniciar el proyecto:

```bash
./gradlew bootRun
```

3. La API estará disponible en `http://localhost:8080`.

## Endpoints Principales

### Franquicias

- `POST /api/franchises`: Agregar una nueva franquicia.
- `GET /api/franchises`: Listar todas las franquicias.
- `PUT /api/franchises/{id}`: Actualizar el nombre de una franquicia.

### Sucursales

- `POST /api/franchises/{franchiseId}/branches`: Agregar una sucursal a una franquicia.
- `GET /api/franchises/{franchiseId}/branches`: Listar sucursales de una franquicia.
- `PUT /api/franchises/{franchiseId}/branches/{branchId}`: Actualizar nombre de una sucursal.

### Productos

- `POST /api/franchises/{franchiseId}/branches/{branchId}/products`: Agregar un producto a una sucursal.
- `PUT /api/franchises/{franchiseId}/branches/{branchId}/products/{productId}/name`: Actualizar nombre de un producto.
- `PUT /api/franchises/{franchiseId}/branches/{branchId}/products/{productId}/stock`: Actualizar stock de un producto.
- `DELETE /api/franchises/{franchiseId}/branches/{branchId}/products/{productId}`: Eliminar un producto.

### Consultas Específicas

- `GET /api/franchises/{franchiseId}/top-products`: Consultar el producto con mayor stock por sucursal para una franquicia.

## Pruebas

El proyecto incluye pruebas unitarias para todas las capas utilizando **JUnit** y **Mockito**. Para ejecutar las pruebas:

```bash
./gradlew test
```

## Contribuciones

Si deseas contribuir, por favor abre un `pull request` o reporta un problema en el repositorio.
