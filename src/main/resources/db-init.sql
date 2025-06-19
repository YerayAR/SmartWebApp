-- Definición de la tabla de usuarios
CREATE TABLE IF NOT EXISTS user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Definición de la tabla de tareas
CREATE TABLE IF NOT EXISTS task (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    user_id INTEGER REFERENCES user(id)
);
