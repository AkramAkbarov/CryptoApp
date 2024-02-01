package com.nexis.cryptoapp.fragment

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.nexis.cryptoapp.R
import com.nexis.cryptoapp.apis.ApiInterface
import com.nexis.cryptoapp.apis.ApiUtilities
import com.nexis.cryptoapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding :FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

        getTopCurrencyList()
    }

    private fun getTopCurrencyList() {
        lifecycleScope.launch (Dispatchers.IO){
            val res = ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData()

            Log.d("SHUBH","getTopCurrencyList: ${res.body()!!.data.cryptoCurrencyList}")
        }
    }


}