
Identificar fallos en logs

- Linter (Checkstyle)
  - En la consola de Maven/NetBeans, el paso `checkstyle:check` muestra archivo y línea junto con la regla. Ejemplo: `FinalParameters` o `UnusedImports` indicando dónde falló.
  - El run se marca `BUILD FAILURE` si hay infracciones y se detiene antes de pruebas.
  - Comando relevante: `mvn -B checkstyle:check`.
- Pruebas (JUnit/Surefire)
  - El resumen muestra `Tests run`, `Failures`, `Errors`. Si falla una aserción, se lista el test y la línea.
  - Ejemplo visto: `CalculadoraBasicaTest.sumarFunciona` falla en `src/test/java/com/alvaro/parcialcalidadsoftware/CalculadoraBasicaTest.java:20` con “expected X but was Y”.
  - Comando relevante: `mvn -B test` o dentro de `mvn -B clean verify`.
  - Reportes: `target/surefire-reports`.
- Cobertura (JaCoCo)
  - El paso `jacoco:check` imprime el ratio alcanzado y el mínimo requerido; si es menor, `BUILD FAILURE`.
  - El reporte HTML para detalle está en `target/site/jacoco/index.html`.
  - Comando relevante: `mvn -B jacoco:report jacoco:check` o parte de `verify`.

Generar run fallido y exitoso

- Run fallido por pruebas
  - Modificar un test para que falle (por ejemplo, cambiar una aserción en `CalculadoraBasicaTest.java:20` a un valor incorrecto).
  - Ejecutar `mvn -B clean verify` en NetBeans (Run Maven → verify). Resultado: `BUILD FAILURE` en la fase de pruebas; `jacoco:check` no se ejecuta.
- Run fallido por cobertura
  - Subir el umbral en `pom.xml` a `<coverage.minimum>0.90</coverage.minimum>` y/o usar `<counter>BRANCH</counter>` con mínimo 0.90.
  - Agregar un método sin pruebas en `src/main/java/com/alvaro/parcialcalidadsoftware/CalculadoraBasica.java:1` para bajar la cobertura efectiva.
  - Ejecutar `verify`. Resultado: pruebas pasan pero falla en `jacoco:check` por no alcanzar el umbral.
- Run exitoso
  - Restaurar `<coverage.minimum>0.80</coverage.minimum>` y corregir todas las pruebas.
  - Ejecutar `verify`. Resultado: `BUILD SUCCESS` y cobertura cumple.
- Diferencia clave
  - Si fallan pruebas, el pipeline se detiene antes del paso de cobertura.
  - Si fallan cobertura/umbral, pruebas pasan y el fallo ocurre en `jacoco:check`.

Métodos para detectar código generado por IA

- Estilometría y métricas lingüísticas/técnicas
  - Análisis de estilo del código (nombres, consistencia, complejidad ciclomática, distribución de tokens) y medidas como perplejidad; identificación de patrones excesivamente uniformes.
- Detectores especializados y trazabilidad
  - Herramientas que estiman probabilidad de IA (modelos entrenados en señales textuales), revisión de historial: frecuencia y tamaño de commits, timestamps, repetición de estructuras, y auditoría de prompts/plantillas cuando es posible.

Por qué no se puede asegurar 100% la autoría

- Los detectores tienen falsos positivos y falsos negativos; humanos pueden escribir como IA y viceversa.
- El código puede ser editado, refactorizado o post‑procesado; se diluyen las señales originales.
- No siempre hay trazabilidad completa (no hay “ground truth”); sin registro verificado del proceso no hay certeza absoluta.

Políticas razonables de uso de IA en educación y calidad

- Declaración obligatoria de uso
  - Establecer qué se permite (apoyo en documentación, brainstorming, sugerencias de nombres) y qué se prohíbe (entrega de código completo sin comprensión).
- Verificación de comprensión
  - Defensa oral breve del código, preguntas sobre decisiones de diseño, y demostración de ejecución (pruebas, cobertura, CI).
- Uso de detectores como apoyo
  - Emplear herramientas de detección con umbrales no deterministas; permitir apelaciones y revisión humana.
- Buenas prácticas de entrega
  - Requerir logs de runs (fallido y exitoso), umbral mínimo de cobertura, linter estricto y README que explique el pipeline.
- Evaluación por pares y transparencia
