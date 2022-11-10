import { Component, OnInit } from '@angular/core';
import { Enrollment } from 'src/app/model/enrollment';
import { User } from 'src/app/model/user';
import { CourseService } from 'src/app/teacher/course.service';

@Component({
  selector: 'app-dashbord',
  templateUrl: './dashbord.component.html',
  styleUrls: ['./dashbord.component.css'],
})
export class DashbordComponent implements OnInit {
  //employees: Observable<ApiResponse>;
  enrollments: Enrollment[] = [];
  student: User = new User(
    parseInt(localStorage.getItem('userId')!),
    localStorage.getItem('full-name')!,
    localStorage.getItem('token')!
  );
  constructor(private courseService: CourseService) {} //private router: Router //private employeeService: EmployeeService,

  ngOnInit() {
    this.courseService.getEnrollmentsByStudent(this.student.id).subscribe(
      (res) => {
        this.enrollments = res;
        console.log(res);
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
