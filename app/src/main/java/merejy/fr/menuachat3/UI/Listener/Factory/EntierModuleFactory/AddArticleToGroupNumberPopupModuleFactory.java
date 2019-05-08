package merejy.fr.menuachat3.UI.Listener.Factory.EntierModuleFactory;

import android.app.Activity;

import merejy.fr.menuachat3.Data.Entity.Article;
import merejy.fr.menuachat3.UI.Interface.Groupe;
import merejy.fr.menuachat3.UI.Popup.Module.entier.EntierAddToGroupModule;
import merejy.fr.menuachat3.UI.Popup.Module.entier.EntierNumberPopupModule;
import merejy.fr.menuachat3.UI.Popup.Module.entier.GrammeAddToGroupModule;

public class AddArticleToGroupNumberPopupModuleFactory implements EntierPopupModuleFactory<Article> {
    @Override
    public EntierNumberPopupModule createModule(Groupe<Article> groupe, Activity activity, Article object) {
        if(object.poids_fixe){
            return new EntierAddToGroupModule<Article>(groupe,object,activity);
        }
        return new GrammeAddToGroupModule<Article>(groupe,object,activity);
    }
}
