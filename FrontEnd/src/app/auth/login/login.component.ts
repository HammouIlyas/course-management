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

  user: User = new User();

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
          localStorage.setItem('token', res.token);
        }
      },
      (err) => {
        alert('Login failed');
        this.ngOnInit();
      }
    );
  }
}
