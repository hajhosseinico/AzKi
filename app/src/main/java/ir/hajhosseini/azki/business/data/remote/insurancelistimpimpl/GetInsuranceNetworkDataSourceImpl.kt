package ir.hajhosseini.azki.business.data.remote.insurancelistimpimpl

import ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels.GetInsuranceListResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GetInsuranceNetworkDataSourceImpl {

    @Headers("deviceID: 2")
    @GET("/api/product/third/prices")
    suspend fun getInsuranceList(
        @Query("marketer") marketer: Boolean,
        @Query("vehicleConstructionYear") vehicleConstructionYear: Int,
        @Query("vehicleColorTitle") vehicleColorTitle: String,
        @Query("vehicleChangedOwner") vehicleChangedOwner: Int,
        @Query("vehicleTypeID") vehicleTypeID: Int,
        @Query("vehicleUsageID") vehicleUsageID: Int,
        @Query("vehicleBrandID") vehicleBrandID : Int,
        @Query("vehicleModelID") vehicleModelID: Int,
        @Query("zeroKilometer") zeroKilometer: Boolean,
        @Query("withoutInsure") withoutInsure: Boolean,
        @Query("installment") installment: Boolean,
        @Query("sanhabInquiryID") sanhabInquiryID: String,
        @Query("oldCompanyID") oldCompanyID: Int,
        @Query("thirdDiscountID") thirdDiscountID: Int,
        @Query("driverDiscountID") driverDiscountID: Int,
        @Query("oldInsureUsed") oldInsureUsed: Boolean,
        @Query("oldInsureStartDate") oldInsureStartDate: String,
        @Query("oldInsureExpireDate") oldInsureExpireDate: String,
    ): ArrayList<GetInsuranceListResponseModel>
}





























