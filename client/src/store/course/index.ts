import {CourseState} from '@/store/course/types';
import {ActionTree, GetterTree, Module, MutationTree} from 'vuex';
import {RootState} from '@/store/types';
import Course from '@/model/Course';
import axios from 'axios';
import Lecturer from '@/model/Lecturer';
import Student from '@/model/Student';

const state: CourseState = {
    courses: new Map<string, Course>(),
    course: new Course(),
};

const getters: GetterTree<CourseState, RootState> = {
    GET_COURSES_MAP: (courseState) => {
        return courseState.courses;
    },
    GET_COURSE: (courseState) => {
        return courseState.course;
    },
};

const mutations: MutationTree<CourseState> = {
    RESET_COURSE: (courseState) => {
        courseState.courses = new Map<string, Course>();
        courseState.course = new Course();
    },
    ADD_COURSE: (courseState, payload: Course) => {
        courseState.courses.set(payload.id, payload);
    },
    REMOVE_COURSE: (courseState, payload: string) => {
        courseState.courses.delete(payload);
    },
    SET_COURSE: (courseState, payload: Course) => {
        courseState.course = payload;
    },
    SET_COURSE_LECTURERS: (courseState, payload: Lecturer[]) => {
        courseState.course.lecturers = payload;
    },
    SET_COURSE_STUDENTS: (courseState, payload: Student[]) => {
        courseState.course.students = payload;
    },
};

const actions: ActionTree<CourseState, RootState> = {
    'course/REGISTER_STUDENT': (context, payload: Course[]) => {
        return new Promise((resolve, reject) => {
            context.dispatch('student/ADD_COURSES', payload)
                .then((registeredCourses: Course[]) => {
                    registeredCourses.forEach((course, index) => {
                        context.commit('ADD_COURSE', course);
                    });
                    resolve(registeredCourses);
                });
        });
    },
    'course/GET_COURSE': (context, payload: string) => {
        return new Promise((resolve, reject) => {
            axios.get('/api/v1/courses/' + payload)
                .then((response) => {
                    state.course = response.data;
                    context.commit('SET_COURSE', response.data);
                    resolve();
                })
                .catch((reason) => {
                    reject(reason);
                });
        });
    },
    'course/GET_COURSE_LECTURERS': (context, payload: string) => {
        return new Promise((resolve, reject) => {
            axios.get('/api/v1/courses/' + payload + '/lecturers')
                .then((response) => {
                    context.commit('SET_COURSE_LECTURERS', response.data);
                    resolve();
                })
                .catch((reason) => {
                    reject(reason);
                });
        });
    },
    'course/GET_COURSE_STUDENTS': (context, payload: string) => {
        return new Promise((resolve, reject) => {
            axios.get('/api/v1/courses/' + payload + '/students')
                .then((response) => {
                    context.commit('SET_COURSE_STUDENTS', response.data);
                    resolve();
                })
                .catch((reason) => {
                    reject(reason);
                });
        });
    },
    'course/RESET_COURSE': (context) => {
        context.commit('RESET_COURSE');
    },
};

export const CourseStore: Module<CourseState, RootState> = {
    state,
    getters,
    mutations,
    actions,
};
