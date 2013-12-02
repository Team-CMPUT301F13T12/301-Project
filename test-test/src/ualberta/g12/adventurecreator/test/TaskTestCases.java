package ualberta.g12.adventurecreator.test;

import ualberta.g12.adventurecreator.tasks.CacheStoryTask;
import ualberta.g12.adventurecreator.tasks.DownloadStoryTask;
import ualberta.g12.adventurecreator.tasks.DownloadTitleAuthorsTask;
import ualberta.g12.adventurecreator.tasks.PublishStoryTask;
import ualberta.g12.adventurecreator.tasks.TryPublishStoryTask;
import ualberta.g12.adventurecreator.views.MainActivity;
import ualberta.g12.adventurecreator.views.OView;
import ualberta.g12.adventurecreator.views.OnlineStoryViewActivity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

public class TaskTestCases extends
		ActivityInstrumentationTestCase2<MainActivity> {

	public TaskTestCases(Class<MainActivity> activityClass) {
		super(activityClass);
		this.context = getActivity();
	}

	private CacheStoryTask cacheTask;
	private DownloadStoryTask downloadTask;
	private DownloadTitleAuthorsTask downloadTitleAuthors;
	private PublishStoryTask publishTask;
	private TryPublishStoryTask tryPublishTask;
	private Context context;

	public void testNoNulls() {
		this.cacheTask = new CacheStoryTask(this.context);
		this.downloadTask = new DownloadStoryTask(this.context);
		// This seems silly
		OnlineStoryViewActivity o = new OnlineStoryViewActivity();
		this.downloadTitleAuthors = new DownloadTitleAuthorsTask(this.context, o);
		this.publishTask = new PublishStoryTask(this.context);
		this.tryPublishTask = new TryPublishStoryTask(this.context);
		assertNotNull(this.cacheTask);
		assertNotNull(this.downloadTask);
		assertNotNull(this.downloadTitleAuthors);
		assertNotNull(this.publishTask);
		assertNotNull(this.tryPublishTask);
	}
}
