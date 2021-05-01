package com.example.mvvmpokemon.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmpokemon.R
import com.example.mvvmpokemon.databinding.FragmentPokemonListBinding
import com.example.mvvmpokemon.databinding.PokemonRowBinding
import com.example.mvvmpokemon.model.PokemonDataModel
import com.example.mvvmpokemon.view.adapter.ItemsAdapter
import com.example.mvvmpokemon.viewmodels.RecyclerPokemonViewModel


class PokemonListFragment : Fragment(), ClickListener {
    lateinit var viewModel: RecyclerPokemonViewModel
    lateinit var binding: FragmentPokemonListBinding
    private var mAdapter: ItemsAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it).get(RecyclerPokemonViewModel::class.java)
            }!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon_list, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // inicializa recyclerview
        mAdapter = ItemsAdapter(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = mAdapter

        //observador de la lista
        viewModel.listState.observe(viewLifecycleOwner) {
            mAdapter?.setItems(list = it)
            binding.progress.isInvisible = true
        }

        viewModel.progressState.observe(viewLifecycleOwner) { show ->
            binding.progress.isVisible = show
        }

        //mAdapter?.setItems(list)
        viewModel.fetchPokemonData()

    }

    override fun itemSelect(data: PokemonDataModel) {
        viewModel.setItemSelection(data)

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(android.R.id.content, PokemonDetailFragment.newInstance())
            ?.addToBackStack(null)
            ?.commit()
    }


}

interface ClickListener {
    fun itemSelect(data: PokemonDataModel)
}