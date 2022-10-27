import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/model/course';
import { User } from 'src/app/model/user';
import { CourseService } from '../course.service';

@Component({
  selector: 'app-tdashbord',
  templateUrl: './tdashbord.component.html',
  styleUrls: ['./tdashbord.component.css'],
})
export class TdashbordComponent implements OnInit {
  coursesByTeacher: Course[] = [];
  teacher: User = new User(
    parseInt(localStorage.getItem('userId')!),
    localStorage.getItem('full-name')!,
    localStorage.getItem('token')!
  );
  constructor(private courseService: CourseService) {} //private router: Router //private employeeService: EmployeeService,

  ngOnInit(): void {
    console.log(this.teacher);
    this.courseService.getCoursesByTeacher(2).subscribe(
      (res) => {
        this.coursesByTeacher = res;
        console.log(res);
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
