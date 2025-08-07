-- Auto-generated SQL schema
CREATE TABLE attendance (
    id BIGINT PRIMARY KEY,
    subjectId VARCHAR(255),
    studentId BIGINT,
    date DATE,
    status VARCHAR(255),
    observation VARCHAR(255)
);

CREATE TABLE assessment_type (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    weight DOUBLE
);

CREATE TABLE assessment (
    id BIGINT PRIMARY KEY,
    subjectId VARCHAR(255),
    teacherId BIGINT,
    academicYearId VARCHAR(255),
    title VARCHAR(255),
    description VARCHAR(255),
    assessmentTypeId BIGINT,
    maxScore DOUBLE,
    date DATE
);

CREATE TABLE disciplinary_action (
    id BIGINT PRIMARY KEY,
    studentId BIGINT,
    date DATE,
    action VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE behavior_report (
    id BIGINT PRIMARY KEY,
    studentId BIGINT,
    termId BIGINT,
    score DOUBLE,
    observations VARCHAR(255)
);

CREATE TABLE meeting_minutes (
    id BIGINT PRIMARY KEY,
    meetingId BIGINT,
    summary VARCHAR(255),
    decisions VARCHAR(255),
    nextSteps VARCHAR(255)
);

CREATE TABLE meeting_attachment (
    id BIGINT PRIMARY KEY,
    meetingId BIGINT,
    fileUrl VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE meeting_attendance (
    id BIGINT PRIMARY KEY,
    meetingId BIGINT,
    personId BIGINT,
    role VARCHAR(255),
    status VARCHAR(255)
);

CREATE TABLE meeting (
    id BIGINT PRIMARY KEY,
    courseId VARCHAR(255),
    academicYearId VARCHAR(255),
    meetingDate DATE,
    location VARCHAR(255),
    meetingType VARCHAR(255),
    createdBy BIGINT
);

CREATE TABLE subject_enrollment (
    id BIGINT PRIMARY KEY,
    enrollmentId BIGINT,
    subjectId VARCHAR(255)
);

CREATE TABLE enrollment (
    id BIGINT PRIMARY KEY,
    studentId BIGINT,
    courseId VARCHAR(255),
    academicYearId VARCHAR(255),
    enrollmentDate DATE,
    status VARCHAR(255)
);

CREATE TABLE teacher_assignment (
    id BIGINT PRIMARY KEY,
    teacherId BIGINT,
    subjectId VARCHAR(255),
    courseId VARCHAR(255),
    academicYearId VARCHAR(255)
);

CREATE TABLE term (
    id BIGINT PRIMARY KEY,
    academicYearId VARCHAR(255),
    name VARCHAR(255),
    startDate DATE,
    endDate DATE
);

CREATE TABLE report_card (
    id BIGINT PRIMARY KEY,
    academicYearId VARCHAR(255),
    studentId BIGINT,
    averageScore DOUBLE,
    status VARCHAR(255)
);

CREATE TABLE audit_log (
    id BIGINT PRIMARY KEY,
    entity VARCHAR(255),
    entityId BIGINT,
    action VARCHAR(255),
    changedBy VARCHAR(255),
    changedAt TIMESTAMP,
    oldValue VARCHAR(255),
    newValue VARCHAR(255)
);

CREATE TABLE term_grade (
    id BIGINT PRIMARY KEY,
    termId BIGINT,
    subjectId VARCHAR(255),
    studentId BIGINT,
    averageScore DOUBLE,
    status VARCHAR(255)
);

CREATE TABLE final_grade (
    id BIGINT PRIMARY KEY,
    academicYearId VARCHAR(255),
    studentId BIGINT,
    averageScore DOUBLE,
    status VARCHAR(255)
);

CREATE TABLE grade (
    id BIGINT PRIMARY KEY,
    assessmentId BIGINT,
    studentId BIGINT,
    subjectId BIGINT,
    termId BIGINT,
    assessmentTypeId BIGINT,
    score DOUBLE,
    feedback VARCHAR(255)
);

CREATE TABLE grading_term (
    id BIGINT PRIMARY KEY,
    gradingSystemId BIGINT,
    name VARCHAR(255),
    order INT,
    weight DOUBLE
);

CREATE TABLE academic_year_grading (
    id BIGINT PRIMARY KEY,
    academicYearId VARCHAR(255),
    gradingSystemId BIGINT
);

CREATE TABLE grading_system (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    numberOfTerms INT,
    passingScore DOUBLE
);

CREATE TABLE grade (
    id BIGINT PRIMARY KEY,
    assessmentId BIGINT,
    studentId BIGINT,
    score DOUBLE,
    feedback VARCHAR(255)
);

CREATE TABLE student_representative (
    id BIGINT PRIMARY KEY,
    relationShip VARCHAR(255),
    studentId BIGINT,
    representativeId BIGINT,
    isMain BOOLEAN,
    tenantId VARCHAR(255)
);

CREATE TABLE student (
    id BIGINT PRIMARY KEY,
    firtName VARCHAR(255),
    lastName VARCHAR(255),
    phone VARCHAR(255),
    birthDate DATE,
    uuidUser VARCHAR(255),
    address VARCHAR(255),
    identification VARCHAR(255),
    nacionality VARCHAR(255),
    gender VARCHAR(255),
    image VARCHAR(255),
    deleted BOOLEAN,
    createdAt DATE,
    updatedAt DATE,
    deletedAt DATE,
    tenantId VARCHAR(255),
    uuidCourse VARCHAR(255),
    uuidParallel VARCHAR(255),
    uuidSchoolYear VARCHAR(255)
);

CREATE TABLE teacher (
    id BIGINT PRIMARY KEY,
    firtName VARCHAR(255),
    lastName VARCHAR(255),
    phone VARCHAR(255),
    birthDate DATE,
    uuidUser VARCHAR(255),
    address VARCHAR(255),
    identification VARCHAR(255),
    nacionality VARCHAR(255),
    gender VARCHAR(255),
    image VARCHAR(255),
    deleted BOOLEAN,
    createdAt DATE,
    updatedAt DATE,
    deletedAt DATE,
    tenantId VARCHAR(255)
);

CREATE TABLE representative (
    id BIGINT PRIMARY KEY,
    firtName VARCHAR(255),
    lastName VARCHAR(255),
    phone VARCHAR(255),
    birthDate DATE,
    uuidUser VARCHAR(255),
    address VARCHAR(255),
    identification VARCHAR(255),
    nacionality VARCHAR(255),
    gender VARCHAR(255),
    image VARCHAR(255),
    deleted BOOLEAN,
    createdAt DATE,
    updatedAt DATE,
    deletedAt DATE,
    tenantId VARCHAR(255),
    isActive BOOLEAN
);
