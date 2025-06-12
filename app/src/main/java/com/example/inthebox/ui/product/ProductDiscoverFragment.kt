package com.example.inthebox.ui.product

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inthebox.R
import com.example.inthebox.data.model.Product
import com.example.inthebox.ui.adapter.ProductAdapter

class ProductDiscoverFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar

    private val sampleProducts = listOf(
        Product(
            name = "Small Box - N1",
            price = 13.99,
            height = 20,
            width = 25,
            length = 22,
            imageUrls = listOf(
                "https://via.placeholder.com/300x200.png?text=Big+Box+1",
                "https://via.placeholder.com/300x200.png?text=Big+Box+2",
                "https://via.placeholder.com/300x200.png?text=Big+Box+3")
        ),
        Product(
            name = "Small Box - N2",
            price = 24.99,
            height = 22,
            width = 28,
            length = 24,
            imageUrls = listOf(
                "https://via.placeholder.com/300x200.png?text=Big+Box+1",
                "https://via.placeholder.com/300x200.png?text=Big+Box+2",
                "https://via.placeholder.com/300x200.png?text=Big+Box+3")
        ),
        Product(
            name = "Mid Box - N1",
            price = 13.99,
            height = 30,
            width = 35,
            length = 28,
            imageUrls = listOf(
                "https://imgur.com/FFRnezJ",
                "https://imgur.com/wLCfeMf",
                "https://imgur.com/BLQfL4y")
        ),
        Product(
            name = "Big Box",
            price = 24.99,
            height = 40,
            width = 45,
            length = 35,
            imageUrls = listOf(
                "https://via.placeholder.com/300x200.png?text=Big+Box+1",
                "https://via.placeholder.com/300x200.png?text=Big+Box+2",
                "https://via.placeholder.com/300x200.png?text=Big+Box+3")
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_product_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        drawerLayout = requireActivity().findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
            requireActivity(),
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        recyclerView = view.findViewById(R.id.rv_products)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val adapter = ProductAdapter(sampleProducts) { product ->
            val fragment = ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("product", product)
                }
            }

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerView.adapter = adapter
    }
}