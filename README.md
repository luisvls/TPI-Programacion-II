# TPI Programación II – Sistema de Gestión Hospitalaria

Trabajo Práctico Integrador de Programación II (UTN – Tecnicatura Universitaria en Programación a Distancia).
Implementa en Java el modelo de objetos de un hospital (departamentos, salas, médicos, pacientes y citas)
y se verifica con el programa de pruebas provisto por la cátedra (`MainPruebas`).

- **Autor:** Luis Alfredo Suarez
- **Repositorio:** https://github.com/luisvls/TPI-Programacion-II
- **Video explicativo:** https://youtu.be/0Esc6UDpTjY

## Requisitos

- Java 17 o superior (JDK).
- Maven (incluido en NetBeans e IntelliJ) o cualquier IDE que abra proyectos Maven.
- Solo biblioteca estándar: no hay dependencias externas ni persistencia (todo vive en memoria).

## Cómo ejecutar

### Desde NetBeans
1. `File > Open Project…` y elegir la carpeta del proyecto (`TPI_P2`).
2. Abrir `src/main/java/org/jcr/MainPruebas.java`.
3. Clic derecho sobre el archivo > `Run File` (o `Shift + F6`).

### Desde la terminal (con Maven)
```
mvn compile
java -cp target/classes org.jcr.MainPruebas
```

Resultado esperado al final de la salida:

```
===== RESULTADO: 106 de 106 verificaciones correctas =====
Todas las pruebas pasaron.
```

## Estructura del proyecto

```
src/main/java/org/jcr/
├── MainPruebas.java      Pruebas de la cátedra (sin modificar)
├── entidades/            Persona, Paciente, Medico, Matricula, HistoriaClinica,
│                         Hospital, Departamento, Sala, Cita, Validaciones
├── enums/                TipoSangre, EspecialidadMedica, EstadoCita
├── servicios/            CitaService (interfaz), CitaManager (implementación)
└── excepciones/          CitaException (verificada)
```

## Decisiones de diseño

- **Herencia y polimorfismo:** `Persona` es abstracta; `Paciente` y `Medico` la extienden.
- **Composición:** el dueño crea a su parte. `Paciente` crea su `HistoriaClinica`, `Medico` crea su
  `Matricula` y `Departamento` crea sus `Sala` con `crearSala()` (constructores no públicos).
- **Relación 1→1 unidireccional:** `Medico` contiene una `Matricula`; la matrícula no conoce a su médico.
- **Relaciones bidireccionales** (`Hospital`–`Departamento`, `Hospital`–`Paciente`, `Departamento`–`Medico`):
  un único método (`setHospital` / `setDepartamento`) actualiza las dos puntas, por lo que no hay duplicados.
  Un médico solo puede pertenecer a un departamento de su misma especialidad.
- **Encapsulamiento:** los getters de listas devuelven `Collections.unmodifiableList(...)`.
- **Validaciones:** los constructores validan sus datos y lanzan `IllegalArgumentException`;
  la clase `Validaciones` evita repetir código.
- **Citas:** `CitaService` define el contrato y `CitaManager` lo implementa. `programarCita()` lanza
  `CitaException` (verificada) si falta algún dato, la fecha ya pasó, el costo no es positivo, la
  especialidad no coincide o el médico/la sala tienen otra cita a menos de 2 horas
  (`HORAS_ENTRE_CITAS`).
