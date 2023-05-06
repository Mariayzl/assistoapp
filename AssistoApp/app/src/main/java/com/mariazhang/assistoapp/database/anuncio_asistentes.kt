package com.mariazhang.assistoapp.database


data class anuncio_asistentes(

    val anuncio_asistente_id: String,
    val enunciado: String?,
    val experiencia: String?,
    val exp_tipo_discapacidad: List<Boolean>,
    val disponibilidad: String?,
    val referencias: String?,
    val habilidades: String?,
    val salario: String?,
    val contacto: String?,
    val ciudad: String?,
    val provincia: String?

)
{

    companion object {


    }

}



