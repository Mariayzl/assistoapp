package com.mariazhang.assistoapp.database

data class anuncio_asistentes(

    var anuncio_asistente_id: String,
    var email: String,
    var enunciado: String,
    var experiencia: String,
    var exp_tipo_discapacidad: List<Boolean>,
    var disponibilidad: String,
    var habilidades: String,
    var salario: String,
    var contacto: String,
    var ciudad: String,
    var provincia: String



){

    companion object {

        fun fromJson(anuncioMapa: MutableMap<String, Any>): anuncio_asistentes {

            var exp_tipo_discapacidad = anuncioMapa["exp_tipo_discapacidad"] as List<Boolean>

            return anuncio_asistentes(
                anuncioMapa["anuncio_asistente_id"].toString(),
                anuncioMapa["email"].toString(),
                anuncioMapa["enunciado"].toString(),
                anuncioMapa["experiencia"].toString(),
                exp_tipo_discapacidad ,
                anuncioMapa["disponibilidad"].toString(),
                anuncioMapa["habilidades"].toString(),
                anuncioMapa["salario"].toString(),
                anuncioMapa["contacto"].toString(),
                anuncioMapa["ciudad"].toString(),
                anuncioMapa["provincia"].toString()
            )
        }

    }


}



