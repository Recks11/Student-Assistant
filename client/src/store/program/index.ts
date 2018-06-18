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
    GET_PROGRAM: (programState) => {
        return programState.programsMap;
    },
    GET_ALL_PROGRAMS: (programState) => {
        return programState.programsMap;
    },
    GET_PROGRAM_ARRAY: (programState) => {
        return programState.programArray;
    },
};

const mutations: MutationTree<ProgramState> = {
    PICK_PROGRAM: (programState, payload: Program) => {
        programState.program = payload;
    },
    SET_PROGRAMS: (programState, payload: Map<string, Program>) => {
        programState.programsMap = payload;
        payload.forEach((value, key) => {
            programState.programArray.push(value);
        });
    },
    RESET_PROGRAM: (programState) => {
        programState.programArray = [];
        programState.programsMap = new Map<string, Program>();
        programState.program = new Program();
    },
};

const actions: ActionTree<ProgramState, RootState> = {
    'program/GET_STORED_PROGRAMS': (context) => {
        return new Promise((resolve, reject) => {
            axios.get('/api/v1/program')
                .then((response) => {
                    const programArray: Program[] = response.data;
                    const responseMap = new Map<string, Program>();
                    programArray.forEach(
                        (item) => {
                            responseMap.set(item.id, item);
                        },
                    );
                    context.commit('SET_PROGRAMS', responseMap);
                    resolve(responseMap);
                }).catch((reason) => {
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
