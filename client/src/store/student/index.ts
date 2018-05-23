///<reference path="../../model/Program.ts"/>
import Student from '../../model/Student';
import {StudentState} from '@/store/student/types';
import {ActionTree, GetterTree, Module, MutationTree} from 'vuex';
import {RootState} from '@/store/types';
import axios, {AxiosResponse} from 'axios';
import Program from '@/model/Program';
import Course from '@/model/Course';

const state: StudentState = {
    student: new Student(),
    registeredCourses: [],
    unregisteredCourses: [],
};

const getters: GetterTree<StudentState, RootState> = {
    'student/GET_STUDENT': (state) => {
        return state.student;
    },
    'student/GET_REGISTERED_COURSES': (state) => {
        return state.registeredCourses;
    },
    'student/GET_UNREGISTERED_COURSES': (state) => {
        return state.unregisteredCourses;
    },
};

const mutations: MutationTree<StudentState> = {
    SET_STUDENT: (state, student: Student) => {
        state.student = student;
    },
    RESET_STUDENT: (state) => {
        state.student = new Student();
        state.registeredCourses = [];
        state.unregisteredCourses = [];
    },
    SET_COURSES: (state, payload: Course[]) => {
        state.student.courses = [];
        state.registeredCourses = [];
        payload.forEach((value, index) => {
            state.student.courses.push(value);
            state.registeredCourses.push(value);
        });
    },
    UPDATE_UNREGISTERED_COURSES: (state) => {
        if ( state.student.program === null ) {
            state.student.program = new Program();
        }

        state.unregisteredCourses = state.student.program.courses.filter((value, index) => {

            let idx = state.student.courses.findIndex((value1, index1) => {
                return value1.id === value.id;
            });
            return idx === -1;
        });
        state.registeredCourses = state.student.courses;
    }
};

const actions: ActionTree<StudentState, RootState> = {
    'student/SET_STUDENT': (context, student: Student) => {
        context.commit('SET_STUDENT', student);
    },
    'student/GET_STORED_STUDENT': (context) => {
        return new Promise((resolve, reject) => {
            axios.get('/api/v1/student')
                .then((response: AxiosResponse) => {
                    if ( response.data !== null ) {
                        let returnedData: Student = response.data;
                        context.commit('SET_STUDENT', returnedData);
                        context.commit('UPDATE_UNREGISTERED_COURSES');
                        resolve(returnedData);
                    }
                }).catch((response) => {
                reject(response);
            });
        });
    },
    'student/ADD_PROGRAM': (context, payload: Program) => {
        return new Promise((resolve, reject) => {
            let studentId = context.getters[ 'student/GET_STUDENT' ].id;

            axios({
                method: 'post',
                url: '/api/v1/student/' + studentId + '/program',
                data: payload,
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then((response) => {
                let newStudent: Student = response.data;
                context.commit('SET_STUDENT', newStudent);
                context.commit('UPDATE_UNREGISTERED_COURSES');
                resolve(newStudent);
            }).catch(reason => {
                reject(reason);
            });
        });
    },
    'student/ADD_COURSES': (context, payload: Course[]) => {

        return new Promise((resolve, reject) => {
            let studentId = context.getters[ 'student/GET_STUDENT' ].id;
            axios({
                method: 'post',
                url: '/api/v1/student/' + studentId + '/courses',
                data: payload,
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then((response) => {
                    let newCourses: Course[] = response.data;
                    context.commit('SET_COURSES', newCourses);
                    resolve(newCourses);
                })
                .then(() => context.commit('UPDATE_UNREGISTERED_COURSES'))
                .catch(reason => {
                    reject(reason);
                });
        });
    },
    'student/REMOVE_COURSE': (context, payload: string) => {
        return new Promise((resolve, reject) => {
            let studentId = context.getters[ 'student/GET_STUDENT' ].id;
            axios({
                method: 'delete',
                url: '/api/v1/student/' + studentId + '/courses/'+ payload,
                data: payload,
            }).then((response) => {
                    let newCourses: Course[] = response.data;
                    context.commit('SET_COURSES', newCourses);
                    resolve(newCourses);
                })
                .then(() => context.commit('UPDATE_UNREGISTERED_COURSES'))
                .then(() => context.commit('REMOVE_COURSE', payload))
                .catch(reason => {
                    reject(reason);
                });
        });
    },
    'student/RESET_STUDENT': (context) => {
        context.commit('RESET_STUDENT');
    },
};

export const studentStore: Module<StudentState, RootState> = {
    state,
    getters,
    mutations,
    actions,
};
