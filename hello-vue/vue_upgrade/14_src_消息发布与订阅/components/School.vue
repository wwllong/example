<template>
	<div class="school">
		<h2>学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
	</div>
</template>

<script>
  import pubsub from 'pubsub-js'
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
      // this.$bus.$on('hello', (data) => {
      //   console.log('我是 School 组件，收到了数据', data)
      // })
      this.pubId = pubsub.subscribe('hello', (msg, data) => {
          console.log(this)
          console.log('有人发布了 hello 消息，hello 消息回调执行', msg, data)
      })
    },
    beforeDestroy() {
      // this.$bus.$off('hello')
      pubsub.unsubscribe(this.pubId)
    }
  }
</script>

<style scoped>
  .school{
    background-color: skyblue;
    padding: 5px;
  }
</style>