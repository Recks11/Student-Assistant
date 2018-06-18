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
    'student/GET_STUDENT': (studentState) => {
        return studentState.student;
    },
    'student/GET_REGISTERED_COURSES': (studentState) => {
        return studentState.registeredCourses;
    },
    'student/GET_UNREGISTERED_COURSES': (studentState) => {
        return studentState.unregisteredCourses;
    },
};

const mutations: MutationTree<StudentState> = {
    SET_STUDENT: (studentState, student: Student) => {
        studentState.student = student;
    },
    RESET_STUDENT: (studentState) => {
        studentState.student = new Student();
        studentState.registeredCourses = [];
        studentState.unregisteredCourses = [];
    },
    SET_COURSES: (studentState, payload: Course[]) => {
        studentState.student.courses = [];
        studentState.registeredCourses = [];
        payload.forEach((value) => {
            studentState.student.courses.push(value);
            studentState.registeredCourses.push(value);
        });
    },
    UPDATE_UNREGISTERED_COURSES: (studentState) => {
        if ( studentState.student.program === null ) {
            studentState.student.program = new Program();
        }

        studentState.unregisteredCourses = studentState.student.program.courses.filter((value, index) => {

            const idx = studentState.student.courses.findIndex((value1, index1) => {
                return value1.id === value.id;
            });
            return idx === -1;
        });
        studentState.registeredCourses = studentState.student.courses;
    },
};

const actions: ActionTree<StudentState, RootState> = {
    'student/SET_STUDENT': (context, student: Student) => {
        context.commit('SET_STUDENT', student);
        context.dispatch('action/SET_STUDENT', student);
    },
    'student/GET_STORED_STUDENT': (context) => {
        return new Promise((resolve, reject) => {
            axios.get('/api/v1/student')
                .then((response: AxiosResponse) => {
                    if ( response.data !== null ) {
                        const returnedData: Student = response.data;
                        context.dispatch('student/SET_STUDENT', returnedData);
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
            const studentId = context.getters[ 'student/GET_STUDENT' ].id;

            axios({
                method: 'post',
                url: '/api/v1/student/' + studentId + '/program',
                data: payload,
                headers: {
                    'Content-Type': 'application/json',
                },
            }).then((response) => {
                const newStudent: Student = response.data;
                context.dispatch('student/SET_STUDENT', newStudent);
                context.commit('UPDATE_UNREGISTERED_COURSES');
                resolve(newStudent);
            }).catch((reason) => {
                reject(reason);
            });
        });
    },
    'student/ADD_COURSES': (context, payload: Course[]) => {

        return new Promise((resolve, reject) => {
            const studentId = context.getters[ 'student/GET_STUDENT' ].id;
            axios({
                method: 'post',
                url: '/api/v1/student/' + studentId + '/courses',
                data: payload,
                headers: {
                    'Content-Type': 'application/json',
                },
            }).then((response) => {
                    const newCourses: Course[] = response.data;
                    context.commit('SET_COURSES', newCourses);
                    context.dispatch('action/SET_STUDENT', context.state.student);
                    resolve(newCourses);
                })
                .then(() => context.commit('UPDATE_UNREGISTERED_COURSES'))
                .catch((reason) => {
                    reject(reason);
                });
        });
    },
    'student/REMOVE_COURSE': (context, payload: string) => {
        return new Promise((resolve, reject) => {
            const studentId = context.getters[ 'student/GET_STUDENT' ].id;
            axios({
                method: 'delete',
                url: '/api/v1/student/' + studentId + '/courses/' + payload,
                data: payload,
            }).then((response) => {
                    const newCourses: Course[] = response.data;
                    context.commit('SET_COURSES', newCourses);
                    resolve(newCourses);
                })
                .then(() => context.commit('UPDATE_UNREGISTERED_COURSES'))
                .then(() => context.commit('REMOVE_COURSE', payload))
                .then(() => context.dispatch('action/SET_STUDENT', context.state.student))
                .catch((reason) => {
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
