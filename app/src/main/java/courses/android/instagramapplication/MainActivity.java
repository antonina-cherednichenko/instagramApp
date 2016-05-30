package courses.android.instagramapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new AuthWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(ApplicationData.authURLString);
    }

    public class AuthWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith(ApplicationData.CALLBACK_URL)) {
                String parts[] = url.split("=");
                String requestToken = parts[1];
                try {
                    //TODO - check how to get result of asyncTask asynchronously, not like here
                    String feedResult = new InstagramTask().execute(requestToken).get();
                    Intent feedIntent = new Intent(MainActivity.this, FeedViewerActivity.class);
                    feedIntent.putExtra("response", feedResult);
                    MainActivity.this.startActivity(feedIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return true;
            }
            return false;
        }
    }


}
