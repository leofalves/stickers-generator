import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorLanguage implements ContentExtractor{

    @Override
    public List<Content> contentsExtractor(String json) {
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        for (Map<String, String> atribute : attributesList) {
            String title = atribute.get("title");
            String urlImage = atribute.get("image");
            Content content = new Content(title, urlImage);
            contents.add(content);
        }
        return contents;
    }
    
}
