<template>
    <div class="modal fade" id="courseOptionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="CourseOptionTitle">No Courses?</h5>
                </div>
                <div class="modal-body">
                    You have not registered any courses. would you like to register your course now?
                </div>
                <div class="modal-footer">
                    <a role="button" class="btn btn-secondary" href="/student/">No</a>
                    <a role="button" class="btn btn-primary"  href="/student/course/register?add-courses=true">Yes</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';

    @Component
    export default class MessageModal extends Vue {
    }
</script>

<style scoped>

</style>