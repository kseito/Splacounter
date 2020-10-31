package kztproject.jp.splacounter.presentation.todo

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kztproject.jp.splacounter.todo.application.DeleteTodoUseCase
import kztproject.jp.splacounter.todo.application.GetTodoListUseCase
import kztproject.jp.splacounter.todo.application.UpdateTodoUseCase
import kztproject.jp.splacounter.todo.domain.Todo
import javax.inject.Inject

class TodoListViewModel @Inject constructor(
        private val getTodoListUseCase: GetTodoListUseCase,
        private val updateTodoUseCase: UpdateTodoUseCase,
        private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {

    private lateinit var callback: Callback
    val todoList: LiveData<List<Todo>> = getTodoListUseCase.execute().asLiveData()

    fun initialize(callback: Callback) {
        this.callback = callback
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.Default) {
            updateTodoUseCase.execute(todo)

            callback.afterTodoUpdate()
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.Default) {
            deleteTodoUseCase.execute(todo)

            callback.afterTodoUpdate()
        }
    }

    interface Callback {
        fun afterTodoUpdate()
    }
}
