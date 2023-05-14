package com.mariazhang.assistoapp.database

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Objects


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



):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createBooleanArray()?.toList() ?: emptyList(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(anuncio_asistente_id)
        parcel.writeString(email)
        parcel.writeString(enunciado)
        parcel.writeString(experiencia)
        //falta aqui ???
    //    parcel.writeString(exp_tipo_discapacidad.toString())
        parcel.writeString(disponibilidad)
        parcel.writeString(habilidades)
        parcel.writeString(salario)
        parcel.writeString(contacto)
        parcel.writeString(ciudad)
        parcel.writeString(provincia)
    }

    override fun describeContents(): Int {
        return 0
    }




    companion object CREATOR : Parcelable.Creator<anuncio_asistentes> {


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

        override fun createFromParcel(parcel: Parcel): anuncio_asistentes {
            return anuncio_asistentes(parcel)
        }

        override fun newArray(size: Int): Array<anuncio_asistentes?> {
            return arrayOfNulls(size)
        }


    }


}
