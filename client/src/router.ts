import Vue from 'vue';
import Router from 'vue-router';
import store from './store';

const LecturerHome = () => import('./views/lecturer/LecturerHome.vue');
const Register = () => import('@/views/Register.vue');
const Login = () => import('@/views/Login.vue');
const StudentHome = () => import('@/views/student/StudentHome.vue');
const Course = () => import('@/views/student/StudentCourse.vue');
const CourseRegistration = () => import('@/components/course/student/CourseRegistration.vue');
const StudentViewAllCourses = () => import('@/components/course/student/ViewAllCourses.vue');
const StudentViewCourse = () => import('@/components/course/student/ViewCourse.vue');
const LecturerCourses = () => import('@/views/lecturer/LecturerCourses.vue');
const LecturerAddCourse = () => import ('@/components/course/lecturer/AddCourse.vue');
const LecturerViewAllCourses = () => import('@/components/course/lecturer/ViewAllCourses.vue');
const LecturerViewCourse = () => import('@/components/course/lecturer/ViewCourse.vue');

Vue.use(Router);

export const router: Router = new Router({
    routes: [
        {
            path: '/login',
            name: 'login',
            component: Login,
        },
        {
            path: '/register',
            name: 'register',
            component: Register,
        },
        {
            path: '/student',
            name: 'studentHome',
            component: StudentHome,
        },
        {
            path: '/course',
            component: Course,
            children: [
                {
                    path: 'register',
                    component: CourseRegistration
                },
                {
                    path: 'view',
                    component: StudentViewAllCourses,
                },
                {
                    name: 'courseInfo',
                    path: 'view/:id',
                    component: StudentViewCourse,
                    beforeEnter: (to, from, next) => {
                        console.log("before enter");
                        store.dispatch('course/GET_COURSE', to.params.id)
                            .then(() => store.dispatch('course/GET_COURSE_LECTURERS', to.params.id)
                                .then(() => next()))
                            .catch(() => next(from))
                    },
                },
            ],
        },
        {
            path: '/lecturer',
            component: LecturerHome
        },
        {
            path: '/lecturer/courses',
            component: LecturerCourses,
            children: [
                {
                    path: 'add',
                    component: LecturerAddCourse,
                    beforeEnter: (to, from, next) => {
                        store.dispatch('department/GET_COURSES_FOR_DEPARTMENT')
                            .then(() => next())
                            .catch(() => next());
                    },
                },
                {
                    path: 'view',
                    component: LecturerViewAllCourses,
                },
                {
                    path: 'view/:id',
                    component: LecturerViewCourse,
                    beforeEnter: (to, from, next) => {
                        store.dispatch('course/GET_COURSE', to.params.id)
                            .then(() => store.dispatch('course/GET_COURSE_STUDENTS', to.params.id)
                                .then(() => next())
                                .catch(() => next(from)))
                            .catch(() => next(from))
                    }
                },
            ]
        },
        {
            path: '*',
            redirect: '/login',
        }
    ],
    mode: 'history',
    linkActiveClass: 'active',
});
