<template>
    <div>
        <div class="modal fade" :class="{show: show.modal}" :style="{display: show.card===true ? 'block' : 'none' }"
             id="programModal" tabindex="-1"
             role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="programModalTitle">Add Program</h5>
                    </div>
                    <div class="modal-body">
                        <h3>Please Select your program</h3>
                        <p style="color: red;">!!This will happen only once!!</p>
                        <form id="programForm" method="post">
                            <div class="form-group">
                                <label for="college">College: </label>
                                <p type="text" readonly class="form-control-plaintext" id="college">
                                    {{programs[0].department.college.name}}</p>
                            </div>
                            <div class="form-group">
                                <label for="department">Department:</label>
                                <p type="text" readonly class="form-control-plaintext" id="department">
                                    {{programs[0].department.name}}</p>
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlSelect2">Program:</label>
                                <select class="form-control" id="exampleFormControlSelect2" v-model="pickedProgramId">
                                    <option disabled value="">Please select one</option>
                                    <option v-for="program in programs" :value="program.id"> {{program.name}}</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <a role="button" class="btn btn-secondary" style="color: white" @click="goHome()">Cancel</a>
                        <button type="button" class="btn btn-primary" :disabled="pickedProgramId===''"
                                @click.prevent="chooseProgram(pickedProgramId)">
                            Select
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-backdrop fade" :class="{show: show.background}"
             :style="{display: show.background===true ? 'block': 'none' }"></div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import Program from '../../model/Program';

    @Component
    export default class ProgramModal extends Vue {
        public show: { modal: boolean, background: boolean, card: boolean } = {
            modal: false,
            background: false,
            card: true,
        };
        public programs: Program[] = this.$store.getters[ 'GET_PROGRAM_ARRAY' ];
        public allPrograms: Map<String, Program> = this.$store.getters[ 'GET_ALL_PROGRAMS' ];
        public pickedProgramId: string = '';

        public chooseProgram(programId: string): void {
            this.$store.commit('main/LOADING', true);
            this.$store.dispatch('program/PICK_PROGRAM', this.allPrograms.get(programId))
                .then(() => {
                    this.$store.commit('main/LOADING', false);
                    this.closeModal();
                })
                .catch(() => this.$store.commit('main/LOADING', false));
        }

        public closeModal(): void {
            this.show.modal = false;
            this.show.background = false;
            this.show.card = false;
        }

        public goHome(): void {
            this.closeModal();
            this.$router.push('/student');
        }

        public created(): void {
            setTimeout(() => {
                this.show.background = true;
                this.show.modal = true;
            }, 100);
        }
    }
</script>

<style>
    .fade {
        transition: opacity .50s linear;
        -webkit-transition: transform .20s ease-in;
        -moz-transition: transform .20s ease-in;
        -ms-transition: transform .20s ease-in;
        -o-transition: transform .20s ease-in;
        transition: transform .20s ease-in;
    }

    .modal {
        -webkit-transform: translateY(-50%);
        -moz-transform: translateY(-50%);
        -ms-transform: translateY(-50%);
        -o-transform: translateY(-50%);
        transform: translateY(-50%);
    }

    .show {
        -webkit-transform: translateX(0%);
        -moz-transform: translateX(0%);
        -ms-transform: translateX(0%);
        -o-transform: translateX(0%);
        transform: translateX(0%);
    }
</style>