package pe.area51.clasekotlin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Producto(
    var producto: String? = null,
    var precio: Double? = null,
    var cantidad: Int? = null) : Parcelable