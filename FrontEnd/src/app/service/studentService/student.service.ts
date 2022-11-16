import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from 'src/app/model/course';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  readonly url = 'http://localhost:8082/student/';
  auth_token = localStorage.getItem('token');
  headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${this.auth_token}`,
  });

  constructor(private http: HttpClient) {}

  getEnrollmentsByStudent(id: number): Observable<any> {
    return this.http.get<any>(this.url + 'enrollments/' + id, {
      headers: this.headers,
    });
  }

  enrollCourse(id: number, course: Course): Observable<any> {
    return this.http.post<any>(this.url + 'enrollcourse/' + id, course, {
      headers: this.headers,
    });
  }

  getAllCoursesForStudent(): Observable<any> {
    return this.http.get<any>(this.url + 'courseslist', {
      headers: this.headers,
    });
  }
}
