package ir.hajhosseini.azki.presentation.insurancelist

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.hajhosseini.azki.R
import ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels.GetInsuranceListRequestModel
import ir.hajhosseini.azki.business.domain.model.getinsurancelistmodels.GetInsuranceListResponseModel
import ir.hajhosseini.azki.business.domain.state.DataState
import ir.hajhosseini.azki.databinding.FragmentInsuranceListBinding
import ir.hajhosseini.azki.presentation.App
import ir.hajhosseini.azki.util.TopSpacingItemDecoration
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

/**
 *  Mixed Clean and MVVM architecture
 *  The flow is like ->
 *  View request an api call from view model and view model will call the repository with coroutine,
 *  repository will call the api and will emmit the response to view model. After that view
 *  model will pass the response throw a live data object to the view.
 */

@AndroidEntryPoint
class InsuranceListFragment : Fragment(R.layout.fragment_insurance_list),
    InsuranceListRecyclerAdapter.Interaction {

    private var _binding: FragmentInsuranceListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InsuranceListViewModel by viewModels()
    private lateinit var insuranceListRecyclerAdapter: InsuranceListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsuranceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        subscribeObservers()
        setTabBar()

        viewModel.setStateEvent(InsuranceListStateEvent.GetInsurances, App.insuranceListRequestModel)

        binding.swipeRefresh.setOnRefreshListener {
            displayProgressBar(true)
            viewModel.setStateEvent(InsuranceListStateEvent.GetInsurances, App.insuranceListRequestModel)
        }
    }

    private fun setTabBar() {
        binding.tabLayout.addTab(binding.tabLayout.newTab()
            .setIcon(R.drawable.ic_filter))
        binding.tabLayout.getTabAt(0)?.text =
            resources.getStringArray(R.array.tab_names)[0].toString()


        binding.tabLayout.addTab(binding.tabLayout.newTab()
            .setIcon(R.drawable.ic_sort))
        binding.tabLayout.getTabAt(1)?.text =
            resources.getStringArray(R.array.tab_names)[1].toString()


        binding.tabLayout.addTab(
            binding.tabLayout.newTab()
                .setIcon(R.drawable.ic_world)
        )
        binding.tabLayout.getTabAt(2)?.text =
            resources.getStringArray(R.array.tab_names)[2].toString()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            insuranceListRecyclerAdapter = InsuranceListRecyclerAdapter()

            val alphaAdapter = AlphaInAnimationAdapter(insuranceListRecyclerAdapter)
            adapter = ScaleInAnimationAdapter(alphaAdapter).apply {
                setDuration(300)
                setFirstOnly(false)
            }
        }
    }

    // onItemSelected of the recycler view
    override fun onItemSelected(position: Int, item: GetInsuranceListResponseModel) {
        Toast.makeText(
            requireContext(),
            "Company is: " + item.title + " and position is: " + position,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<ArrayList<GetInsuranceListResponseModel>> -> {
                    // getting response and handling it
                    displayProgressBar(false)
                    insuranceListRecyclerAdapter.submitList(dataState.data)
                }
                is DataState.Error -> {
                    // getting error and handling it
                    displayProgressBar(false)
                    Toast.makeText(
                        requireContext(),
                        dataState.exception.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }

                is DataState.Loading -> {
                    // show loading
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayProgressBar(shouldDisplay: Boolean) {
        binding.prgLoading.visibility =
            if (shouldDisplay) View.VISIBLE else View.GONE
        binding.swipeRefresh.isRefreshing = false

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}