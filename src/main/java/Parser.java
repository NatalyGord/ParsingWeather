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

    public static int printValues(Elements values, int index){
        int countIter = 4;
        if (index == 0) {
            Element valueLn = values.get(3);
            boolean isNight = valueLn.text().contains("Ночь");
            boolean isMorning = valueLn.text().contains("Утро");
            boolean isDay = valueLn.text().contains("День");
            boolean isEvening = valueLn.text().contains("Вечер");

            if (isNight) {
                countIter = 3;
            } else if (isDay) {
                countIter = 1;
            }else if(isMorning){
                countIter = 2;
            }
        }
        for (int i = 0; i < countIter; i++){
            Element valueLine = values.get(index + i);
            for (Element td: valueLine.select("td")){
                System.out.print(td.text() + "        ");
            }
            System.out.println();
        }
        return countIter;
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(getPageCode());
        Document pageCode = getPageCode();
        String nameDate = "";
        int index = 0;  //показывает где мы сейчас находимся

        Element pageCodeWeather = pageCode.selectFirst("div[class=block rc5 b2]");
//        System.out.println(pageCodeWeather);
        Elements nameColumns = pageCodeWeather.select("tr[height=20px]");
//        System.out.println(nameColumns);
        String nameDateString = pageCodeWeather.text();
//        System.out.println(nameDateString);

        Elements values = pageCodeWeather.select("tr[height=125px]");
//        System.out.println(values);

        for (Element nameColumn: nameColumns){

            System.out.println();
            System.out.println(nameDate + "             осадки                     температура        ветер             влажность        давление");

            int countIter = printValues(values, index);
            index = index + countIter;
        }

    }
}
