package com.example.mvvmpokemon.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvmpokemon.R
import com.example.mvvmpokemon.databinding.FragmentPokemonDetailBinding
import com.example.mvvmpokemon.viewmodels.RecyclerPokemonViewModel


class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding

    lateinit var viewModel: RecyclerPokemonViewModel

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
        _binding =  FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.itemDataSelected?.let { data ->
            Glide.with(requireContext()).load(data.img).into(binding!!.img)
            binding!!.tvName.text = data.name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(

        ) = PokemonDetailFragment()
    }

}