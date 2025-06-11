<template>
  <li>
		<label>
			<input type="checkbox" :checked="todo.done" @click="handleCheck(todo.id)"/>
      <!--下面代码也能实现功能，但不推荐，因为修改了props-->
      <!--<input type="checkbox" v-model="todo.done"/>-->
			<span v-show="!todo.isEdit">{{todo.title}}</span>
			<input
          type="text"
          v-show="todo.isEdit"
          :value="todo.title"
          @blur="handleBlur(todo, $event)"
          ref="inputTitle"
      />
		</label>
    <button class="btn btn-danger" @click="handleDeleted(todo.id)">删除</button>
    <button v-show="!todo.isEdit" class="btn btn-edit" @click="handleEdit(todo)">编辑</button>
  </li>
</template>

<script>
  import pubsub from 'pubsub-js'
  export default {
    name: 'TodoItem',
    props: ['todo'],
    methods: {
      handleCheck (id) {
        // 通知 App 组件将对应的td对象done值取反
        // this.checkTodo(id);
        this.$bus.$emit('checkTodo', id)
      },
      handleDeleted (id) {
        if (confirm('确定删除吗？')) {
          // this.deleteTodo(id);
          // this.$bus.$emit('deleteTodo', id)
          pubsub.publish('deleteTodo', id)
        }
      },
      handleEdit (todo) {
        if (todo.hasOwnProperty('isEdit')) {
          todo.isEdit = true;
        } else {
          console.log('todo 没有 isEdit 属性，添加中...')
          this.$set(todo, 'isEdit', true)
        }
        this.$nextTick(function () {
          this.$refs.inputTitle.focus()
        })
      },
      handleBlur (todo, e) {
        todo.isEdit = false;
        if (!e.target.value.trim()) {
          return alert('输入不能为空')
        }
        this.$bus.$emit('updateTodo', todo.id, e.target.value)
      }
    }
  }
</script>

<style scoped>
  /*item*/
  li {
      list-style: none;
      height: 36px;
      line-height: 36px;
      padding: 0 5px;
      border-bottom: 1px solid #ddd;
    }

    li label {
      float: left;
      cursor: pointer;
    }

    li label li input {
      vertical-align: middle;
      margin-right: 6px;
      position: relative;
      top: -1px;
    }

    li button {
      float: right;
      display: none;
      margin-top: 3px;
    }

    li:before {
      content: initial;
    }

    li:last-child {
      border-bottom: none;
    }

    li:hover{
      background-color: #ddd;
    }
    
    li:hover button{
      display: block;
    }
</style>