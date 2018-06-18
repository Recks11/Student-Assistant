import {ActionTree, GetterTree, Module, MutationTree, StoreOptions} from 'vuex';
import {DepartmentState} from '@/store/department/types';
import {RootState} from '@/store/types';
import Department from '@/model/Department';
import axios from 'axios';

const state: DepartmentState = {
    department: new Department(),
    departmentArray: [],
    departmentMap: new Map<string, Department>(),
};

const getters: GetterTree<DepartmentState, RootState> = {
    GET_DEPARTMENT: (departmentState) => {
        return departmentState.department;
    },
    GET_DEPARTMENT_ARRAY: (departmentState) => {
        return departmentState.departmentArray;
    },
    GET_DEPARTMENT_MAP: (departmentState) => {
        return departmentState.departmentMap;
    },

};

const mutations: MutationTree<DepartmentState> = {
    'department/SET_DEPARTMENT': (departmentState, payload: Department) => {
        return departmentState.department = payload;
    },
    'REPLACE_DEPARTMENT_ARRAY': (departmentState, payload: Department[]) => {
        return departmentState.departmentArray = payload;
    },
    'SET_DEPARTMENT_MAP': (departmentState, payload: Department) => {
        departmentState.departmentMap.set(payload.id, payload);
        return departmentState.departmentMap;
    },
};

const actions: ActionTree<DepartmentState, RootState> = {
    'department/GET_STORED_DEPARTMENTS': (context) => {
        return new Promise((resolve, reject) => {
            axios.get('/api/v1/department')
                .then((response) => {
                    const responseData: Department[] = response.data;
                    context.commit('REPLACE_DEPARTMENT_ARRAY', responseData);
                    responseData.forEach((value, index) => {
                        context.commit('SET_DEPARTMENT_MAP', value);
                    });
                    resolve(responseData);
                }).catch(() => reject('Unable to Fetch Departments'));
        });
    },
    'department/GET_COURSES_FOR_DEPARTMENT': (context, payload: Department) => {
        return new Promise((resolve, reject) => {
            const id = payload.id;
            const dept = payload;
            axios.get('/api/v1/department/' + id + '/programs')
                .then((response) => {

                    dept.Programs = response.data;
                    context.state.department = dept;
                    context.commit('SET_DEPARTMENT_MAP', context.state.department);
                    const idx = context.state.departmentArray.findIndex((value) => value.id === id);
                    if (idx !== -1) {
                        context.state.departmentArray[idx].Programs = response.data;
                    } else {
                        context.state.departmentArray.push(dept);
                    }
                    resolve(response.data);
                })
                .catch((reason) => reject(reason));
        });
    },
    'department/RESET_DEPARTMENT': (context) => {
        context.state.department = new Department();
        context.state.departmentMap = new Map();
        context.state.departmentArray = [];
    },
};

export const departmentStore: Module<DepartmentState, RootState> = {
    state,
    getters,
    mutations,
    actions,
};
