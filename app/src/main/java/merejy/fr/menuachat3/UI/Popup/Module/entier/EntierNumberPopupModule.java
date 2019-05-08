package merejy.fr.menuachat3.UI.Popup.Module.entier;

import java.io.Serializable;

public interface EntierNumberPopupModule extends Serializable{

    Runnable getMethodOnComfirm(int val);

    Runnable getMethodOnAnuller();

    String getQuestion();
}
