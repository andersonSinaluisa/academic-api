# Academic API Endpoints

## Módulo de Reportes y Estadísticas

### GET `/report-cards/{studentId}?academicYearId=`
**Response**
```json
{
  "id": 1,
  "academicYearId": "2024",
  "studentId": 123,
  "averageScore": 8.5,
  "status": "PROMOTED",
  "subjects": [
    {
      "subjectId": "MAT",
      "name": "Matemáticas",
      "scores": { "P1": 8.0, "P2": 9.0, "Q1": 8.5, "Final": 8.7 }
    }
  ]
}
```

### GET `/official-records/{studentId}`
**Response**
```json
{
  "studentId": 123,
  "fullName": "Juan Pérez",
  "records": [
    {
      "academicYearId": "2023",
      "course": "10A",
      "averageScore": 8.2,
      "status": "PROMOTED"
    }
  ]
}
```

### GET `/statistics/attendance?courseId=&academicYearId=`
**Response**
```json
{
  "courseId": "10A",
  "academicYearId": "2024",
  "totalStudents": 30,
  "present": 95.0,
  "absent": 4.0,
  "justified": 1.0
}
```

### GET `/statistics/performance?courseId=&subjectId=&academicYearId=`
**Response**
```json
{
  "courseId": "10A",
  "subjectId": "MAT",
  "academicYearId": "2024",
  "average": 8.1,
  "highest": 10.0,
  "lowest": 5.0,
  "distribution": { "0-4": 1, "5-6": 5, "7-8": 15, "9-10": 9 }
}
```

### GET `/exports/report-card/{studentId}?academicYearId=&format=PDF|EXCEL`
**Response**: Archivo binario (boletín)

### GET `/exports/statistics?courseId=&academicYearId=&format=PDF|EXCEL`
**Response**: Archivo binario (estadísticas)

---

## Módulo de Promoción y Cierre de Año

### POST `/promotions`
**Request**
```json
{
  "studentId": 123,
  "academicYearId": "2024"
}
```
**Response**
```json
{
  "studentId": 123,
  "academicYearId": "2024",
  "finalAverage": 6.8,
  "status": "SUPLETORIO",
  "generatedAt": "2024-02-01"
}
```

### POST `/promotion-acts`
**Request**
```json
{
  "courseId": "10A",
  "academicYearId": "2024",
  "generatedBy": 99
}
```
**Response**
```json
{
  "id": 1,
  "courseId": "10A",
  "academicYearId": "2024",
  "generatedBy": 99,
  "generatedAt": "2024-02-01"
}
```

### GET `/promotion-acts/{id}`
**Response**
```json
{
  "id": 1,
  "courseId": "10A",
  "academicYearId": "2024",
  "generatedBy": 99,
  "generatedAt": "2024-02-01"
}
```

### GET `/promotion-acts?courseId=&academicYearId=`
**Response**
```json
[
  {
    "id": 1,
    "courseId": "10A",
    "academicYearId": "2024",
    "generatedBy": 99,
    "generatedAt": "2024-02-01"
  }
]
```

### DELETE `/promotion-acts/{id}`
**Response**: 204 No Content
---

## Módulo de Gestión Docente

### POST `/teacher-assignments` *(header `X-Teacher-Id`)*
**Request**
```json
{
  "teacherId": 10,
  "courseId": 5,
  "subjectId": 2,
  "schoolYearId": "2024"
}
```
**Response**
```json
{
  "id": 1,
  "teacherId": 10,
  "courseId": 5,
  "subjectId": 2,
  "schoolYearId": "2024",
  "createdAt": "2024-02-01T10:00:00"
}
```

### GET `/teacher-assignments/{id}` *(header `X-Teacher-Id`)*
**Response**
```json
{
  "id": 1,
  "teacherId": 10,
  "courseId": 5,
  "subjectId": 2,
  "schoolYearId": "2024",
  "createdAt": "2024-02-01T10:00:00"
}
```

### GET `/teacher-assignments?teacherId=&courseId=` *(header `X-Teacher-Id`)*
**Response**
```json
[
  {
    "id": 1,
    "teacherId": 10,
    "courseId": 5,
    "subjectId": 2,
    "schoolYearId": "2024",
    "createdAt": "2024-02-01T10:00:00"
  }
]
```

### POST `/teacher-plannings` *(header `X-Teacher-Id`)*
**Request**
```json
{
  "teacherId": 10,
  "subjectId": 2,
  "courseId": 5,
  "schoolYearId": "2024",
  "topic": "Álgebra",
  "description": "Ecuaciones lineales",
  "week": 3
}
```
**Response**
```json
{
  "id": 1,
  "teacherId": 10,
  "subjectId": 2,
  "courseId": 5,
  "schoolYearId": "2024",
  "topic": "Álgebra",
  "description": "Ecuaciones lineales",
  "week": 3,
  "createdAt": "2024-02-01T10:00:00"
}
```

### GET `/teacher-plannings/{id}` *(header `X-Teacher-Id`)*
**Response**
```json
{
  "id": 1,
  "teacherId": 10,
  "subjectId": 2,
  "courseId": 5,
  "schoolYearId": "2024",
  "topic": "Álgebra",
  "description": "Ecuaciones lineales",
  "week": 3,
  "createdAt": "2024-02-01T10:00:00"
}
```

### GET `/teacher-plannings?teacherId=&subjectId=&courseId=` *(header `X-Teacher-Id`)*
**Response**
```json
[
  {
    "id": 1,
    "teacherId": 10,
    "subjectId": 2,
    "courseId": 5,
    "schoolYearId": "2024",
    "topic": "Álgebra",
    "description": "Ecuaciones lineales",
    "week": 3,
    "createdAt": "2024-02-01T10:00:00"
  }
]
```

---

## Módulo de Gestión de Personas

### POST `/students`
**Request**
```json
{
  "firstName": "Juan",
  "lastName": "Pérez",
  "uuidParallel": "10A"
}
```
**Response**
```json
{
  "id": 1,
  "firstName": "Juan",
  "lastName": "Pérez",
  "uuidParallel": "10A"
}
```

### GET `/students?page=&limit=&uuidParallel=`
**Response**
```json
[
  {
    "id": 1,
    "firstName": "Juan",
    "lastName": "Pérez",
    "uuidParallel": "10A"
  }
]
```

### GET `/students/{id}`
**Response**
```json
{
  "id": 1,
  "firstName": "Juan",
  "lastName": "Pérez",
  "uuidParallel": "10A"
}
```

### PATCH `/students/{id}`
**Request**
```json
{
  "firstName": "Juan"
}
```
**Response**
```json
{
  "id": 1,
  "firstName": "Juan",
  "lastName": "Pérez",
  "uuidParallel": "10A"
}
```

### DELETE `/students/{id}`
**Response**: 204 No Content

### POST `/teachers`
**Request**
```json
{
  "firstName": "Ana",
  "lastName": "García"
}
```
**Response**
```json
{
  "id": 1,
  "firstName": "Ana",
  "lastName": "García"
}
```

### GET `/teachers?page=&limit=&firstName=&lastName=&identification=&gender=`
**Response**
```json
[
  {
    "id": 1,
    "firstName": "Ana",
    "lastName": "García"
  }
]
```

### GET `/teachers/{id}`
**Response**
```json
{
  "id": 1,
  "firstName": "Ana",
  "lastName": "García"
}
```

### PATCH `/teachers/{id}`
**Request**
```json
{
  "firstName": "Ana"
}
```
**Response**
```json
{
  "id": 1,
  "firstName": "Ana",
  "lastName": "García"
}
```

### DELETE `/teachers/{id}`
**Response**: 204 No Content

### POST `/representative`
**Request**
```json
{
  "firstName": "Luis",
  "lastName": "Mora"
}
```
**Response**
```json
{
  "id": 1,
  "firstName": "Luis",
  "lastName": "Mora"
}
```

### GET `/representative?page=&limit=`
**Response**
```json
[
  {
    "id": 1,
    "firstName": "Luis",
    "lastName": "Mora"
  }
]
```

### GET `/representative/{id}`
**Response**
```json
{
  "id": 1,
  "firstName": "Luis",
  "lastName": "Mora"
}
```

### PATCH `/representative/{id}`
**Request**
```json
{
  "firstName": "Luis"
}
```
**Response**
```json
{
  "id": 1,
  "firstName": "Luis",
  "lastName": "Mora"
}
```

### DELETE `/representative/{id}`
**Response**: 204 No Content

---

## Módulo Académico

### POST `/assessments`
**Request**
```json
{
  "studentId": 1,
  "subjectId": 2,
  "score": 9.5
}
```
**Response**
```json
{
  "id": 1,
  "studentId": 1,
  "subjectId": 2,
  "score": 9.5
}
```

### GET `/assessments`
**Response**
```json
[
  {
    "id": 1,
    "studentId": 1,
    "subjectId": 2,
    "score": 9.5
  }
]
```

### PUT `/assessments/{id}`
**Request**
```json
{
  "score": 8.0
}
```
**Response**
```json
{
  "id": 1,
  "studentId": 1,
  "subjectId": 2,
  "score": 8.0
}
```

### DELETE `/assessments/{id}`
**Response**: 204 No Content

### POST `/attendance`
**Request**
```json
{
  "studentId": 1,
  "date": "2024-02-01",
  "status": "PRESENT"
}
```
**Response**
```json
{
  "id": 1,
  "studentId": 1,
  "date": "2024-02-01",
  "status": "PRESENT"
}
```

### GET `/attendance`
**Response**
```json
[
  {
    "id": 1,
    "studentId": 1,
    "date": "2024-02-01",
    "status": "PRESENT"
  }
]
```

### PUT `/attendance/{id}`
**Request**
```json
{
  "status": "ABSENT"
}
```
**Response**
```json
{
  "id": 1,
  "studentId": 1,
  "date": "2024-02-01",
  "status": "ABSENT"
}
```

### DELETE `/attendance/{id}`
**Response**: 204 No Content

### POST `/behavior-reports`
**Request**
```json
{
  "studentId": 1,
  "description": "Llegó tarde"
}
```
**Response**
```json
{
  "id": 1,
  "studentId": 1,
  "description": "Llegó tarde"
}
```

### GET `/behavior-reports`
**Response**
```json
[
  {
    "id": 1,
    "studentId": 1,
    "description": "Llegó tarde"
  }
]
```

### PUT `/behavior-reports/{id}`
**Request**
```json
{
  "description": "Interrupciones en clase"
}
```
**Response**
```json
{
  "id": 1,
  "studentId": 1,
  "description": "Interrupciones en clase"
}
```

### DELETE `/behavior-reports/{id}`
**Response**: 204 No Content

### POST `/meetings`
**Request**
```json
{
  "topic": "Reunión de padres",
  "date": "2024-02-10"
}
```
**Response**
```json
{
  "id": 1,
  "topic": "Reunión de padres",
  "date": "2024-02-10"
}
```

### GET `/meetings`
**Response**
```json
[
  {
    "id": 1,
    "topic": "Reunión de padres",
    "date": "2024-02-10"
  }
]
```

### PUT `/meetings/{id}`
**Request**
```json
{
  "topic": "Reunión extraordinaria"
}
```
**Response**
```json
{
  "id": 1,
  "topic": "Reunión extraordinaria",
  "date": "2024-02-10"
}
```

### DELETE `/meetings/{id}`
**Response**: 204 No Content

### POST `/enrollments`
**Request**
```json
{
  "studentId": 1,
  "courseId": "10A"
}
```
**Response**
```json
{
  "id": 1,
  "studentId": 1,
  "courseId": "10A"
}
```

### GET `/enrollments`
**Response**
```json
[
  {
    "id": 1,
    "studentId": 1,
    "courseId": "10A"
  }
]
```

### PUT `/enrollments/{id}`
**Request**
```json
{
  "courseId": "9B"
}
```
**Response**
```json
{
  "id": 1,
  "studentId": 1,
  "courseId": "9B"
}
```

### DELETE `/enrollments/{id}`
**Response**: 204 No Content

### POST `/grading/final?studentId=&subjectId=&schoolYearId=`
**Response**
```json
{
  "studentId": 1,
  "subjectId": 2,
  "finalScore": 8.5
}
```

### GET `/academic-history/{studentId}`
**Response**
```json
{
  "studentId": 123,
  "records": [
    {
      "academicYearId": "2023",
      "course": "9A",
      "finalAverage": 8.4,
      "status": "PROMOTED"
    }
  ]
}
```

---

> Todas las respuestas son en JSON salvo las operaciones de exportación (`/exports`) que entregan archivos binarios.
