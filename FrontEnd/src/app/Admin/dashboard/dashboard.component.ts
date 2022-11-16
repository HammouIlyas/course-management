import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  Teachers: User[] = [];
  Students: User[] = [];
  teacher: User = new User(
    parseInt(localStorage.getItem('userId')!),
    localStorage.getItem('full-name')!,
    localStorage.getItem('token')!
  );
  constructor(private adminService: AdminService) {} //private router: Router //private employeeService: EmployeeService,

  ngOnInit(): void {
    console.log(this.teacher);
    this.adminService.getAllTeachers().subscribe(
      (res) => {
        this.Teachers = res;
        console.log(res);
      },
      (err) => {
        console.log(err);
      }
    );

    this.adminService.getAllStudents().subscribe(
      (res) => {
        this.Students = res;
        console.log(res);
      },
      (err) => {
        console.log(err);
      }
    );
  }

  deleteStudent(id: number) {
    this.adminService.deleteStudent(id).subscribe(
      (res) => {
        console.log(res);
        this.ngOnInit();
      },
      (err) => {
        console.log(err);
      }
    );
  }
  deleteteacher(id: number) {
    this.adminService.deleteTeacher(id).subscribe(
      (res) => {
        console.log(res);
        this.ngOnInit();
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
