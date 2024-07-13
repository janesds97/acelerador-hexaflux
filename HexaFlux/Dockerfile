# Usar una imagen de base de Java
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo en /app
WORKDIR /app

# Copiar el archivo JAR de la aplicación en el contenedor
COPY target/HexaFlux-0.0.1-SNAPSHOT.jar /app/acelerador.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8760

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/acelerador.jar"]
