# Etapa 1: Construcción con Maven y OpenJDK
FROM openjdk:17-jdk-alpine AS Maven-builder

# Instalar dependencias necesarias para Alpine
RUN apk add --no-cache bash curl git unzip libc6-compat

# Configurar el directorio de trabajo en el contenedor
WORKDIR /app/customer_data

# Copiar archivos de Maven y configuración desde la raíz del proyecto
COPY .mvn ./.mvn
COPY mvnw .
COPY pom.xml .

# Dar permisos de ejecución al Maven Wrapper
RUN chmod +x mvnw

# Configurar variable de entorno para os-maven-plugin
ENV MAVEN_OPTS="-Dos.detected.classifier=linux-x86_64"

# Descargar dependencias de Maven para uso offline
RUN ./mvnw dependency:go-offline

# Copiar el código fuente del proyecto
COPY BDA-Customer-Data-Layer/src ./src

# Construir el proyecto con Maven
RUN ./mvnw clean package -Pcontainer -DskipTests

# Limpia el cache de Maven
RUN rm -rf /root/.m2/repository

# Etapa 2: Imagen final para ejecución
FROM openjdk:17-jdk-alpine

# Configurar el directorio de trabajo para la imagen de ejecución
WORKDIR /app

# Copiar el artefacto generado en la etapa anterior
COPY --from=Maven-builder /app/customer_data/target/customer-data-layer.jar .

# Crear directorio de logs
RUN mkdir logs

# Exponer el puerto del servicio
EXPOSE 5090

# Comando de inicio
ENTRYPOINT ["java", "-jar", "./customer-data-layer.jar"]
