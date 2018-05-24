import Department from '@/model/Department';

export interface DepartmentState {
    departmentMap: Map<string, Department>;
    departmentArray: Department[];
    department: Department;
}