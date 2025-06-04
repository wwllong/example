<template>
	<div class="school">
		<h2 class="title">学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
		<button @click="sendSchoolName">把学校名给App</button>
	</div>
</template>

<script>
	export default {
		name:'School',
    props: ['getSchoolName'],
		data() {
			return {
        name:'北大青鸟, 欢迎大家',
        address:'北京路',
			}
		},
    methods: {
      sendSchoolName() {
        return this.getSchoolName(this.name)
      }
    }
	}
</script>

<style scoped>
  .school{
    background-color: skyblue;
    padding: 5px;
  }
</style>