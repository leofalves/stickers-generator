import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        //fazer conexão para buscar os filmes
        //String url = "https://api.mocki.io/v2/549a5d8b";
        //ContentExtractor extractor = new ContentExtractorImdb();

        //String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        //ContentExtractor extractor = new ContentExtractorNasa();

        String url = "http://localhost:8080/languages";
        ContentExtractor extractor = new ContentExtractorLanguage();

        HttpClientConnect http = new HttpClientConnect();
        String json = http.findContent(url);

        List<Content> contents = extractor.contentsExtractor(json);
        StickersGenerator stickersGenerator = new StickersGenerator();

        for (int i=0; i<3; i++) {
            
            Content content = contents.get(i);
            String title = content.getTitle()
                            .replace(" ", "")
                            .replace(",", "")
                            .replace(".", "")
                            .replace(":", "");
            String fileName = title + ".png";
            String ImageUrl = content.getUrlImage();

            System.out.println("File: " + fileName);
            InputStream inputStream = new URL(ImageUrl).openStream();
            stickersGenerator.create(inputStream, fileName, "Dev");
            System.out.println("-----------------------------------------------------------------");
        } 

    }
}
