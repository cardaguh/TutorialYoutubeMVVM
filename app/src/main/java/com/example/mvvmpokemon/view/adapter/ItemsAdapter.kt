package com.example.mvvmpokemon.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpokemon.R
import com.example.mvvmpokemon.databinding.PokemonRowBinding
import com.example.mvvmpokemon.model.PokemonDataModel
import com.example.mvvmpokemon.view.fragments.ClickListener
import com.example.mvvmpokemon.view.viewholder.ItemViewHolder

class ItemsAdapter(private val listener: ClickListener): RecyclerView.Adapter<ItemViewHolder>() {
    private val resource = R.layout.pokemon_row
    lateinit var context: Context
    private val itemList = mutableListOf<PokemonDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: PokemonRowBinding =
            DataBindingUtil.inflate(layoutInflater, resource, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setItem(itemList[position])

        holder.itemView.setOnClickListener {
            listener.itemSelect(itemList[position])
        }
    }

    fun setItems(list: MutableList<PokemonDataModel>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}