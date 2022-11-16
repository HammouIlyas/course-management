import { Component, OnInit } from '@angular/core';
import { Enrollment } from 'src/app/model/enrollment';
import { User } from 'src/app/model/user';
import { StudentService } from 'src/app/service/studentService/student.service';

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
  constructor(private studentService: StudentService) {} //private router: Router //private employeeService: EmployeeService,

  ngOnInit() {
    this.studentService.getEnrollmentsByStudent(this.student.id).subscribe(
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
