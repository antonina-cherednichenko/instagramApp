package courses.android.instagramapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        Intent intent = getIntent();
        String imageURL = intent.getStringExtra("URL");

        Picasso
                .with(this)
                .load(imageURL)
                .fit()
                .into((ImageView) findViewById(R.id.imageDetail));
    }
}
