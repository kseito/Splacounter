package kztproject.jp.splacounter.presentation.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kztproject.jp.splacounter.todo.domain.Todo
import javax.inject.Inject

class TodoListViewModel @Inject constructor() : ViewModel() {

    private val todoList: MutableLiveData<List<Todo>> = MutableLiveData()

    fun loadTodo() {
        val todoList = listOf(1, 2, 3, 4, 5)
                .map {
                    Todo(it.toLong(), "Test Todo $it", 0.5f, true)
                }
        this.todoList.value = todoList
    }

    fun observeTodo(): LiveData<List<Todo>> {
        return todoList
    }

    interface Callback {
        fun loadTodoCompleted()
    }
}
