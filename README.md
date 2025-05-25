# La Oficina Cartelera

Aplicación web para La Oficina Cartelera, una empresa que ofrece soluciones visuales integrales.

## Características

- Diseño responsive con Tailwind CSS
- Navegación intuitiva con menús desplegables mejorados
- Banner dinámico con transiciones suaves
- Categorización clara de servicios

## Despliegue en Render

Esta aplicación está configurada para ser desplegada fácilmente en [Render](https://render.com/).

### Pasos para desplegar

1. Crea una cuenta en Render si aún no tienes una
2. En el dashboard de Render, selecciona "New" y luego "Web Service"
3. Conecta con tu repositorio de GitHub
4. Selecciona el repositorio `oficinacartelera`
5. Render detectará automáticamente la configuración en `render.yaml` y `Dockerfile`
6. Haz clic en "Create Web Service"

Render construirá y desplegará automáticamente la aplicación. Una vez completado, podrás acceder a ella a través de la URL proporcionada por Render.

## Desarrollo local

### Requisitos previos
- Java 17 o superior
- Maven 3.6 o superior

### Ejecutar localmente
```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## Docker

Para ejecutar la aplicación en Docker:

```bash
# Construir la imagen
docker build -t oficinacartelera .

# Ejecutar el contenedor
docker run -p 8080:8080 oficinacartelera
```
