import { Course } from './course';

export class Enrollment {
  id: number = 0;
  course?: Course;
  enrollmentDate?: Date;

  constructor(id: number, course: Course, enrollmentDate: Date) {
    this.id = id;
    this.course = course;
    this.enrollmentDate = enrollmentDate;
  }
}
