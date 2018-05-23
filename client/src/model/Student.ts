import Course from '@/model/Course';
import Program from '@/model/Program';

export default class Student {
    private _id: string = '';
    private _firstName: string = '';
    private _lastName: string = '';
    private _email: string = '';
    private _username: string = '';
    private _password?: string = '';
    private _hall: string = '';
    private _roomNumber: string = '';
    private _program: Program = new Program();
    private _courses: Course[] = [];


    get id(): string {
        return this._id;
    }

    set id(value: string) {
        this._id = value;
    }

    get firstName(): string {
        return this._firstName;
    }

    set firstName(value: string) {
        this._firstName = value;
    }

    get lastName(): string {
        return this._lastName;
    }

    set lastName(value: string) {
        this._lastName = value;
    }

    get email(): string {
        return this._email;
    }

    set email(value: string) {
        this._email = value;
    }

    get username(): string {
        return this._username;
    }

    set username(value: string) {
        this._username = value;
    }

    get password(): string | undefined {
        return this._password;
    }

    set password(value: string | undefined) {
        this._password = value;
    }

    get hall(): string {
        return this._hall;
    }

    set hall(value: string) {
        this._hall = value;
    }

    get roomNumber(): string {
        return this._roomNumber;
    }

    set roomNumber(value: string) {
        this._roomNumber = value;
    }

    get program(): Program {
        return this._program;
    }

    set program(value: Program) {
        this._program = value;
    }

    get courses(): Course[] {
        return this._courses;
    }

    set courses(value: Course[]) {
        this._courses = value;
    }

    public addCourse(course: Course): Course[] {
        this.courses.push(course);
        return this.courses;
    }

    public removeCourse(courseId: string): Course[] {
        const id = this.courses.findIndex((value) => value.id === courseId);
        this.courses.splice(id, 1);
        return this.courses;
    }

    get json(): {} {
        return {
            firstName: this._id,
            lastName: this.lastName,
            email: this.email,
            username: this.username,
            password: this.password,
            hall: this.hall,
            roomNumber: this.roomNumber,
            program: this.program,
            courses: this.courses,
        };
    }
}
