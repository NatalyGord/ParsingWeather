import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {


    public static Document getPageCode() throws IOException {
        String url = "https://pogodavtomske.ru/threeday.html";
        Document pageCode = Jsoup.parse(new URL(url), 3000);
        return pageCode;
    }

    private static Pattern pattern = Pattern.compile("[А-Я][а-я]{4,}\\s\\d\\d?\\s[а-яА-Я]+"); //Четверг 28 октября

    public static String[] getDate(String nameDateString) throws Exception {
        int count = 0;
        String[] allMatches = new String[4];
        Matcher matcher = pattern.matcher(nameDateString);

        while (matcher.find()) {
            allMatches[count] = matcher.group();
            count++;
        }
        return allMatches;
    }

    public static int printValues(Elements values, int index) {
        int countIter = 4;
        if (index == 0) {
            Element valueLn = values.get(3);
            boolean isNight = valueLn.text().contains("Ночь");
            boolean isMorning = valueLn.text().contains("Утро");
            boolean isDay = valueLn.text().contains("День");

            if (isNight) {
                countIter = 3;
            } else if (isDay) {
                countIter = 1;
            } else if (isMorning) {
                countIter = 2;
            }
        }
        for (int i = 0; i < countIter; i++) {
            Element valueLine = values.get(index + i);
            for (Element td : valueLine.select("td")) {
                System.out.print(td.text() + "\t\t\t\t\t");
            }
            System.out.println();
        }
        return countIter;
    }

    public static void main(String[] args) throws Exception {
        Document pageCode = getPageCode();
        int index = 0;  //показывает где мы сейчас находимся

        Element pageCodeWeather = pageCode.selectFirst("div[class=block rc5 b2]");
        Elements nameColumns = pageCodeWeather.select("tr[height=20px]");

        String nameDateString = pageCodeWeather.text(); //строка из которой выбираем даты

//        System.out.println(nameDateString);
//        String [] nameDate = {" 1", " 2", " 3", " 4"};
        String[] nameDate = getDate(nameDateString);  //массив найденых дат

//        System.out.println(nameDateString);
//        for (String n : nameDate) {
//            System.out.println(n);
//        }
        Elements values = pageCodeWeather.select("tr[height=125px]");
        int itr = 0;  //Выбор элемента из массива найденых дат
        for (Element nameColumn : nameColumns) {
            System.out.println();
            System.out.println(nameDate[itr] + "\t\t\tосадки\t\t\t\t\t\t\t\tтемпература\t\t\t\t\tветер\t\t\t\t\tвлажность\t\t\tдавление");

            int countIter = printValues(values, index);
            index = index + countIter;
            itr++;
        }

    }
}
