-- To populate the database initially in project GestionBilicence
-- UTF-8 encoding 

INSERT INTO students(stud_firstname, stud_lastname) VALUES('Marc','Dupont');
INSERT INTO students(stud_firstname, stud_lastname) VALUES('Jean','Martin');
INSERT INTO students(stud_firstname, stud_lastname) VALUES('Marie','Richard');

INSERT INTO stud_num(id_stud,stud_number) VALUES(1,7510);
INSERT INTO stud_num(id_stud,stud_number) VALUES(2,7511);

INSERT INTO semesters(semester_name) VALUES('L1S1 2017-2018');
INSERT INTO semesters(semester_name) VALUES('L1S2 2016-2017');
INSERT INTO semesters(semester_name) VALUES('L2S1 2015-2016');
INSERT INTO semesters(semester_name) VALUES('L2S2 2016-2017');
INSERT INTO semesters(semester_name) VALUES('APB 2017');

INSERT INTO acad_years(ay_name) VALUES('2017-2018');
INSERT INTO acad_years(ay_name) VALUES('2016-2017');

INSERT INTO subjects(subject_name) VALUES('English');
INSERT INTO subjects(subject_name) VALUES('History');