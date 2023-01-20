package enchere.enchere.retour;

import jdk.nashorn.internal.parser.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataRetour<T> {
    T data;

    public DataRetour(T list) {
        this.data = list;

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
