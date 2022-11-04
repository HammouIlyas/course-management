import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/model/course';
import { User } from 'src/app/model/user';
import { CourseService } from 'src/app/teacher/course.service';
import { ModalService } from 'src/app/teacher/modal/modal.service';

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
  courseId = 0;
  courseName: string = '';
  courseDescription: string = '';
  openDate: string = '';
  closeDate: string = '';
  coursesByTeacher: Course[] = [];
  course: Course = new Course();

  constructor(
    private courseService: CourseService,
    private modalService: ModalService
  ) {}

  ngOnInit(): void {
    this.courseService.getAllCoursesForStudent().subscribe(
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

  showCourseWithDetails(id: string, courseId: number) {
    this.course = this.Allcourses.filter((course) => course.id == courseId)[0];
    this.courseName = this.course.nom;
    this.courseId = courseId;
    this.courseDescription = this.course.description;
    this.openDate = this.course.openDate;
    this.closeDate = this.course.closeDate;
    this.modalService.open(id);
  }

  closeModal(id: string) {
    this.modalService.close(id);
  }
}
