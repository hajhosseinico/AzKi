package ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PriceModel(
    @SerializedName("totalPenalty")
    @Expose
    var totalPenalty: Double,
    @SerializedName("penaltyAmount")
    @Expose
    var penaltyAmount: Double,
    @SerializedName("penaltyDays")
    @Expose
    var penaltyDays: Int,
    @SerializedName("durationID")
    @Expose
    var durationID: Int,
    @SerializedName("durationTitle")
    @Expose
    var durationTitle: String,
    @SerializedName("coverID")
    @Expose
    var coverID: Int,
    @SerializedName("coverAmount")
    @Expose
    var coverAmount: Double,
    @SerializedName("price")
    @Expose
    var price: Double,
    @SerializedName("discountedPrice")
    @Expose
    var discountedPrice: Double,
    @SerializedName("installmentPrice")
    @Expose
    var installmentPrice: Double,
    @SerializedName("features")
    @Expose
    var features: ArrayList<FeaturesModel>,
)
