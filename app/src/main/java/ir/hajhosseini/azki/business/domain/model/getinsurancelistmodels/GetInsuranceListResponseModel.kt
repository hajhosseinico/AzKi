package ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// response entity model
data class GetInsuranceListResponseModel(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("icon")
    @Expose
    var icon: String,
    @SerializedName("satisfaction")
    @Expose
    var satisfaction: Double,
    @SerializedName("wealthLevel")
    @Expose
    var wealthLevel: Double,
    @SerializedName("complaintResponseTime")
    @Expose
    var complaintResponseTime: Double,
    @SerializedName("branchNumber")
    @Expose
    var branchNumber: Int,
    @SerializedName("discountTitle")
    @Expose
    var discountTitle: String,
    @SerializedName("description")
    @Expose
    var description: String,
    @SerializedName("giftTitle")
    @Expose
    var giftTitle: String,
    @SerializedName("hasGift")
    @Expose
    var hasGift: Boolean,
    @SerializedName("prices")
    @Expose
    var prices: ArrayList<PriceModel>,
)
