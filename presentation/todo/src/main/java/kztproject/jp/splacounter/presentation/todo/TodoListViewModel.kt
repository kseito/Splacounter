package kztproject.jp.splacounter.presentation.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kztproject.jp.splacounter.todo.application.GetTodoListUseCase
import kztproject.jp.splacounter.todo.domain.Todo
import javax.inject.Inject

class TodoListViewModel @Inject constructor(
        private val getTodoListUseCase: GetTodoListUseCase
) : ViewModel() {

    private val todoList: MutableLiveData<List<Todo>> = MutableLiveData()

    fun loadTodo() {
        viewModelScope.launch {
            val newTodoList = getTodoListUseCase.execute()
            todoList.value = newTodoList
        }
    }

    fun observeTodo(): LiveData<List<Todo>> {
        return todoList
    }

    interface Callback {
        fun loadTodoCompleted()
    }
}
