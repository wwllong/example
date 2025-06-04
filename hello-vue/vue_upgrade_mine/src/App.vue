<template>
	<div class="app">
		<h1>{{msg}}, 学生姓名：{{studentName}}</h1>
    <!-- 通过父组件给子组件传递函数类型的props实现：子给父传递数据 -->
		<School :getSchoolName="getSchoolName"/>
    <!-- 通过父组件给子组件绑定一个自定义事件实现：子给父传递数据（第一种写法，使用@或v-on） -->
		<Student v-on:sendStudentNameEvent="getStudentName"/>
		<Student @sendStudentNameEvent.once="getStudentName" @demo="m1"/>
    <!-- 通过父组件给子组件绑定一个自定义事件实现：子给父传递数据（第二种写法，使用ref） -->
		<Student ref="student" @click.native="show"/>
	</div>
</template>

<script>
	import Student from './components/Student'
	import School from './components/School'

	export default {
		name:'App',
		components:{School,Student},
    data() {
      return {
         msg: '你好啊！',
         studentName: ''
      }
    },
    methods: {
      getSchoolName(name) {
        console.log('App收到了学校名：', name)
      },
      getStudentName(name,...params) {
        console.log('App收到了学生名：', name, params)
        this.studentName = name;
      },
      m1() {
        console.log('demo事件被触发了')
      },
      show() {
        alert("Show time")
      }
    },
    mounted() {
      setTimeout(() => {
        // this.$refs.student.$on('sendStudentNameEvent', this.getSchoolName) //绑定自定义事件
        this.$refs.student.$once('sendStudentNameEvent', this.getStudentName) //绑定自定义事件（一次性）
        // 这里用function会去找触发事件的 VC, 如果用this或者箭头函数则会用 apps 的实例VC
        // this.$refs.student.$once('sendStudentNameEvent', function (name,...params) {
        //   console.log('App收到了学生名：', name, params)
        //   console.log(this)
        //   this.studentName = name;
        // })
      }, 3000)
    }
  }
</script>

<style scoped>
	.app{
		background-color: gray;
    padding: 5px;
	}
</style>
