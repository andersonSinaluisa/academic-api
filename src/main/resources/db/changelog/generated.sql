-- liquibase formatted sql

CREATE TABLE academic_year (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR,
    start_date DATE,
    end_date DATE
);

CREATE TABLE grading_system (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR,
    description VARCHAR,
    number_of_terms INT,
    passing_score DOUBLE PRECISION
);

CREATE TABLE grading_term (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    grading_system_id BIGINT REFERENCES grading_system(id),
    name VARCHAR,
    "order" INT,
    weight DOUBLE PRECISION
);

CREATE TABLE academic_year_grading (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    academic_year_id BIGINT REFERENCES academic_year(id),
    grading_system_id BIGINT REFERENCES grading_system(id)
);

CREATE TABLE student (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR,
    last_name VARCHAR,
    phone VARCHAR,
    birth_date DATE,
    uuid_user VARCHAR,
    address VARCHAR,
    identification VARCHAR,
    nationality VARCHAR,
    gender VARCHAR,
    image VARCHAR,
    deleted BOOLEAN,
    created_at DATE,
    updated_at DATE,
    deleted_at DATE,
    tenant_id VARCHAR,
    uuid_course VARCHAR,
    uuid_parallel VARCHAR,
    uuid_school_year VARCHAR
);

CREATE TABLE representative (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR,
    last_name VARCHAR,
    phone VARCHAR,
    birth_date DATE,
    uuid_user VARCHAR,
    address VARCHAR,
    identification VARCHAR,
    nationality VARCHAR,
    gender VARCHAR,
    image VARCHAR,
    deleted BOOLEAN,
    created_at DATE,
    updated_at DATE,
    deleted_at DATE,
    tenant_id VARCHAR,
    is_active BOOLEAN
);

CREATE TABLE student_representative (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    relationship VARCHAR,
    student_id BIGINT REFERENCES student(id),
    representative_id BIGINT REFERENCES representative(id),
    is_main BOOLEAN,
    tenant_id VARCHAR
);

CREATE TABLE teacher (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR,
    last_name VARCHAR,
    phone VARCHAR,
    birth_date DATE,
    uuid_user VARCHAR,
    address VARCHAR,
    identification VARCHAR,
    nationality VARCHAR,
    gender VARCHAR,
    image VARCHAR,
    deleted BOOLEAN,
    created_at DATE,
    updated_at DATE,
    deleted_at DATE,
    tenant_id VARCHAR
);

CREATE TABLE term (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    academic_year_id BIGINT REFERENCES academic_year(id),
    name VARCHAR,
    start_date DATE,
    end_date DATE
);

CREATE TABLE subject (
    id VARCHAR PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE assessment_type (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR,
    weight DOUBLE PRECISION
);

CREATE TABLE assessment (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    subject_id VARCHAR REFERENCES subject(id),
    teacher_id BIGINT REFERENCES teacher(id),
    academic_year_id BIGINT REFERENCES academic_year(id),
    title VARCHAR,
    description VARCHAR,
    assessment_type_id BIGINT REFERENCES assessment_type(id),
    max_score DOUBLE PRECISION,
    date DATE
);

CREATE TABLE grade (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    assessment_id BIGINT REFERENCES assessment(id),
    student_id BIGINT REFERENCES student(id),
    subject_id VARCHAR REFERENCES subject(id),
    term_id BIGINT REFERENCES term(id),
    assessment_type_id BIGINT REFERENCES assessment_type(id),
    score DOUBLE PRECISION,
    feedback VARCHAR
);

CREATE TABLE final_grade (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    academic_year_id BIGINT REFERENCES academic_year(id),
    student_id BIGINT REFERENCES student(id),
    average_score DOUBLE PRECISION,
    status VARCHAR
);

CREATE TABLE term_grade (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    term_id BIGINT REFERENCES term(id),
    subject_id VARCHAR REFERENCES subject(id),
    student_id BIGINT REFERENCES student(id),
    average_score DOUBLE PRECISION,
    status VARCHAR
);

CREATE TABLE report_card (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    academic_year_id BIGINT REFERENCES academic_year(id),
    student_id BIGINT REFERENCES student(id),
    average_score DOUBLE PRECISION,
    status VARCHAR
);

CREATE TABLE enrollment (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id BIGINT REFERENCES student(id),
    course_id VARCHAR,
    academic_year_id BIGINT REFERENCES academic_year(id),
    enrollment_date DATE,
    status VARCHAR
);

CREATE TABLE subject_enrollment (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    enrollment_id BIGINT REFERENCES enrollment(id),
    subject_id VARCHAR REFERENCES subject(id)
);

CREATE TABLE teacher_assignment (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    teacher_id BIGINT REFERENCES teacher(id),
    subject_id VARCHAR REFERENCES subject(id),
    course_id VARCHAR,
    academic_year_id BIGINT REFERENCES academic_year(id)
);

CREATE TABLE disciplinary_action (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id BIGINT REFERENCES student(id),
    date DATE,
    action VARCHAR,
    description VARCHAR
);

CREATE TABLE behavior_report (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id BIGINT REFERENCES student(id),
    term_id BIGINT REFERENCES term(id),
    score DOUBLE PRECISION,
    observations VARCHAR
);

CREATE TABLE attendance (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    subject_id VARCHAR REFERENCES subject(id),
    student_id BIGINT REFERENCES student(id),
    date DATE,
    status VARCHAR,
    observation VARCHAR
);

CREATE TABLE meeting (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    course_id VARCHAR,
    academic_year_id BIGINT REFERENCES academic_year(id),
    meeting_date DATE,
    location VARCHAR,
    meeting_type VARCHAR,
    created_by BIGINT REFERENCES teacher(id)
);

CREATE TABLE meeting_minutes (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    meeting_id BIGINT REFERENCES meeting(id),
    summary VARCHAR,
    decisions VARCHAR,
    next_steps VARCHAR
);

CREATE TABLE meeting_attachment (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    meeting_id BIGINT REFERENCES meeting(id),
    file_url VARCHAR,
    description VARCHAR
);

CREATE TABLE meeting_attendance (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    meeting_id BIGINT REFERENCES meeting(id),
    person_id BIGINT,
    role VARCHAR,
    status VARCHAR
);

CREATE TABLE audit_log (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    entity VARCHAR,
    entity_id BIGINT,
    action VARCHAR,
    changed_by VARCHAR,
    changed_at TIMESTAMP,
    old_value VARCHAR,
    new_value VARCHAR
);
