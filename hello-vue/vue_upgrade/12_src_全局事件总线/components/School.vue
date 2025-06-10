<template>
	<div class="school">
		<h2>学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
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
    mounted() {
      // console.log('School', this.x)
      this.$bus.$on('hello', (data) => {
        console.log('我是 School 组件，收到了数据', data)
      })
    },
    beforeDestroy() {
      this.$bus.$off('hello')
    }
  }
</script>

<style scoped>
  .school{
    background-color: skyblue;
    padding: 5px;
  }
</style>