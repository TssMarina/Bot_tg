
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Calendar  {
    private Document document;

    public Calendar(){
        connect();
    }

    private void connect() {
        try{
            document = Jsoup.connect("https://kakoyprazdnik.com").get();
        } catch ( IOException e ) {
            e.printStackTrace();

        }
    }

    public String getDayToday() {
        Elements elements = document.getElementsByTag("h1");
        return elements.text();

    }

    public String getHolidays() {
        Elements elements = document.getElementsByTag("h4");
        String text = "";
        for (Element link : elements) {
            text=text.concat("• "+link.text()+"\n");
        }
        return text;
    }
}
