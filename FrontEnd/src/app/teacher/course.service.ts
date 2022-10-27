import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  constructor(private http: HttpClient) {}

  getCoursesByTeacher(id: number): Observable<any> {
    return this.http.get<any>('http://localhost:8082/courses/list/' + id);
  }
}
