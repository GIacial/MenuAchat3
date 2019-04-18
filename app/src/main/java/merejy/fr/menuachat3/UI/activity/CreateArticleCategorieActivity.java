package merejy.fr.menuachat3.UI.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import merejy.fr.menuachat3.Data.AppDatabase;
import merejy.fr.menuachat3.Data.Dao.ArticleCategorieDao;
import merejy.fr.menuachat3.Data.DataTask.ArticleCategorie_CreateTask;
import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;
import merejy.fr.menuachat3.Kernel.ColorManager;
import merejy.fr.menuachat3.Kernel.MyConstante;
import merejy.fr.menuachat3.R;
import merejy.fr.menuachat3.UI.Interface.Cancellable;
import merejy.fr.menuachat3.UI.Interface.Comfirmable;
import merejy.fr.menuachat3.UI.Listener.OnClickOnCancel;
import merejy.fr.menuachat3.UI.Listener.OnClickOnComfirm;
import merejy.fr.menuachat3.UI.Popup.ColorPopup;
import merejy.fr.menuachat3.UI.Popup.Module.color.ColorColorablePopupModule;
import merejy.fr.menuachat3.UI.Interface.Colorable;
import merejy.fr.menuachat3.databinding.ActivityCreateArticleCategorieBinding;


public class CreateArticleCategorieActivity extends AppCompatActivity implements Colorable,Cancellable,Comfirmable{

    public final static String NEW_CAT = "new cat";
    public final static String NEW_COLOR_CAT = "new color cat";

    private ActivityCreateArticleCategorieBinding ui;
    private int colorCategorie = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = DataBindingUtil.setContentView(this,R.layout.activity_create_article_categorie);

        this.setColor(colorCategorie);
        ui.editCouleur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPopup.showInstance(new ColorColorablePopupModule(CreateArticleCategorieActivity.this),CreateArticleCategorieActivity.this);
            }
        });

        ui.AnnulerButton.setOnClickListener(new OnClickOnCancel(CreateArticleCategorieActivity.this));
        ui.comfirmButton.setOnClickListener(new OnClickOnComfirm(CreateArticleCategorieActivity.this));
    }

    @Override
    public void setColor(int color) {
        colorCategorie = color;
        ui.editCouleur.setBackgroundColor(color);
        ui.editCouleur.setTextColor(ColorManager.getTextColorForCategorieColorInst(color));
    }

    @Override
    public void cancel() {
        Intent resultIntent = new Intent();
        setResult(MyConstante.RESULT_CANCEL, resultIntent);
        this.finish();
    }

    @Override
    public void comfirm() {
        final String nomCat = ui.editNomCat.getText().toString();
        if(!nomCat.isEmpty()){
            ArticleCategorie_CreateTask createTask = new ArticleCategorie_CreateTask(this);
            createTask.execute(new ArticleCategorie(nomCat,colorCategorie));
            //finish dans la task
        }
        else{
            //champ vide
            Toast.makeText(this,R.string.erreur_champ_non_remplis,Toast.LENGTH_SHORT).show();
        }
    }
}
