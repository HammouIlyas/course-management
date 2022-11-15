import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from 'src/app/model/course';

@Injectable({
  providedIn: 'root',
})
export class TeacherService {
  readonly url = 'http://localhost:8082/teacher/';
  auth_token = localStorage.getItem('token');
  headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${this.auth_token}`,
  });

  constructor(private http: HttpClient) {}

  updateCourse(course: Course): Observable<any> {
    return this.http.put<any>(this.url + 'updatecourse/' + course.id, course, {
      headers: this.headers,
    });
  }

  addCourse(course: Course) {
    return this.http.post<any>(
      this.url + 'addcourse/' + course.ownerId,
      course,
      { headers: this.headers }
    );
  }

  getCoursesByTeacher(id: number): Observable<any> {
    return this.http.get<any>(this.url + 'list/' + id, {
      headers: this.headers,
    });
  }

  deleteCourse(id: number): Observable<any> {
    return this.http.delete<any>(this.url + 'delete/' + id, {
      headers: this.headers,
    });
  }

  forcedeleteCourse(id: number): Observable<any> {
    console.log(id);
    return this.http.delete<any>(this.url + 'deleteforced/' + id, {
      headers: this.headers,
    });
  }
}
