import Course from '@/model/Course';

export interface CourseState {
    courses: Map<string, Course>;
    course: Course;
}