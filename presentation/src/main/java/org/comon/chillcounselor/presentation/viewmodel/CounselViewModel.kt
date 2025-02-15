package org.comon.chillcounselor.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.comon.chillcounselor.domain.model.RequestCounselData
import org.comon.chillcounselor.domain.model.ResponseCounselData
import org.comon.chillcounselor.domain.usecase.RequestCounselUseCase
import javax.inject.Inject

class CounselViewModel @Inject constructor(
    private val requestCounselUseCase: RequestCounselUseCase
) : ViewModel() {

    private val _counselState = MutableStateFlow<CounselState>(CounselState.Init)
    val counselState: StateFlow<CounselState> = _counselState.asStateFlow()

    fun requestCounsel(prompt: String) = viewModelScope.launch(Dispatchers.IO) {
        requestCounselUseCase.invoke(RequestCounselData(prompt)).collect { result ->
            _counselState.value = CounselState.Loading
            result.onSuccess { responseCounselData ->
                _counselState.value = CounselState.Success(responseCounselData)
            }.onFailure { error ->
                _counselState.value = CounselState.Error(error.message ?: "Unknown Error")
            }
        }
    }
}

sealed class CounselState {
    data object Init : CounselState()
    data object Loading : CounselState()
    data class Success(val counselData: ResponseCounselData) : CounselState()
    data class Error(val message: String) : CounselState()
}