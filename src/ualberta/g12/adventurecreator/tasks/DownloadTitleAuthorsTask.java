
package ualberta.g12.adventurecreator.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import ualberta.g12.adventurecreator.data.TitleAuthor;
import ualberta.g12.adventurecreator.views.OView;

import java.util.ArrayList;
import java.util.List;

public class DownloadTitleAuthorsTask extends AsyncTask<String, String, Void> {

    private List<TitleAuthor> tas;
    private Context context;
    private OView<List<TitleAuthor>> view;

    public DownloadTitleAuthorsTask(Context c, OView<List<TitleAuthor>> view) {
        this.context = c;
        this.view = view;
    }

    @Override
    protected Void doInBackground(String... query) {
        // TODO Call the OnlineHelper method to get the real titles

        if (query[0] == null) {
            // No search
        } else {
            // search
        }

        tas = new ArrayList<TitleAuthor>();
        tas.add(new TitleAuthor("Whaaat", "yo", 1));
        tas.add(new TitleAuthor("And who", "Are you", 4));
        tas.add(new TitleAuthor("The proud lord said", "That I must bow so low?", 8));
        tas.add(new TitleAuthor("Only a cat", "of a different coat,", 15));
        tas.add(new TitleAuthor("that's all", "the truth I know.", 16));
        tas.add(new TitleAuthor("In a coat of gold", "or a coat of red", 23));
        tas.add(new TitleAuthor("a lion still has claws", "", 42));
        tas.add(new TitleAuthor("And mine are long and sharp", "my lord", 108));
        tas.add(new TitleAuthor("as long and sharp as yours", "", 10));
        tas.add(new TitleAuthor("And so he spoke", "and so he spoke", 20));
        tas.add(new TitleAuthor("that lord of", "Castamere", 30));

        return null;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Loading stories", Toast.LENGTH_SHORT).show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void voids) {
        view.update(tas);
    }

}
