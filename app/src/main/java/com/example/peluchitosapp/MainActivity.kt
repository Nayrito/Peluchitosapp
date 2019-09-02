package com.example.peluchitosapp

import android.os.Build
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import org.jetbrains.annotations.NotNull
import java.util.ArrayList
import java.util.function.Predicate

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,ComunicadorEliminar_main,ComunicadorAgregar_Main,ComunicadorBuscarMain{

    var peluchitos: MutableList<Peluchito> = java.util.ArrayList()
    var peluchito_actual:Peluchito ?= null
    var inventario : ArrayList<String> = java.util.ArrayList()
    var bolB = false
    var bolE = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        val manager = supportFragmentManager
        val transaction=manager.beginTransaction()
        val agregarFragment = AgregarFragment()
        transaction.add(R.id.contenedor,agregarFragment).commit()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
         when (item.itemId) {
            R.id.action_limpiar -> peluchitos.clear()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val manager = supportFragmentManager
        val transaction=manager.beginTransaction()

        when (item.itemId) {
            R.id.nav_agregar-> {
                val agregarFragment = AgregarFragment()
                transaction.replace(R.id.contenedor,agregarFragment).commit()

            }
            R.id.nav_buscar-> {
                val buscarFragment = BuscarFragment()
                transaction.replace(R.id.contenedor,buscarFragment).commit()
            }
            R.id.nav_eliminar -> {
                val eliminarFragment = EliminarFragment()
                transaction.replace(R.id.contenedor,eliminarFragment).commit()
            }
            R.id.nav_inventario -> {
                val inventarioFragment = InventarioFragment()
                enviarInventario(peluchitos)
                val args = Bundle()
                args.putStringArrayList("lista",inventario)

                inventarioFragment.arguments = args
                transaction.replace(R.id.contenedor,inventarioFragment).commit()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun enviardatos1(id: String, nombre: String, cantidad: String, precio: String) {
        var peluchito = Peluchito(id,nombre,cantidad,precio)
        peluchitos.add(peluchito)
    }

    override fun enviardatos2(nombre: String) {
        buscarpeluche(nombre,peluchitos)
        val args = Bundle()
        if(bolB){
            args.putString("key","True")
            args.putString("id",peluchito_actual?.id)
            args.putString("nombre",peluchito_actual?.nombre)
            args.putString("cantidad",peluchito_actual?.cantidad)
            args.putString("precio",peluchito_actual?.precio)
        }else{
            args.putString("key","False")
        }
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val buscarFragment = BuscarFragment()
        buscarFragment.arguments = args
        transaction.replace(R.id.contenedor,buscarFragment).commit()
    }

    override fun enviardatos3(nombre: String) {
        eliminarpeluche(nombre,peluchitos)
        val args = Bundle()
        if(bolE.equals(true)){
            args.putString("key","True")
        }else {
            args.putString("key","False")
        }
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val eliminarFragment = EliminarFragment()
        eliminarFragment.arguments = args
        transaction.replace(R.id.contenedor,eliminarFragment).commit()
    }

    private fun buscarpeluche(nombre:String,lista: MutableList<Peluchito>){
        bolB = false
        for(i in lista){
            if (i.nombre.equals(nombre)) {
                peluchito_actual = Peluchito(i.id, i.nombre, i.cantidad, i.precio)
                bolB = true
            }
        }
    }

    private fun eliminarpeluche(nombre:String, lista:MutableList<Peluchito>){
        bolE = false
        for(i in lista){
            if(i.nombre!!.equals(nombre)){
                var index = lista.indexOf(i)
                lista.removeAt(index)
                bolE = true
                break
            }
        }
    }

    private fun enviarInventario(lista:MutableList<Peluchito>){
        inventario.clear()
        for(i in lista ){
            var peluchito = "ID : "+i.id+"\n"+"Nombre : "+i.nombre+"\n"+"Cantidad : "+i.cantidad+"\n"+"Precio : "+i.precio
            var index = lista.indexOf(i)
            inventario?.add(index,peluchito)
        }
    }
}
