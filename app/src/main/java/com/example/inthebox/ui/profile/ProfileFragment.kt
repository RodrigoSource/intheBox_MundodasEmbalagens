package com.example.inthebox.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.inthebox.R
import com.example.inthebox.data.AppDatabase
import kotlinx.coroutines.launch



class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val sharedPref = requireContext().getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val email = sharedPref.getString("logged_in_email", null)

        if (email != null) {
            lifecycleScope.launch {
                val user = AppDatabase.getInstance(requireContext()).userDao().getByEmail(email)
                view.findViewById<TextView>(R.id.tv_profile_name).text = user?.firstName
                view.findViewById<TextView>(R.id.tv_profile_email).text = user?.email
            }
        }
        /*Botão de voltar*/
        val btnBack = view.findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}