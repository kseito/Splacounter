package kztproject.jp.splacounter.presentation.todo.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kztproject.jp.splacounter.todo.domain.Todo

data class EditingTodo(
        var id: Long? = null,
        var name: String = "",
        var numberOfTicketsObtained: Float = 0f,
        private var isRepeat: Boolean = false
) : BaseObservable() {

    companion object {
        fun from(todo: Todo): EditingTodo {
            return EditingTodo(
                    todo.id,
                    todo.name,
                    todo.numberOfTicketsObtained,
                    todo.isRepeat
            )
        }
    }

    @Bindable
    fun getIsRepeat(): Boolean {
        return isRepeat
    }

    fun setIsRepeat(value: Boolean) {
        isRepeat = value
    }

    fun toTodo(): Todo {
        return Todo(
                this.id,
                this.name,
                this.numberOfTicketsObtained,
                this.isRepeat
        )
    }
}