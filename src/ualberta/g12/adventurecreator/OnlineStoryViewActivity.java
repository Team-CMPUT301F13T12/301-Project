
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OnlineStoryViewActivity extends Activity implements OnItemClickListener {

    private Button mainButton;
    private ListView listView;
    private StoryAuthorMapListAdapter adapter;
    private List<TitleAuthor> titleAuthors;

    private static boolean downloadMode = true;
    private static final String DOWNLOAD_MODE = "download_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_story_view);

        loadTitleAuthors();

        setUpUi();

    }

    private void loadTitleAuthors() {
        /*
         * Once online works we are going to load our list full of the title
         * authors
         */
        titleAuthors = new ArrayList<TitleAuthor>();
        
        DownloadTitleAuthors dta = new DownloadTitleAuthors();
        dta.execute(new Void[] {});
        
//        titleAuthors = new ArrayList<TitleAuthor>();
//        titleAuthors.add(new TitleAuthor("And so I cry sometimes", "when I'm lying in bed"));
//        titleAuthors.add(new TitleAuthor("Just to get it all out,", "what's in my head"));
//        titleAuthors.add(new TitleAuthor("And I,", "I am feeling peculiar"));
//        titleAuthors.add(new TitleAuthor("And so I wake up in the morning", "and I step outside"));
//        titleAuthors.add(new TitleAuthor("And I take a big breath", "and I get real high"));
//        titleAuthors.add(new TitleAuthor("And I scream", "from the top of my lungs,"));
//        titleAuthors.add(new TitleAuthor("What's goin' on", "ooh"));
//        titleAuthors.add(new TitleAuthor("And I say", "hey-yeah-yeah-yeah-yeah hey-yeah-yeah"));
//        titleAuthors.add(new TitleAuthor("I said hey", "What's going on"));
//        titleAuthors.add(new TitleAuthor("And I say", "hey-yeah-yeah-yeah-yeah hey-yeah-yeah"));
//        titleAuthors.add(new TitleAuthor("And I said hey", "What's going on"));
//        titleAuthors.add(new TitleAuthor("And I try", "Oh my god, do I try"));
//        titleAuthors.add(new TitleAuthor("I try all the time", "in this institution"));
//        titleAuthors.add(new TitleAuthor("And I pray,", "Oh my God, do I pray"));
//        titleAuthors.add(new TitleAuthor("I pray every single day", "FOR A REVOLUTION!"));
    }

    private void setUpUi() {
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

        listView = (ListView) findViewById(R.id.online_story_author_listview);
        // Set up ListView Stuff-*
        adapter = new StoryAuthorMapListAdapter(this, R.layout.listview_story_list, titleAuthors);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        downloadMode = settings.getBoolean(DOWNLOAD_MODE, true);
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(DOWNLOAD_MODE, downloadMode);

        // Commit them datas
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.online_story_view, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Make sure check box is checked if it needs to be
        menu.findItem(R.id.online_download_mode).setChecked(downloadMode);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.online_download_mode:
                boolean alreadyChecked = item.isChecked();
                // Set it to the opposite of what it was previously
                item.setChecked(!alreadyChecked);

                // Update the flag
                downloadMode = item.isChecked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A holder class that contains the Title and Author of a story
     */
    public class TitleAuthor {
        public final String title, author;

        public TitleAuthor(String t, String a) {
            this.title = t;
            this.author = a;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
        // Download or stream story
        TitleAuthor ta = titleAuthors.get(pos);
        if (downloadMode) {
            // Download story lol
            Toast.makeText(this, String.format("Downloading story %s", ta.title),
                    Toast.LENGTH_SHORT).show();

            DownloadStory ds = new DownloadStory();
            ds.execute(new TitleAuthor[] {ta});
            
        } else {
            // Stream story
            Toast.makeText(this, String.format("Loading story %s", ta.title), Toast.LENGTH_SHORT)
                    .show();
            // Send some stuff to FragmentViewActivity
        }
    }

    private class DownloadStory extends AsyncTask<TitleAuthor, Void, String> {

        private Story s;
        
        @SuppressWarnings("unused")
        @Override
        protected String doInBackground(TitleAuthor... params) {
            // TODO Actually download the story here - this thread will actually do something
            s = new Story(params[0].title, params[0].author);

            // Once we're actually downloading the story this will matter
            if (true) {
                return String.format("%s Download complete", params[0].title);
            } else {
                return String.format("%s Download Failed", params[0].title);
            }

        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getApplicationContext(), "Story Download Cancelled", Toast.LENGTH_SHORT).show();
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(String result) {
            StoryListController slc = AdventureCreator.getStoryListController();
            // Add the story to the list
            slc.addStory(s);
            
            OfflineIOHelper offlineHelper = AdventureCreator.getOfflineIOHelper();
            // Save our list of stories
            offlineHelper.saveOfflineStories(AdventureCreator.getStoryList());
            
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            super.onPostExecute(result);
        }
    }
    
    private class DownloadTitleAuthors extends AsyncTask<Void, String, List<TitleAuthor>>{

        private List<TitleAuthor> tas;
        
        @Override
        protected List<TitleAuthor> doInBackground(Void... params) {
            // TODO: Call The OnlineHelper get Titles and Authors method here plz
            
            // Pretend we're getting this from the intenets
            tas = new ArrayList<TitleAuthor>();
            tas.add(new TitleAuthor("And who", "Are you"));
            tas.add(new TitleAuthor("The proud lord said", "That I must bow so low?"));
            tas.add(new TitleAuthor("Only a cat", "of a different coat,"));
            tas.add(new TitleAuthor("that's all", "the truth I know."));
            tas.add(new TitleAuthor("In a coat of gold", "or a coat of red"));
            tas.add(new TitleAuthor("a lion still has claws", ""));
            tas.add(new TitleAuthor("And mine are long and sharp", "my lord"));
            tas.add(new TitleAuthor("as long and sharp as yours", ""));
            tas.add(new TitleAuthor("And so he spoke", "and so he spoke"));
            tas.add(new TitleAuthor("that lord of", "Castamere"));
            
            return null;
        }
        
        @Override
        protected void onPreExecute(){
            Toast.makeText(getApplicationContext(), "Loading stories", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getApplicationContext(), "Story Loading Cancelled", Toast.LENGTH_SHORT).show();
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(List<TitleAuthor> result) {
            titleAuthors.clear();
            titleAuthors.addAll(tas);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Stories Loaded." + titleAuthors.size(), Toast.LENGTH_SHORT).show();
            super.onPostExecute(result);
        }
        
    }

}
