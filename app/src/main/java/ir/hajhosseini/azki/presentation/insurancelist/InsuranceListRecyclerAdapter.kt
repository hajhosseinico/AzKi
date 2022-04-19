package ir.hajhosseini.azki.presentation.insurancelist


import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.hajhosseini.azki.R.string.insurance_company
import ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels.GetInsuranceListResponseModel
import ir.hajhosseini.azki.databinding.ItemInsuranceListBinding
import ir.hajhosseini.azki.util.PersianNumberConverter
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class InsuranceListRecyclerAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GetInsuranceListResponseModel>() {

        override fun areItemsTheSame(
            oldItem: GetInsuranceListResponseModel,
            newItem: GetInsuranceListResponseModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetInsuranceListResponseModel,
            newItem: GetInsuranceListResponseModel
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding =
            ItemInsuranceListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            itemBinding,
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is ViewHolder -> {
                holder.bind(differ.currentList[position], position == differ.currentList.size - 1)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: ArrayList<GetInsuranceListResponseModel>) {
        differ.submitList(list)
    }

    class ViewHolder
    constructor(
        private val itemBinding: ItemInsuranceListBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: GetInsuranceListResponseModel, isLastLayout: Boolean) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            Glide.with(itemView.context)
                .load(item.icon)
                .into(itemBinding.imgCompanyLogo)

            val companyName = itemView.context.getString(insurance_company) + item.title
            itemBinding.txtCompanyName.text = companyName

            val price = item.prices[0].price
            itemBinding.txtPrice.text =
                PersianNumberConverter().setPersianNumbers(NumberFormat.getInstance(Locale("en", "US")).format((price - (price / 10)).toInt()))

            itemBinding.txtDiscountedPrice.text =
                PersianNumberConverter().setPersianNumbers(NumberFormat.getInstance(Locale("en", "US")).format(price.toInt()))
            itemBinding.txtDiscountedPrice.paintFlags =
                itemBinding.txtDiscountedPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


            val r = Random()
            val randomValue: Double = 1.00f + (5.00f - 1.00f) * r.nextDouble()
            itemBinding.txtRate.text = PersianNumberConverter().setPersianNumbers(
                DecimalFormat("##.##").format(randomValue).toString()
            )

            val randomPeople = (1000..10000).random()
            val text = """از ۵ ($randomPeople نظر)"""
            itemBinding.txtRateDesc.text = PersianNumberConverter().setPersianNumbers(text)
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: GetInsuranceListResponseModel)
    }
}


