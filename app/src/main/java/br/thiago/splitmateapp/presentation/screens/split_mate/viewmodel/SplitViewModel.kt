package br.thiago.splitmateapp.presentation.screens.split_mate.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.thiago.splitmateapp.domain.model.Split
import br.thiago.splitmateapp.domain.usecase.SplitUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplitViewModel @Inject constructor(
    private val splitUseCases: SplitUseCases
) : ViewModel() {

    private val _splits = MutableStateFlow<List<Split>>(emptyList())
    val splits: StateFlow<List<Split>> = _splits

    init {
        loadSplits()
    }

    fun loadSplits() {
        viewModelScope.launch {
            _splits.value = splitUseCases.getSplits()
        }
    }

    fun addNewSplit(split: Split) {
        viewModelScope.launch {
            splitUseCases.addSplit(split)
            loadSplits()
        }
    }

    fun removeSplit(split: Split) {
        viewModelScope.launch {
            splitUseCases.deleteSplit(split)
            loadSplits()
        }
    }
}

