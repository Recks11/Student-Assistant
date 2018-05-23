import Department from '@/model/Department';
import Course from '@/model/Course';

export default class Lecturer {
    private _id: string = '';
    private _firstName: string = '';
    private _lastName: string = '';
    private _email: string = '';
    private _office: string = '';
    private _inOffice: boolean = false;
    private _phoneNumbers: string[] = [];
    private _username: string = '';
    private _password: string = '';
    private _departments: Department[] = [];
    private _courses: Course[] = [];

    constructor() {
    }

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

    get office(): string {
        return this._office;
    }

    set office(value: string) {
        this._office = value;
    }

    get inOffice(): boolean {
        return this._inOffice;
    }

    set inOffice(value: boolean) {
        this._inOffice = value;
    }

    get phoneNumbers(): string[] {
        return this._phoneNumbers;
    }

    set phoneNumbers(value: string[]) {
        this._phoneNumbers = value;
    }

    get username(): string {
        return this._username;
    }

    set username(value: string) {
        this._username = value;
    }


    get password(): string {
        return this._password;
    }

    set password(value: string) {
        this._password = value;
    }

    get departments(): Department[] {
        return this._departments;
    }

    set departments(value: Department[]) {
        this._departments = value;
    }

    get courses(): Course[] {
        return this._courses;
    }

    set courses(value: Course[]) {
        this._courses = value;
    }

    public addDepartment(department: Department): Department[] {
        this.departments.push(department);
        return this.departments;
    }

    public removeDepartment(departmentId: string): Department[] {
        const id = this.courses.findIndex((value) => value.id === departmentId);
        this.departments.splice(id, 1);
        return this.departments;
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
            firstName: this._firstName,
            lastName: this._lastName,
            email: this._email,
            office: this._office,
            inOffice: this._inOffice,
            phoneNumbers: this._phoneNumbers,
            username: this._username,
            password: this._password,
            departments: this._departments,
            courses: this._courses,
        };
    }
}
