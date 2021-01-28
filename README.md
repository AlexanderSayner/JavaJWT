# JavaJWT
Simple api for jwt token demo

## Postgres set up
docker pull postgres
docker run --name postgres -e POSTGRES_PASSWORD=mysecretpassword -d -p 5432:5432 postgres
docker exec -it $(docker ps -q --filter name=postgres) bash
psql -U postgres
CREATE DATABASE star;
\c star

\l
select table_name, column_name from information_schema.columns where table_schema='public';
\dt
