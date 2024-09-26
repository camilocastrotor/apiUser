# apiUser
api rest para guardar Usuario
 
---
## Tabla de contenido 
* [Descripción](#descrippción)
* [Desarrollo](#desarrollo)
* [Configuración local](#configuración-local) 
* [Pruebas unitarias](#pruebas-unitarias)

---
## Descripción
Guarda un usuario en base de datos en memoria H2
Valida el formato de correo electronico
Valida la existencia de un usuario con el correo Electronico

## Desarrollo
1. Instale Java 11 y un IDE de desarrollo como Eclipse o IntelliJ.
2. Clone este repositorio.
3. Permita la construcción del proyecto con gradle.
4. Inicialice el proyecto desde apiUsersApplication
5. Pruebe conumiendo el servicio con una herramienta para consumir apis como postman


## Configuración local
1. Se debe instalar java en el equipo  MAC (https://www.java.com/es/download/help/mac_install.html), Windows (https://www.java.com/es/download/ie_manual.jsp)
2. Se debe instalar un IDE para abrir el proyecto https://www.jetbrains.com/idea/download
3. Se debe abrir el proyecto con intellij
4. Se debe ingresar al archivo resources/application.yamel y ajustar las variables  de expresion regular de contraseña
5. Una vez podamos ver todos los archivos en intellij se debe dar click derecho en la clase apiUsersApplication y dar click en Run
---


-metodo Post
-Url:http://localhost:80/userapi/v1/user
##### REQUEST

```
{
    "name": "Juan Rodriguez",
    "email": "juan2@rodriguez.org",
    "password": "Camil19",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

##### RESPONSE 200
```
{
    "body": {
        "id": "a98c777e-c10a-4f01-9363-bb4d65a75e31",
        "name": "Juan Rodriguez",
        "email": "juan2@rodriguez.org",
        "password": "Camil19",
        "created": "2024-09-25T19:37:47.895246",
        "modified": "2024-09-25T19:37:47.895277",
        "lastLogin": "2024-09-25T19:37:47.895282",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImlhdCI6MTcyNzMxMTA2NywiZXhwIjoxNzI3MzE0NjY3fQ.44VPNL4TOpclFwOb5VXdKE88jRYw6IRj6BCIaT4tFSo",
        "phones": [
            {
                "id": 1,
                "number": "1234567",
                "cityCode": null,
                "countryCode": null
            }
        ],
        "active": true
    },
    "dataHeader": {
        "code": 200,
        "status": "OK",
        "errorList": [],
        "hasErrors": false,
        "currentPage": 0,
        "totalPage": 0,
        "totalRecords": 0
    }
}
```

##### RESPONSE ERROR
```
{
    "body": null,
    "dataHeader": {
        "code": 400,
        "status": "El correo ya está registrado",
        "errorList": [
            {
                "errorCode": "400",
                "errorDescription": "El correo ya está registrado"
            }
        ],
        "hasErrors": true,
        "currentPage": 0,
        "totalPage": 0,
        "totalRecords": 0
    }
}
```

## Pruebas unitarias
* JUnit
* Framework Mockito.


## Autor
- Camilo Castro

---

