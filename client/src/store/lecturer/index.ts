import Lecturer from '../../model/Lecturer';
import {LecturerState} from '@/store/lecturer/types';
import {RootState} from '../types';
import {ActionTree, GetterTree, Module, MutationTree} from 'vuex';
import axios, {AxiosResponse} from 'axios';
import Program from '@/model/Program';
import Department from '@/model/Department';
import Course from '@/model/Course';

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
                        let returnedLecturer: Lecturer = response.data;
                        context.dispatch('lecturer/SET_LECTURER', returnedLecturer);
                        resolve(returnedLecturer);
                    }
                }).catch((response) => {
                reject(response);
            });
        });
    },
    'lecturer/ADD_PROGRAM': (context, payload: Program) => {
        return new Promise((resolve, reject) => {
            let lecturerId = context.getters[ 'ACTIVE_LECTURER' ].id;

            axios({
                method: 'post',
                url: '/api/v1/lecturer/' + lecturerId + '/program',
                data: payload,
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then((response) => {
                let newLecturer: Lecturer = response.data;
                context.dispatch('lecturer/SET_LECTURER', newLecturer);
                resolve(newLecturer);
            }).catch(reason => {
                reject(reason);
            });
        });
    },
    'lecturer/SET_DEPARTMENT': (context, payload: Department) => {
        return new Promise((resolve, reject) => {
            let lecturerId = context.getters[ 'ACTIVE_LECTURER' ].id;
            axios.post('/api/v1/lecturer/' + lecturerId + '/department', payload)
                .then((response) => {
                    context.state.lecturer = response.data
                })
                .then(() => {
                    context.commit('department/SET_DEPARTMENT',
                        context.state.lecturer.departments[ context.state.lecturer.departments.length - 1 ]);
                    context.dispatch('action/SET_LECTURER', context.state.lecturer);
                    resolve('Success!');
                })
                .catch(() => reject('an error occurred while setting department'));
        });
    },
    'lecturer/toggleInOffice': (context) => {
        return new Promise((resolve, reject) => {
            let lecturerId = context.getters[ 'ACTIVE_LECTURER' ].id;
            axios.get('/api/v1/lecturer/' + lecturerId + '/status/toggleStatus')
                .then(() => {
                    context.dispatch('lecturer/GET_STORED_LECTURER')
                        .then((response) => resolve(response))
                        .catch(() => reject('could not return your information'))
                }).catch(() => reject('Could not update status'))
        })
    },
    'lecturer/ADD_COURSE': (context, payload: Course) => {
        return new Promise((resolve, reject) => {
            let lecturerId = context.getters[ 'ACTIVE_LECTURER' ].id;
            axios.post('/api/v1/lecturer/' + lecturerId + '/course', payload)
                .then((response) => {
                    let updatedLecturer: Lecturer = response.data;
                    context.state.lecturer.courses = updatedLecturer.courses;
                    resolve(updatedLecturer.courses);
                }).catch((error) => reject(error));
        });
    },
    'lecturer/REMOVE_COURSE': (context, payload: Course) => {
        return new Promise((resolve, reject) => {
            let lecturerId = context.getters[ 'ACTIVE_LECTURER' ].id;
            axios.delete('/api/v1/lecturer/' + lecturerId + '/course/' + payload.id)
                .then((response) => {
                    let updatedLecturer: Lecturer = response.data;
                    context.state.lecturer.courses = updatedLecturer.courses;
                    resolve(updatedLecturer.courses);
                }).catch((error) => reject(error));
        });
    }
};

export const lecturerStore: Module<LecturerState, RootState> = {
    state,
    getters,
    mutations,
    actions,
};
