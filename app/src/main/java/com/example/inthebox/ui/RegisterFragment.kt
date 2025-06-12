package com.example.inthebox.ui

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.inthebox.R
import com.example.inthebox.data.AppDatabase
import com.example.inthebox.data.model.User
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val etFirstName = view.findViewById<EditText>(R.id.et_first_name)
        val etLastName = view.findViewById<EditText>(R.id.et_last_name)
        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etPassword = view.findViewById<EditText>(R.id.et_password)
        val btnRegister = view.findViewById<Button>(R.id.btn_register)
        val tvLoginNow = view.findViewById<TextView>(R.id.tv_login_now)

        btnRegister.setOnClickListener {
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password
                )

                lifecycleScope.launch {
                    val db = AppDatabase.getInstance(requireContext())
                    val existing = db.userDao().getByEmail(email)

                    if (existing != null) {
                        Toast.makeText(requireContext(), "Email already registered", Toast.LENGTH_SHORT).show()
                    } else {
                        db.userDao().insert(user)
                        Toast.makeText(requireContext(), "User registered", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_register_to_login)
                    }
                }
            }
        }

        tvLoginNow.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_login)
        }

        return view
    }
}