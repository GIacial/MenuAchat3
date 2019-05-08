package merejy.fr.menuachat3.UI.Listener.Factory.OnClickFactory;

import android.app.Activity;
import android.view.View;

public interface OnClickFactory <T>{

    View.OnClickListener createListener (final T item, final Activity activity);

}
