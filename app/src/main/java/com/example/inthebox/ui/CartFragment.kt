package com.example.inthebox.ui

import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inthebox.R
import com.example.inthebox.data.AppDatabase
import com.example.inthebox.data.entity.CartItem
import com.example.inthebox.ui.adapter.CartAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var totalTextView: TextView
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerView = view.findViewById(R.id.rv_cart)
        totalTextView = view.findViewById(R.id.tv_total)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartAdapter = CartAdapter { item ->
            removeFromCart(item)
        }
        recyclerView.adapter = cartAdapter

        loadCartItems()

        /*Botão de voltar*/
        val btnBack = view.findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

    private fun loadCartItems() {
        lifecycleScope.launch {
            val db = AppDatabase.getInstance(requireContext())
            val cartItems = withContext(Dispatchers.IO) {
                db.cartDao().getAll()
            }
            val total = withContext(Dispatchers.IO) {
                db.cartDao().getTotal() ?: 0.0
            }
            cartAdapter.submitList(cartItems)
            totalTextView.text = "Total: R$ %.2f".format(total)
        }
    }

    private fun removeFromCart(item: CartItem) {
        lifecycleScope.launch {
            val db = AppDatabase.getInstance(requireContext())
            withContext(Dispatchers.IO) {
                db.cartDao().delete(item)
            }
            loadCartItems()
        }
    }
}