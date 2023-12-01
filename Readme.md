# Spring Boot: Api creación de usuarios

Este proyecto es para gestionar usuarios, como primera parte dispone de dos endpoints de libre acceso,
uno es para registrar usuarios y el otro es para actualizar el token.
- /auth/registrar
- /auth/login

Luego tienes otros endpoints que si necesitas tener algun rol asignado
para las solicitudes GET, necesitas ingresar el token bearer y puedes consultarlo con un rol de ADMIN o USER
- GET---> /api/usuarios 
- GET--->/api/usuarios/{id}

Para el caso de las otras solicitudes HTTP, se necesita el rol ADMIN.
- DELETE---> /api/usuarios/{id}
- PUT ---> /api/usuarios


### Importante
- El proyecto se esta levantando con el puerto 80.
- La url de Swagger es la siguiente: http://localhost/swagger-ui/index.html
- Hay un usuario con el rol ADMIN creado por defecto, su usuario y contraseña es admin.
- Deje una coleccion postman llamada "UsuariosCollection.postman_collection.json" con todos los endpoints creados para que se puedan realizar las solicitudes.

##Tecnologias usadas:
  - Spring Boot 3
  - Spring Security 6
  - jsonwebtoken
  - Base de datos h2
  - Java 17
  - Gradle
  
## Ejecución de la aplicación

1. Clona el repositorio.
2. Configura tu entorno con java 17
3. Abre la aplicación con un IDE.
4. compila el proyecto con el comando build.
5. corre el proyecto.
6. realiza las pruebas.

##Ejecución de la aplicación utilizando el JAR

1. Clona el repositorio.
2. Configura tu entorno con java 17.
3. Luego ve a la siguiente ruta dentro del proyecto "usuarios/build/libs".
4. Ahí encontraras el jar de la aplicación.
5. Luego abre una terminal dentro de esa ruta.
6. En la terminal debes ingresar el siguiente comando "java -jar usuarios-0.0.1-SNAPSHOT.jar"
7. Luego realiza las pruebas.
