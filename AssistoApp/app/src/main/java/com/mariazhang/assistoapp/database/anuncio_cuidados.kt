package com.mariazhang.assistoapp.database

data class anuncio_cuidados(

    var anuncio_cuidado_id: String,
    var mail: String,
    var enunciado: String,
    var descripcion: String,
    var grado_discapacidad: String,
    var tipo_discapacidad: List<Boolean>,
    var discapacidades: List<Boolean>,
    var necesidades: List<Boolean>,
    var tipo_jornada: String,
    var salario: String,
    var contacto: String,
    var ciudad: String,
    var provincia: String


) {
    companion object {

        fun fromJson(anuncioMapa: MutableMap<String, Any>): anuncio_cuidados {

            val tipo_discapacidad = anuncioMapa["tipo_discapacidad"] as List<Boolean>
            val discapacidades = anuncioMapa["discapacidades"] as List<Boolean>
            val necesidades = anuncioMapa["necesidades"] as List<Boolean>

            return anuncio_cuidados(
                anuncioMapa["anuncio_cuidados_id"].toString(),
                anuncioMapa["mail"].toString(),
                anuncioMapa["enunciado"].toString(),
                anuncioMapa["descripcion"].toString(),
                anuncioMapa["grado_discapacidad"].toString(),
                tipo_discapacidad ,
                discapacidades,
                necesidades,
                anuncioMapa["tipo_jornada"].toString(),
                anuncioMapa["salario"].toString(),
                anuncioMapa["contacto"].toString(),
                anuncioMapa["ciudad"].toString(),
                anuncioMapa["provincia"].toString()
            )
        }

    }
}

