package com.teamtreehouse.albumcover.sandbox;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.jimulabs.mirrorsandbox.MirrorSandboxBase;
import com.teamtreehouse.albumcover.R;

/**
 * Created by lintonye on 15-10-07.
 */
public class AlbumDetailBox extends MirrorSandboxBase {


    ImageView albumArtView;

    ImageButton fab;

    ViewGroup cyanPanel;

    ViewGroup whitePanel;

    ViewGroup detailContainer;

    public AlbumDetailBox(View rootView) {
        super(rootView);
        albumArtView = rootView.findViewById(R.id.album_art);
        fab = rootView.findViewById(R.id.fab);
        cyanPanel = rootView.findViewById(R.id.title_panel);
        whitePanel = rootView.findViewById(R.id.track_panel);
        detailContainer = rootView.findViewById(R.id.detail_container);
        //ButterKnife.bind(this, rootView);
    }

    @Override
    public void $onLayoutDone(View rootView) {
        // Put animation code here
    }
}
