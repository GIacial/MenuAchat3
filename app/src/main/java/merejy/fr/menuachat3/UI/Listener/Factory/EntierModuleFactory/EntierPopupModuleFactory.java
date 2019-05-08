package merejy.fr.menuachat3.UI.Listener.Factory.EntierModuleFactory;

import android.app.Activity;

import merejy.fr.menuachat3.UI.Interface.Groupe;
import merejy.fr.menuachat3.UI.Popup.Module.entier.EntierNumberPopupModule;

public interface EntierPopupModuleFactory<T> {

    EntierNumberPopupModule createModule(Groupe<T> groupe , Activity activity , T object);
}
