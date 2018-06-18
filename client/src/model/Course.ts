import Lecturer from '@/model/Lecturer';
import Student from '@/model/Student';

export default class Course {

    private _id: string = '';
    private _units: number = 0;
    private _level: number = 0;
    private _title: string = '';
    private _compulsory: boolean = false;
    private _password: string = '';
    private _semester: string = '';
    private _lecturers: Lecturer[] = [];
    private _students: Student[] = [];


    get id(): string {
        return this._id;
    }

    set id(value: string) {
        this._id = value;
    }

    get units(): number {
        return this._units;
    }

    set units(value: number) {
        this._units = value;
    }


    get level(): number {
        return this._level;
    }

    set level(value: number) {
        this._level = value;
    }

    get title(): string {
        return this._title;
    }

    set title(value: string) {
        this._title = value;
    }

    get compulsory(): boolean {
        return this._compulsory;
    }

    set compulsory(value: boolean) {
        this._compulsory = value;
    }

    get password(): string {
        return this._password;
    }

    set password(value: string) {
        this._password = value;
    }

    get semester(): string {
        return this._semester;
    }

    set semester(value: string) {
        this._semester = value;
    }

    get lecturers(): Lecturer[] {
        return this._lecturers;
    }

    set lecturers(value: Lecturer[]) {
        this._lecturers = value;
    }

    get students(): Student[] {
        return this._students;
    }

    set students(value: Student[]) {
        this._students = value;
    }

    public addLecturer(lecturer: Lecturer): void {
        this.lecturers.push(lecturer);
    }

    public removeLecturer(id: string): Lecturer[] {
        const idx = this.lecturers.findIndex((lecturer) => lecturer.id === id);
        this.lecturers.splice(idx, 1);
        return this._lecturers;
    }

    public addStudent(student: Student): void {
        this._students.push(student);
    }

    public removeStudent(id: string): Student[] {
        const idx = this.students.findIndex((student) => student.id === id);
        this.lecturers.splice(idx, 1);
        return this._students;
    }

    get json(): {} {
        return {
            id: this.id,
            units: this.units,
            level: this.level,
            title: this.title,
            compulsory: this.compulsory,
            password: this.password,
            semester: this.semester,
            lecturers: this.lecturers,
            students: this.students,
        };
    }
}
