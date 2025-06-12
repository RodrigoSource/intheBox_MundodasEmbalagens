package com.example.inthebox.ui

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inthebox.R
import com.example.inthebox.data.AppDatabase
import com.example.inthebox.data.dao.CartDao
import com.example.inthebox.ui.adapter.LastPurchaseAdapter
import kotlinx.coroutines.launch
import android.widget.ImageButton

class LastPurchaseFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvTotal: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_last_purchase, container, false)

        // Botão de voltar
        val btnBack = view.findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rv_last_purchase)
        tvTotal = view.findViewById(R.id.tv_total_purchase)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        lifecycleScope.launch {
            val dao = AppDatabase.getInstance(requireContext()).cartDao()
            val items = dao.getAll()
            val total = dao.getTotal() ?: 0.0

            recyclerView.adapter = LastPurchaseAdapter(items)
            tvTotal.text = "Total: R$ %.2f".format(total)
        }
    }
}