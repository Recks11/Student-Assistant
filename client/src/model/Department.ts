import College from '@/model/College';

export default class Department {
    private _id: string;
    private _name: string;
    private _college: College;


    constructor(id: string, name: string, college: College) {
        this._id = id;
        this._name = name;
        this._college = college;
    }

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

    get json(): {} {
        return {
            id : this._id,
            name : this._name,
            college : this._college,
        };

    }
}
