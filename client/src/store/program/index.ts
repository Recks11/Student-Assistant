import {ActionTree, GetterTree, Module, MutationTree} from 'vuex';
import {RootState} from '@/store/types';
import {ProgramState} from '@/store/program/types';
import Program from '@/model/Program';
import axios from 'axios';

const state: ProgramState = {
    programsMap: new Map<string, Program>(),
    programArray: [],
    program: new Program(),
};

const getters: GetterTree<ProgramState, RootState> = {
    GET_PROGRAM: (state) => {
        return state.programsMap;
    },
    GET_ALL_PROGRAMS: (state) => {
        return state.programsMap;
    },
    GET_PROGRAM_ARRAY: (state) => {
        return state.programArray;
    },
};

const mutations: MutationTree<ProgramState> = {
    PICK_PROGRAM: (state, payload: Program) => {
        state.program = payload;
    },
    SET_PROGRAMS: (state, payload: Map<string, Program>) => {
        state.programsMap = payload;
        payload.forEach((value, key) => {
            state.programArray.push(value)
        });
    },
    RESET_PROGRAM: (state) => {
        state.programArray = [];
        state.programsMap = new Map<string, Program>();
        state.program = new Program();
    },
};

const actions: ActionTree<ProgramState, RootState> = {
    'program/GET_STORED_PROGRAMS': (context) => {
        return new Promise((resolve, reject) => {
            axios.get('/api/v1/program')
                .then(response => {
                    let programArray: Program[] = response.data;
                    let responseMap = new Map<string, Program>();
                    programArray.forEach(
                        item => {
                            responseMap.set(item.id, item);
                        }
                    );
                    context.commit('SET_PROGRAMS', responseMap);
                    resolve(responseMap);
                }).catch(reason => {
                reject(reason);
            });
        });
    }, 'program/PICK_PROGRAM': (context, payload: Program) => {
        return new Promise((resolve, reject) => {

            context.dispatch('student/ADD_PROGRAM', payload)
                .then(() => resolve())
                .catch(() => reject());
        });
    },
    'program/RESET_PROGRAM': (context) => {
        context.commit('RESET_PROGRAM');
    },
};

export const ProgramStore: Module<ProgramState, RootState> = {
    state,
    getters,
    mutations,
    actions,
};
