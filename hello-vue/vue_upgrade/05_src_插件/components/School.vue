<template>
	<div>
		<h2>学校名称：{{name | mySlice}}</h2>
		<h2>学校地址：{{address}}</h2>
		<button @click="test">点我测试一个hello方法</button>
	</div>
</template>

<script>
	export default {
		name:'School',
		data() {
			return {
        name:'北大青鸟, 欢迎大家',
        address:'北京路',
			}
		},
		methods: {
			test(){
				this.hello()
			}
		},
	}
</script>