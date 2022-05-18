# Enunciado(12+1)
En una agencia empleadora se desea gestionar la bolsa de ubicación laboral. Dicha
agencia se encarga de gestionar las ofertas y demandas de empleo de variadas
empresas. De cada empresa se conoce: nombre, dirección, teléfono y sector en el
que se encuentra ubicada (turismo, salud, educación, entre otros). Una empresa
puede realizar solicitudes para tantos puestos de trabajo como necesite, así como
solicitar la eliminación de puestos de trabajo que solicitó. De cada oferta se conoce:
ID, rama a la que pertenece, salario del puesto y empresa a la que pertenece esta
oferta.
Al llegar un candidato a empleado a la agencia se le recogen los siguientes datos:
carné de identidad, nombre, sexo, dirección, teléfono, nivel de escolaridad,
especialidad, rama en la que desea trabajar (directivo, ingeniero, recursos humanos,
entre otras) y años de experiencia en su área de especialidad. En dependencia de
la rama en la que desee trabajar, se deben incluir otros datos (un candidato que
desee trabajar en turismo debe presentar un certificado de idioma, un candidato que
desee incorporarse a la plantilla de custodios debe presentar sus calificaciones en
las pruebas de eficiencia física, así como su número de historia clínica). Una vez
ingresado los datos al sistema, se comprueba si existen actualmente ofertas de
empleo de la rama que le interesa. Se debe crear una funcionalidad en cada
candidato, en la cual, dada una oferta, se sepa si el candidato puede o no aspirar a
ese empleo.
De existir alguna oferta de empleo para el candidato, se programa una cita con la
empresa interesada. El candidato puede asistir a todas las entrevistas que desee,
pero solo puede asistir a una cita al día. De no existir un empleo que cumpla con los
requerimientos del candidato, sus datos quedan almacenados en el sistema. Cuando
una empresa realice una solicitud de empleo, los candidatos inscritos en el sistema
deben ser revisados a ver si existe alguno con las cualidades necesarias para
satisfacer dicha solicitud.
La agencia empleadora cuenta con un registro mensual de la planificación de
entrevista, donde se tiene por día, para cada empleo que solicita una empresa, si
hay planificada una entrevista o no.

# REQUERIMIENTOS GENERALES OBLIGATORIOS
- Implementar adecuadamente las propiedades de la Programación Orientada a
Objetos.
- Se deben validar todos los datos de entrada que lo requieran.
- La aplicación tiene que garantizar la gestión y visualización de toda la información
en cualquier momento de la ejecución.
- El proyecto debe estar divido en dos capas: lógica e interfaz, de modo que en el
código exista una separación que permita que la lógica pueda ser utilizada de
manera satisfactoria, aunque se cambie la interfaz.
- La aplicación debe emitir reportes de sus principales funcionalidades. Cada miembro
de equipo debe tener al menos dos reportes: uno que procese información en al
menos dos clases y uno que procese listas de listas.
- Se deben aplicar patrones y principios de diseño, los cuales están explicados en la
bibliografía de la asignatura. Además de realizar un tratamiento adecuado de los
errores mediante el uso de las excepciones.
- Para la exposición, se debe traer el código de la aplicación para ser visto por el
tribunal, y el ejecutable .jar para presentar la aplicación funcionando.

# ESTRUCTURA Y REQUISITOS DEL INFORME DEL PROYECTO FINAL
### Presentación: 
 - Título del proyecto
 - Número del proyecto
 - Nombre de los autores
 - Lugar y fecha.
 - Se debe colocar que es el Informe de Proyecto Final de las asignaturas: Diseño y Programación Orientada a Objetos y Diseño de Interfaces y Pruebas.
### Resumen: 
- Donde se debe expresar el objetivo del proyecto enfocado a la problemática que están resolviendo, y la forma en la que lo solucionaron.
### Índice de contenido: 
- Generado de manera automática.
### Introducción: 
- Debe emplearse una redacción fluida y lógica. 
- Entre los elementos a abordar se debe presentar el enunciado del problema, el objetivo del trabajo y el plan de trabajo que se siguió para resolverlo.
- Para la elaboración de este plan de trabajo se deben definir las tareas a realizar, el responsable de cada una y el tiempo límite para su cumplimiento. 
- La fecha inicial de este plan de trabajo es a partir de la asignación de los enunciado y la fecha final es hasta la entrega del
informe al final del trabajo de curso.
### Desarrollo:
- Tarjetas CRC para cada una de las clases identificadas.
- Diagrama de clases en UML de la capa lógica que da respuesta al problema.
- Consideraciones para el diseño de la interfaz (ver orientaciones de la asignatura Diseño de Interfaz y Pruebas).
- Reportes a implementar en la aplicación con la argumentación de su elección. La propuesta debe incluir dos reportes por estudiante, donde uno se obtenga a partir del procesamiento de información en al menos dos clases y el otro procese listas de listas. Se deben documentar uno por cada estudiante y deben ser de tipos diferentes.
- Descripción de al menos 3 patrones de diseño a través de su diagrama de clases en UML. De ser presentado el patrón Singleton, no se debe presentar el patrón MVC, y viceversa. En caso de que ambos patrones sean los únicos presentes en la solución diseñada para su problema, deben explicar por qué otros patrones (al menos 2 más) no lo son. Tome como guía el material de “IntroducciónPOO” suministrado como parte de la bibliografía complementaria.
- Descripción de los mecanismos fundamentales para la validación de campos, describiendo cuál validación corresponde con cada campo, y además, el tratamiento de excepciones.
- Pruebas realizadas (ver orientaciones de la asignatura de Diseño de Interfaz y Pruebas).
### Conclusiones: 
- donde se exprese la justificación de las decisiones más importantes tomadas para darle solución al problema. Describir haciendo uso de
viñetas.
### Recomendaciones: 
- Enfocadas hacia nuevas funcionalidades que se puedan incorporar al software para mejorar sus prestaciones. 
- Describir haciendo uso de una lista numerada.
### Bibliografía: 
- Donde se deben presentar con todos los datos necesarios, los materiales que se utilizaron para dar solución al problema.

# ESTRUCTURA Y REQUISITOS DE LOS CORTES DEL TRABAJO DE CURSO
#### Presentación: 
- Título del proyecto, 
- Número del proyecto, 
- Nombre de los autores,
- Lugar y fecha. 
- Se debe colocar que es el Corte 1 ó 2 del Informe de Proyecto Final de las asignaturas: Diseño y Programación Orientada a Objetos y Diseño de Interfaces y Pruebas.
#### Resumen:
- Donde se debe expresar el objetivo del proyecto enfocado a la problemática que están resolviendo, y la forma en la que lo solucionaron.
#### Índice de contenido:
- Generado de manera automática con la estructura exigida en cada corte.
#### Introducción:
- Debe emplearse una redacción fluida y lógica.
- Entre los elementos a abordar se debe presentar el enunciado del problema, el objetivo del trabajo y el plan de trabajo que se siguió para resolverlo. 
- Para la elaboración de este plan de trabajo se deben definir las tareas a realizar, el responsable de cada una y el tiempo límite para su cumplimiento. 
- La fecha inicial de este plan de trabajo es a partir de la asignación de los enunciado y la fecha final es hasta la entrega del informa final del trabajo de curso.

### CORTE 1
#### Desarrollo:
- Tarjetas CRC para cada una de las clases identificadas.
- Diagrama de clases en UML de la capa lógica que da respuesta al problema.
- Consideraciones para el diseño de la interfaz (ver orientaciones de la asignatura Diseño de Interfaz y Pruebas). En este corte se documentan todas de las interfaces gráficas propuestas para la solución de su problema.
- Una propuesta de reportes a implementar en la aplicación con la argumentación de su elección. La propuesta debe incluir dos reportes por estudiante, donde uno se obtenga a partir del procesamiento de información en al menos dos clases y el otro procese listas de listas.
### CORTE 2:
#### Desarrollo:
- Actualización de los señalamientos realizados en el Corte #1.
- Consideraciones para el diseño de la interfaz (ver orientaciones de la asignatura Diseño de Interfaz y Pruebas). En este corte se documentan todas las interfaces gráficas implementadas.
- Reportes implementados donde se documente uno por cada integrante del equipo y sean de tipos diferentes.
- Descripción de al menos 3 patrones de diseño a través de su diagrama de clases en UML. De ser presentado el patrón Singleton, no se debe presentar el patrón MVC, y viceversa. En caso de que ambos patrones sean los únicos presentes en la solución diseñada para su problema, deben explicar por qué otros patrones (al menos 2 más) no lo son. Tome como guía el material de “IntroducciónPOO” suministrado como parte de la bibliografía complementaria.
- Descripción de los mecanismos fundamentales para la validación de campos, describiendo cuál validación corresponde con cada campo, y además, el tratamiento de excepciones.
- Pruebas realizadas (ver orientaciones de la asignatura de Diseño de Interfaz y Pruebas).
#### Conclusiones: 
- Donde se exprese la justificación de las decisiones más
importantes tomadas para darle solución al problema. Describir haciendo uso de viñetas.
#### Recomendaciones: 
- Enfocadas hacia nuevas funcionalidades que se puedan incorporar al software para mejorar sus prestaciones. Describir haciendo uso de una lista numerada.
#### Bibliografía: 
- Donde se deben presentar con todos los datos necesarios, los materiales que se utilizaron para dar solución al problema.
# PARTES DEL PROCESO DE EVALUACIÓN DEL TRABAJO DE CURSO
El Trabajo de Curso de la asignatura tiene dos convocatorias, las cuales constan de tres partes que se evalúan en el orden que se presenta a continuación:
- Informe final del Trabajo de Curso que debe cumplir con la estructura definida y donde se debe documentar de forma adecuada los aspectos que se exigen. Para su correcta elaboración los equipos disponen de dos cortes evaluativos en la semana 4 y 8 que les permitirán corregir y refinar el contenido del informe antes de su entrega final en la semana 10.
- Evaluación escrita del Trabajo de Curso que consiste en un examen escrito en la semana 11 que permite medir conocimientos teóricos y prácticos de los cuatro temas impartidos en la asignatura necesarios para la realización del Trabajo de Curso.
- Evaluación práctica del Trabajo de Curso que consiste en la defensa en la semana 12 de la solución informática del problema asignado donde se miden diferentes habilidades a alcanzar por el estudiante en la asignatura. Las tres partes del Trabajo de Curso explicados anteriormente son evaluadas por
un tribunal de tres profesores de la carrera. El obtener una calificación de 2 puntos en cualquiera de estas evaluaciones implica suspender el Trabajo de Curso, y en consecuencia, suspender la asignatura.

# glasswindow
A Job posting app for DPOO's final project (A clone of GlassDoor)
