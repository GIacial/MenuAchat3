package merejy.fr.menuachat3.UI.Popup.Module.color;

import java.io.Serializable;


public interface ColorPopupModule extends Serializable {

    Runnable getMethodOnComfirm(int red , int green , int blue);

    Runnable getMethodOnAnuller();
}