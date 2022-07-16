package taxmachine.hentaidownloader.handlers;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class HentaiHandler {
    public static void download(String mURL, String path) throws Exception {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            URL url = new URL(mURL);
            URLConnection urlConn = url.openConnection();
            urlConn.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36");
            in = urlConn.getInputStream();
            out = new FileOutputStream(path);
            int c;
            byte[] b = new byte[1024];
            while ((c = in.read(b)) != -1)
                out.write(b, 0, c);
        } finally {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
        }
    }
    public static String HttpGet(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }
    public static String generateString() {
        return UUID.randomUUID().toString();
    }
}
