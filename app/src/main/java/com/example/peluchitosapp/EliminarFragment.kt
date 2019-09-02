package com.example.peluchitosapp


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_buscar.view.*
import kotlinx.android.synthetic.main.fragment_buscar.view.etNombre
import kotlinx.android.synthetic.main.fragment_buscar.view.tvMostrar
import kotlinx.android.synthetic.main.fragment_eliminar.view.*
import java.lang.ClassCastException


class EliminarFragment : Fragment() {

    var interfaz:ComunicadorEliminar_main ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_eliminar, container, false)
        var key = arguments?.getString("key")

        view.btEliminar.setOnClickListener {
            var nombre = view.etNombre.text.toString()
            if (nombre.isEmpty()) {
                Toast.makeText(this.context, "Ingrese el nombre", Toast.LENGTH_SHORT).show()
            } else {
                interfaz?.enviardatos3(nombre)
                view.etNombre.text.clear()
            }
        }
        if(key.equals("True")){
            Toast.makeText(this.context,"Elemento eliminado",Toast.LENGTH_LONG).show()
        }else if(key.equals("False")){
            Toast.makeText(this.context,"Elemento no encontrado",Toast.LENGTH_LONG).show()
        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            interfaz = context!! as ComunicadorEliminar_main
        } catch (e: ClassCastException){}
    }
}
