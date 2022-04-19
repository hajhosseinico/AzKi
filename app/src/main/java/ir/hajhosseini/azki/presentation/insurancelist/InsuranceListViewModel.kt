package ir.hajhosseini.azki.presentation.insurancelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels.GetInsuranceListRequestModel
import ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels.GetInsuranceListResponseModel
import ir.hajhosseini.azki.business.domain.repository.InsuranceListRepository
import ir.hajhosseini.azki.business.domain.state.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * InsuranceListViewModel
 */
@HiltViewModel
class InsuranceListViewModel
@Inject
constructor(
    private val insuranceListRepository: InsuranceListRepository,
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<ArrayList<GetInsuranceListResponseModel>>> =
        MutableLiveData()

    val dataState: LiveData<DataState<ArrayList<GetInsuranceListResponseModel>>>
        get() = _dataState

    fun setStateEvent(
        mainStateEvent: InsuranceListStateEvent,
        request: GetInsuranceListRequestModel
    ) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is InsuranceListStateEvent.GetInsurances -> {
                    // getting data from repository and passing it to fragment
                    insuranceListRepository.getInsuranceList(request)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is InsuranceListStateEvent.None -> {
                    // Do Nothing
                }
            }
        }
    }
}

sealed class InsuranceListStateEvent {
    object GetInsurances : InsuranceListStateEvent()
    object None : InsuranceListStateEvent()
}

