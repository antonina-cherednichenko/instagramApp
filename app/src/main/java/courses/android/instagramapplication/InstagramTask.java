package courses.android.instagramapplication;

import android.os.AsyncTask;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by tonya on 5/29/16.
 */
public class InstagramTask extends AsyncTask<String, Void, String> {
    private String accessToken;
    private String userId;
    private String userName;

    @Override
    protected String doInBackground(String... params) {
        getAccessToken(params[0]);
        return getUserFeeds();
    }

    private void getAccessToken(String code) {
        try {
            URL url = new URL(ApplicationData.TOKEN_URL);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream());
            outputStreamWriter.write("client_id=" + ApplicationData.CLIENT_ID +
                    "&client_secret=" + ApplicationData.CLIENT_SECRET +
                    "&grant_type=authorization_code" +
                    "&redirect_uri=" + ApplicationData.CALLBACK_URL +
                    "&code=" + code);
            outputStreamWriter.flush();
            String response = streamToString(httpsURLConnection.getInputStream());
            JSONObject jsonObject = (JSONObject) new JSONTokener(response).nextValue();

            //Here is access token
            accessToken = jsonObject.getString("access_token");
            userId = jsonObject.getJSONObject("user").getString("id");
            userName = jsonObject.getJSONObject("user").getString("username");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getUserFeeds() {
        try {
            String urlString = ApplicationData.API_URL + "/users/self/media/recent/?access_token=" + accessToken;
            URL url = new URL(urlString);
            InputStream inputStream = url.openConnection().getInputStream();
            return streamToString(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String streamToString(InputStream is) throws IOException {
        String str = "";

        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is));

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                reader.close();
            } finally {
                is.close();
            }

            str = sb.toString();
        }

        return str;
    }
}
