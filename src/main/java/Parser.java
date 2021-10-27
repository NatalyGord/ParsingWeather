import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Parser {


    public static Document getPageCode() throws IOException {
        String url = "https://pogodavtomske.ru/threeday.html";
        Document pageCode = Jsoup.parse(new URL(url), 3000);
        return pageCode;
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(getPageCode());
        Document pageCode = getPageCode();

        Element pageCodeWeather = pageCode.selectFirst("div[class=block rc5 b2]");
        System.out.println(pageCodeWeather);
        Elements nameColumns = pageCodeWeather.select("tr[height=20px]");
//        System.out.println(nameColumns);
        Elements values = pageCodeWeather.select("tr[height=125px]");
//        System.out.println(values);

    }
}
