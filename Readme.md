# Taller 3 - POO: Spooky Season

## Descripción del proyecto

Este proyecto corresponde al Taller 3 de Programación Orientada a Objetos.

El programa implementa una simulación de minijuegos llamada **Spooky Season**, en la que los jugadores pueden transformarse en diferentes tipos de monstruos para participar en cuatro desafíos: Carrera del Cementerio, Caza de Calabazas, Lanzamiento de Lápidas y Sobrevivir al Amanecer.

Cada monstruo posee atributos generales y características particulares que determinan su potencial en los distintos minijuegos. Además, cuenta con dos habilidades especiales que pueden utilizarse para intentar obtener una bonificación en el puntaje.

El sistema permite registrar jugadores, consultar el catálogo de monstruos, participar en los minijuegos, revisar el historial de una sesión y visualizar un ranking basado en los récords históricos.

Los datos se cargan desde archivos de texto y los récords de los jugadores se guardan en `Registros.txt`, permitiendo mantener la información entre distintas ejecuciones del programa.

## Integrante

- **David Villalobos** - 21.646.173-8 - ICCI - GitHub: HitoriDakeno

## Estructura del proyecto

```text
Taller03/
├── src/
│   ├── logica/
│   │   ├── Main.java
│   │   ├── ISistema.java
│   │   └── SistemaImpl.java
│   │
│   ├── dominio/
│   │   ├── Monstruo.java
│   │   ├── Zombi.java
│   │   ├── Vampiro.java
│   │   ├── Momia.java
│   │   ├── HombreLobo.java
│   │   ├── Jugador.java
│   │   ├── RegistroPartida.java
│   │   ├── MiniJuego.java
│   │   ├── Mordedor.java
│   │   ├── Volador.java
│   │   ├── Regenerable.java
│   │   ├── Furioso.java
│   │   ├── CarreraCementerio.java
│   │   ├── CazaCalabazas.java
│   │   ├── LanzamientoLapidas.java
│   │   └── SobrevivirAmanecer.java
│   │
│   └── visitor/
│       └── VisitorMinijuego.java
│
├── Monstruos.txt
├── Registros.txt
├── README.md
└── DiagramaSpookySeason.png
```

## Paquetes principales

- `logica`: contiene las clases encargadas de administrar el funcionamiento general del programa y la interacción con el usuario.
- `dominio`: contiene las clases que representan los monstruos, jugadores, registros, minijuegos y habilidades.
- `visitor`: contiene la interfaz utilizada para implementar el patrón Visitor.

## Clases principales

### Main.java

Es la clase principal del programa. Contiene el método `main`, desde el cual se cargan los archivos, se inicia el sistema y se controla la interacción con el usuario mediante los distintos menús.

Sus responsabilidades incluyen:

- Leer los archivos `Monstruos.txt` y `Registros.txt`.
- Separar los datos de cada línea y enviarlos al sistema.
- Mostrar el menú principal y los menús de los minijuegos.
- Permitir seleccionar monstruos y habilidades.
- Mostrar el catálogo, historial y ranking.
- Guardar los registros actualizados al terminar una sesión.

### ISistema.java

Define las operaciones que debe proporcionar el sistema, permitiendo separar las funcionalidades de la lógica de su implementación.

### SistemaImpl.java

Es la clase encargada de implementar la lógica del juego. Administra los monstruos, jugadores, minijuegos y resultados de las sesiones.

Entre sus responsabilidades se encuentran:

- Crear monstruos y jugadores a partir de los datos recibidos.
- Validar los atributos y sus rangos permitidos.
- Controlar los monstruos disponibles y los minijuegos realizados.
- Calcular los potenciales y puntajes obtenidos.
- Aplicar las bonificaciones por habilidades.
- Mantener el historial de cada sesión.
- Ordenar el ranking de jugadores.
- Actualizar los récords históricos.

### Monstruo.java

Es una clase abstracta que representa los atributos generales compartidos por todos los monstruos.

Contiene las características de vida, velocidad, fuerza y resistencia. Además, declara las operaciones abstractas que deben implementar sus subclases.

### Zombi.java, Vampiro.java, Momia.java y HombreLobo.java

Representan los cuatro tipos de monstruos disponibles en el sistema.

Cada clase hereda los atributos generales de `Monstruo`, incorpora sus propias características y define las habilidades que puede utilizar.

También implementa el método `aceptar()` necesario para trabajar con el patrón Visitor.

### MiniJuego.java

Define las operaciones generales que debe implementar cada minijuego, como su nombre, descripción, habilidad premiada y cálculo del potencial de un monstruo.

### CarreraCementerio.java, CazaCalabazas.java, LanzamientoLapidas.java y SobrevivirAmanecer.java

Representan los cuatro desafíos disponibles.

Cada clase implementa `MiniJuego` y `VisitorMinijuego`, incorporando las fórmulas correspondientes para calcular el potencial de cada tipo de monstruo.

### VisitorMinijuego.java

Es la interfaz que permite implementar el patrón de diseño Visitor.

Declara un método `visitar()` para cada tipo de monstruo, permitiendo que cada minijuego realice sus cálculos específicos sin necesidad de preguntar por el tipo de monstruo mediante condiciones.

### Jugador.java

Representa a un jugador registrado en el sistema. Almacena su nombre y su puntaje máximo histórico.

### RegistroPartida.java

Representa el resultado de un minijuego realizado durante una sesión. Almacena el nombre del desafío, el tipo de monstruo utilizado, la habilidad seleccionada y el puntaje obtenido.

## Requisitos

- Java JDK instalado.
- Eclipse IDE o un entorno compatible con proyectos Java.
- Git instalado para clonar el repositorio.
- Los archivos `Monstruos.txt` y `Registros.txt` disponibles en la ubicación desde la cual el programa los busca.

## Archivos de entrada

### Monstruos.txt

Contiene los monstruos disponibles para participar en los minijuegos.

Cada línea representa un monstruo diferente y sus atributos están separados por punto y coma (`;`).

Ejemplo:

```text
Zombi;100;20;70;60;90
Vampiro;90;80;55;30;70;20
Momia;120;30;45;85;180
HombreLobo;110;60;90;50;75;4
```

El programa valida los datos y descarta las líneas que contienen tipos desconocidos, cantidades incorrectas de campos, valores no numéricos o atributos fuera de los rangos permitidos.

### Registros.txt

Almacena los jugadores registrados y sus respectivos récords históricos.

Formato:

```text
Jugador;PuntajeMaximo
```

Ejemplo:

```text
Koshao;7823
Clapt;7600
Churrasquita;6767
```

Este archivo se carga al iniciar el programa y se actualiza al finalizar cada sesión.

## Instrucciones de ejecución

### 1. Clonar el repositorio

Abrir una terminal y ejecutar el siguiente comando, reemplazando la dirección por la URL real del repositorio:

```bash
git clone https://github.com/HitoriDakeno/Taller03.git
```

Luego, ingresar a la carpeta del proyecto.

### 2. Importar el proyecto en Eclipse

1. Abrir Eclipse.
2. Seleccionar **File > Import**.
3. Elegir **General > Existing Projects into Workspace**.
4. Seleccionar la carpeta del proyecto clonado.
5. Finalizar la importación.

### 3. Verificar los archivos de entrada

Comprobar que los archivos `Monstruos.txt` y `Registros.txt` se encuentren en la ubicación desde la cual el programa los busca.

Si `Registros.txt` no existe, el programa puede comenzar con una lista vacía y crear el archivo al guardar los resultados.

Si `Monstruos.txt` no existe o no contiene monstruos válidos, el programa informa la situación y termina de manera controlada.

### 4. Ejecutar el programa

1. Abrir `src/logica/Main.java`.
2. Hacer clic derecho sobre el archivo.
3. Seleccionar **Run As > Java Application**.
4. Utilizar las opciones disponibles en la consola.

## Funcionamiento del programa

El sistema permite realizar las siguientes operaciones:

1. **Jugar un minijuego:** permite seleccionar uno de los cuatro desafíos disponibles, elegir un monstruo y utilizar una de sus habilidades.
2. **Ver catálogo de monstruos:** muestra todos los monstruos cargados, sus atributos, habilidades y disponibilidad durante la sesión.
3. **Ver historial de la sesión:** permite consultar los resultados de los minijuegos realizados y el puntaje acumulado.
4. **Ver ranking de jugadores:** muestra los jugadores ordenados de mayor a menor récord histórico, utilizando el nombre como criterio de desempate alfabético.
5. **Terminar sesión:** finaliza la partida, actualiza el récord cuando corresponde y guarda los registros.
6. **Salir:** finaliza la sesión actual, guarda los registros y cierra el programa.

Cada monstruo puede utilizarse una sola vez por sesión y cada minijuego solamente puede realizarse una vez.

Las habilidades especiales permiten obtener un multiplicador de 1.5 cuando coinciden con la habilidad premiada por el minijuego. En caso contrario, el multiplicador es 1.0.

El puntaje se calcula mediante la siguiente expresión:

```text
puntaje = Math.round(potencial * 10 * multiplicador)
```

## Patrones y conceptos de POO utilizados

### Herencia

Se utiliza una clase abstracta `Monstruo`, de la cual heredan `Zombi`, `Vampiro`, `Momia` y `HombreLobo`.

Esto permite compartir los atributos generales y mantener las características particulares de cada tipo de monstruo.

### Interfaces

Se utilizan interfaces para representar las habilidades especiales de los monstruos: `Mordedor`, `Volador`, `Regenerable` y `Furioso`.

También se utiliza la interfaz `MiniJuego` para definir las operaciones comunes de los desafíos.

### Polimorfismo

Se aprovecha el comportamiento polimórfico de los monstruos y minijuegos para realizar los cálculos correspondientes sin utilizar condiciones que comprueben el tipo de monstruo.

### Patrón Visitor

Se implementa Visitor para separar las fórmulas de potencial de las clases de monstruos.

Cada minijuego actúa como visitante y contiene los cálculos específicos para los cuatro tipos de monstruos.

### Colecciones

Se utilizan las siguientes estructuras:

- `ArrayList<Monstruo>` para almacenar los monstruos.
- `ArrayList<Jugador>` para almacenar los jugadores.
- `LinkedList<RegistroPartida>` para mantener el historial de la sesión.

## Testeo

Para comprobar el funcionamiento del programa, se recomienda probar las siguientes situaciones:

- Carga de monstruos desde `Monstruos.txt`.
- Carga de jugadores y récords desde `Registros.txt`.
- Archivos inexistentes o líneas con datos incorrectos.
- Monstruos con atributos fuera de los rangos permitidos.
- Registro de varios monstruos del mismo tipo.
- Inicio de sesión con un jugador existente y uno nuevo.
- Ingreso de nombres vacíos o que contengan `;`.
- Realización de los cuatro minijuegos.
- Comprobación de las fórmulas de potencial para cada monstruo.
- Uso de habilidades efectivas y no efectivas.
- Selección de la opción de no utilizar habilidades.
- Intentar repetir un minijuego durante la misma sesión.
- Intentar utilizar nuevamente un monstruo que ya fue seleccionado.
- Consultar el catálogo y verificar la disponibilidad de los monstruos.
- Consultar el historial y comprobar el puntaje acumulado.
- Comprobar el ordenamiento del ranking y los desempates alfabéticos.
- Terminar una sesión sin haber jugado ningún minijuego.
- Finalizar una sesión con un récord nuevo y otra sin superar el récord anterior.
- Verificar que los cambios se guarden correctamente en `Registros.txt`.
- Ingresar letras, opciones fuera de rango y entradas vacías en los menús.
- Comprobar que todas las opciones para volver y salir funcionen correctamente.

Estas pruebas permiten verificar las funcionalidades principales, el control de errores y el comportamiento general del sistema.

## Diagrama de clases

El repositorio incluye el diagrama de clases UML del proyecto en formato de imagen.

En él se representan las clases, interfaces, atributos, métodos y relaciones de herencia, implementación y asociación utilizadas en el desarrollo de Spooky Season.