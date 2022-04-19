package ir.hajhosseini.azki.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels.GetInsuranceListRequestModel

@HiltAndroidApp
class App : Application(){

    companion object {
        // In production application, we would fill this data from users not with hardcoded data
        @JvmStatic val insuranceListRequestModel = GetInsuranceListRequestModel(
            false,
            1399,
            "red",
            0,
            6,
            1,
            84,
            806662,
            zeroKilometer = false,
            withoutInsure = false,
            installment = false,
            sanhabInquiryID = "",
            oldCompanyID = 7,
            thirdDiscountID = 6,
            driverDiscountID = 6,
            oldInsureUsed = false,
            oldInsureStartDate = "2020-07-03",
            oldInsureExpireDate = "2021-05-20"
        )
    }
}