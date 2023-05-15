package co.edu.ufps.sitiosturisticos.vista

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.ufps.sitiosturisticos.R
import co.edu.ufps.sitiosturisticos.controller.SitioAdapter
import co.edu.ufps.sitiosturisticos.modelo.Sitio
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SitiosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SitiosFragment : Fragment() {
    lateinit var contenedorSitio: RecyclerView
    lateinit var sitioAdapter: SitioAdapter
    val TAG: String = "SitiosFragment"

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sitios, container, false)
        contenedorSitio = view.findViewById(R.id.contenedor_sitio)
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        contenedorSitio.layoutManager=linearLayout
        sitioAdapter = SitioAdapter(context,cargarDatosFireBase(), R.id.card)
        contenedorSitio.adapter=sitioAdapter
        return view
    }

    private fun cargarDatos(): ArrayList<Sitio> {
        val sitios: java.util.ArrayList<Sitio> = java.util.ArrayList()
        sitios.add (
            Sitio("-MMQjzb-huiSVI2i3EFu",
                "Caño Cristales",
             "https://www.1000y1viajes.com/wp-content/uploads/2019/12/Can%CC%83o-Cristales-lugares-turisticos-colombia.jpg",
         "Ubicado en la sierra de la Macarena en el Meta, se encuentra Caño Cristales, llamado por muchos portales de turismo a nivel internacional como el “río más hermoso del mundo”. Esto es debido a los diversos colores que se pueden observar en el fondo gracias a la presencia de plantas acuáticas que le dan esas tonalidades características.",
         0.0,
        0.0))
        sitios.add (
            Sitio(
                "-MMQkJzDLJQbZAtMQfk1",
            "Minas de sal de Nemocón, Cundinamarca",
        "https://www.colombia.co/wp-content/uploads/2017/09/Salt_Mine_Nemocon_Colombia_5743986824.jpg",
         "Nemocón es un pueblo en Cundinamarca famoso por su gran actividad minera. A pocos kilómetros de Bogotá, se puede llegar a este lugar para explorar las minas de sal que están abiertas al público, donde se pueden apreciar los increíbles espejos de agua adecuados con hermosa iluminación.",
         0.0,
         0.0
        ))
        return sitios

    }

   private fun cargarDatosFireBase(): ArrayList<Sitio>{
       val sitios: java.util.ArrayList<Sitio> = java.util.ArrayList()
       val database: FirebaseDatabase = FirebaseDatabase.getInstance();
       val myRef: DatabaseReference = database.reference
       myRef.child("sitios").addValueEventListener(object: ValueEventListener {
           override fun onDataChange(dataSnapshot: DataSnapshot) {
               if (dataSnapshot.exists()){
                   sitios.clear()
                   for(data in dataSnapshot.children){
                       val sitio=data.getValue(Sitio::class.java)
                       sitios.add(sitio as Sitio)
                       sitioAdapter.notifyDataSetChanged()
                   }

               }
           }

           override fun onCancelled(databaseError: DatabaseError) {
               // Getting Post failed, log a message
               Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
           }
       })
      return sitios

   }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SitiosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SitiosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}