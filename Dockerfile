# Usar una imagen base de JDK para construir la aplicación
FROM openjdk:17-jdk-slim as builder

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos necesarios para construir la aplicación
COPY . .

# Construir el JAR usando Gradle
RUN ./gradlew bootJar

# Usar una imagen más ligera para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer el puerto que utiliza la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]