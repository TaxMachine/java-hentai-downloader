package taxmachine.hentaidownloader;

import org.json.JSONObject;
import taxmachine.hentaidownloader.handlers.HentaiHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HentaiDownloader {
    public static String getHentai(String path) throws Exception {
        String raw_waifu = HentaiHandler.HttpGet(path);
        JSONObject waifu = new JSONObject(raw_waifu);
        String waifu_url = (String) waifu.get("url");
        return waifu_url;
    }
    public static void main(String[] args) throws Exception {
        List<String> endpoints = new ArrayList<>();
        int i = 0;
        endpoints.add("https://api.waifu.pics/nsfw/waifu");
        endpoints.add("https://api.waifu.pics/nsfw/neko");
        endpoints.add("https://api.waifu.pics/nsfw/blowjob");
        do {
            for (String stuff : endpoints) {
                String url = getHentai(stuff);
                String name = url.split("/")[3];
                File path = new File("./hentai");
                if (!path.exists()) {
                    path.mkdir();
                }
                HentaiHandler.download(url, "./hentai/" + name);
                System.out.println("Downloaded " + i + " hentai");
                i++;
            }
        } while (true);
    }
}
