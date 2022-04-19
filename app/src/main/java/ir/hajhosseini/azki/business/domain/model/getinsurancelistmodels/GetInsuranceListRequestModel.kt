package ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels

// just for calling the api
// In production, we will post this kind of requests
data class GetInsuranceListRequestModel(
    var marketer: Boolean,
    var vehicleConstructionYear: Int,
    var vehicleColorTitle: String,
    var vehicleChangedOwner: Int,
    var vehicleTypeID: Int,
    var vehicleUsageID: Int,
    var vehicleBrandID : Int,
    var vehicleModelID: Int,
    var zeroKilometer: Boolean,
    var withoutInsure: Boolean,
    var installment: Boolean,
    var sanhabInquiryID: String,
    var oldCompanyID: Int,
    var thirdDiscountID: Int,
    var driverDiscountID: Int,
    var oldInsureUsed: Boolean,
    var oldInsureStartDate: String,
    var oldInsureExpireDate: String,
)
