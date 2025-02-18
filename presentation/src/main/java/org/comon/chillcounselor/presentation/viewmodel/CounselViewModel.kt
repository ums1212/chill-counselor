package org.comon.chillcounselor.presentation.viewmodel

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
    val counselUiState = _counselUiState.asStateFlow()

    private val _chillGuyState = MutableStateFlow<ChillGuyState>(ChillGuyState.SmallGuy)
    val chillGuyState = _chillGuyState.asStateFlow()

    private val _bgmButtonState = MutableStateFlow(BGMPlayState.Play)
    val bgmButtonState = _bgmButtonState.asStateFlow()

    private val _bgmPlayState = MutableStateFlow(false)
    val bgmPlayState = _bgmPlayState.asStateFlow()

    private val _inputCounselContent = MutableStateFlow("")
    val inputCounselContent: StateFlow<String> = _inputCounselContent.asStateFlow()

    private val _requestViewModelScope = viewModelScope

    private val _cancelHandler = CoroutineExceptionHandler { _, throwable ->
        if(throwable is CancellationException){
            _counselUiState.value = CounselUiState.WriteScreen
        }else{
            _counselUiState.value = CounselUiState.ServerOutScreen(throwable.message ?: "Unexpected Exception")
        }
    }

    fun changeInToInitScreen(){
        _counselUiState.value = CounselUiState.InitialScreen
        _inputCounselContent.value = ""
        _chillGuyState.value = ChillGuyState.LargeGuy
    }

    fun startWrite(){
        _counselUiState.value = CounselUiState.WriteScreen
        _inputCounselContent.value = ""
    }

    fun changeInputValue(input: String){
        if(input.length <= 1000){
            _inputCounselContent.value = input
        }
    }

    fun requestCounsel() = _requestViewModelScope.launch(Dispatchers.IO + _cancelHandler) {

        _counselUiState.value = CounselUiState.LoadingScreen

        requestCounselUseCase.invoke(RequestCounselData(_inputCounselContent.value)).collect { result ->
            result.onSuccess { responseCounselData ->
                if(responseCounselData.error.isEmpty()){
                    _counselUiState.value = CounselUiState.ResultScreen(responseCounselData)
                }else{
                    _counselUiState.value = CounselUiState.WrongAskScreen(responseCounselData.reason)
                }
            }.onFailure { error ->
                _counselUiState.value = CounselUiState.ServerOutScreen(error.message ?: "Unknown Error")
                _chillGuyState.value = ChillGuyState.ErrorGuy
            }
        }
    }

    fun cancelRequest(){
        _requestViewModelScope.cancel(CancellationException("사용자 취소"))
    }

    fun playBGM(){
        _bgmPlayState.value = true
    }

    fun stopBGM(){
        _bgmPlayState.value = false
    }

    fun toggleBGM(){
        if(_bgmPlayState.value){
            stopBGM()
        }else{
            playBGM()
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

sealed class ChillGuyState {
    data object SmallGuy: ChillGuyState()
    data object LargeGuy: ChillGuyState()
    data object ErrorGuy: ChillGuyState()
}

sealed class BGMPlayState {
    data object Stop: BGMPlayState()
    data object Play: BGMPlayState()
}