# Prueba Nissum

Este programa se encarga de administrar la creación de usuarios junto con sus respectivos números de teléfono. Ofrece una interfaz para registrar nuevos usuarios en el sistema, proporcionando sus datos básicos, como nombre, dirección de correo electrónico y contraseña, así como también permite asociar uno o varios números de teléfono a cada usuario creado para posteriormente poder consultarlo.

## Implementación

Para ejecutar este proyecto, ejecuta el método principal de la clase main.

## Unit tests

Para ejecutar las pruebas unitarias, clic derecho en el paquete de pruebas y selecciona la opción "Run tests".

## Api Reference

#### Obtener todos los usuarios

```http
GET /users
```

#### Crear usuario

```http
POST /users
```

| Parámetro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `name`    | `string` | **Requerido**. Nombre del usuario |
| `email`   | `string` | **Requerido**. Correo electrónico del usuario |
| `password`| `string` | **Requerido**. Contraseña del usuario |
| `phones`  | `array`  | **Requerido**. Teléfonos del usuario |

### Password criteria

- Debe tener al menos 8 caracteres.
- Debe componerse exclusivamente de letras mayúsculas y minúsculas, dígitos numéricos y los caracteres especiales mencionados (!@#$%.^&*()\-+=). 

### JSON Excample

```json
{
  "email": "jlopez1029@hotmail.com",
  "name": "Jorge Lopez",
  "password": "Waddiwas1*.0",
  "phones": [
    {
      "city_code": 1,
      "country_code": 1,
      "number": "3045965387"
    }
  ]
}
```

### Ejemplo de Respuesta JSON

```json
{
  "id": "480c0b7d-8af9-46ff-8225-5c11028285db",
  "token": "630d4906-5f5d-44b2-98e8-2f06a6c0ca36",
  "timeLastLogin": "2024-05-03T08:29:52.6128795",
  "is_active": true,
  "created": "2024-05-03T08:29:52.6128795",
  "modified": "2023-05-03T08:29:52.6128795"
}
```

## Pila Tecnológica

**Cliente:** Swagger

**Servidor:** Java, JPA, Gradle, JUnit 5

Para acceder a Swagger, utiliza: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html) y 
para acceder a la base de datos, utiliza: [http://localhost:8081/h2-console/login.jsp](http://localhost:8081/h2-console/login.jsp) y utiliza la palabra **password** como contraseña. 

En el paquete de recursos se encuentra el diagrama de clases.

classDiagram

    class UserEntity {
        + id: UUID
        + isActive: Boolean
        + name: String
        + email: String (unique)
        + password: String
        + token: String (CHAR(36))
        + timeCreated
        + timeModified
        + timeLastLogin
    }
    
    class PhoneEntity {
        + id: UUID
        + number: String
        + cityCode: Integer
        + countryCode: Integer
        + isActive: Boolean
        + user: UserEntity
    }
	
![Diagrama de clases](/src/main/resources/PruebaNisum.png)


## Autor

- Jorge Lopez
