package com.teamtreehouse.albumcover;

import android.animation.AnimatorSet;
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
        //set this to be like the animation sequence at the bottom but make it play together first before join:
        //first we make animation of fab scale to x:
        ObjectAnimator fabScaleX = ObjectAnimator.ofFloat(fab, "scaleX", 0, 1);

        //then scale to Y:
        ObjectAnimator fabScaleY = ObjectAnimator.ofFloat(fab, "scaleY", 0, 1);

        //and then join them to play together:
        AnimatorSet fabScale = new AnimatorSet();
        fabScale.playTogether(fabScaleX, fabScaleY);


        int titleStartValue = titlePanel.getTop();

        int titleEndValue = titlePanel.getBottom();

        //set this to be assigned to ObjectAnimator Effect to be choreographed:
        ObjectAnimator animatorTitle = ObjectAnimator.ofInt(titlePanel,"bottom",
                titleStartValue, titleEndValue);

        //set this also to be assigned to Object Animator for choreographing:
        ObjectAnimator animatorTrack = ObjectAnimator.ofInt(trackPanel, "bottom",
                trackPanel.getTop(), trackPanel.getBottom());

        //create the set animation by instantiating a new AnimatorSet object:
        AnimatorSet set = new AnimatorSet();

        //using the set Object above to choreograph the whole animations from fab to titles:
        set.playSequentially(fabScale, animatorTitle, animatorTrack);

        //fix the flickering of views by adding these codes:
        titlePanel.setBottom(titleStartValue); //set the bottom to be Top.... hmmmm
        trackPanel.setBottom(trackPanel.getTop()); //set the bottom of track panel as top?
        //both panel comes into animation slided from top to bottom thus the initial state before animation is not
        //visible thus the bottom meet the top of the view.

        //as for the fab we need initialized the starting point of both scaleX and scaleY as 0:
        fab.setScaleX(0);
        fab.setScaleY(0);

        //start the animation: NOTE we cannot chain it with the code above, we need to separate it:
        set.start();
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
