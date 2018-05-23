import Student from '@/model/Student';
import Course from '@/model/Course';

export interface StudentState {
    student: Student;
    registeredCourses: Course[];
    unregisteredCourses: Course[];
}
