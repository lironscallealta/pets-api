# Recursos — Pets-API (Mascotas)

Solo enlaces de **este** microservicio. Cada API tiene su propio phpMyAdmin en otro puerto.

## phpMyAdmin — base de datos `pets`

### Levantar servicios

Desde la carpeta `pets-api`:

```bash
docker compose up db phpmyadmin -d
```

### Abrir (solo este enlace)

**[http://localhost:8190](http://localhost:8190)**

### Iniciar sesión

| Campo | Valor |
|-------|--------|
| Usuario | `user` |
| Contraseña | `password` |

### Comprobar que es la BD correcta

En el panel izquierdo de phpMyAdmin debe aparecer la base **`pets`**.  
Si ves `users` o `vets`, cerraste otro puerto por error (8191 o 8192).

---

## MySQL directo (DBeaver, Workbench)

| Parámetro | Valor |
|-----------|--------|
| Host | `localhost` |
| Puerto | `3390` |
| Base de datos | `pets` |
| Usuario | `user` |
| Contraseña | `password` |

---

## Otros microservicios (no uses estos enlaces aquí)

| Microservicio | phpMyAdmin | Base de datos |
|---------------|------------|---------------|
| Users | `http://localhost:8191` | `users` |
| Vets | `http://localhost:8192` | `vets` |

Documentación en `users-api/RECURSOS.md` y `vets-api/RECURSOS.md`.

---

## Swagger (solo con Spring en ejecución)

[http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html)
