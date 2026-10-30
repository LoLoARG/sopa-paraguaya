# Laboratorio de rebote

Game Jam Java, juego 1. Lanzás pelotas de distintos materiales (densidad y elasticidad) para embocarlas en un aro que se mueve.
Requiere JDK 11 o superior.

## Compilar y correr

| Sistema | Compilar | Jugar | Verificar estructuras |
| --- | --- | --- | --- |
| Windows | `compilar.bat` | `ejecutar.bat juego.Main` | `ejecutar.bat verificador.PruebaMisEstructuras` |
| Linux / macOS | `./compilar.sh` | `./ejecutar.sh juego.Main` | `./ejecutar.sh verificador.PruebaMisEstructuras` |

En VS Code: abrir `src/juego/Main.java` y tocar **Run**.

## Estado

Esqueleto navegable: Menú → Partida → Pantalla de fin, con las tres escenas conectadas.
En la partida todavía no hay juego (ESC termina la partida): cada módulo completa lo suyo según `docs/ACTA.md`.

## Cómo trabajar en equipo

1. Leer `docs/ACTA.md`: define qué clase hace cada módulo y con qué firmas. Es el contrato.
2. Cada uno trabaja en **su paquete** y en **su rama** (`git checkout -b a1-pelotas`).
3. Juntar cada 45-60 minutos: `git pull`, compilar de cero, y recién ahí hacer merge.
4. Lo provisorio (`Mundo`, `Partida`, `Hud`, `Menu`, `PantallaFin`) se reemplaza por dentro, sin cambiar las firmas públicas.
