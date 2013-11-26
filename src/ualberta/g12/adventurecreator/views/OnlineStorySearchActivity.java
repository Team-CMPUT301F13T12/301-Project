
package ualberta.g12.adventurecreator.views;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.tasks.CacheStoryTask;
import ualberta.g12.adventurecreator.tasks.DownloadStoryTask;
import ualberta.g12.adventurecreator.tasks.DownloadTitleAuthorsTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OnlineStorySearchActivity extends Activity implements OnItemClickListener,
        OView<List<Story>> {

    private List<Story> tas;
    private ListView listView;
    private StoryAuthorMapListAdapter adapter;
    private static boolean downloadMode;
    private String query;

    private static final String TAG = "OnlineStorySearchActivity";
    private static final boolean DEBUG = true;

    private DownloadTitleAuthorsTask downloadTitleAuthorsTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_story_search);

        setupActionBar();

        downloadTitleAuthorsTask = new DownloadTitleAuthorsTask(
                getApplicationContext(), this);

        // See who started us
        handleIntent(getIntent());

        // Load our title authors from buddy
        loadTitleAuthors();

        setUpUi();
    }

    private void setupActionBar() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadTitleAuthors() {
        tas = new ArrayList<Story>();

        downloadTitleAuthorsTask.execute(new String[] {
                this.query
        });
    }

    private void setUpUi() {
        listView = (ListView) findViewById(R.id.online_story_search_listview);
        adapter = new StoryAuthorMapListAdapter(this, R.layout.listview_story_list, tas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void handleIntent(Intent i) {
        // Set up the DownloadMode flag
        downloadMode = i.getBooleanExtra(OnlineStoryViewActivity.DOWNLOAD_MODE, true);

        /*
         * If this is null then we don't have a search term and we don't have to
         * change anything
         */
        if (i.getStringExtra(SearchManager.QUERY) != null) {
            if (DEBUG)
                Log.d(TAG, "There was some stuff to search");
            String query = i.getStringExtra(SearchManager.QUERY).toLowerCase(Locale.CANADA);
            if (DEBUG)
                Log.d(TAG, String.format("Search query was: %s", query));
            this.query = query;
            // performSearch();
        }
    }

    private void performSearch() {

        if (this.tas != null && this.query != null) {
            if (DEBUG)
                Log.d(TAG,
                        String.format("Searching for query: %s", this.query));
            List<Story> stories = new ArrayList<Story>();
            stories.addAll(tas);
            tas.clear();
            if (DEBUG)
                Log.d(TAG, String.format("%d TitleAuthors to search from", tas.size()));
            for (Story t : stories) {
                if (t.getTitle() == null || t.getAuthor() == null) {
                    // Do nothing as this is a title author with a null title or
                    // // author
                } else {
                    if (t.getTitle().toLowerCase(Locale.CANADA).contains(this.query) ||
                            t.getAuthor().toLowerCase(Locale.CANADA).contains(this.query)) {
                        if (DEBUG)
                            Log.d(TAG, String.format("Adding story %s", t.getTitle()));
                        tas.add(t);
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        downloadMode = settings.getBoolean(OnlineStoryViewActivity.DOWNLOAD_MODE, true);
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(OnlineStoryViewActivity.DOWNLOAD_MODE, downloadMode);

        editor.commit();
    }

    @Override
    protected void onNewIntent(Intent i) {
        setIntent(i);
        handleIntent(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.online_story_search, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.online_download_mode).setChecked(downloadMode);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.online_download_mode:
                boolean alreadyChecked = item.isChecked();
                // Set it to the opposite of what it was previously
                item.setChecked(!alreadyChecked);

                // Update the flag
                downloadMode = item.isChecked();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
        // Download or stream story
        Story ta = tas.get(pos);
        if (downloadMode) {
            // Download story lol
            Toast.makeText(this, String.format("Downloading story %s", ta.getTitle()),
                    Toast.LENGTH_SHORT).show();

            DownloadStoryTask dst = new DownloadStoryTask(getApplicationContext());
            dst.execute(new Story[] {
                    ta
            });

        } else {
            // Stream story
            // Stream story
            Toast.makeText(this, String.format("Loading story %s", ta.getTitle()),
                    Toast.LENGTH_SHORT)
                    .show();
            // Send some stuff to FragmentViewActivity
            CacheStoryTask cst = new CacheStoryTask(this);
            cst.execute(new Story[] {
                ta
            });
        }
    }

    @Override
    public void update(List<Story> list) {
        tas.clear();
        tas.addAll(list);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Stories Loaded " + list.size(),
                Toast.LENGTH_SHORT).show();

    }
}
