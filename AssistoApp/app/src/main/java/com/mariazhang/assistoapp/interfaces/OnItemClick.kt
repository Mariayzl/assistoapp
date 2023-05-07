package com.mariazhang.assistoapp.interfaces

import com.mariazhang.assistoapp.database.anuncio_asistentes
import com.mariazhang.assistoapp.database.anuncio_cuidados

interface OnItemClickAsistentes {
    fun onItemClick(anuncioAsistente:anuncio_asistentes)

}

interface OnItemClickCuidados {
    fun onItemClick(anuncioCuidados:anuncio_cuidados)

}