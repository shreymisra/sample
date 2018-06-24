package org.shreya.project.sample;

import android.support.design.widget.Snackbar;
import android.view.View;

public class ColoredSnackbar {

    private static final int red = 0xfff44336;
    private static final int green = 0xff4caf50;
    private static final int blue = 0xff2196f3;
    private static final int orange = 0xffff5722;
    private static final int yellow = 0xffffff00;
    private static final int white = 0xffffffff;

    private static View getSnackBarLayout(Snackbar snackbar) {
        if (snackbar != null) {
            return snackbar.getView();
        }
        return null;
    }
 
    private static Snackbar colorSnackBar(Snackbar snackbar, int colorId) {
        View snackBarView = getSnackBarLayout(snackbar);
        if (snackBarView != null) {
            snackBarView.setBackgroundColor(colorId);
        }
 
        return snackbar;
    }
 
    public static Snackbar info(Snackbar snackbar) {
        return colorSnackBar(snackbar, blue);
    }
 
    public static Snackbar warning(Snackbar snackbar) {
        return colorSnackBar(snackbar, orange);
    }
 
    public static Snackbar alert(Snackbar snackbar) {
        return colorSnackBar(snackbar, red);
    }
 
    public static Snackbar confirm(Snackbar snackbar) {
        return colorSnackBar(snackbar, green);
    }

    public static Snackbar perfect(Snackbar snackbar) {
        return colorSnackBar(snackbar, white);
    }

    public static Snackbar normal(Snackbar snackbar) {
        return colorSnackBar(snackbar, yellow);
    }
}