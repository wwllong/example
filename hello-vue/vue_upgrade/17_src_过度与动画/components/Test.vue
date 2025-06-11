<template>
  <div id="app">
    <button @click="isShow = !isShow">显示/隐藏</button>
    <transition name="hello" appear>
      <h1 v-show="isShow">Hello Vue</h1>
    </transition>
  </div>
</template>

<script>
  export default {
    name: 'Test',
    data() {
      return {
        isShow: true
      }
    }
  }
</script>

<style scoped>
  h1 {
    background-color: orange;
  }

  .hello-enter-active {
    animation: helloVue 1s linear;
  }

  .hello-leave-active {
    animation: helloVue 1s linear reverse;
  }
  
  @keyframes helloVue {
    from {
      transform: translateX(-100%);
    }
    to {
      transform: translateX(0px);
    }
  }
</style>
