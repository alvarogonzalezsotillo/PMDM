package alvarogonzalez.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class EjemploConexionBuiltIn {

    private static URL getURL(String busqueda) throws UnsupportedEncodingException, MalformedURLException {
        return new URL("https://www.google.es/search?hl=es&q=\"" + URLEncoder.encode(busqueda, "UTF-8") + "\"");
    }

    private static long buscaTextoAproximado(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String linea = br.readLine();
        // LEO LA ENTRADA LINEA A LINEA
        while( linea != null ) {
            int index = linea.toLowerCase().indexOf("aproximadamente");
            if( index != -1 ) {
                // SI EN LA LINEA PONE APROXIMADAMENTE, EXTRAIGO EL NUMERO POSTERIOR
                int inicio = index + "aproximadamente".length() + 1;
                int fin = linea.toLowerCase().indexOf(' ',inicio);
                String numeroS = linea.substring(inicio,fin);
                // PARSEO LA CADENA SIN PUNTOS COMO SI FUERA UN LONG
                return Long.parseLong( numeroS.replace(".", "") );
            }

            linea = br.readLine();
        }
        throw new IOException("No se ha encontrado 'aproximadamente'");
    }

    public static long numeroResultadosGoogle(String busqueda) throws IOException {

        URL url = getURL(busqueda);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        // ESTA CABECERA HACE PENSAR A GOOGLE QUE LA PETICION ES DE FIREFOX, YA QUE
        // EN OTRO CASO LA PÁGINA QUE DEVUELVE NO ES LA MISMA
        conexion.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:87.0) Gecko/20100101 Firefox/87.0");
        if (conexion.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("No se completó la conexión:" + conexion.getResponseMessage());
        }

        long ret = buscaTextoAproximado(conexion.getInputStream());
        conexion.disconnect();
        return ret;
    }
}
