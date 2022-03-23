package alvarogonzalez.webservices;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public class EjemploConexionRetrofit {

    public static OkHttpClient httpClient = new OkHttpClient();

    private static String API_KEY= EjemploConexionVolley.API_KEY;

    private static Retrofit _retrofitInstance = null;
    public static Retrofit getRetrofit() {
        if( _retrofitInstance == null ) {
            _retrofitInstance = new Retrofit.Builder().
                    baseUrl(ServicioMunicipios.BASE_URL).
                    client(httpClient).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return _retrofitInstance;
    }

    private static ServicioMunicipios _servicioMunicipiosInstance = null;
    public static ServicioMunicipios getServicioMunicipios() {
        if( _servicioMunicipiosInstance == null ) {
            _servicioMunicipiosInstance = getRetrofit().create(ServicioMunicipios.class);
        }
        return _servicioMunicipiosInstance;
    }

    private static ServicioRiesgoIncendio _servicioRiesgoIncendioInstance = null;
    public static ServicioRiesgoIncendio getServicioRiesgoIncencio(){
        if( _servicioRiesgoIncendioInstance == null ){
            _servicioRiesgoIncendioInstance = getRetrofit().create(ServicioRiesgoIncendio.class);
        }
        return _servicioRiesgoIncendioInstance;
    }

    public interface ServicioMunicipios {
        public static final String BASE_URL = "https://opendata.aemet.es/opendata/api/maestro/";

        @GET("municipios")
        Call<List<Municipio>> listaMunicipios(@Header("api_key") String apiKey);

    }

    public enum Zona{
        PENINSULA{ public String toString() { return "p"; } },
        BALEARES{ public String toString() { return "b"; } },
        CANARIAS{ public String toString() { return "c"; } },
    }

    public static class RiesgoIncendio{
        public String estado;
        public String datos;
    }

    public interface ServicioRiesgoIncendio{
        @GET("https://opendata.aemet.es/opendata/api/incendios/mapasriesgo/estimado/area/{id}")
        Call<RiesgoIncendio> riesgoIndencio(@Header("api_key") String apiKey, @Path("id") Zona zona);
    }

    public static CompletableFuture<RiesgoIncendio> getRiesgoIncencio(Zona zona){
        Call<RiesgoIncendio> riesgoRequest = getServicioRiesgoIncencio().riesgoIndencio(API_KEY,zona);
        CompletableFuture<RiesgoIncendio> cf = new CompletableFuture<>();

        riesgoRequest.enqueue(new Callback<RiesgoIncendio>() {
            @Override
            public void onResponse(Call<RiesgoIncendio> call, Response<RiesgoIncendio> response) {
                Request request = call.request();
                System.out.println("request headers------------------------------------");
                System.out.println(request.headers());
                System.out.println(request.url());
                System.out.println("request body------------------------------------");
                System.out.println(request.body());
                System.out.println( "response headers----------------------------------");
                System.out.println( response.headers().toString() );
                System.out.println( "response message----------------------------------");
                System.out.println( response.message() );
                if( !response.isSuccessful() ){
                    cf.completeExceptionally( new RuntimeException("No ha funcionado"));
                }
                RiesgoIncendio ri = response.body();
                cf.complete(ri);
            }

            @Override
            public void onFailure(Call<RiesgoIncendio> call, Throwable t) {
                cf.completeExceptionally(t);
            }
        });

        return cf;
    }

    public static CompletableFuture<Municipio[]> getMunicipios(){
        Call<List<Municipio>> municipiosRequest = getServicioMunicipios().listaMunicipios(API_KEY );

        CompletableFuture<Municipio[]> cf = new CompletableFuture<>();

        municipiosRequest.enqueue( new Callback<List<Municipio>>(){

            @Override
            public void onResponse(Call<List<Municipio>> call, Response<List<Municipio>> response) {
                System.out.println( "headers----------------------------------");
                System.out.println( response.headers().toString() );
                System.out.println( "message----------------------------------");
                System.out.println( response.message() );
                if( !response.isSuccessful() ){
                    cf.completeExceptionally( new RuntimeException("No ha funcionado"));
                }

                List<Municipio> ms = response.body();
                cf.complete( ms.toArray(new Municipio[0]) );
            }

            @Override
            public void onFailure(Call<List<Municipio>> call, Throwable t) {
                System.out.println( "Error en retrofit ------------------------------------" );
                t.printStackTrace();
                cf.completeExceptionally(t);
            }
        });
        return cf;
    }



    public static class Municipio {
        public String latitud;
        public String altitud;
        public String nombre;
        public String id;
        public String longitud;

        @Override
        public String toString() {
            return "Municipio{" +
                    "latitud='" + latitud + '\'' +
                    ", altitud='" + altitud + '\'' +
                    ", nombre='" + nombre + '\'' +
                    ", id='" + id + '\'' +
                    ", longitud='" + longitud + '\'' +
                    '}';
        }
    }
}
