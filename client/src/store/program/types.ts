import Program from '@/model/Program';

export interface ProgramState {
    programsMap: Map<string, Program>;
    programArray: Program[];
    program: Program;

}