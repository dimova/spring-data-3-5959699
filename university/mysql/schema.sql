CREATE TABLE staff_member (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL
);


CREATE TABLE department (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  chair_id BIGINT
);

ALTER TABLE department ADD FOREIGN KEY (chair_id) REFERENCES staff_member(id);

CREATE TABLE student (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  age INTEGER NOT NULL,
  full_time BOOLEAN NOT NULL
);

CREATE TABLE course (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  credits INTEGER NOT NULL,
  instructor_id BIGINT NOT NULL,
  department_id BIGINT NOT NULL
);
ALTER TABLE course ADD FOREIGN KEY (instructor_id) REFERENCES staff_member(id);
ALTER TABLE course ADD FOREIGN KEY (department_id) REFERENCES department(id);

CREATE TABLE course_student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL
);

ALTER TABLE course_student ADD FOREIGN KEY (course_id) REFERENCES course(id);
ALTER TABLE course_student ADD FOREIGN KEY (student_id) REFERENCES student(id);

CREATE TABLE department_course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT  NOT NULL,
    department_id BIGINT NOT NULL
);

ALTER TABLE department_course ADD FOREIGN KEY (course_id) REFERENCES course(id);
ALTER TABLE department_course ADD FOREIGN KEY (department_id) REFERENCES department(id);

CREATE TABLE course_prerequisites (
 id_of_course BIGINT,
 id_prerequisite_course BIGINT
);

ALTER TABLE course_prerequisites ADD FOREIGN KEY (id_of_course) REFERENCES course(id);
ALTER TABLE course_prerequisites ADD FOREIGN KEY (id_prerequisite_course) REFERENCES course(id);
insert into staff_member (id, first_name, last_name) values
(1, 'Dean', 'Thomas'),
(2, 'Dean', 'Green'),
(3, 'Dean', 'Jones'),
(4, 'Dean', 'Martin'),
(5, 'James', 'Brown'),
(6, 'Judy', 'MIller'),
(7, 'James', 'Davis'),
(8, 'Allson', 'Moore'),
(9, 'Whitney', 'White'),
(10, 'Jack', 'Black'),
(11, 'Queen', 'King');

insert into department (id, name, chair_id) values
(21, 'Humanities', 3),
(22, 'Natural Sciences', 4),
(23, 'Social Sciences', 3);


insert into course (id, name, credits, instructor_id, department_id) values
(31, 'English 101', 3, 10, 21),
(32, 'English 201', 3, 5, 21),
(33, 'English 301', 4, 10, 21),
(34, 'Chemistry', 3, 7, 22),
(35, 'Physics', 3, 7, 22),
(36, 'C Programming', 3, 8, 22),
(37, 'Java Programming', 4, 8, 22),
(38, 'History 101', 3, 6, 23),
(39, 'Anthropology', 3, 11, 23),
(40, 'Sociology', 3, 11, 23),
(41, 'Psychology', 3, 10, 23),
(42, 'Chemistry Lab', 1, 7, 22),
(43, 'Physics Lab', 1, 7, 22);

insert into course_prerequisites (id_of_course, id_prerequisite_course) values
(32, 31),
(33, 32),
(35, 34),
(39, 38),
(40, 38),
(41, 38);

ALTER TABLE staff_member AUTO_INCREMENT = 12;
ALTER TABLE department AUTO_INCREMENT = 24;
ALTER TABLE course AUTO_INCREMENT =44;
ALTER TABLE course_prerequisites AUTO_INCREMENT =56;
