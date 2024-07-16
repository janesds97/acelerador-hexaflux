# acelerador-hexaflux

Este proyecto es un acelerador de software para facilitar la creación y despliegue de microservicios reactivos con buenas prácticas de desarrollo de software usando WebFlux y Spring Cloud Netflix Eureka.

## Tecnologías Utilizadas
- Maven
- Spring Boot
- Spring WebFlux
- MongoDB
- Eureka
- JUnit
- Mockito
- WebTestClient


## Arquitectura
Este proyecto sigue la arquitectura hexagonal para separar las preocupaciones y mejorar la mantenibilidad. La arquitectura se divide en tres capas principales: dominio, aplicación e infraestructura.

Este repositorio contiene dos proyectos: `eureka-server` y `HexaFlux`. Ambos son parte del acelerador HexaFlux.

## Estructura del Proyecto

- **eureka-server**: Proyecto del servidor Eureka para registrar microservicios.
  - `src/`: Código fuente.
  - `target/`: Archivos generados después de la compilación.
  - `dockerfile`: Dockerfile para construir la imagen Docker.
  - `pom.xml`: Archivo de configuración de Maven.

- **HexaFlux**: Proyecto principal.
  - `src/`: Código fuente.
  - `target/`: Archivos generados después de la compilación.
  - `dockerfile`: Dockerfile para construir la imagen Docker.
  - `pom.xml`: Archivo de configuración de Maven.

- `docker-compose.yml`: Archivo de configuración para Docker Compose.

- `zAcelerador-HexaFlux.postman_collection.json`: Colección de Postman para testear los Endpoints.

## Cómo comenzar

1. Clona el repositorio:
   ```sh
   git clone https://github.com/tu-usuario/Acelerador-HexaFlux.git

2. Dirígete a la carpeta eureka-server para empaquetar el proyecto con Maven y crear la imagen de Docker
    ```sh
   cd acelerador-hexaflux\eureka-server
   mvn clean package
   docker build -t eureka-server:latest .

3. Dirígete a la carpeta \HexaFlux para empaquetar el proyecto con Maven y crear la imagen de Docker
    ```sh
   cd ..\HexaFlux
   mvn clean package
   docker build -t acelerador:latest .

4. Dirígete a la carpeta raíz del repositorio para levantar los servicios
    ```sh
   cd ..
   docker-compose up --build

5. En Docker Desktop se creará el contenedor con las imágenes generadas a través de los Dockerfiles
   
    [![Containers-Docker.png](https://i.postimg.cc/Qt5wgvFY/Containers-Docker.png)](https://postimg.cc/yg1LsQX0)

6. Desde http://localhost:8761/ se podrá ver el servidor de Eureka activo
   
    [![Eureka-Server.png](https://i.postimg.cc/cChDXW0h/Eureka-Server.png)](https://postimg.cc/H8J4kfJ8)
   
8. Usando la colección `zAcelerador-HexaFlux.postman_collection.json` se podrán consumir los endpoints desde Postman

     [![End-Point-Postman.png](https://i.postimg.cc/XYRgKq93/End-Point-Postman.png)](https://postimg.cc/NKxTBgNP)
