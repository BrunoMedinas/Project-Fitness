package com.example.fitnesspoy

import android.content.Context
import android.content.DialogInterface
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.getSystemService
import com.example.fitnesspoy.model.Calc
import kotlin.concurrent.thread

class ImcActivity : AppCompatActivity() {

    private lateinit var editHeight: EditText
    private lateinit var editWeight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        editHeight = findViewById(R.id.edit_imc_heitght)
        editWeight = findViewById(R.id.edit_imc_weitght)

        val btnSend: Button = findViewById(R.id.btn_imc_send)
        btnSend.setOnClickListener() {
               if (!validate()){
                   Toast.makeText(this,R.string.fields_message, Toast.LENGTH_LONG).show()
                   return@setOnClickListener
               }
            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()
            val result = calc(height, weight)

            val imcResponseId = imcResponse(result)


             AlertDialog.Builder(this)// fique bem esoerto isso é uma class que tem de tudo

            .setTitle(getString(R.string.imc_response, result))
            .setMessage(imcResponseId)
            .setPositiveButton(android.R.string.ok
            ) { _, _ -> }
            .setNegativeButton(R.string.save){ dialog, which ->

                thread {  //muito importante, para fazer roda a img fora do db!
                    val app = application as App
                    val dao = app.db.calcDao()
                    dao.insert(Calc(type = "imc", res = result))
                    runOnUiThread {
                        Toast.makeText(this@ImcActivity, R.string.calc_saved, Toast.LENGTH_LONG).show()
                    }

                }.start()

            }
            .create()
            .show()

            val servise = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            servise.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    @StringRes
    private fun imcResponse(imc : Double) : Int {

      return  when{
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.0 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.normal
            imc < 30.0 -> R.string.imc_high_weight
            imc < 35.0 -> R.string.imc_so_high_weight
            imc < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
      }

    }

    private fun calc(height: Int, weight : Int ) : Double{
         return weight / ((height / 100.0) * (height / 100.0))
    }

    private fun validate(): Boolean {
        return (editWeight.text.toString().isNotEmpty()
            && editHeight.text.toString().isNotEmpty()
            && !editWeight.text.toString().startsWith("0")
            && !editHeight.text.toString().startsWith("0"))
    }
}