package courses.android.instagramapplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonya on 5/30/16.
 */
public class JSONParser {
    public static List<FeedInfo> parse(String response) {

        List<FeedInfo> feeds = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(response);
            JSONArray jArr = json.getJSONArray("data");
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject obj = jArr.getJSONObject(i);
                JSONObject imagesObj = obj.getJSONObject("images");

                String thumbnail = imagesObj.getJSONObject("thumbnail").getString("url");
                String standard = imagesObj.getJSONObject("standard_resolution").getString("url");

                feeds.add(new FeedInfo(thumbnail, standard));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return feeds;

    }
}
