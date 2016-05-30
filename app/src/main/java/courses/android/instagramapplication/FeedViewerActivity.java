package courses.android.instagramapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FeedViewerActivity extends AppCompatActivity {

    private List<FeedInfo> feeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_viewer);
        GridView gridView = (GridView) findViewById(R.id.feedView);
        Intent intent = getIntent();
        String response = intent.getStringExtra("response");
        feeds = JSONParser.parse(response);

        List<String> images = new ArrayList<>();
        for (FeedInfo feed : feeds) {
            images.add(feed.getImageURL());
        }

        gridView.setAdapter(new ImageListAdapter(FeedViewerActivity.this, images));
    }

    private class ImageListAdapter extends ArrayAdapter {
        private Context context;
        private LayoutInflater inflater;

        private List<String> imageUrls;

        public ImageListAdapter(Context context, List<String> imageUrls) {
            super(context, R.layout.gridview_item_image, imageUrls);

            this.context = context;
            this.imageUrls = imageUrls;

            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (null == convertView) {
                convertView = inflater.inflate(R.layout.gridview_item_image, parent, false);

                convertView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        System.out.println("POSITION =" + position);
                        Intent intent = new Intent(FeedViewerActivity.this, ImageDetailActivity.class);
                        intent.putExtra("URL", imageUrls.get(position));
                        FeedViewerActivity.this.startActivity(intent);
                    }
                });
            }

            Picasso
                    .with(context)
                    .load(imageUrls.get(position))
                    .fit()
                    .into((ImageView) convertView);

            return convertView;
        }
    }
}
