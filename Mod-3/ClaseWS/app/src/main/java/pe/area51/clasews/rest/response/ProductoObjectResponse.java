package pe.area51.clasews.rest.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductoObjectResponse {

    @SerializedName("data")
    @Expose
    private Datum data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Datum getData() {
        return data;
    }

    public void setData(Datum data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}