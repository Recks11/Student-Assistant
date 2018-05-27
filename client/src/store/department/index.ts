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
    GET_DEPARTMENT: (state) => {
        return state.department;
    },
    GET_DEPARTMENT_ARRAY: (state) => {
        return state.departmentArray;
    },
    GET_DEPARTMENT_MAP: (state) => {
        return state.departmentMap;
    },

};

const mutations: MutationTree<DepartmentState> = {
    'department/SET_DEPARTMENT': (state, payload: Department) => {
        return state.department = payload;
    },
    REPLACE_DEPARTMENT_ARRAY: (state, payload: Department[]) => {
        return state.departmentArray = payload;
    },
    SET_DEPARTMENT_MAP: (state, payload: Department) => {
        state.departmentMap.set(payload.id, payload);
        return state.departmentMap;
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
                    if(idx !== -1){
                        context.state.departmentArray[idx].Programs = response.data;
                    }
                    else {
                        context.state.departmentArray.push(dept);
                    }
                    resolve(response.data)
                })
                .catch((reason) => reject(reason))
        })
    },
    'department/RESET_DEPARTMENT': (context) => {
        context.state.department = new Department();
        context.state.departmentMap = new Map();
        context.state.departmentArray = [];
    }
};

export const departmentStore: Module<DepartmentState, RootState> = {
    state: state,
    getters: getters,
    mutations: mutations,
    actions: actions
};
