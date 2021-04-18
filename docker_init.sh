#!/usr/bin/env bash
set -x
set -eo pipefail

DB_USER=${POSTGRES_USER:=dev}
DB_PASSWORD="${POSTGRES_PASSWORD:=dev}"
DB_NAME="${POSTGRES_DB:=mymori}"
DB_PORT="${POSTGRES_PORT:=5432}"
DB_SCRIPT=$(<db_script.sql)

# Allow to skip Docker if a dockerized Postgres database is already running
if [[ -z "${SKIP_DOCKER}" ]]
then
  docker run \
      -e POSTGRES_USER=${DB_USER} \
      -e POSTGRES_PASSWORD=${DB_PASSWORD} \
      -e POSTGRES_DB=${DB_NAME} \
      -p "${DB_PORT}":5432 \
      -d postgres \
      postgres -N 1000
fi

export PGPASSWORD="${DB_PASSWORD}"
until psql -h "localhost" -U "${DB_USER}" -p "${DB_PORT}" -d "postgres" -c '\q'; do
  >&2 echo "Postgres is still unavailable - sleeping"
  sleep 1
done

>&2 echo "Creating tables..."

psql -h "localhost" -U "${DB_USER}" -p "${DB_PORT}" -d "mymori" -c "${DB_SCRIPT}"

>&2 echo "Tables created"

>&2 echo "Postgres is up and running on port ${DB_PORT}"
