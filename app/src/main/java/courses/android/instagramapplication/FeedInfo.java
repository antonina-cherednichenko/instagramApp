package courses.android.instagramapplication;

/**
 * Created by tonya on 5/30/16.
 */
public class FeedInfo {

    private String thumbnailURL;
    private String imageURL;

    public FeedInfo(String thumbnailURL, String imageURL) {
        this.thumbnailURL = thumbnailURL;
        this.imageURL = imageURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public String getImageURL() {
        return imageURL;
    }
}
