package com.hsofiamunoz.deudoresapp2.ui.update

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hsofiamunoz.deudoresapp2.DeudoresApp2
import com.hsofiamunoz.deudoresapp2.R
import com.hsofiamunoz.deudoresapp2.data.dao.DebtorDao
import com.hsofiamunoz.deudoresapp2.data.entities.Debtor
import com.hsofiamunoz.deudoresapp2.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    companion object {
        fun newInstance() = UpdateFragment()
    }

    private lateinit var updateViewModel: UpdateViewModel
    private var _binding : FragmentUpdateBinding ?= null

    private val binding get() = _binding!!

    // Variables
    private var isSearching = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        updateViewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)
        _binding = FragmentUpdateBinding.inflate(inflater,container,false)

        val root : View = binding.root

        var idDebtor = 0
        // Procedimiento

        binding.updateButton.setOnClickListener {

            val debtorDao : DebtorDao = DeudoresApp2.database.DebtorDao()
            val name = binding.nameEditText.text.toString()

            if(isSearching){ // Buscando

                val debtor: Debtor = debtorDao.readDebtor(name)

                if(debtor != null){
                    idDebtor = debtor.id
                    binding.amountEditText.setText(debtor.amount.toString())
                    binding.phoneEditText.setText(debtor.phone)
                    binding.updateButton.text = getString(R.string.title_update)
                    isSearching = false

                }
                else{
                    Toast.makeText(requireContext(), "No existe", Toast.LENGTH_SHORT).show()
                    cleanWidgets()
                }
            }
            else { //Actualizando
                val debtor: Debtor = Debtor(id = idDebtor,
                    name = binding.nameEditText.text.toString(),
                    phone = binding.phoneEditText.text.toString(),
                    amount = binding.amountEditText.text.toString().toLong()
                    )
                debtorDao.updateDebtor(debtor)
                binding.updateButton.text = getString(R.string.buscar_hint)
                isSearching = true
                cleanWidgets()
                Toast.makeText(requireContext(),"Deudor actualizado",Toast.LENGTH_SHORT).show()

            }
        }

        return root
    }

    private fun cleanWidgets() {
        with(binding){
            nameEditText.setText("")
            phoneEditText.setText("")
            amountEditText.setText("")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)

    }

}