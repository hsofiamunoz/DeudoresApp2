package com.hsofiamunoz.deudoresapp2.ui.List

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hsofiamunoz.deudoresapp2.DeudoresApp2
import com.hsofiamunoz.deudoresapp2.data.dao.DebtorDao
import com.hsofiamunoz.deudoresapp2.data.entities.Debtor
import com.hsofiamunoz.deudoresapp2.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var homeViewModel: ListViewModel
    private var _binding: FragmentListBinding? = null

    private lateinit var debtorsAdapter: DebtorsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

       // val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
           // textView.text = it
        })


        // apply permite utilizar varios atributos y cosas en la misma linea
        debtorsAdapter = DebtorsAdapter(onItemClicked = { onDebtorItemClicked(it) })
        binding.debtorRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = debtorsAdapter
            setHasFixedSize(false)
        }

        // A conitnuacion se agrega la lista
        val debtorDAO : DebtorDao = DeudoresApp2.database.DebtorDao()
        val listaDebtors : MutableList<Debtor> = debtorDAO.getDebtors()
        debtorsAdapter.appendItems(listaDebtors)


        return root
    }

    private fun onDebtorItemClicked(debtor: Debtor) {
        // LLamar al detalle
        findNavController().navigate(ListFragmentDirections.actionNavigationListToDetailFragment(debtor = debtor))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}