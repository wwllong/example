<template>
  <div id="app">
		<div class="todo-container">
			<div class="todo-wrap">
				<TodoHeader @addTodo="addTodo"/>
				<TodoList :todos="todos" />
				<TodoFooter :todos="todos" @checkAllTodo="checkAllTodo" @clearAllDoneTodo="clearAllDoneTodo"/>
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
      todos: JSON.parse(localStorage.getItem("todos")) || []
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
  },
  watch: {
    todos: {
      deep: true,
      handler(value) {
        localStorage.setItem("todos", JSON.stringify(value))
      }
    }
  },
  mounted() {
    this.$bus.$on('checkTodo', this.checkTodo)
    this.$bus.$on('deleteTodo', this.deleteTodo)
  },
  beforeDestroy() {
    this.$bus.$off(['checkTodo', 'deleteTodo'])
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
