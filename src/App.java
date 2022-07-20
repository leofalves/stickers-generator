import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    private static StickersGenerator stickersGenerator;

    public static void main(String[] args) throws Exception {
        
        //fazer conexão para buscar os filmes
        String url = "https://api.mocki.io/v2/549a5d8b";
        //String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();

        //extrair só os dados que interessam
        JsonParser parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

        //exibir os dados
        String rating = ""; 
        //final String star = " * "; 
        final String star = " \uD83D\uDC8E ";
        StickersGenerator stickersGenerator = new StickersGenerator();
        for (Map<String,String> movie : movieList) {
            System.out.println(movie.get("title"));

            String ImageUrl = movie.get("image");
            String title = movie.get("title")
                            .replace(" ", "")
                            .replace(",", "")
                            .replace(".", "")
                            .replace(":", "");
            String fileName = title + ".png";
            InputStream inputStream = new URL(ImageUrl).openStream();
            
            rating = "";     
            double i = Double.parseDouble(movie.get("imDbRating"));
            for (int j=0; j<i; j++){
                rating = rating + star;
            }
            rating = movie.get("imDbRating") + rating;

            stickersGenerator.create(inputStream, fileName, rating);

            // System.out.println("Title: \u001b[37;1m \u001b[44;1m " + movie.get("title") + " \u001b[m ");
            // System.out.println(movie.get("image"));
            // System.out.println(" \u001b[33m " + rating + " \u001b[m ");
            // System.out.println("      " + movie.get("imDbRating"));
            System.out.println("-----------------------------------------------------------------");
        } 

    }
}
