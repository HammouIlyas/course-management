import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { CourseService } from 'src/app/teacher/course.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  Teachers: User[] = [];
  teacher: User = new User(
    parseInt(localStorage.getItem('userId')!),
    localStorage.getItem('full-name')!,
    localStorage.getItem('token')!
  );
  constructor(private courseService: CourseService) {} //private router: Router //private employeeService: EmployeeService,

  ngOnInit(): void {
    console.log(this.teacher);
    this.courseService.getAllTeachers().subscribe(
      (res) => {
        this.Teachers = res;
        console.log(res);
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
