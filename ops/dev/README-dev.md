# Dev quickstart (viajes-back)
# Variables para poner en el .env
- POSTGRES_DB=plan2go
- POSTGRES_USER=postgres
- POSTGRES_PASSWORD=postgres
- HOST_PORT=5432    # cambia a 5433 si el 5432 está ocupado

## Requisitos
- Docker Desktop
- JDK 21 (usa `./mvnw` para compilar/ejecutar)

## 1) Levantar Postgres
```bash
cd ops/dev
cp .env.example .env
docker compose up -d

cd ../..          # raíz del repo (donde está pom.xml)
./mvnw spring-boot:run
```
## 2) Comandos utiles
```bash
# Estado y logs
cd ops/dev
docker compose ps
docker compose logs -f db

# Parar/arrancar
docker compose stop
docker compose start

# Apagar y quitar (sin borrar datos)
docker compose down

# Reset total (borra datos)
docker compose down -v

```