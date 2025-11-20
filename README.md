Cómo se ejecuta el pipeline (explicación breve)

El pipeline se activa con `push` y `pull_request` y usa `.github/workflows/ci-quality.yml`.

Pasos principales: checkout, Java 17, cache de Maven, build sin tests, linter (Checkstyle), pruebas (JUnit 5) y cobertura (JaCoCo report + check de umbral).

Si falla linter, pruebas o cobertura mínima (80%), el run se detiene y se marca en rojo en `Actions`. Cuando todo cumple, queda en verde.

Para probar un fallo de cobertura, cambio el umbral en `pom.xml` a `0.90` y corro `verify`; normalmente el paso `jacoco:check` no alcanza y falla.

Si quiero simular Actions en mi máquina, uso `act` (con Docker), por ejemplo `act push`. Si la imagen de `ubuntu-latest` no está disponible, me funcionó `act -P ubuntu-latest=ghcr.io/catthehacker/ubuntu:22.04`.



Diferencia CI vs CD

CI integra y valida automáticamente cambios con linter, build, pruebas y cobertura.

CD despliega automáticamente o con aprobación cuando CI pasa.

Lenguaje, linter y cobertura (justificación)

Lenguaje: Java 17 con Maven
  Estándar en proyectos académicos y empresariales, con soporte amplio.
  Maven facilita el ciclo `verify` y la integración de plugins de calidad.
Linter: Checkstyle
  Plugin maduro de Maven, configurable y capaz de fallar el build ante infracciones.
  Reglas conocidas que ayudan a mantener formato básico sin ser demasiado rígidas.
Cobertura: JaCoCo
  Integración directa con Maven (`prepare-agent`, `report`, `check`).
  Permite definir umbral mínimo y detener el build si no se alcanza.

