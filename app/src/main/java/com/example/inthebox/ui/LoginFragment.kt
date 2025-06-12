package com.example.inthebox.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.inthebox.R
import com.example.inthebox.data.AppDatabase
import com.example.inthebox.ui.MainActivity
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etPassword = view.findViewById<EditText>(R.id.et_password)
        val btnLogin = view.findViewById<Button>(R.id.btn_login)
        val tvRegisterNow = view.findViewById<TextView>(R.id.tv_register_now)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val db = AppDatabase.getInstance(requireContext())
                    val user = db.userDao().authenticate(email, password)

                    if (user != null) {
                        Toast.makeText(requireContext(), "Welcome, ${user.firstName}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    } else {
                        Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        tvRegisterNow.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        return view
    }
}