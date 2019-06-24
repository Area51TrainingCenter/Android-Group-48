package com.area51.clase.realm;

import io.realm.RealmResults;

public interface IProducto {
    RealmResults<ProductoEntidad> obtenerProductos();

    ProductoEntidad guardar(ProductoEntidad entidad);

    boolean eliminar(long codigo);

    boolean eliminar(ProductoEntidad entidad);

}
