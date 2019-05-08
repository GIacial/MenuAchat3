package merejy.fr.menuachat3.UI.Listener.Factory;

import android.app.Activity;
import android.view.View;

import merejy.fr.menuachat3.UI.Interface.Groupe;
import merejy.fr.menuachat3.UI.Popup.EntierPopup;
import merejy.fr.menuachat3.UI.Popup.Module.entier.EntierAddToGroupModule;

public class AddToGroupClickFactory<T> implements OnClickFactory<T> {

    private Groupe<T> groupe;

    public AddToGroupClickFactory(Groupe<T> groupe){
        this.groupe = groupe;
    }

    @Override
    public View.OnClickListener createListener(final T item, final Activity activity) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntierPopup.showInstance(new EntierAddToGroupModule<T>(groupe,item,activity),activity);
            }
        };
    }
}
