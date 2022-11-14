import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course';
import { CourseService } from '../teacher/course.service';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { ModalService } from '../teacher/modal/modal.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css'],
})
export class LandingPageComponent implements OnInit {
  customOptions: OwlOptions = {
    loop: true,
    mouseDrag: true,
    touchDrag: true,
    pullDrag: true,
    dots: false,
    navSpeed: 700,
    navText: ['<', '>'],
    responsive: {
      0: {
        items: 1,
      },
      400: {
        items: 2,
      },
      740: {
        items: 3,
      },
      940: {
        items: 4,
      },
    },
    nav: true,
  };

  courseId = 0;
  courseName: string = '';
  courseDescription: string = '';
  openDate: string = '';
  closeDate: string = '';
  courseToDelete: number = 0;
  course: Course = new Course();

  Allcourses: Course[] = [];
  constructor(
    private courseService: CourseService,
    private modalService: ModalService,
    private route: Router
  ) {}

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
    console.log(this.Allcourses);
  }

  closeModal(id: string) {
    this.modalService.close(id);
  }

  showCourseWithDetails(id: string, courseId: number) {
    this.course = this.Allcourses.filter((course) => course.id == courseId)[0];
    console.log(courseId);
    this.courseName = this.course.nom;
    this.courseId = courseId;
    this.courseDescription = this.course.description;
    this.openDate = this.course.openDate;
    this.closeDate = this.course.closeDate;
    console.log(this.course);
    console.log('close date :' + this.closeDate);
    console.log('open date :' + this.openDate);
    this.modalService.open(id);
  }

  EnrollCourseLP(id: string) {
    this.closeModal(id);
    this.route.navigate(['login/' + this.courseId]);
  }
}
