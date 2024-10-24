# Proyecto de clase de Acceso a Datos I.E.S. Donoso Cortés

Un proyecto de clase para la practica de la gestion, creacion y modificación de archivos XML, serialización de datos y el trabajo en equipo, el proyecto parte de una premisa data como enunciado del ejercicio:

Realizar un proyecto para dar persistencia a una colección de empleados(emp_no,apellido,dir,salario,oficio,fecha_alt,comision,dept_no),y departamentos(dept_no,dnombre,loc), usando Xstream.

Un empleado sólo puede estar en un departamento.

## Descripción de algunos campos:

**Comisión:** null, si el empleado no tiene comisiones o un valor en euros (decimal)

**Emp_no:** número de empleado

**Dir:** número de empleado director. Un empleado puede ser director de varios empleados y un empleado tiene un único director. Si el empleado es director su campo dir será nulo.

**Fecha_alt:** fecha de alta

**Dept_no:** número de departamento

**Dnombre:** nombre del departamento
loc: localización del departamento (Población)

## Operaciones

Operaciones para empleados y departamentos: alta,baja,modificación de todos los campos, listados y buscar empleados y departamentos por todos sus campos.

Al ejecutarse el programa, carga los datos de los ficheros xml de partida que deben existir: empleados.xml y departamentos.xml.

Controlar todos los errores posibles y justificar el modo del funcionamiento persistente.

Entregar un fichero: nombreApellidosRepresentanteDeEquipo.rar. que contenga además de los recursos para la implementación (proyecto eclipse), un documento pdf con el manual del programador: modelo E/R, diagramas uml, algoritmos, diagramas de gannt, pruebas, ...

## Diseño de datos

        Modelo E/R

        Esquemas XML

## Diseño de procesos

         Diagrama flujo del main

         Pseudocódigo de algunos métodos

         Diagrama Casos de uso

         Diagrama de clases

## Pruebas

En el texto en línea de la entrega comentáis los problemas que habéis tenido, lo que no hace el programa

## Integrantes

**Profesor: Saul González**

-   David Rámirez

-   Gaspar Gómez

-   Juan Colilla

-   Julian Martín

##
