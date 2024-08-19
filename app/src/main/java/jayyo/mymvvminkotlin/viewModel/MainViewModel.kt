package jayyo.mymvvminkotlin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jayyo.mymvvminkotlin.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users

    init {
        loadUsers()
    }

    private fun loadUsers() {
        _users.value = listOf(
            User(1, "Alice", "alice@example.com"),
            User(2, "Bob", "bob@example.com"),
            User(3, "Charlie", "charlie@example.com")
        )
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            _users.value = _users.value.toMutableList().apply { add(user) }
        }
    }

    fun removeUser(user: User) {
        viewModelScope.launch {
            _users.value = _users.value.toMutableList().apply { remove(user) }
        }
    }
}