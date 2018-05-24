<template>
    <main>
        <app-department-modal v-if="currentLecturer.departments.length === 0"></app-department-modal>
    </main>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import DepartmentModal from '../components/modal/DepartmentModal.vue';
    import Lecturer from '../model/Lecturer';

    @Component({
        components: {
            appDepartmentModal: DepartmentModal,
        },
    })
    export default class LecturerCourses extends Vue {
        public get currentLecturer(): Lecturer {
            return this.$store.getters[ 'ACTIVE_LECTURER' ];
        };
    }
</script>

<style scoped>

</style>