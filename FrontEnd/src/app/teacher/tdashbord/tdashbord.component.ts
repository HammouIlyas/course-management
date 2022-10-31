import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/model/course';
import { User } from 'src/app/model/user';
import { CourseService } from '../course.service';
import { ModalService } from '../modal/modal.service';

@Component({
  selector: 'app-tdashbord',
  templateUrl: './tdashbord.component.html',
  styleUrls: ['./tdashbord.component.css'],
})
export class TdashbordComponent implements OnInit {
  courseName: string = '';
  courseDescription: string = '';
  openDate: string = '';
  closeDate: string = '';
  coursesByTeacher: Course[] = [];
  course: Course = new Course();
  teacher: User = new User(
    parseInt(localStorage.getItem('userId')!),
    localStorage.getItem('full-name')!,
    localStorage.getItem('token')!
  );

  constructor(
    private courseService: CourseService,
    private modalService: ModalService
  ) {}

  ngOnInit(): void {
    this.courseService.getCoursesByTeacher(this.teacher.id).subscribe(
      (res) => {
        this.coursesByTeacher = res;
        this.course = this.coursesByTeacher[0];
      },
      (err) => {
        console.log(err);
      }
    );
  }

  deleteCourse(id: number) {
    this.courseService.deleteCourse(id).subscribe(
      (res) => {
        this.coursesByTeacher = this.coursesByTeacher.filter(
          (course) => course.id != id
        );
      },
      (err) => {
        console.log(err);
      }
    );
  }

  openModal(id: string, idCourse: number) {
    this.modalService.open(id);
    this.course = this.coursesByTeacher.filter(
      (course) => course.id == idCourse
    )[0];
  }

  closeModal(id: string) {
    this.modalService.close(id);
  }

  addCourse(id: string) {
    let course = new Course();
    course.nom = this.courseName;
    course.description = this.courseDescription;
    course.openDate = this.openDate;
    course.closeDate = this.closeDate;
    course.ownerId = this.teacher.id;
    this.courseService.addCourse(course).subscribe(
      (res) => {
        this.coursesByTeacher.push(res);
        this.closeModal(id);
      },
      (err) => {
        console.log(err);
      }
    );
  }

  editCourse(id: string, courseId: number) {
    this.course = this.coursesByTeacher.filter(
      (course) => course.id == courseId
    )[0];
    this.courseName = this.course.nom;
    this.courseDescription = this.course.description;
    this.openDate = this.course.openDate;
    this.closeDate = this.course.closeDate;
    this.modalService.open(id);
  }
}
