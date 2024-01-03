/*package com.example.stustay.Ui.Fragments

import LogementAdapter
import LogementViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager


import com.example.stustay.databinding.FragmentLogementListBinding


class LogementListFragment : Fragment() {

    private lateinit var binding: FragmentLogementListBinding
    private lateinit var logementAdapter: LogementAdapter
    private val logementViewModel: LogementViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogementListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialiser RecyclerView
        logementAdapter = LogementAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = logementAdapter
        }

        // Observer pour mettre à jour la liste des logements
        logementViewModel.logements.observe(viewLifecycleOwner, Observer { logements ->
            logementAdapter.submitList(logements)
        })

        // Appeler la méthode pour obtenir tous les logements
        logementViewModel.getAllLogements()
    }
}
*/