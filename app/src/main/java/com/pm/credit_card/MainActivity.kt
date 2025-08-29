package com.pm.credit_card

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.pm.credit_card.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentCardNumber = ""
    private var currentExpiry = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCardNumberFormatter()
        setupExpiryFormatter()
        setupValidations()
    }

    private fun setupCardNumberFormatter() {
        binding.editCardNumber.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false

            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return

                s?.let {
                    val digitsOnly = it.toString().replace(Regex("\\D"), "")
                    val limited = if (digitsOnly.length > 16) digitsOnly.take(16) else digitsOnly
                    val formatted = limited.chunked(4).joinToString(" ")

                    if (formatted != currentCardNumber) {
                        currentCardNumber = formatted
                        isUpdating = true
                        binding.editCardNumber.setText(formatted)
                        binding.editCardNumber.setSelection(formatted.length)
                        isUpdating = false
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupExpiryFormatter() {
        binding.editMonthAndYear.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false

            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return

                s?.let {
                    val digitsOnly = it.toString().replace(Regex("\\D"), "")
                    val limited = if (digitsOnly.length > 4) digitsOnly.take(4) else digitsOnly

                    val formatted = when {
                        limited.length >= 3 -> limited.substring(0, 2) + "/" + limited.substring(2)
                        limited.length >= 1 -> limited
                        else -> ""
                    }

                    if (formatted != currentExpiry) {
                        currentExpiry = formatted
                        isUpdating = true
                        binding.editMonthAndYear.setText(formatted)
                        binding.editMonthAndYear.setSelection(formatted.length)
                        isUpdating = false
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupValidations() {
        binding.editCardNumber.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val digits = binding.editCardNumber.text.toString().replace(" ", "")
                if (digits.length != 16) {
                    binding.editCardNumber.error = "Número do cartão deve ter 16 dígitos"
                }
            }
        }

        binding.editCardHolder.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val name = binding.editCardHolder.text.toString().trim()
                if (name.length < 3) {
                    binding.editCardHolder.error = "Nome deve ter pelo menos 3 caracteres"
                }
            }
        }

        binding.editMonthAndYear.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val expiry = binding.editMonthAndYear.text.toString()
                if (!expiry.matches(Regex("^(0[1-9]|1[0-2])/\\d{2}\$"))) {
                    binding.editMonthAndYear.error = "Data deve estar no formato MM/AA"
                }
            }
        }

        binding.editCvv.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val cvv = binding.editCvv.text.toString()
                if (cvv.length != 3) {
                    binding.editCvv.error = "CVV deve ter 3 dígitos"
                }
            }
        }
    }
}


