package merejy.fr.menuachat3.Data.DataTask;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import merejy.fr.menuachat3.Data.AppDatabase;
import merejy.fr.menuachat3.Data.Dao.ArticleDao;
import merejy.fr.menuachat3.Data.DataObject.ArticleWithCategorie;
import merejy.fr.menuachat3.Data.DataTask.LoaderAskerInterface.LoaderAsker_Article;
import merejy.fr.menuachat3.Data.DataTask.LoaderAskerInterface.LoaderAsker_CategorieArticle;

public class Load_Article_ByName extends AsyncTask<String,Void,List<ArticleWithCategorie>> {

    private Activity activity;
    private LoaderAsker_Article loaderAsker;

    public Load_Article_ByName(Activity activity , LoaderAsker_Article loaderAsker){
        this.activity = activity;
        this.loaderAsker = loaderAsker;
    }

    @Override
    protected List<ArticleWithCategorie> doInBackground(String... names) {
        List<ArticleWithCategorie> result = new ArrayList<>();
        ArticleDao dao = AppDatabase.getInstance(activity).getArticleDao();
        for(String name : names){
            List<ArticleWithCategorie> article = dao.findByName(name);
            if (article.size() == 1 && !result.contains(article.get(0))){
                result.addAll(article);
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<ArticleWithCategorie> ok) {
        super.onPostExecute(ok);
        if(!ok.isEmpty()){
            loaderAsker.setArticle(ok);
        }
    }
}
