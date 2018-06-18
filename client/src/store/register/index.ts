import axios from 'axios';
import Student from '../../model/Student';
import Lecturer from '../../model/Lecturer';
import {RegisterState} from '@/store/register/types';
import {ActionTree, GetterTree, Module, MutationTree} from 'vuex';
import {RootState} from '@/store/types';

const state: RegisterState = {
    successful: false,
    error: '',
};

const getters: GetterTree<RegisterState, RootState> = {
    REGISTERED: (registerState) => {
        return registerState.successful;
    },
    REGISTER_ERROR: (registerState) => {
        return registerState.error;
    },
};

const mutations: MutationTree<RegisterState> = {
    REGISTRATION_SUCCESS: (registerState) => {
        registerState.successful = true;
    },
    REGISTRATION_FAILURE: (registerState) => {
        registerState.successful = false;
        registerState.error = 'Unable to register User at this time';
    },
    REGISTER_RESET_STATE: (registerState) => {
        registerState.successful = false;
        registerState.error = '';
    },
};

const actions: ActionTree<RegisterState, RootState> = {
    'REGISTER_STUDENT': (context, student: Student) => {
        return new Promise((resolve, reject) => {
            axios.post('/auth/register/student', student.json)
                .then(() => {
                    context.commit('REGISTRATION_SUCCESS');
                    resolve();
                })
                .catch((reason) => {
                    context.commit('REGISTRATION_FAILURE');
                    context.state.error = 'Cannot Register User at this time';
                    reject(reason);
                });
        });
    },
    'REGISTER_LECTURER': (context, lecturer: Lecturer) => {
        return new Promise((resolve, reject) => {
            axios.post('/auth/register/lecturer', lecturer.json)
                .then(() => {
                    context.commit('REGISTRATION_SUCCESS');
                    resolve();
                })
                .catch((reason) => {
                    context.commit('REGISTRATION_FAILURE');
                    reject(reason);
                });
        });
    },
    'register/RESET': (context) => {
        context.commit('REGISTER_RESET_STATE');
    },
};

export const registerStore: Module<RegisterState, RootState> = {
    state,
    getters,
    mutations,
    actions,
};
