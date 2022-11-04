import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course';
import { CourseService } from '../teacher/course.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css'],
})
export class LandingPageComponent implements OnInit {
  Allcourses: Course[] = [];
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
}
