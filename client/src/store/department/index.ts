import {ActionTree, GetterTree, Module, MutationTree, StoreOptions} from 'vuex';
import {DepartmentState} from '@/store/department/types';
import {RootState} from '@/store/types';
import Department from '@/model/Department';
import College from '@/model/College';
import axios from 'axios';

const state: DepartmentState = {
    department: new Department('', '', new College()),
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
                    let responseData: Department[] = response.data;
                    context.commit('REPLACE_DEPARTMENT_ARRAY', responseData);
                    responseData.forEach((value, index) => {
                        context.commit('SET_DEPARTMENT_MAP', value);
                    });
                    resolve(responseData);
                }).catch(() => reject('Unable to Fetch Departments'));
        });
    },
};

export const departmentStore: Module<DepartmentState, RootState> = {
    state: state,
    getters: getters,
    mutations: mutations,
    actions: actions
};
