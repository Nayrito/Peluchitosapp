package com.example.peluchitosapp


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_agregar.view.*
import java.lang.ClassCastException


class AgregarFragment : Fragment() {

    var interfaz:ComunicadorAgregar_Main ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_agregar, container, false)
        view.btAgregar.setOnClickListener {
            var key:String = "agregar"
            var id = view.etId.text.toString()
            var nombre = view.etNombre.text.toString()
            var cantidad = view.etCantidad.text.toString()
            var precio = view.etPrecio.text.toString()
            if(id.isEmpty()){
                Toast.makeText(this.context, "Ingrese el ID", Toast.LENGTH_SHORT).show()
            }else if(nombre.isEmpty()){
                Toast.makeText(this.context, "Ingrese el Nombre", Toast.LENGTH_SHORT).show()
            }else if(cantidad.isEmpty()){
                Toast.makeText(this.context, "Ingrese la cantidad", Toast.LENGTH_SHORT).show()
            }else if(precio.isEmpty()){
                Toast.makeText(this.context, "Ingrese el Precio", Toast.LENGTH_SHORT).show()
            }else{
                interfaz?.enviardatos1(id,nombre,cantidad,precio)
                Toast.makeText(this.context,"Peluchito Agregado",Toast.LENGTH_SHORT).show()
                view.etId.text.clear()
                view.etNombre.text.clear()
                view.etCantidad.text.clear()
                view.etPrecio.text.clear()
            }
        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            interfaz = context!! as ComunicadorAgregar_Main
        } catch (e:ClassCastException){}
    }
}
