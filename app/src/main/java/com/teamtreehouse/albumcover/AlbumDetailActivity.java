package com.teamtreehouse.albumcover;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


public class AlbumDetailActivity extends Activity {

    static final String EXTRA_ALBUM_ART_RESID = "EXTRA_ALBUM_ART_RESID"; //private field

    ImageView albumArtView;

    ImageButton fab;

    ViewGroup titlePanel;

    ViewGroup trackPanel;

    ViewGroup detailContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        albumArtView = findViewById(R.id.album_art);
        fab = findViewById(R.id.fab);
        titlePanel = findViewById(R.id.title_panel);
        trackPanel = findViewById(R.id.track_panel);
        detailContainer = findViewById(R.id.detail_container);

        populate();
        albumArtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate();
            }
        });
    }

    private void animate() {
        fab.setScaleX(0);
        fab.setScaleY(0);
        fab.animate().scaleX(1).scaleY(1).start();

        //animate titlePanel using wrapper of PropertyAnimator called ObjectAnimator. this class is static thus
        //ready to be called: then do the same for trackPanel
        //public static ObjectAnimator ofInt (Object target,
        //                String propertyName,
        //                int... values)
        int titleStartValue = titlePanel.getTop(); //define the start from top of activity layout

        int titleEndValue = titlePanel.getBottom(); //in the end in the bottom of the activity layout

        ObjectAnimator.ofInt(titlePanel,"bottom", titleStartValue, titleEndValue).start();

        //for the trackPanel: NOTE: we just put all int values directly inside the static ObjectAnimator ofInt method!
        ObjectAnimator.ofInt(trackPanel, "bottom",
                trackPanel.getTop(), trackPanel.getBottom()).start();
    }



    private void populate() {
        int albumArtResId = getIntent().getIntExtra(EXTRA_ALBUM_ART_RESID, R.drawable.mean_something_kinder_than_wolves);
        albumArtView.setImageResource(albumArtResId);

        Bitmap albumBitmap = getReducedBitmap(albumArtResId);
        colorizeFromImage(albumBitmap);
    }

    private Bitmap getReducedBitmap(int albumArtResId) {
        // reduce image size in memory to avoid memory errors
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 8;
        return BitmapFactory.decodeResource(getResources(), albumArtResId, options);
    }

    private void colorizeFromImage(Bitmap image) {
        Palette palette = Palette.from(image).generate();

        // set panel colors
        int defaultPanelColor = 0xFF808080;
        int defaultFabColor = 0xFFEEEEEE;
        titlePanel.setBackgroundColor(palette.getDarkVibrantColor(defaultPanelColor));
        trackPanel.setBackgroundColor(palette.getLightMutedColor(defaultPanelColor));

        // set fab colors
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled},
                new int[]{android.R.attr.state_pressed}
        };

        int[] colors = new int[]{
                palette.getVibrantColor(defaultFabColor),
                palette.getLightVibrantColor(defaultFabColor)
        };
        fab.setBackgroundTintList(new ColorStateList(states, colors));
    }
}
