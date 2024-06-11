package com.example.pricecheckstationkotlin.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pricecheckstationkotlin.domain.repository.usecase.ItemUseCases
import com.example.pricecheckstationkotlin.model.data.InvalidItemException
import com.example.pricecheckstationkotlin.util.GlobalVariable
import com.example.pricecheckstationkotlin.view.ui.ItemEvent
import com.example.pricecheckstationkotlin.view.ui.ItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PriceCheckViewModel @Inject constructor(
    private val itemUseCases: ItemUseCases,
//    private val departmentUseCase: DepartmentUseCases,
//    private val vendorUseCase: VendorUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Using this could be potential memory leak but let's do it easily <-- Deal with it later
    var context: Context? = GlobalVariable.context

    private val _state = mutableStateOf(ItemState())
    val state: State<ItemState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    sealed class UiEvent {
        data class ShowSnackBar(val message: String): UiEvent()
        data class ShowItem(val message: String): UiEvent()
    }

    fun onEvent(event: ItemEvent) {
        when(event) {
            is ItemEvent.GetItem -> {
                //
            }
            is ItemEvent.GetItemById -> {
                viewModelScope.launch {
                    try {
                        // TODO Add all item detail into Item
                        _state.value.item = itemUseCases.getItemById(event.item, context)
                        Log.i("Print Test ---> ", _state.value.item.description)
                        //itemUseCases.getItemById(event.id)
                        _eventFlow.emit(UiEvent.ShowItem("Show Item Screen"))
                    } catch(e: InvalidItemException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Item not in DB"
                            )
                        )
                    }
                }
            }
            is ItemEvent.InsertItem -> {
                // TODO
            }
            is ItemEvent.PrintItem -> {
                // Todo
            }

        }
    }

}
