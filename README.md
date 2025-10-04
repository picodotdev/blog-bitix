Repositorio de código fuente de Blog Bitix

## Uso del Taskfile

Este proyecto utiliza [Task](https://taskfile.dev/) para automatizar tareas comunes. El archivo `Taskfile.yml` contiene todas las tareas que anteriormente estaban en scripts shell.

### Instalación de Task

```bash
# Usando Go
go install github.com/go-task/task/v3/cmd/task@latest

# O usando el instalador oficial
sh -c "$(curl --location https://taskfile.dev/install.sh)" -- -d -b /usr/local/bin
```

### Tareas disponibles

Para ver todas las tareas disponibles:

```bash
task --list
```

### Tareas principales

- `task generate-styles` - Generar estilos CSS desde LESS y Hugo chromastyles
- `task build` - Compilar el sitio Hugo
- `task deploy` - Desplegar el sitio a GitHub Pages
- `task server` - Ejecutar el servidor de desarrollo Hugo
- `task update-build-deploy` - Actualizar, compilar y desplegar
- `task new` - Crear un nuevo post (ej: `task new -- posts/2025/mi-post.md`)

### Migración desde scripts shell

Los scripts shell originales (*.sh) han sido convertidos al Taskfile.yml. Cada script tiene su tarea correspondiente:

- `blog-generate-styles.sh` → `task generate-styles`
- `site-build.sh` → `task build`
- `site-deploy.sh` → `task deploy`
- `server.sh` → `task server`
- `new.sh` → `task new`
- `site-update-build-deploy.sh` → `task update-build-deploy`
- Y más...
