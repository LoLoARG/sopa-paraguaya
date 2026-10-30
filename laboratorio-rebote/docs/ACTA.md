# Acta de contrato: Laboratorio de rebote

Una fila por cada clase que un módulo le pide a otro. Lo que está acá es lo único que los demás pueden usar:
lo que no está en el acta es interno de cada módulo. Si hay que cambiar algo, se avisa en el grupo **antes** de tocarlo.

**Equipo (6):** completar nombres.

| Persona | Módulo | Paquete | Responsable de |
| --- | --- | --- | --- |
| _A1_ | Entidades (pelotas) | `juego.entidades` | `Material`, `Pelota` y subclases |
| _A2_ | Entidades (aro y lanzador) | `juego.entidades` | `Aro`, `Lanzador`, `FabricaPelotas`, `Acierto`, interfaz `Colisionable` |
| _B1_ | Estructuras (Lista) | `estructuras` | `ListaDoble` o `ListaArreglo` (la que elijan), y la segunda como extra |
| _B2_ | Estructuras (Pila y Cola) | `estructuras` | `PilaEnlazada`, `ColaEnlazada` |
| _C_ | Reglas | `juego.reglas` | `Mundo`, `Partida`, `Resultado` (el esqueleto ya trae versiones provisorias) |
| _D_ | Pantallas | `juego.pantallas` | `Menu`, `PantallaFin`, `Hud` (el esqueleto ya trae versiones provisorias) |

## Qué hace cada estructura en el juego

| Estructura | Función real | Quién la usa |
| --- | --- | --- |
| Lista | Las pelotas en vuelo y el aro del mundo (el mundo las recorre para actualizar, dibujar y chocar) | C |
| Cola | Cargador: pelotas por lanzar, en el orden en que salen | C |
| Pila | Historial de aciertos: el último acierto queda arriba (se muestra en el HUD) | C |

## Reglas de convivencia

- Cada uno toca solo su paquete. Para pedirle algo a otro módulo se agrega una fila en este acta.
- Sin `ArrayList`, `LinkedList`, `Stack`, `Queue`, `Map`, etc. de `java.util`, y sin arreglos `[]` fuera de las estructuras.
- Todos los atributos `private`. Constructores y setters validan y lanzan `IllegalArgumentException`.
- Se permite `java.util.Random` y `java.util.Iterator`. Las estructuras pueden implementar `Iterable` (es un extra: la interfaz `Lista` no lo exige).
- Compilar **de cero** (`compilar.bat` / `./compilar.sh`) antes de juntar con los demás.

---

## B1 y B2: Estructuras (`estructuras`)

Las interfaces `Lista<T>`, `Pila<T>` y `Cola<T>` vienen en el kit y no se tocan. Los demás programan contra ellas desde el minuto cero.

| Clase | Implementa | Constructor | Notas |
| --- | --- | --- | --- |
| `ListaDoble<T>` (o `ListaArreglo<T>`) | `Lista<T>`, `Iterable<T>` | `new ListaDoble<>()` | Índice fuera de rango: `IndexOutOfBoundsException` |
| `PilaEnlazada<T>` | `Pila<T>` | `new PilaEnlazada<>()` | `desapilar` y `tope` vacías: `IllegalStateException` |
| `ColaEnlazada<T>` | `Cola<T>` | `new ColaEnlazada<>()` | `desencolar` y `frente` vacías: `IllegalStateException` |

**Orden:** B1 entrega la Lista primero y rápido, porque el mundo depende de ella. Después se completa `verificador/PruebaMisEstructuras.java`
con los nombres reales y se corre con `ejecutar verificador.PruebaMisEstructuras` hasta que diga OK en las tres.

## A1: Pelotas (`juego.entidades`)

| Clase | Qué es | Contrato que usan los demás |
| --- | --- | --- |
| `Material` (enum) | Tipo de material | `getNombre()`, `getDensidad()`, `getElasticidad()` (0 a 1), `getColor()` |
| `Pelota` (**abstracta**) | Base de todas las pelotas. Implementa `Actualizable`, `Dibujable` y `Colisionable` | Ver abajo |
| `PelotaBasquet`, `PelotaGoma`, `PelotaPesada` | 3 subclases concretas que se comportan distinto (rebote, masa, puntos) | Se usan siempre como `Pelota` |

`Pelota`:

```java
protected Pelota(double x, double y, double radio, Material material)  // valida radio > 0 y material no nulo
public void lanzar(double vx, double vy)         // fija la velocidad inicial (píxeles por segundo)
public void actualizar(double dt)                // gravedad + movimiento
public void rebotarEnPiso(double yPiso)         // invierte vy usando la elasticidad
public void rebotarEnPared(double xPared, boolean esIzquierda)
public double getX(); public double getY(); public double getRadio();
public double getVx(); public double getVy();
public double getMasa();                         // densidad * volumen
public Material getMaterial();
public abstract int getPuntos();                 // puntos que da al acertar
public abstract String getNombre();              // "Básquet", "Goma", "Pesada"
public Rectangle2D getLimites();                 // de Colisionable
```

## A2: Aro, lanzador y fábrica (`juego.entidades`)

| Clase | Qué es | Contrato |
| --- | --- | --- |
| `Colisionable` (interfaz propia) | Algo que ocupa lugar | `Rectangle2D getLimites()` |
| `Aro` | Aro que se mueve de lado a lado. Implementa `Actualizable`, `Dibujable` y `Colisionable` | Ver abajo |
| `Lanzador` | Guarda ángulo y potencia, y calcula la velocidad inicial | Ver abajo |
| `FabricaPelotas` | Crea pelotas de distintos materiales para llenar el cargador | `Pelota crear(double x, double y)` (material al azar) |
| `Acierto` | Registro inmutable que se apila en el historial | `new Acierto(String nombrePelota, int puntos)`, `getNombrePelota()`, `getPuntos()` |

```java
// Aro
public Aro(double x, double y, double ancho, double velocidad, double xMin, double xMax)  // valida
public void actualizar(double dt)                  // se mueve y rebota entre xMin y xMax
public boolean contiene(Pelota p)                  // true si la pelota pasó por el aro hacia abajo
public double getX(); public double getY(); public double getAncho();

// Lanzador
public Lanzador(double x, double y)
public void setAngulo(double grados)               // valida 0..90
public void setPotencia(double potencia)           // valida 0..1
public double getAngulo(); public double getPotencia();
public double getVelocidadX(); public double getVelocidadY();   // para pasar a Pelota.lanzar
public double getX(); public double getY();
```

## C: Reglas (`juego.reglas`)

El esqueleto ya trae `Marcador`, `VistaHud`, `Resultado`, `Partida` y un `Mundo` provisorio. C reemplaza el interior de `Mundo` y `Partida`, **sin cambiar las firmas públicas**.

```java
public class Mundo implements Actualizable, Dibujable, Marcador {
    public static final int ANCHO = 900, ALTO = 520;
    public Mundo()                                  // llena el cargador (Cola) con FabricaPelotas
    public void lanzar()                            // saca la próxima pelota del cargador y la agrega a la Lista
    public void setAngulo(double grados)
    public void setPotencia(double potencia)
    public void actualizar(double dt)               // física, aro, aciertos (apila Acierto), fin del juego
    public void dibujar(Graphics2D g)
    public void terminar(String motivo)
    public Resultado getResultado()
    // + todos los métodos de Marcador
}
```

`Marcador` y `VistaHud` ya están en el esqueleto: D los usa y C los implementa. Controles sugeridos en `Partida`: flechas arriba/abajo = ángulo, izquierda/derecha = potencia, ESPACIO = lanzar, P = pausa, ESC = abandonar.

## D: Pantallas (`juego.pantallas`)

D recibe del mundo **solo** lo que dice `Marcador` y `Resultado`. Ya hay versiones provisorias de `Menu`, `PantallaFin` y `Hud`: D las mejora (barra de potencia, indicador de ángulo, cargador dibujado, efectos) sin cambiar los constructores.

| Clase | Constructor | Notas |
| --- | --- | --- |
| `Menu` | `Menu(Escena partida)` | ENTER va a la partida |
| `PantallaFin` | `PantallaFin(Partida partida)` y `setMenu(Escena)` | Lee `partida.getResultado()` en `alEntrar` |
| `Hud` | `Hud()` | Implementa `VistaHud` |
| `Main` (en `juego`) | | Ya conecta las tres escenas |

---

## Dependencias entre paquetes (sin ciclos)

```
juego            -> pantallas, reglas
juego.pantallas  -> reglas
juego.reglas     -> entidades, estructuras, motor, contratos, ui
juego.entidades  -> estructuras (si hace falta), contratos
estructuras      -> (nada)
```

Nadie en `entidades` o `estructuras` importa `reglas` ni `pantallas`.
