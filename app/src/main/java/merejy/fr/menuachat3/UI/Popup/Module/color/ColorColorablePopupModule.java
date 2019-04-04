package merejy.fr.menuachat3.UI.Popup.Module.color;


import android.graphics.Color;

import merejy.fr.menuachat3.UI.Interface.Colorable;

public class ColorColorablePopupModule implements ColorPopupModule {

    private Colorable colorable;

    public ColorColorablePopupModule(Colorable colorable){
        this.colorable = colorable;
    }

    @Override
    public Runnable getMethodOnComfirm(final int red, final int green,final int blue) {
        return new Runnable() {
            @Override
            public void run() {
                colorable.setColor(Color.rgb(red,green,blue));
            }
        };
    }

    @Override
    public Runnable getMethodOnAnuller() {
        return new Runnable() {
            @Override
            public void run() {

            }
        };
    }

}
