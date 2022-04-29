package ir.hajhosseini.azki.business.domain.repository

import ir.hajhosseini.azki.business.data.remote.insurancelistimpimpl.GetInsuranceNetworkDataSourceImpl
import ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels.GetInsuranceListRequestModel
import ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels.GetInsuranceListResponseModel
import ir.hajhosseini.azki.business.domain.state.DataState
import ir.hajhosseini.azki.util.InternetStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// Insurance repository.
// If we had DataBase, we would inject DataBase interface into this class like we did with Retrofit
class InsuranceListRepository
constructor(
    private val retrofitInterface: GetInsuranceNetworkDataSourceImpl,
    private val internetStatus: InternetStatus,
) {
    suspend fun getInsuranceList(
        request: GetInsuranceListRequestModel
    ): Flow<DataState<ArrayList<GetInsuranceListResponseModel>>> =
        flow {
            emit(DataState.Loading)

            // checking internet availability
            try {
                // getting data from server
                val baseNetworkInsurance = retrofitInterface.getInsuranceList(
                    request.marketer,
                    request.vehicleConstructionYear,
                    request.vehicleColorTitle,
                    request.vehicleChangedOwner,
                    request.vehicleTypeID,
                    request.vehicleUsageID,
                    request.vehicleBrandID,
                    request.vehicleModelID,
                    request.zeroKilometer,
                    request.withoutInsure,
                    request.installment,
                    request.sanhabInquiryID,
                    request.oldCompanyID,
                    request.thirdDiscountID,
                    request.driverDiscountID,
                    request.oldInsureUsed,
                    request.oldInsureStartDate,
                    request.oldInsureExpireDate
                )
                emit(DataState.Success(baseNetworkInsurance))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }

        }
}
