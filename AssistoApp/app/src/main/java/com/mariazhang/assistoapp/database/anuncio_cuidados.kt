package com.mariazhang.assistoapp.database

data class anuncio_cuidados(

    val anuncio_cuidado_id: String,
    val mail: String,
    val enunciado: String?,
    val descripcion: String?,
    val grado_discapacidad: String?,
    val tipo_discapacidad: List<Boolean>,
    val discapacidades: List<Boolean>,
    val necesidades: List<Boolean>,
    val tipo_jornada: String?,
    val salario: String?,
    val contacto: String?,
    val ciudad: String?,
    val provincia: String?


)

