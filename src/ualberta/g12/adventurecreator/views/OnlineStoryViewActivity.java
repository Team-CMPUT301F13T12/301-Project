
package ualberta.g12.adventurecreator.views;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.TitleAuthor;
import ualberta.g12.adventurecreator.tasks.DownloadStoryTask;
import ualberta.g12.adventurecreator.tasks.DownloadTitleAuthorsTask;

import java.util.ArrayList;
import java.util.List;

public class OnlineStoryViewActivity extends Activity implements OnItemClickListener,
        OView<List<TitleAuthor>> {

    private Button mainButton;
    private ListView listView;
    private StoryAuthorMapListAdapter adapter;
    private List<TitleAuthor> titleAuthors;
    private DownloadTitleAuthorsTask downloadTitleAuthorsTask;

    private static boolean downloadMode = true;
    public static final String DOWNLOAD_MODE = "download_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_story_view);

        downloadTitleAuthorsTask = new DownloadTitleAuthorsTask(getApplicationContext(), this);

        loadTitleAuthors();

        setUpUi();

        // Lets see who started us
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Intent searchIntent = new Intent(getApplicationContext(),
                    OnlineStorySearchActivity.class);
            searchIntent.putExtra(SearchManager.QUERY, query);
            searchIntent.putExtra(OnlineStoryViewActivity.DOWNLOAD_MODE, downloadMode);
            startActivity(searchIntent);
        }
    }

    private void loadTitleAuthors() {
        /*
         * Once online works we are going to load our list full of the title
         * authors
         */
        titleAuthors = new ArrayList<TitleAuthor>();

        downloadTitleAuthorsTask.execute(new String[] {
                null
        });

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

    public void update(List<TitleAuthor> list) {
        titleAuthors.clear();
        titleAuthors.addAll(list);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Stories Loaded " + list.size(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.online_story_view, menu);

        // Associate searchable configuration with the searchview
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.online_story_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
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

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
        // Download or stream story
        TitleAuthor ta = titleAuthors.get(pos);
        if (downloadMode) {
            // Download story lol
            Toast.makeText(this, String.format("Downloading story %s", ta.getTitle()),
                    Toast.LENGTH_SHORT).show();
            
            DownloadStoryTask dst = new DownloadStoryTask(getApplicationContext());
            dst.execute(new TitleAuthor[] {ta});
            

        } else {
            // Stream story
            Toast.makeText(this, String.format("Loading story %s", ta.getTitle()),
                    Toast.LENGTH_SHORT)
                    .show();
            // Send some stuff to FragmentViewActivity
        }
    }

    public class DownloadStory extends AsyncTask<TitleAuthor, Void, String> {

        private Story s;

        @SuppressWarnings("unused")
        @Override
        protected String doInBackground(TitleAuthor... params) {
            // TODO Actually download the story here - this thread will actually
            // do something
            s = new Story(params[0].getTitle(), params[0].getAuthor());

            // Once we're actually downloading the story this will matter
            if (true) {
                return String.format("%s Download complete", params[0].getTitle());
            } else {
                return String.format("%s Download Failed", params[0].getTitle());
            }

        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getApplicationContext(), "Story Download Cancelled", Toast.LENGTH_SHORT)
                    .show();
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

}
