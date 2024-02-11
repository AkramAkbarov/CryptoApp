package com.nexis.cryptoapp.fragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ybq.android.spinkit.SpinKitView

import com.nexis.cryptoapp.R
import com.nexis.cryptoapp.adapter.LanguageAdapter


class NewspaperFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val languages = listOf(
        Language("İki Dev Şirketten Bitcoin","Tahmini:2024'te Bunlar Olacak",
            "https://kriptokoin.com/iki-dev-sirketten-bitcoin-tahmini/?utm_source=coingecko&utm_content=coingecko&utm_campaign=coingecko&utm_medium=coingecko&utm_term=coingecko",
            R.drawable.cripto),

        Language("Ethereum ve Bitcoin'in Piyasa", "Dinamiklerindeki Rolü",
            "https://www.bitcoinhaber.net/ethereum-ve-bitcoinin-piyasa-dinamiklerindeki-rolu/?utm_source=coingecko&utm_content=coingecko&utm_campaign=coingecko&utm_medium=coingecko&utm_term=coingecko",
            R.drawable.kriptoe),

        Language("Crypto Capo Altcoin","Patlamasına lşaret Etti! Ancak\n" +
                "Bir Şarti Var ", "https://coin-turk.com/crypto-capo-altcoin-patlamasina-isaret-etti-ancak-bir-sarti-var?utm_source=coingecko&utm_medium=coingecko&utm_campaign=coingecko&utm_content=coingecko&utm_term=coingecko",
            R.drawable.kripto),

        Language("Bitcoin'in Yükseliş eğilimi.", "Michael van de Poppe'nin\n" +
                "Analizi ","https://www.bitcoinhaber.net/bitcoinin-yukselis-egilimi-ve-michael-van-de-poppenin-analizi/?utm_source=coingecko&utm_content=coingecko&utm_campaign=coingecko&utm_medium=coingecko&utm_term=coingecko",
            R.drawable.bitcoinhaberi013),

        Language("Son Zamanların Yıldızı: ERC","404 Coinleri 'Sıradaki Büyük\n" +
                "Şey' mi? ", "https://kriptokoin.com/yildiz-erc-404-coinleri/?utm_source=coingecko&utm_content=coingecko&utm_campaign=coingecko&utm_medium=coingecko&utm_term=coingecko",
            R.drawable.etherium),
        Language("Kaynaklar, ABD'de 110\n" +
                "Milyon Kişinin Izlediği Super","Bowl Etkinliğinde Bitcoin Spot.."
            , "https://www.bitcoinsistemi.com/kaynaklar-abdde-110-milyon-kisinin-izledigi-super-bowl-etkinliginde-bitcoin-spot-etflerin-bulunup-bulunmayacagini-acikladi/?utm_source=coingecko&utm_content=coingecko&utm_campaign=coingecko&utm_medium=coingecko&utm_term=coingecko",
            R.drawable.bitcoinabd),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_newspaper, container, false)

        recyclerView = view.findViewById(R.id.lvProgram)
        recyclerView.layoutManager = LinearLayoutManager(context)


        val spinKitView = view.findViewById<SpinKitView>(R.id.spinKitView)
        Handler().postDelayed({
            spinKitView.visibility = View.GONE
        }, 2000) //

        recyclerView.adapter = LanguageAdapter(languages) { language ->
            // Tıklanan dilin web sitesine yönlendirme
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(language.url)
            startActivity(intent)
        }

        return view
    }
}


