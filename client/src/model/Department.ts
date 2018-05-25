import College from '@/model/College';
import Reference from '@/model/Reference';

export default class Department {
    private _id: string = '';
    private _name: string = '';
    private _college: College = new College();
    private _Programs: Reference[] = []

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

    get college(): College {
        return this._college;
    }

    set college(value: College) {
        this._college = value;
    }

    get Programs(): Reference[] {
        return this._Programs;
    }

    set Programs(value: Reference[]) {
        this._Programs = value;
    }

    get json(): {} {
        return {
            id : this._id,
            name : this._name,
            college : this._college,
            programs: this._Programs,
        };

    }
}
