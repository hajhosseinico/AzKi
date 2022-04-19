package ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FeaturesModel(
    @SerializedName("icon")
    @Expose
    var icon: String,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("description")
    @Expose
    var description: String,
)
