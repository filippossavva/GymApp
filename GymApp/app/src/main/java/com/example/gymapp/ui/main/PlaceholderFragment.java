package com.example.gymapp.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.gymapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_facilities_classes, container, false);
        TextView tv = root.findViewById(R.id.textView4);
        Button b2 = root.findViewById(R.id.button2);
        CheckBox c1 = root.findViewById(R.id.cbWeights);
        CheckBox c2 = root.findViewById(R.id.cbClasses);
        CheckBox c3 = root.findViewById(R.id.cbPersonal);
        Switch s1 = root.findViewById(R.id.swTrx);
        Switch s2 = root.findViewById(R.id.swBoxing);
        Switch s3 = root.findViewById(R.id.swPilates);
        Switch s4 = root.findViewById(R.id.swTabata);
        Switch s5 = root.findViewById(R.id.swYoga);
        Switch s6 = root.findViewById(R.id.swZumba);
//        VideoView videoRtx = root.findViewById(R.id.videoTrx);
//        String videoPath = "android.resource://com.android.developer.arsl.videoplayer/"+R.raw.TRX;
//        Uri uri = Uri.parse(videoPath);
//        videoRtx.setVideoURI(uri);
//        MediaController mediaController = new MediaController(this);
//        videoRtx.setMediaController(mediaController);
//        mediaController.setAnchorView(videoRtx);


        if(getArguments().getInt(ARG_SECTION_NUMBER) == 1)
        {
            tv.setText("Please select the type of gymnastic you would like:");
            b2.setVisibility(View.INVISIBLE);
            s1.setVisibility(View.INVISIBLE);
            s2.setVisibility(View.INVISIBLE);
            s3.setVisibility(View.INVISIBLE);
            s4.setVisibility(View.INVISIBLE);
            s5.setVisibility(View.INVISIBLE);
            s6.setVisibility(View.INVISIBLE);

        }
        else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2)
        {
            tv.setText("Please select the class you want to join in:");
            c1.setVisibility(View.INVISIBLE);
            c2.setVisibility(View.INVISIBLE);
            c3.setVisibility(View.INVISIBLE);


        }
        return root;
    }
}