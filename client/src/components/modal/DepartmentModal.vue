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
                        <h3>Please Select your Department</h3>
                        <form method="post">
                            <div class="form-group">
                                <label for="exampleFormControlSelect2">Department:</label>
                                <select class="form-control" id="exampleFormControlSelect2" v-model="deptId">
                                    <option disabled value="">Please select one</option>
                                    <option v-for="dept in allDepts" :value="dept.id"> {{dept.name}}</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <a role="button" class="btn btn-secondary" style="color: white" @click="goHome()">Cancel</a>
                        <button type="button" class="btn btn-primary" :disabled="deptId===''" @click.prevent="chooseDepartment()">
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
    import Department from '../../model/Department';

    @Component
    export default class DepartmentModal extends Vue {
        public show: { modal: boolean, background: boolean, card: boolean } = {
            modal: false,
            background: false,
            card: true
        };
        public allDepts: Department[] = this.$store.getters['GET_DEPARTMENT_ARRAY'];
        public allDepartments: Map<String, Department> = this.$store.getters['GET_DEPARTMENT_MAP'];
        public deptId: string = '';
        public message: string = '';

        public chooseDepartment(): void {
            let chosenDept = this.allDepartments.get(this.deptId);
            console.log(chosenDept);
            this.$store.dispatch('lecturer/SET_DEPARTMENT', chosenDept)
                .catch((r) => {
                    this.message = r;
                    return;
                });
            this.closeModal();
        }

        public closeModal(): void {
            this.show.modal = false;
            this.show.background = false;
            this.show.card = false;
        }

        public goHome(): void {
            this.closeModal();
            this.$router.push('/lecturer');
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

</style>