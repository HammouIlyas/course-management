import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/service/auth.service';
import { Subscription } from 'rxjs';
import { Course } from 'src/app/model/course';
import { StudentService } from 'src/app/service/studentService/student.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';
  courseid: number = 0;
  private routeSub?: Subscription;

  user: User = new User(0, '', '');

  constructor(
    private authService: AuthService,
    private route: Router,
    private route2: ActivatedRoute,
    private studentService: StudentService
  ) {}

  ngOnInit(): void {
    this.username = '';
    this.password = '';

    this.routeSub = this.route2.params.subscribe((params) => {
      this.courseid = params['idc'];
      console.log(this.courseid);
    });
  }

  login() {
    this.user.username = this.username;
    this.user.password = this.password;
    //var paramId = params.get('id');

    this.authService.login(this.user).subscribe(
      (res) => {
        if (res == null) {
          alert('Uername or password is wrong');
          this.ngOnInit();
        } else {
          alert('Login successful');
          localStorage.setItem('token', res.accessToken);
          localStorage.setItem('userId', res.id);
          localStorage.setItem('full-name', res.fullName);
          console.log(res);
          console.log(res.roles[0]);
          if (res.roles[0] === 'ROLE_ADMIN') {
            console.log('admin');
            this.route.navigate(['admin/dashboard']);
          }
          if (res.roles[0] === 'ROLE_STUDENT') {
            //code to add here
            console.log('student');
            if (this.courseid != 0) {
              this.enrollCourse(this.courseid, res.id);
            }
            this.route.navigate(['student/dashboard']);
          }
          if (res.roles[0] === 'ROLE_TEACHER') {
            console.log('teacher');
            this.route.navigate(['teacher/dashboard']);
          }
        }
      },
      (err) => {
        alert('Login failed');
        this.ngOnInit();
      }
    );
  }

  enrollCourse(id: number, idStudent: number) {
    let course = new Course();
    course.id = id;
    this.studentService.enrollCourse(idStudent, course).subscribe(
      (res) => {
        console.log('happy coding');
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
