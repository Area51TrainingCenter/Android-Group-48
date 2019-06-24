package com.area51.clase.realm;

import com.area51.clase.Producto;

import io.realm.Realm;
import io.realm.RealmResults;

public class ProductoImpl implements IProducto {
    @Override
    public RealmResults<ProductoEntidad> obtenerProductos() {

        Realm realm = Realm.getDefaultInstance();
        return realm.where(ProductoEntidad.class)
                .findAll();
    }

    @Override
    public ProductoEntidad guardar(ProductoEntidad entidad) {
        Realm realm = Realm.getDefaultInstance();

        long cantidad = realm.where(ProductoEntidad.class)
                .count();

        try {
            realm.beginTransaction();
            if (entidad.getCodigo() == 0)
                entidad.setCodigo(cantidad + 1);
            realm.insertOrUpdate(entidad);
            realm.commitTransaction();

            return entidad;
        } catch (Exception e) {
            realm.cancelTransaction();
        }

        return null;
    }

    @Override
    public boolean eliminar(long codigo) {
        Realm realm = Realm.getDefaultInstance();

        try {
            realm.beginTransaction();
            ProductoEntidad entidad = realm.where(ProductoEntidad.class)
                    .equalTo("codigo", codigo)
                    .findFirst();
            if (entidad != null) {
                entidad.deleteFromRealm();
                realm.commitTransaction();
                return true;
            }
        } catch (Exception e) {
            realm.cancelTransaction();
        }
        return false;
    }

    @Override
    public boolean eliminar(ProductoEntidad entidad) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            entidad.deleteFromRealm();
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            realm.cancelTransaction();
        }
        return false;
    }
}
