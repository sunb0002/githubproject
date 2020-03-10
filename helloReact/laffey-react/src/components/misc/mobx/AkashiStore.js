import { computed, decorate, observable, autorun } from 'mobx';

class AkashiStore {

	todos = [];
    pendingRequests = 0;

    constructor() {
        autorun(() => console.log(this.report));
    }

	get completedTodosCount() {
    	return this.todos.filter(
			todo => todo.completed === true
		).length;
    }

	get report() {
		if (this.todos.length === 0)
			return "<none>";
		const nextTodo = this.todos.find(todo => todo.completed === false);
		return `Next todo: "${nextTodo ? nextTodo.task : "<none>"}". ` +
			`Progress: ${this.completedTodosCount}/${this.todos.length}`;
	}

    addTodo(task) {
		this.todos.push({
			task: task,
			completed: false,
            assignee: null
		});
	}

}

// Has to 'npm run eject' if you really want to use annotation form, which requires changes in babel config
// Probably no issue for TypeScript
decorate(AkashiStore, {
	todos: observable,
	pendingRequests: observable,
	completedTodosCount: computed,
	report: computed,
});

export default AkashiStore;
