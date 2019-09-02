package com.example.peluchitosapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lista_item.view.*

class InventarioAdapter : RecyclerView.Adapter<InventarioAdapter.InventarioViewHolder>{

    private var lista : List<String> ?= null
    private var context:Context ?= null

    constructor(lista: List<String>?,context: Context?){
        this.lista = lista
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventarioViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.lista_item,parent,false)
        return InventarioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista?.size!!
    }

    override fun onBindViewHolder(holder: InventarioViewHolder, position: Int) {
        var peluchito: String? = lista!![position]
        holder.loadItem(peluchito)
    }

    class InventarioViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){

        fun loadItem(peluchitos : String?){
            itemView.tvPeluchito.text = peluchitos
        }
    }
}
