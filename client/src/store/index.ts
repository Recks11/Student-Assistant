import Vue from 'vue';
import Vuex, {StoreOptions} from 'vuex';
import {loginStore} from './login';
import {registerStore} from './register';
import {studentStore} from './student';
import {lecturerStore} from './lecturer';
import {ProgramStore} from './program';
import {CourseStore} from './course';
import {RootState} from './types';

import Student from '@/model/Student';
import Lecturer from '@/model/Lecturer';

Vue.use(Vuex);

const store: StoreOptions<RootState> = {
    state: {
        activeStudent: new Student(),
        activeLecturer: new Lecturer(),
    },
    getters: {
        ACTIVE_LECTURER: (state) => {
            return state.activeLecturer;
        },
        ACTIVE_STUDENT: (state) => {
            return state.activeStudent;
        },
    },
    mutations: {
        SET_STUDENT: (state, student: Student) => {
            state.activeStudent = student;
        },
        SET_LECTURER: (state, lecturer: Lecturer) => {
            state.activeLecturer = lecturer;
        },
        RESET_STATE: (state) => {
            state.activeLecturer = new Lecturer();
            state.activeStudent = new Student();
        },
    },
    actions: {
        'action/SET_STUDENT': (context, student: Student) => {
            context.commit('SET_STUDENT');
        },
        'action/SET_LECTURER': (context, lecturer: Lecturer) => {
            context.commit('SET_LECTURER');
        },
        'action/RESET_STATE': (context) => {
            context.commit('RESET_STATE')
        },
    },
    modules: {
        login: loginStore,
        register: registerStore,
        student: studentStore,
        lecturer: lecturerStore,
        program: ProgramStore,
        course: CourseStore,
    },
};

export default new Vuex.Store<RootState>(store);

