import Student from '../model/Student';
import Lecturer from '../model/Lecturer';

export interface RootState {
    activeStudent: Student;
    activeLecturer: Lecturer;
}
