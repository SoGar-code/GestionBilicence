# GestionBilicence
A program to manage the marks of students using a SQL database.

Main class: GestionBilicence.java, the program then runs in a window (no output, just modification of the database).

At the moment, it uses a PostgreSQL database to store data (use CreateDataBase.sql and PopulateDataBase.sql to create and populate the database).

The architecture of the program includes provisions to use a MySQL database later on.



## Packages:

- "csvImport" - package containing the methods to import.
- "dao" - package of classes for the DAO design pattern.
- "edition" - panel used to edit the different objects of the database.
- "general" - classes used in other parts of the program. 
- "statistics" - panel used to display statistics about the students and their marks.
- main class "GestionBilicence.java".

## Installation procedure:

Based on import of csv file.
* In PostgreSQL, create databases "testdb" and "livedb" using CreateDataBase.sql.
* Create suitable semesters (then save) and at least two Exams (then save).
* Import csv file, which will populate the Student database.
