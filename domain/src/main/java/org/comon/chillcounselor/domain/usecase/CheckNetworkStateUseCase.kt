package org.comon.chillcounselor.domain.usecase

import org.comon.chillcounselor.domain.util.NetworkStateManager

class CheckNetworkStateUseCase(
    private val networkStateManager: NetworkStateManager
) {

    operator fun invoke() = networkStateManager.checkNetworkState()

}