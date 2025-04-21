<template>
	<div class="todo-footer" v-show="total">
		<label>
      <!--<input type="checkbox" :checked="isAllDone" @change="checkAll"/>-->
			<input type="checkbox" v-model="isAllDone"/>
		</label>
		<span>
			<span>已完成{{doneTotal}}</span> / 全部{{total}}
		</span>
		<button class="btn btn-danger" @click="clearAllDoneTodo()">清除已完成任务</button>
	</div>
</template>

<script>
  export default {
    name: 'TodoFooter',
    props: ['todos', 'checkAllTodo', 'clearAllDoneTodo'],
    computed: {
      total() {
        return this.todos.length;
      },
      doneTotal() {
        return this.todos.reduce((pre, cur) => {
          return pre + (cur.done ? 1 : 0);
        }, 0)
      },
      isAllDone: {
        get() {
          return this.doneTotal === this.total && this.total > 0
        },
        set(value) {
          this.checkAllTodo(value);
        }
      }
    },
    methods: {
      checkAll(e) {
        this.checkAllTodo(e.target.checked);
      },
      clearAllDone() {
        this.clearAllDoneTodo()
      }
    }
  }
</script>

<style scoped>
	/*footer*/
	.todo-footer {
		height: 40px;
		line-height: 40px;
		padding-left: 6px;
		margin-top: 5px;
	}

	.todo-footer label {
		margin-right: 10spx;
		cursor: pointer;
    float: inline-start;
	}

	.todo-footer label input {
		position: relative;
		top: -1px;
		vertical-align: middle;
		margin-right: 5px;
	}

	.todo-footer button {
		float: right;
		margin-top: 5px;
	}
</style>