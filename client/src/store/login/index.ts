import axios, {AxiosResponse} from 'axios';
import {LoginState, UserDetails} from '@/store/login/types';
import {ActionTree, GetterTree, Module, MutationTree} from 'vuex';
import {RootState} from '@/store/types';

const state: LoginState = {
    loggedIn: false,
    username: '',
    authenticationToken: '',
    role: '',
};

const getters: GetterTree<LoginState, RootState> = {
    'login/LOGGED_IN': (state) => {
        return state.loggedIn;
    },
    'login/USER_ROLE': (state) => {
        return state.role;
    },
};

const mutations: MutationTree<LoginState> = {
    LOG_IN: (state, response: AxiosResponse) => {

        state.loggedIn = true;
        const authToken = response.config.headers.Authorization;
        const role: string = response.data.roles[ 0 ];

        state.authenticationToken = authToken;
        axios.defaults.headers.common.Authorization = authToken;
        state.role = role.replace('ROLE_', '').toLowerCase();
        state.username = response.data.name;
    },
    LOG_OUT: (state) => {
        state.authenticationToken = '';
        axios.defaults.headers.common.Authorization = '';
        state.loggedIn = false;
        state.username = '';
        state.role = 'anonymous';
    },
};

const actions: ActionTree<LoginState, RootState> = {
    'login/LOG_IN_ACTION': (context, payload: UserDetails) => {
        return new Promise((resolve, reject) => {
            axios.get('/auth/login', {
                auth: {username: payload.username, password: payload.password},
            }).then((response) => {
                context.commit('LOG_IN', response);
            }).then(() => {
                context.dispatch('student/GET_STORED_STUDENT')
                    .then(() => {
                        resolve();
                    })
                    .catch(reason => {
                        reject(reason);
                    });
            }).catch(() => {
                context.commit('LOG_OUT');
                reject('Invalid credentials');
            });
        });
    },

    'login/LOGOUT_ACTION': (context) => {
        return new Promise((resolve) => {
            context.dispatch('course/RESET_COURSE');
            context.dispatch('program/RESET_PROGRAM');
            context.dispatch('register/RESET');
            context.dispatch('student/RESET_STUDENT');
            context.dispatch('lecturer/RESET_LECTURER');
            context.commit('LOG_OUT');
            resolve();
        });
    },
};

export const loginStore: Module<LoginState, RootState> = {
    state,
    getters,
    mutations,
    actions,
};
