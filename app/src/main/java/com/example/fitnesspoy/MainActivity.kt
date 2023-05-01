package com.example.fitnesspoy

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(){

    private lateinit var rvMain : RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItem = mutableListOf<MainItem>()

        mainItem.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.ic_baseline_light_mode_24,
                textStringId = R.string.label_imc,
                color = Color.GREEN
            )
        )
        mainItem.add(
            MainItem(
                id = 2,
                drawableId = R.drawable.ic_baseline_remove_red_eye_24,
                textStringId = R.string.label_tmd,
                color = Color.YELLOW
            )
        )




                             // METADO 2 IMPL VIA OBJETO ANONIMO
//        val adapter = MainAdapter(mainItem, object : OnItemClickListener{
//            override fun onClick(id: Int) {
//                when(id){
//                    1 -> {
//                        val intent = Intent(this@MainActivity, ImcActivity :: class.java)
//                        startActivity(intent)
//                    }
//                    2 -> {
//                        // segundo objeto
//                    }
//                }
//            }
//
//        })

        val adapter = MainAdapter(mainItem) { id ->
            when(id){
                1 -> {
                    val intent = Intent(this@MainActivity, ImcActivity :: class.java)
                    startActivity(intent)
                }
                2 -> {
                    // segundo objeto
                }
            }
        }
        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = GridLayoutManager(this, 2)
        }


    //Metodo 1 usando a interface

//    override fun onClick(id: Int) {
//        when(id){
//            1 -> {
//                val intent = Intent(this, ImcActivity :: class.java)
//                startActivity(intent)
//            }
//            2 -> {
//                // segundo objeto
//            }
//        }
//       Log.i("Test", "Clicou!!, $id")
//    }


    //class responsavel para adaptar meu layout na recycleView
    private inner class MainAdapter(private val mainItem : List<MainItem>,
//                                    private val onItemClickListener : OnItemClickListener
                                                              ///uma fun do tipo int que
                                                             /// nao retorna nada = Unit
                                    private val onItemClickListener : (Int) -> Unit,
    ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

        //Qual é o leyout  XML da celula especifica (item)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        // Dispara toda vez que houver uma rolagem na tela e for necessario trocar o conteudo da celula
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itemCurrent = mainItem[position]
            holder.bind(itemCurrent)
        }

        // informa quantas celulas essa listagem terá
        override fun getItemCount(): Int {
            return mainItem.size
        }

        // é a class da celula em si!!
        private inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            @SuppressLint("SuspiciousIndentation")
            fun bind(item : MainItem){
                val img : ImageView = itemView.findViewById(R.id.item_img_icon)
                val name : TextView = itemView.findViewById(R.id.item_txt_name)
                val container : LinearLayout = itemView.findViewById(R.id.item_container_imc)

                img.setImageResource(item.drawableId)
                name.setText(item.textStringId)
                container.setBackgroundColor(item.color)

                container.setOnClickListener{

                    //aqui ele é uma ref. função
                    //invoke ele vai executa a função ele invoca a função
                    onItemClickListener.invoke(item.id)

                    //aqui ele é uma ref. interface
                    // onItemClickListener.onClick(item.id)
                }
            }
        }
    }
}