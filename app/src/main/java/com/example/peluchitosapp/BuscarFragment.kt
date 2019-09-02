package com.example.peluchitosapp


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_buscar.view.*
import java.lang.ClassCastException


class BuscarFragment : Fragment() {

    var interfaz : ComunicadorBuscarMain ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_buscar, container, false)
        var key = arguments?.getString("key")
        var id = arguments?.getString("id")
        var nombre = arguments?.getString("nombre")
        var cantidad = arguments?.getString("cantidad")
        var precio = arguments?.getString("precio")

        view.btBuscar.setOnClickListener {
            var nombre = view.etNombre.text.toString()
            if(nombre.isEmpty()){
                Toast.makeText(this.context,"Ingrese el nombre",Toast.LENGTH_SHORT).show()
            }else{
                interfaz?.enviardatos2(nombre)
                view.etNombre.text.clear()
            }
        }
        if(key.equals("True")){
            view.tvMostrar.text = "ID : "+id+"\n"+"Nombre : "+nombre+"\n"+"Cantidad : "+cantidad+"\n"+"Precio : "+precio
        }else if(key.equals("False")){
            view.tvMostrar.text = "Elemento no encontrado"
        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            interfaz = context!! as ComunicadorBuscarMain
        } catch (e: ClassCastException){}
    }
}
