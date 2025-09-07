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

> Todas las respuestas son en JSON salvo las operaciones de exportación (`/exports`) que entregan archivos binarios.
