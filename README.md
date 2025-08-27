# Fondos App

Este proyecto implementa un backend en **Spring Boot** con arquitectura **Hexagonal (Ports & Adapters)** y una base de datos **MongoDB**, orquestados con **Docker Compose**. El front esta desarrollado con Angular.

Se decidio trabajar con Java y Mongo para darle mayor facilidad al desarrollo, ya que no requeria de relaciones fuertes, adicional a esto, usamos la arquitectura hexagonal para dividir nuestra aplicacion de tal manera que el modelo de dominio sea independiente del framework usado,

---

## 🚀 Ejecución del proyecto

### 1. Requisitos previos
- [Docker](https://docs.docker.com/get-docker/)  
- [Docker Compose](https://docs.docker.com/compose/install/)  

### 2. Clonar el repositorio
```bash
git clone https://github.com/mkcastro85/fondos-app.git
cd fondos-app
```
### 3. Ejecutar
```bash
docker-compose up -d


