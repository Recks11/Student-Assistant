export default class College {
    private _id: string = '';
    private _name: string = '';

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

    get json(): {} {
        return {
            id: this._id,
            name: this._name,
        };
    }
}
