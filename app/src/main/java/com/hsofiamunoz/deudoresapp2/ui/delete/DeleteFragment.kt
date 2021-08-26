package com.hsofiamunoz.deudoresapp2.ui.delete

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.system.Os.accept
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hsofiamunoz.deudoresapp2.DeudoresApp2
import com.hsofiamunoz.deudoresapp2.R
import com.hsofiamunoz.deudoresapp2.data.dao.DebtorDao
import com.hsofiamunoz.deudoresapp2.data.entities.Debtor
import com.hsofiamunoz.deudoresapp2.databinding.FragmentDeleteBinding

class DeleteFragment : Fragment() {

    companion object {
        fun newInstance() = DeleteFragment()
    }

    private lateinit var deleteViewModel: DeleteViewModel
    private var _binding: FragmentDeleteBinding ?= null

    private val binding get() = _binding!!
    //private lateinit var viewModel: DeleteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deleteViewModel = ViewModelProvider(this).get(DeleteViewModel::class.java)
        _binding = FragmentDeleteBinding.inflate(inflater,container,false)
        val root : View = binding.root


        //Procedimiento
        binding.deleteButton.setOnClickListener {
            deleteDebtor(binding.nameEditText.text.toString())
        }

        return root
    }

    private fun deleteDebtor(name: String) {
        val debtorDao: DebtorDao = DeudoresApp2.database.DebtorDao()
        val debtor : Debtor = debtorDao.readDebtor(name)

        if(debtor != null){
            val alertDialog: AlertDialog? = activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setTitle(R.string.title_delete)
                    setMessage("Desea eliminar a: " + debtor.name + ", su deuda es: "+ debtor.amount.toString() + "?")
                    setPositiveButton(R.string.accept){
                            dialog,id ->
                            debtorDao.deleteDebtor(debtor)
                            Toast.makeText(requireContext(),"Deudor eliminado",Toast.LENGTH_SHORT).show()
                            binding.nameEditText.setText("")
                    }
                    setNegativeButton(R.string.negative){ dialog,id ->
                        binding.nameEditText.setText("")
                    }
                    create()
                }
                builder.create()
            }
            alertDialog?.show()


        }
        else{
            Toast.makeText(requireContext(),"No existe",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(DeleteViewModel::class.java)

    }

}