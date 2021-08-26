package com.hsofiamunoz.deudoresapp2.ui.Create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hsofiamunoz.deudoresapp2.DeudoresApp2
import com.hsofiamunoz.deudoresapp2.data.dao.DebtorDao
import com.hsofiamunoz.deudoresapp2.data.entities.Debtor
import com.hsofiamunoz.deudoresapp2.databinding.FragmentCreateBinding
import java.sql.Types.NULL

class CreateFragment : Fragment() {

    private lateinit var createViewModel: CreateViewModel
    private var _binding: FragmentCreateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createViewModel =
            ViewModelProvider(this).get(CreateViewModel::class.java)

        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textDashboard
        createViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        with(binding){
            createButton.setOnClickListener {
                val name = nameEditText.text.toString()
                val phone = phoneEditrText.text.toString()
                val amount = amountEditText.text.toString().toLong()

                createDebtor(name,phone,amount)
            }

        }

        return root
    }

    private fun createDebtor(name: String, phone: String, amount: Long) {
        val debtor = Debtor(id = NULL,name = name, phone = phone, amount = amount)
        val debtorDAO : DebtorDao = DeudoresApp2.database.DebtorDao()
        debtorDAO.createDebtor(debtor)
        cleanViews()


    }

    private fun cleanViews() {
        with(binding){
            nameEditText.setText("")
            phoneEditrText.setText("")
            amountEditText.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}