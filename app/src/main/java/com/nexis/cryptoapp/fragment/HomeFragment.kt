package com.nexis.cryptoapp.fragment

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.transition.Visibility
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.GONE
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import androidx.viewpager2.widget.ViewPager2.VISIBLE
import com.google.android.material.tabs.TabLayoutMediator
import com.nexis.cryptoapp.R
import com.nexis.cryptoapp.adapter.TopLossGainPagerAdapter
import com.nexis.cryptoapp.adapter.TopMarketAdapter
import com.nexis.cryptoapp.apis.ApiInterface
import com.nexis.cryptoapp.apis.ApiUtilities
import com.nexis.cryptoapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private lateinit var binding :FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater)

        getTopCurrencyList()
        setTabLayout()
        return binding.root



    }


    private fun setTabLayout() {
        val adapter =TopLossGainPagerAdapter(this)
        binding.contentViewPager.adapter=adapter
        binding.contentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.topGainIndicator.visibility = View.VISIBLE
                    binding.topLoseIndicator.visibility = View.GONE
                } else {
                    binding.topGainIndicator.visibility = View.GONE
                    binding.topLoseIndicator.visibility = View.VISIBLE
                }
            }

        })

        TabLayoutMediator(binding.tabLayout,binding.contentViewPager){
            tab,positon ->
            var title = if (positon==0){
                "Top Gainers"
            }else{
                "Top Losers"
            }
            tab.text =title
        }.attach()
    }

    private fun getTopCurrencyList() {
        lifecycleScope.launch (Dispatchers.IO){
            val res = ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData()

            withContext(Dispatchers.Main){
                binding.topCurrencyRecyclerView.adapter=TopMarketAdapter(requireContext(),res.body()!!.data.cryptoCurrencyList)
            }


            Log.d("SHUBH","getTopCurrencyList: ${res.body()!!.data.cryptoCurrencyList}")
        }
    }


}