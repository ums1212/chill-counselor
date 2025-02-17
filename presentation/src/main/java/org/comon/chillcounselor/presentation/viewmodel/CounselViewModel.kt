package org.comon.chillcounselor.presentation.viewmodel

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.comon.chillcounselor.domain.model.RequestCounselData
import org.comon.chillcounselor.domain.model.ResponseCounselData
import org.comon.chillcounselor.domain.usecase.RequestCounselUseCase
import javax.inject.Inject

@HiltViewModel
class CounselViewModel @Inject constructor(
    private val requestCounselUseCase: RequestCounselUseCase
) : ViewModel() {

    private val _counselUiState = MutableStateFlow<CounselUiState>(CounselUiState.SplashScreen)
    val counselUiState: StateFlow<CounselUiState> = _counselUiState.asStateFlow()

    private val _requestViewModelScope = viewModelScope

    private val _cancelHandler = CoroutineExceptionHandler { _, throwable ->
        if(throwable is CancellationException){
            _counselUiState.value = CounselUiState.WriteScreen
        }else{
            _counselUiState.value = CounselUiState.ServerOutScreen(throwable.message ?: "Unexpected Exception")
        }
    }

    fun backToInitScreen(){
        _counselUiState.value = CounselUiState.InitialScreen
        _inputCounselContent.value = ""
    }

    fun startWrite(){
        _counselUiState.value = CounselUiState.WriteScreen
        _inputCounselContent.value = ""
    }

    fun cancelRequest(){
        _requestViewModelScope.cancel(CancellationException("사용자 취소"))
    }

    private val _inputCounselContent = MutableStateFlow("")
    val inputCounselContent: StateFlow<String> = _inputCounselContent.asStateFlow()

    fun changeInputValue(input: String){
        _inputCounselContent.value = input
    }

    private val _chillGuySize = MutableStateFlow(150.dp)
    val chillGuySize: StateFlow<Dp> = _chillGuySize.asStateFlow()

    fun requestCounsel() = _requestViewModelScope.launch(Dispatchers.IO + _cancelHandler) {
        requestCounselUseCase.invoke(RequestCounselData(_inputCounselContent.value)).collect { result ->
            _counselUiState.value = CounselUiState.LoadingScreen
            result.onSuccess { responseCounselData ->
                if(responseCounselData.error.isEmpty()){
                    _counselUiState.value = CounselUiState.ResultScreen(responseCounselData)
                }else{
                    _counselUiState.value = CounselUiState.WrongAskScreen(responseCounselData.reason)
                }
            }.onFailure { error ->
                _counselUiState.value = CounselUiState.ServerOutScreen(error.message ?: "Unknown Error")
            }
        }
    }
}

sealed class CounselUiState {
    data object SplashScreen: CounselUiState()
    data object InitialScreen: CounselUiState()
    data object WriteScreen: CounselUiState()
    data object LoadingScreen: CounselUiState()
    data class ResultScreen(val counselData: ResponseCounselData): CounselUiState()
    data class WrongAskScreen(val message: String): CounselUiState()
    data class ServerOutScreen(val message: String): CounselUiState()
}