
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class OnlineStoryViewActivity extends Activity {

    private Button mainButton;
    private ListView storyListView;
    private List<TitleAuthor> titleAuthors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_story_view);

        mainButton = (Button) findViewById(R.id.online_story_start_main_activity);
        mainButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                 * The only time we're ever going to be called is when
                 * MainActivity starts us. If we finish we'll bring the user
                 * back to the the MainActivity
                 */
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.online_story_view, menu);
        return true;
    }

    public class TitleAuthor{
        public final String title, author;
        public TitleAuthor(String t, String a){
            this.title = t;
            this.author = a;
        }
    }
}
