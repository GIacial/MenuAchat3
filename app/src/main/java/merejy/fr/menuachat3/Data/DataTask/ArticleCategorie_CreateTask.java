package merejy.fr.menuachat3.Data.DataTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import merejy.fr.menuachat3.Data.AppDatabase;
import merejy.fr.menuachat3.Data.Dao.ArticleCategorieDao;
import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;
import merejy.fr.menuachat3.R;

public class ArticleCategorie_CreateTask extends AsyncTask<ArticleCategorie,String,Boolean> {

    private Activity activity;

    public ArticleCategorie_CreateTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(ArticleCategorie... articleCategories) {
        final ArticleCategorieDao articleCategorieDao = AppDatabase.getInstance(activity).getArticleCategorieDao();
        boolean ok = true;
        for ( ArticleCategorie categorie : articleCategories){
            // Escape early if cancel() is called
            if (isCancelled()){
                ok = false;
                break;
            }
            if(articleCategorieDao.findByName(categorie.nom).isEmpty()){
                System.err.println("ok free");
                articleCategorieDao.insertAll(categorie);

            }
            else {
                //nom pas dispo
                this.publishProgress(categorie.nom +" "+activity.getString(R.string.erreur_nom_deja_pris) );
                ok = false;
            }
        }

        return ok;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        for (String val : values) {
            Toast.makeText(activity, val, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPostExecute(Boolean ok) {
        super.onPostExecute(ok);
        if(ok){
            Toast.makeText(activity,R.string.text_categorie_rajout_comfirmation,Toast.LENGTH_SHORT).show();
            this.activity.finish();
        }
    }
}
