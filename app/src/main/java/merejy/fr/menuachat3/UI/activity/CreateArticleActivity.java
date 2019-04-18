package merejy.fr.menuachat3.UI.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import merejy.fr.menuachat3.Data.DataTask.ArticleCategorie_CreateTask;
import merejy.fr.menuachat3.Data.DataTask.Article_createTask;
import merejy.fr.menuachat3.Data.DataTask.SpinnerLoadTask_ArticleCategorie;
import merejy.fr.menuachat3.Data.Entity.Article;
import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;
import merejy.fr.menuachat3.Kernel.MyConstante;
import merejy.fr.menuachat3.R;
import merejy.fr.menuachat3.UI.Interface.Cancellable;
import merejy.fr.menuachat3.UI.Interface.Comfirmable;
import merejy.fr.menuachat3.UI.Listener.OnClickOnCancel;
import merejy.fr.menuachat3.UI.Listener.OnClickOnComfirm;
import merejy.fr.menuachat3.UI.adapter.ArrayAdapter_CategorieArticle;
import merejy.fr.menuachat3.databinding.ActivityCreateArticleBinding;

public class CreateArticleActivity extends AppCompatActivity implements Comfirmable,Cancellable {

   private ActivityCreateArticleBinding ui;
   private ArrayAdapter_CategorieArticle catSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = DataBindingUtil.setContentView(this,R.layout.activity_create_article);

        this.catSpinnerAdapter = new ArrayAdapter_CategorieArticle(this,R.layout.support_simple_spinner_dropdown_item,new ArrayList<ArticleCategorie>());
        SpinnerLoadTask_ArticleCategorie loadTaskSpinner = new SpinnerLoadTask_ArticleCategorie(this,catSpinnerAdapter);
        loadTaskSpinner.execute();

        ui.editCat.setAdapter(this.catSpinnerAdapter);

        ui.cancelButton.setOnClickListener(new OnClickOnCancel(this));
        ui.comfirmeButton.setOnClickListener(new OnClickOnComfirm(this));

        ui.buttonAddCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(CreateArticleActivity.this,CreateArticleCategorieActivity.class);
                if(nextActivity != null){
                    startActivityForResult(nextActivity,MyConstante.REQUEST_ADD_CAT_ARTICLE);
                }
                else{
                    Log.e(MyConstante.APP_TAG,getString(R.string.debug_no_activity_add_cat_article));
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (MyConstante.REQUEST_ADD_CAT_ARTICLE) :
                if (resultCode == MyConstante.RESULT_OK) {
                    // Extract the data returned from the child Activity.
                    String catName = data.getStringExtra(CreateArticleCategorieActivity.NEW_CAT);
                    int catColor = data.getIntExtra(CreateArticleCategorieActivity.NEW_COLOR_CAT,0);
                    //ajout des info dans le spinner et le selectionné
                    ArticleCategorie categorie = new ArticleCategorie(catName,catColor);
                    this.catSpinnerAdapter.add(categorie);
                    ui.editCat.setSelection(this.catSpinnerAdapter.getPosition(categorie));
                }
                break;
                default: Log.d(MyConstante.APP_TAG,getString(R.string.debug_unknow_activity));

        }
    }

    @Override
    public void cancel() {
            this.finish();
    }

    @Override
    public void comfirm() {
        final String nom = ui.editNom.getText().toString();
        final ArticleCategorie categorie = (ArticleCategorie) ui.editCat.getSelectedItem();
        if(!nom.isEmpty() && categorie != null){
            Article_createTask createTask = new Article_createTask(this);
            createTask.execute(new Article(nom,categorie.id,ui.editPoidsfixe.isChecked()));
            //finish dans la task
        }
        else{
            //champ vide
            Toast.makeText(this,R.string.erreur_champ_non_remplis,Toast.LENGTH_SHORT).show();
        }
    }
}
