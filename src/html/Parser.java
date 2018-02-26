package html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;

public class Parser {
    public static Document Readhtml(String urll){
        try {
            Document doc;
            if(!urll.startsWith("http:") && !urll.startsWith("https:")){
                doc = Jsoup.connect("https://" + urll).userAgent("Mozilla/5.0").get();
            } else{
                doc = Jsoup.connect(urll).userAgent("Mozilla/5.0").get();
            }
            return doc;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static Set<String> get_links(Document doc){
        Set<String> links = new HashSet<>();
        for(Element x : doc.getElementsByTag("a")) {
            String str = x.absUrl("href");
            if(!str.isEmpty())
                links.add(str);
        }
        return links;
    }

    public static Set<String> get_images(Document doc){
        Set<String> images = new HashSet<>();
        for(Element x : doc.getElementsByTag("img")) {
            String str = x.absUrl("src");
            if(!str.isEmpty())
                images.add(str);
        }
        return images;
    }

    public static long get_image_size(String url) throws IOException {
        URL image = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) image.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestMethod("HEAD");
        long size = connection.getContentLength();
        return size;
    }
}

