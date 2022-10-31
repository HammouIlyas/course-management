import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from '../model/course';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  addCourse(course: Course) {
    return this.http.post<any>(
      'http://localhost:8082/courses/addcourse/' + course.ownerId,
      course
    );
  }
  constructor(private http: HttpClient) {}

  getCoursesByTeacher(id: number): Observable<any> {
    return this.http.get<any>('http://localhost:8082/courses/list/' + id);
  }
  getAllCourses(): Observable<any> {
    return this.http.get<any>('http://localhost:8082/courses/all');
  }
  getEnrollmentsByStudent(id: number): Observable<any> {
    return this.http.get<any>(
      'http://localhost:8082/courses/enrollments/' + id
    );
  }

  getAllTeachers(): Observable<any> {
    return this.http.get<any>('http://localhost:8082/courses/teachers');
  }
  deleteCourse(id: number): Observable<any> {
    return this.http.delete<any>('http://localhost:8082/courses/delete/' + id);
  }
}
