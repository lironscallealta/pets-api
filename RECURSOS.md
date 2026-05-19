# Recursos — Pets-API (Mascotas)

Guía para **ver y administrar la base de datos** con phpMyAdmin. Los puertos salen de tu `.env`.

## Ver la base de datos (phpMyAdmin)

### 1. Levantar MySQL y phpMyAdmin

Desde la carpeta `pets-api`:

```bash
docker compose up db phpmyadmin -d
```

Espera ~30 s a que `db` esté **healthy** en Docker Desktop.

### 2. Abrir en el navegador

| Recurso | Enlace |
|--------|--------|
| **phpMyAdmin (mascotas)** | [http://localhost:8190](http://localhost:8190) |

### 3. Iniciar sesión en phpMyAdmin

| Campo | Valor |
|-------|--------|
| Servidor | `db` (si no funciona, prueba dejar el que venga por defecto) |
| Usuario | `user` |
| Contraseña | `password` |
| Base de datos | `pets` |

Usuario root (solo si lo necesitas): `root` / `root_password`

---

## Conexión directa a MySQL (DBeaver, Workbench, etc.)

| Parámetro | Valor |
|-----------|--------|
| Host | `localhost` |
| Puerto | `3390` |
| Base de datos | `pets` |
| Usuario | `user` |
| Contraseña | `password` |

> MySQL **no** se abre en el navegador como una web normal; usa phpMyAdmin o un cliente SQL.

---

## Las 3 bases del proyecto (phpMyAdmin)

Cada microservicio tiene su propio Docker. Levanta `db` + `phpmyadmin` en cada carpeta antes de abrir el enlace.

| Microservicio | phpMyAdmin | Puerto MySQL | Base de datos |
|---------------|------------|--------------|---------------|
| **Pets** | [http://localhost:8190](http://localhost:8190) | `3390` | `pets` |
| **Users** | [http://localhost:8191](http://localhost:8191) | `3391` | `users` |
| **Vets** | [http://localhost:8192](http://localhost:8192) | `3392` | `vets` |

Credenciales iguales en los tres: usuario `user`, contraseña `password`.

---

## API y Swagger (solo si Spring está corriendo)

Estos enlaces **no** funcionan solo con Docker `db`. Necesitas ejecutar la aplicación (IDE o `mvn spring-boot:run`):

| Recurso | Enlace |
|--------|--------|
| Swagger | [http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html) |

---

## Si phpMyAdmin no carga

1. Docker Desktop abierto.
2. Contenedores `pets-api-db-1` y `pets-api-phpmyadmin-1` en ejecución.
3. Comando: `docker compose up db phpmyadmin -d` dentro de `pets-api`.
4. Probar [http://localhost:8190](http://localhost:8190) (no uses el puerto `3390` en el navegador).

## Repositorio

[github.com/lironscallealta/api-mascotas](https://github.com/lironscallealta/api-mascotas)
