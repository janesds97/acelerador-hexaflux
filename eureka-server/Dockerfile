# Usar una imagen de base de Java
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo en /app
WORKDIR /app

# Copiar el archivo JAR del servidor Eureka en el contenedor
COPY target/eureka-server-0.0.1-SNAPSHOT.jar /app/eureka-server.jar

# Exponer el puerto en el que se ejecutará el servidor Eureka
EXPOSE 8761

# Comando para ejecutar el servidor Eureka
ENTRYPOINT ["java", "-jar", "/app/eureka-server.jar"]
