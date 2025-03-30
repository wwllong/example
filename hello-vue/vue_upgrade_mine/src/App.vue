<template>
  <div id="app">
		<div class="todo-container">
			<div class="todo-wrap">
				<TodoHeader :addTodo="addTodo"/>
				<TodoList :todos="todos" :checkTodo="checkTodo" :deleteTodo="deleteTodo"/>
				<TodoFooter :todos="todos" :checkAllTodo="checkAllTodo" :clearAllDoneTodo="clearAllDoneTodo"/>
			</div>
		</div>
	</div>
</template>

<script>
import TodoHeader from './components/TodoHeader'
import TodoList from './components/TodoList'
import TodoFooter from './components/TodoFooter'

export default {
  name: 'App',
  components: {TodoHeader, TodoList, TodoFooter},
  data() {
    return {
      todos: [
        {id: '001', title: '抽烟', done: true},
        {id: '002', title: '喝酒', done: false},
        {id: '003', title: '烫头', done: true},
        {id: '004', title: '开车', done: true},
      ]
    }
  },
  methods: {
    addTodo(todoObj) {
      this.todos.unshift(todoObj)
    },
    checkTodo(id) {
      this.todos.forEach((todoObj) => {
        if (todoObj.id === id) todoObj.done = !todoObj.done;
      })
    },
    deleteTodo(id) {
      this.todos = this.todos.filter((todoObj) => todoObj.id !== id);
    },
    checkAllTodo(done) {
      this.todos.forEach((todoObj) => {
        todoObj.done = done;
      })
    },
    clearAllDoneTodo() {
      if (confirm('确定删除吗？')) {
        this.todos = this.todos.filter((todoObj) => !todoObj.done)
      }
    }
  }
}
</script>

<style>
	/*base*/
	body {
		background: #fff;
	}
	.btn {
		display: inline-block;
		padding: 4px 12px;
		margin-bottom: 0;
		font-size: 14px;
		line-height: 20px;
		text-align: center;
		vertical-align: middle;
		cursor: pointer;
		box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
		border-radius: 4px;
	}
	.btn-danger {
		color: #fff;
		background-color: #da4f49;
		border: 1px solid #bd362f;
	}
	.btn-danger:hover {
		color: #fff;
		background-color: #bd362f;
	}
	.btn:focus {
		outline: none;
	}
	.todo-container {
		width: 600px;
		margin: 0 auto;
	}
	.todo-container .todo-wrap {
		padding: 10px;
		border: 1px solid #ddd;
		border-radius: 5px;
	}
</style>
