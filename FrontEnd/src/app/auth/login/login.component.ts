import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';

  user: User = new User(0, '', '');

  constructor(private authService: AuthService, private route: Router) {}

  ngOnInit(): void {
    this.username = '';
    this.password = '';
  }

  login() {
    this.user.username = this.username;
    this.user.password = this.password;

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
            console.log('student');
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
}
