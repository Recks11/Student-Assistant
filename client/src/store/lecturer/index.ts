import Lecturer from '../../model/Lecturer';
import {LecturerState} from '@/store/lecturer/types';
import {RootState} from '../types';
import {ActionTree, GetterTree, Module, MutationTree} from 'vuex';
import axios, {AxiosResponse} from 'axios';

const state: LecturerState = {
    lecturer: new Lecturer(),
};

const getters: GetterTree<LecturerState, RootState> = {
    GET_LECTURER: (state) => {
        return state.lecturer;
    },
};

const mutations: MutationTree<LecturerState> = {
    SET_LECTURER: (state, lecturer: Lecturer) => {
        state.lecturer = lecturer;
    },
    RESET_LECTURER_STATE: (state) => {
        state.lecturer = new Lecturer();
    },
};

const actions: ActionTree<LecturerState, RootState> = {
    'lecturer/SET_LECTURER': (context, lecturer: Lecturer) => {
        context.commit('SET_STUDENT');
        context.rootState.activeLecturer = lecturer;
    },
    'lecturer/RESET_LECTURER': (context) => {
        context.commit('RESET_LECTURER_STATE');
    },
    'lecturer/GET_STORED_LECTURER': (context) => {
        return new Promise((resolve, reject) => {
            axios.get('/api/v1/lecturer')
                .then((response: AxiosResponse) => {
                    if ( response.data !== null ) {
                        let returnedData: Lecturer = response.data;
                        context.commit('SET_LECTURER', returnedData);
                        resolve();
                    }
                }).catch((response) => {
                reject(response);
            });
        });
    },
};

export const lecturerStore: Module<LecturerState, RootState> = {
    state,
    getters,
    mutations,
    actions,
};
