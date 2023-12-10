package hu.ait.nonprofitapp.ui.screen

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.ait.nonprofitapp.data.NonprofitDAO

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import hu.ait.nonprofitapp.data.NonprofitItem
import kotlinx.coroutines.runBlocking

@HiltViewModel
class OrgViewModel @Inject constructor(
    private val nonprofitDAO: NonprofitDAO
) : ViewModel() {

    fun getAllShoppingList(): Flow<List<NonprofitItem>> {
        return nonprofitDAO.getAllShopping()
    }

    fun getAllLiked(): Flow<List<NonprofitItem>> {
        return nonprofitDAO.getAllShopping()
    }


    fun addTodoList(todoItem: NonprofitItem) {
        viewModelScope.launch {
            nonprofitDAO.insert(todoItem)
        }
    }

    fun removeShoppingItem(todoItem: NonprofitItem) {
        viewModelScope.launch {
            nonprofitDAO.delete(todoItem)
        }
    }

    fun editShoppingItem(editedTodo: NonprofitItem) {
        viewModelScope.launch {
            nonprofitDAO.update(editedTodo)
        }
    }

    fun changeShoppingState(todoItem: NonprofitItem, value: Boolean) {
        //val newTodoItem = todoItem.copy()
        todoItem.isLiked = value //used to be isDone
        viewModelScope.launch {
            nonprofitDAO.update(todoItem)
        }
    }

    //TODO
    fun setFalse(todoItem: NonprofitItem) {
        todoItem.isLiked = false
        viewModelScope.launch {
            nonprofitDAO.update(todoItem)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
           nonprofitDAO.deleteAll()
        }
    }
}