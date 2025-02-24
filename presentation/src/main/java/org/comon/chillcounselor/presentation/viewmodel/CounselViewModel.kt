package org.comon.chillcounselor.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.comon.chillcounselor.domain.model.RequestCounselData
import org.comon.chillcounselor.domain.model.ResponseCounselData
import org.comon.chillcounselor.domain.usecase.CheckNetworkStateUseCase
import org.comon.chillcounselor.domain.usecase.PauseBGMUseCase
import org.comon.chillcounselor.domain.usecase.PlayBGMUseCase
import org.comon.chillcounselor.domain.usecase.RequestCounselUseCase
import javax.inject.Inject

@HiltViewModel
class CounselViewModel @Inject constructor(
    private val requestCounselUseCase: RequestCounselUseCase,
    private val checkNetworkStateUseCase: CheckNetworkStateUseCase,
    private val playBGMUseCase: PlayBGMUseCase,
    private val pauseBGMUseCase: PauseBGMUseCase,
) : ViewModel() {

    private val _counselUiState = MutableStateFlow<CounselUiState>(CounselUiState.SplashScreen)
    val counselUiState = _counselUiState.asStateFlow()

    private val _chillGuyState = MutableStateFlow<ChillGuyState>(ChillGuyState.SmallGuy)
    val chillGuyState = _chillGuyState.asStateFlow()

    private val _bgmPlayState = MutableStateFlow(false)
    val bgmPlayState = _bgmPlayState.asStateFlow()

    private val _inputCounselContent = MutableStateFlow("")
    val inputCounselContent = _inputCounselContent.asStateFlow()

    val isInputValid = _inputCounselContent
        .map { it.isNotEmpty() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    fun checkNetworkState(){
        if(checkNetworkStateUseCase.invoke()){
            _counselUiState.value = CounselUiState.InitialScreen
            _chillGuyState.value = ChillGuyState.LargeGuy
        }else{
            _counselUiState.value = CounselUiState.ServerOutScreen("네트워크 연결이 안됨")
            _chillGuyState.value = ChillGuyState.ErrorGuy
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
        _chillGuyState.value = ChillGuyState.LargeGuy
    }

    fun changeInputValue(input: String){
        if(input.length <= 1000){
            _inputCounselContent.value = input
        }
    }

    fun completeCounselInput(){
        _counselUiState.value = CounselUiState.LoadingScreen
        requestCounsel()
    }

    private val _exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if(throwable is CancellationException){
            _counselUiState.value = CounselUiState.WriteScreen
        }else{
            _counselUiState.value = CounselUiState.ServerOutScreen(throwable.message ?: "Unexpected Exception")
        }
    }

    private lateinit var _requestJob: Job

    private fun requestCounsel(){
        _requestJob = viewModelScope.launch(_exceptionHandler) {
            requestCounselUseCase.invoke(RequestCounselData(_inputCounselContent.value)).collect { result ->
                result.onSuccess { responseCounselData ->
                    if(responseCounselData.error.isEmpty()){
                        _chillGuyState.value = ChillGuyState.SmallGuy
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
    }

    fun cancelRequest(){
        _requestJob.cancel(CancellationException("사용자 취소"))
        _counselUiState.value = CounselUiState.WriteScreen
    }

    fun toggleBGM(){
        _bgmPlayState.value = !_bgmPlayState.value
        if(_bgmPlayState.value){
            playBGMUseCase.invoke()
        }else{
            pauseBGMUseCase.invoke()
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