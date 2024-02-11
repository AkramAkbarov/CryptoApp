package com.nexis.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexis.cryptoapp.R
import com.nexis.cryptoapp.fragment.Language

class LanguageAdapter(
    private val languages: List<Language>,
    private val onItemClickListener: (Language) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.newspaper_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val language = languages[position]
        holder.textView.text = language.name
        holder.textView2.text = language.forname
        holder.imageView.setImageResource(language.image)
        holder.itemView.setOnClickListener {
            onItemClickListener(language)
        }
    }

    override fun getItemCount(): Int {
        return languages.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView1)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}