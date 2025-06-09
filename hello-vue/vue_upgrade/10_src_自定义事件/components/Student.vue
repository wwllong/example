<template>
	<div class="student">
		<h2>学生姓名：{{name}}</h2>
		<h2>学生性别：{{sex}}</h2>
		<h2>sum：{{number}}</h2>
    <button @click="add">点我number++</button>
    <button @click="sendStudentName">把学生名给App</button>
    <button @click="unbind">解绑sendStudentName事件</button>
    <button @click="death">销毁组件 </button>
	</div>
</template>

<script>
	export default {
		name:'Student',
		data() {
			return {
				name:'张三',
				sex:'男',
        number: 0
			}
		},
    methods: {
      add() {
        console.log('add被调用了')
        this.number++
      },
      sendStudentName() {
        this.$emit('sendStudentNameEvent', this.name, this.sex)
        this.$emit('demo')
        // this.$emit('click')
      },
      unbind() {
        // 解绑单个事件
        // this.$off('sendStudentNameEvent')
        // 解绑多个事件
        this.$off(['sendStudentNameEvent', 'demo'])
        // 解绑所有自定义事件
        this.$off()
      },
      death() {
        // 销毁当前 VC, 销毁后所有的当前实例的自定义事件失效
        this.$destroy()
      }
    }
	}
</script>

<style lang="less" scoped>
  .student{
    background-color: pink;
    padding: 5px;
    margin-top: 30px;
  }
</style>
