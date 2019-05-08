package merejy.fr.menuachat3.UI.Listener.Factory.OnClickFactory;

import android.app.Activity;
import android.view.View;

import merejy.fr.menuachat3.UI.Interface.Groupe;
import merejy.fr.menuachat3.UI.Listener.Factory.EntierModuleFactory.EntierPopupModuleFactory;
import merejy.fr.menuachat3.UI.Popup.EntierPopup;
import merejy.fr.menuachat3.UI.Popup.Module.entier.EntierAddToGroupModule;

public class AddToGroupClickFactory<T> implements OnClickFactory<T> {

    private Groupe<T> groupe;
    private EntierPopupModuleFactory<T> moduleFactory;

    public AddToGroupClickFactory(Groupe<T> groupe, EntierPopupModuleFactory<T> moduleFactory){
        this.groupe = groupe;
        this.moduleFactory = moduleFactory;
    }

    @Override
    public View.OnClickListener createListener(final T item, final Activity activity) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntierPopup.showInstance(moduleFactory.createModule(groupe,activity,item),activity);
            }
        };
    }
}
