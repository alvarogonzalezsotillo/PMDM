package alvarogonzalez.webservices;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public class EjemploConexionRetrofit {

    private static Retrofit _retrofitInstance = null;
    public static Retrofit getRetrofit() {
        if( _retrofitInstance == null ) {
            _retrofitInstance = new Retrofit.Builder().
                    baseUrl(ServicioMunicipios.BASE_URL).
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

    public interface ServicioMunicipios {
        public static final String BASE_URL = "https://opendata.aemet.es/opendata/api/maestro/";

        @GET("municipios")
        Call<List<Municipio>> listaMunicipios(@Header("api_key") String apiKey);

    }

    public static CompletableFuture<Municipio[]> getMunicipios(){
        Call<List<Municipio>> municipiosRequest = getServicioMunicipios().listaMunicipios(EjemploConexionVolley.API_KEY );

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
