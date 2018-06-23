# GestionBilicence
A program to manage the marks of students using a SQL database.

Main class: GestionBilicence.java, the program then runs in a window (no output, just modification of the database).

At the moment, it uses a PostgreSQL database to store data.

The architecture of the program includes provisions to use a MySQL database later on.

## Quickstart

* The GestionBilicence.jar file enables to run the interface...
* The full system requires on a PostgreSQL database, which can be created using CreateDataBase.sql and PopulateDataBase.sql (in *GestionBilicence/sql* folder). After starting PostgreSQL, type in the command prompt:
```
create database testdb;
\c testdb
\i path/to/folder/CreateDataBase.sql
\i path/to/folder/PopulateDataBase.sql
```
NB: even when using Windows, *path/to/folder* should be specified with '/'.

* Overview of basic functions:
    * When connecting to the database, select "testdb", use your chosen username and provide your select password (if needs be).
    * You land on the "Statistics" page. It includes several tabs.
    * The default tab is 'Evolution' which provides an overview of a student's marks - use the panel on the left-hand side to select one student and see all the associated marks in the system.
    * The next tab is 'Average': selecting one or several semesters in the left-hand panel displays the students' overall average on the selected period.
    * The 'Success rate' tab is still work in progress.
    * The "Edition" page enables you to edit the basic properties of entries in the database (such as students' name, the available semesters, the exams coefficients...).

## Packages:

- "csvImport" - package containing the methods to make a csv import.
- "dao" - package of classes for the DAO design pattern.
- "edition" - panel used to edit the different objects of the database.
- "general" - classes used in other parts of the program.
- "statistics" - panel used to display statistics about the students and their marks.
- main class in "GestionBilicence.java".

## Installation procedure - with a csv file:

Based on import of csv file.
* In PostgreSQL, create databases "testdb" and "livedb" using CreateDataBase.sql.
* Create suitable semesters (then save) and at least two Exams (then save).
* Import csv file, which will populate the Student database.
