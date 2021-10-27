import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class Parser {


    public static Document getPageCode() throws IOException {
        String url = "https://pogodavtomske.ru/threeday.html";
        Document pageCode = Jsoup.parse(new URL(url), 3000);
        return pageCode;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getPageCode());
    }
}
