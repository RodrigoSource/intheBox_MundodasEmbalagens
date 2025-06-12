package com.example.inthebox.ui

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.inthebox.R

class OnboardingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.activity_onboarding, container, false)

        // Navegação após 3 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_onboarding_to_login)
        }, 3000)

        // Aplicar gradiente ao texto "Mundo das Embalagens"
        val textView = view.findViewById<TextView>(R.id.tv_logo_title)
        val shader = LinearGradient(
            0f, 0f, 0f, textView.textSize,
            intArrayOf(
                Color.parseColor("#2196F3"), // Azul claro
                Color.parseColor("#0D47A1")  // Azul escuro
            ),
            null,
            Shader.TileMode.CLAMP
        )
        textView.paint.shader = shader

        return view
    }
}