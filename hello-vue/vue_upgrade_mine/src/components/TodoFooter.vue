<template>
	<div class="todo-footer">
		<label>
			<input type="checkbox"/>
		</label>
		<span>
			<span>已完成xxx</span> / 全部xxx
		</span>
		<button class="btn btn-danger">清除已完成任务</button>
	</div>
</template>

<script>
  export default {
    name: 'TodoFooter'
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