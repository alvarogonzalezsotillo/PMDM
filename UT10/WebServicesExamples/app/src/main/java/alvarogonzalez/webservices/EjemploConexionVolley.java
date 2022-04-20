package alvarogonzalez.webservices;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EjemploConexionVolley {

    private static String TAG = "EjemploConexionVolley";

    // LA CLAVE SE CONSIGUE EN https://opendata.aemet.es/centrodedescargas/altaUsuario?
    public static String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbHZhcm9nb256YWxlenNvdGlsbG9AZ21haWwuY29tIiwianRpIjoiYmI2OTVkNDYtNzFhOC00OGQzLTkyOTQtNmEwMDc4MGQzYzEwIiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE2NDc5NDU1ODAsInVzZXJJZCI6ImJiNjk1ZDQ2LTcxYTgtNDhkMy05Mjk0LTZhMDA3ODBkM2MxMCIsInJvbGUiOiIifQ.Z5H-YcHcvF4Q8N5vAjnnlGE3BAlbVppULOSYsQEmGMg";


    public static CompletableFuture<String> getURLMapa(Context ctx){
        String URL_MAPA = "https://opendata.aemet.es/opendata/api/mapasygraficos/analisis";

        RequestQueue requestQueue = Volley.newRequestQueue(ctx);

        CompletableFuture<String> cf = new CompletableFuture<>();

        // ES IMPORTANTE DISTINGUIR ENTRE JSON OBJECT O ARRAY, EN ESTE CASO ES OBJECT
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_MAPA, null,
                (JSONObject response) -> {
                    Log.d(TAG,"response:" + response );

                    String url = null;
                    try {
                        url = response.getString("datos");
                        cf.complete(url);
                    } catch (JSONException e) {
                        cf.completeExceptionally(e);
                    }
                },
                (VolleyError error) -> {
                    Log.d(TAG,"Error en getURLMapa", error);
                    cf.completeExceptionally(error);
                }
        ){
            @Override
            public Map<String, String> getHeaders() {
                // LA APIKEY DEBE MANDARSE COMO CABECERA HTTP, POR ESO SOBREESCRIBIMOS ESTE MÉTODO
                Map<String, String>  params = new HashMap<String, String>();
                params.put("api_key", API_KEY);
                return params;
            }
        };

        requestQueue.add(jsonObjectRequest);
        return cf;
    }

}
