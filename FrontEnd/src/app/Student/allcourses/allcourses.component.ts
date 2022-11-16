import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/model/course';
import { Enrollment } from 'src/app/model/enrollment';
import { User } from 'src/app/model/user';
import { StudentService } from 'src/app/service/studentService/student.service';
import { ModalService } from 'src/app/teacher/modal/modal.service';

@Component({
  selector: 'app-allcourses',
  templateUrl: './allcourses.component.html',
  styleUrls: ['./allcourses.component.css'],
})
export class AllcoursesComponent implements OnInit {
  Allcourses: Course[] = [];
  enrolledCourses: number[] = [];

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
    private studentService: StudentService,
    private modalService: ModalService
  ) {}

  ngOnInit(): void {
    this.studentService.getAllCoursesForStudent().subscribe(
      (res) => {
        this.Allcourses = res;
        console.log(res);
      },
      (err) => {
        console.log(err);
      }
    );

    this.studentService.getEnrollmentsByStudent(this.student.id).subscribe(
      (res) => {
        res.forEach((element: Enrollment) => {
          this.enrolledCourses.push(element.course!.id);
        });
        console.log(this.enrolledCourses);
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
    this.studentService.enrollCourse(Number(studentId)!, course).subscribe(
      (res) => {
        this.enrolledCourses.push(id);
      },
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

  checkCourse(id: number) {
    return this.enrolledCourses.includes(id);
  }
}
