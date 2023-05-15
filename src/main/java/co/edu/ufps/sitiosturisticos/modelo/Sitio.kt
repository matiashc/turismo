package co.edu.ufps.sitiosturisticos.modelo

data class Sitio(
    val id: String = "",
    val nombre: String = "",
    val imagen: String = "",
    val descripcion: String = "",
    val latitud: Double = 0.0,
    val longitud: Double = 0.0
)
