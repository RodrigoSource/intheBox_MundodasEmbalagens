package com.example.inthebox.ui.product

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.inthebox.R
import com.example.inthebox.data.AppDatabase
import com.example.inthebox.data.entity.CartItem
import com.example.inthebox.data.model.Product
import com.example.inthebox.ui.adapter.ImageSliderAdapter
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment() {

    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.getSerializable("product") as? Product
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)

        product?.let { item ->
            view.findViewById<TextView>(R.id.tv_detail_name).text = item.name
            view.findViewById<TextView>(R.id.tv_detail_price).text = "R$ %.2f".format(item.price)
            view.findViewById<TextView>(R.id.tv_detail_dims).text =
                "H: ${item.height} cm\nW: ${item.width} cm\nL: ${item.length} cm"

            val viewPager = view.findViewById<ViewPager2>(R.id.vp_images)
            viewPager.adapter = ImageSliderAdapter(item.imageUrls)
            // Botão de voltar
            val btnBack = view.findViewById<ImageButton>(R.id.btn_back)
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            val btnAddToCart = view.findViewById<Button>(R.id.btn_add_to_cart)
            btnAddToCart.setOnClickListener {
                lifecycleScope.launch {
                    val db = AppDatabase.getInstance(requireContext())
                    val cartItem = CartItem(
                        name = item.name,
                        price = item.price,
                        height = item.height,
                        width = item.width,
                        length = item.length,
                        imageUrl = item.imageUrls.firstOrNull() ?: ""
                    )
                    db.cartDao().insert(cartItem)
                    Toast.makeText(requireContext(), "Product added to cart", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }
}