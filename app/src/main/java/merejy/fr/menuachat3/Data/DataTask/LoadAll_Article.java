package merejy.fr.menuachat3.Data.DataTask;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.List;

import merejy.fr.menuachat3.Data.AppDatabase;
import merejy.fr.menuachat3.Data.DataObject.ArticleWithCategorie;
import merejy.fr.menuachat3.Data.DataTask.LoaderAskerInterface.LoaderAsker_Article;
import merejy.fr.menuachat3.Data.DataTask.LoaderAskerInterface.LoaderAsker_CategorieArticle;
import merejy.fr.menuachat3.Data.Entity.Article;
import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;

public class LoadAll_Article extends AsyncTask<Void,Void,List<ArticleWithCategorie>> {

    private Activity activity;
    private LoaderAsker_Article loaderAsker;

    public LoadAll_Article(Activity activity , LoaderAsker_Article loaderAsker){
        this.activity = activity;
        this.loaderAsker = loaderAsker;
    }

    @Override
    protected List<ArticleWithCategorie> doInBackground(Void... voids) {
        return AppDatabase.getInstance(activity).getArticleDao().getAllWithCategorie();
    }

    @Override
    protected void onPostExecute(List<ArticleWithCategorie> ok) {
        super.onPostExecute(ok);
        if(!ok.isEmpty()){
            loaderAsker.setArticle(ok);
        }
    }
}
