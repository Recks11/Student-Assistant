import Department from '@/model/Department';
import Course from '@/model/Course';
import College from '@/model/College';

export default class Program {
    private _id: string = '';

    private _name: string = '';

    private _department: Department = new Department('', '', new College());

    private _courses: Course[] = [];


    get id(): string {
        return this._id;
    }

    set id(value: string) {
        this._id = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get department(): Department {
        return this._department;
    }

    set department(value: Department) {
        this._department = value;
    }

    get courses(): Course[] {
        return this._courses;
    }

    set courses(value: Course[]) {
        this._courses = value;
    }

    get json(): {} {
        return {
            id: this._id,
            name: this._name,
            department: this._department,
            courses: this._courses,
        };

    }
}
