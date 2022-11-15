import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from '../model/course';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  constructor(private http: HttpClient) {}
  getAllCourses(): Observable<any> {
    return this.http.get<any>('http://localhost:8082/courses/page');
  }

  /*updateCourse(course: Course): Observable<any> {
    return this.http.put<any>(
      'http://localhost:8082/courses/updatecourse/' + course.id,
      course
    );
  }

  addCourse(course: Course) {
    return this.http.post<any>(
      'http://localhost:8082/courses/addcourse/' + course.ownerId,
      course
    );
  }

  getCoursesByTeacher(id: number): Observable<any> {
    return this.http.get<any>('http://localhost:8082/courses/list/' + id);
  }*/

  getAllCoursesForStudent(): Observable<any> {
    return this.http.get<any>('http://localhost:8082/courses/all');
  }

  getEnrollmentsByStudent(id: number): Observable<any> {
    return this.http.get<any>(
      'http://localhost:8082/courses/enrollments/' + id
    );
  }
  /*
  deleteCourse(id: number): Observable<any> {
    return this.http.delete<any>('http://localhost:8082/courses/delete/' + id);
  }

  forcedeleteCourse(id: number): Observable<any> {
    console.log(id);
    return this.http.delete<any>(
      'http://localhost:8082/courses/deleteforced/' + id
    );
  }*/

  enrollCourse(id: number, course: Course): Observable<any> {
    return this.http.post<any>(
      'http://localhost:8082/courses/enrollcourse/' + id,
      course
    );
  }

  /*
  deleteStudent(id: number): Observable<any> {
    return this.http.delete<any>(
      'http://localhost:8082/courses/deletestudent/' + id
    );
  }
  deleteTeacher(id: number): Observable<any> {
    return this.http.delete<any>(
      'http://localhost:8082/courses/deleteteacher/' + id
    );
  }*/

  /*
  getAllTeachers(): Observable<any> {
    return this.http.get<any>('http://localhost:8082/courses/teachers');
  }

  getAllStudents(): Observable<any> {
    return this.http.get<any>('http://localhost:8082/courses/students');
  }*/
}
