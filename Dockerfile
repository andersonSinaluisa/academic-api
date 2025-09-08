# Etapa 1: Build con Maven
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Copia archivos de Maven y descarga dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el código fuente y compila
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera para producción
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia el JAR desde la imagen de build
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto del servicio (ajústalo si es otro)
EXPOSE 8080

# Ejecuta el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
