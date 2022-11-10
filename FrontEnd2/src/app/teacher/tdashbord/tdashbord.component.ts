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
  courseId = 0;
  courseName: string = '';
  courseDescription: string = '';
  openDate: string = '';
  closeDate: string = '';
  coursesByTeacher: Course[] = [];
  courseToDelete: number = 0;
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
    this.courseToDelete = id;
    this.courseService.deleteCourse(id).subscribe(
      (res) => {
        this.coursesByTeacher = this.coursesByTeacher.filter(
          (course) => course.id != id
        );
      },
      (err) => {
        console.log(err);
        this.modalService.open('modal4');
      }
    );
  }
  forcedeleteCourse() {
    this.courseService.forcedeleteCourse(this.courseToDelete).subscribe(
      (res) => {
        this.coursesByTeacher = this.coursesByTeacher.filter(
          (course) => course.id != this.courseToDelete
        );
      },
      (err) => {
        console.log(err);
      }
    );
    this.closeModal('modal4');
  }

  openModal(id: string, idCourse: number) {
    this.cleanFields();
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
    if (
      course.nom == '' ||
      course.description == '' ||
      course.openDate == '' ||
      course.closeDate == ''
    ) {
      alert('Please fill the fields');
    } else {
      let opdate = new Date(course.openDate);
      let cldate = new Date(course.closeDate);
      if (opdate > cldate) {
        alert('close date must be greater than open date');
      } else {
        console.log(course.openDate);
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
    }
  }

  showCourseWithDetails(id: string, courseId: number) {
    this.course = this.coursesByTeacher.filter(
      (course) => course.id == courseId
    )[0];
    this.courseName = this.course.nom;
    this.courseId = courseId;
    this.courseDescription = this.course.description;
    this.openDate = this.course.openDate;
    this.closeDate = this.course.closeDate;
    this.modalService.open(id);
  }

  editCourse(modalId: string): void {
    let course: Course = new Course();
    course.id = this.courseId;
    course.nom = this.courseName;
    course.description = this.courseDescription;
    course.openDate = this.openDate;
    course.closeDate = this.closeDate;
    this.courseService.updateCourse(course).subscribe(
      (res) => {
        console.log(res);
        let index = 0;
        index = this.coursesByTeacher.findIndex((course) => {
          return course.id == Number(res.id);
        });
        this.coursesByTeacher[index] = res;
        console.log(index);
      },
      (err) => {
        console.log(err);
      }
    );
    this.closeModal(modalId);
  }

  cleanFields() {
    //this.courseId = 0;
    this.courseName = '';
    this.courseDescription = '';
    this.openDate = '';
    this.closeDate = '';
  }
}
