import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/model/course';
import { User } from 'src/app/model/user';
import { CourseService } from 'src/app/teacher/course.service';

@Component({
  selector: 'app-allcourses',
  templateUrl: './allcourses.component.html',
  styleUrls: ['./allcourses.component.css'],
})
export class AllcoursesComponent implements OnInit {
  Allcourses: Course[] = [];
  student: User = new User(
    parseInt(localStorage.getItem('userId')!),
    localStorage.getItem('full-name')!,
    localStorage.getItem('token')!
  );

  constructor(private courseService: CourseService) {}

  ngOnInit(): void {
    this.courseService.getAllCourses().subscribe(
      (res) => {
        this.Allcourses = res;
        console.log(res);
      },
      (err) => {
        console.log(err);
      }
    );
  }

  enrollCoure(id: number) {
    let course = new Course();
    course.id = id;
    let studentId = localStorage.getItem('userId');
    this.courseService.enrollCourse(Number(studentId)!, course).subscribe(
      (res) => {},
      (err) => {
        console.log(err);
      }
    );
  }
}
