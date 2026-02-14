# Introducción

## Instalar react-router y generar el primer proyecto

```
npx create-react-router@latest ejem0 --template remix-run/react-router-templates/minimal
```

Responder a todas las preguntas que si. Y ejecutar los siguientes comandos:

```
cd ejem0
npm iinstall
npm run dev
```

Si se obtiene el error de `ENOSPC: System limit for number of file watchers reached, watch '/home/mica/git/react/ejem0'` se debe ejecutar el comando

```
sudo sysctl fs.inotify.max_user_watches=524288 && sudo sysctl -p
```

Abrir la URL http://localhost:5173/

Instalar las React developer tools.

## Estructura

Vamos a cambiar el contenido del fichero `routes/home.tsx` por:

```
export default function Home() {
  return (
    <h1>
      Curso React
    </h1>
  );
}
```

Vamos a dejar el contenido del fichero `app.css` vacío.

# Mostrar HTML partiendo de datos fijos

```
export default function Home() {
  
  const name = "Anybody";
  
  return (
    <h1>
      Hello {name}!
    </h1>
  );
}
```

# Diferencias HTML y TSX

| Atributo HTML      | Atributo JSX (React)     | Razón                                   |
|--------------------|--------------------------|-----------------------------------------|
| class              | className                | class es palabra reservada en JS.       |
| for                | htmlFor                  | for es palabra reservada (bucles).      |
| style="color: red" | style={{ color: 'red' }} | Los estilos en React deben ser objetos. |
| onclick            | onClick                  | React usa camelCase para los eventos.   |
| <br>               | <br/>                    | Tiene que ser bien formado              |

# Actualizar datos ante eventos

```
import { useState } from "react";

export default function Home() {

  const [name, setName] = useState("Anybody");

  return (
    <>
      <h1>
        Hello {name}!
      </h1>

      <button onClick={() => setName("John")}>
        Hello John
      </button >
    </>  
  );
}
```

También se puede definir la función dentro de la función

```
import { useState } from "react";

export default function Home() {

  const [name, setName] = useState("Anybody");

  function handleClick() {
    setName("John");
  }

  return (
    <>
      <h1>
        Hello {name}!
      </h1>

      <button onClick={handleClick}>
        Hello John
      </button >
    </>  
  );
}
```

# Editar datos con un formulario

```
import { useState } from "react";

export default function Home() {

  const [name, setName] = useState("Anybody");

  function handleClick() {
    setName("John");
  }

  function onChange(e: React.ChangeEvent<HTMLInputElement>) {
    setName(e.target.value);
  }

  return (
    <>
      <input type="text" value={name} onChange={onChange}/>

      <h1>
        Hello {name}!
      </h1>

      <button onClick={handleClick}>
        Hello John
      </button >
    </>  
  );
}
```

# Templates

# REST API

Se ha cambiado la API REST de Google Books por otra gratuita (porque Google ha limitado el acceso)

Se ha configurado el proxy en la configuración de vite.

Se ha actualizado la API REST para empezar por "api" (Hay que actualizar la colección postman)

Hay que poner los comandos para arrancar las aplicaciones:

* Node:
   * `npm install`
   * `npm start`
* Java:
   * `mvn spring-boot:run`

# Servicios

Se usa la librería Zustand

Hay que indicar que existen los selectores, pero no los vamos a aplicar.

Hay que contar que se puede usar tanstack react query, pero no lo vamos a usar.

También indicar que se podría guardar en el local storage de forma automática (preferencias de navegación, claro/oscuro...)

# Multipágina

Hay que actualizar el postman para incluir /api

# SSR: FAlse

Se configura ssr:false en react-router.config.ts

Se añade a root.tsx

export function HydrateFallback() {
  return <p>Loading...</p>;
}

Pendiente: Gestionar mejor los errores. Ahora si hay error de red

# Bootstrap

https://react-bootstrap.netlify.app/

```
npm install react-bootstrap bootstrap
```

# Material

``` 
npm install @mui/material @emotion/react @emotion/styled
```

```
npm install @fontsource/roboto
```

Add
```
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
```

```
npm install @mui/icons-material
```

# Build

https://reactrouter.com/how-to/spa

After running `react-router build`, deploy the build/client directory to whatever static host you prefer.

```
./build_and_publish.sh
```

## Build en folder

Cambio en vite y en react-reouter config y en los routers de Spring y Express.

Cambios en los backends


