<template>
  <ul class="todo-main">
    <TodoItem v-for="todo in todos" :key="todo.id" :todo="todo"/>
  </ul>
</template>

<script>
  import TodoItem from './TodoItem'

  export default {
    name: 'TodoList',
    components: {TodoItem},
    data() {
      return {
        todos: [
          {id: '001', title: '抽烟', done: true},
          {id: '002', title: '喝酒', done: false},
          {id: '003', title: '烫头', done: true},
          {id: '004', title: '开车', done: true},
        ]
      }
    }
  }

</script>

<style scoped>
  /*main*/
  .todo-main {
    margin-left: 0px;
    border: 1px solid #ddd;
    border-radius: 2px;
    padding: 0px;
  }

  .todo-empty {
    height: 40px;
    line-height: 40px;
    border: 1px solid #ddd;
    border-radius: 2px;
    padding-left: 5px;
    margin-top: 10px;
  }
</style>
