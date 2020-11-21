package com.example.gymapp.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.gymapp.GymSelectActivity;
import com.example.gymapp.R;
import com.example.gymapp.ui.login.LoginActivity;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

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
        Button b2 = root.findViewById(R.id.bCalcFee);
        CheckBox c1 = root.findViewById(R.id.cbWeights);
        CheckBox c2 = root.findViewById(R.id.cbClasses);
        CheckBox c3 = root.findViewById(R.id.cbPersonal);
        Switch s1 = root.findViewById(R.id.swTrx);
        Switch s2 = root.findViewById(R.id.swBoxing);
        Switch s3 = root.findViewById(R.id.swPilates);
        Switch s4 = root.findViewById(R.id.swTabata);
        Switch s5 = root.findViewById(R.id.swYoga);
        Switch s6 = root.findViewById(R.id.swZumba);
        ImageButton buttonweights = root.findViewById(R.id.ibWeights);
        ImageButton buttonclasses = root.findViewById(R.id.ibClasses);
        ImageButton buttonpersonal = root.findViewById(R.id.ibPersonal);
        CarouselView carousel = root.findViewById(R.id.carousel);
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
            carousel.setVisibility(View.INVISIBLE);
        }
        else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2)
        {
            tv.setText("Please select the class you want to join in:");
            c1.setVisibility(View.INVISIBLE);
            c2.setVisibility(View.INVISIBLE);
            c3.setVisibility(View.INVISIBLE);
            buttonweights.setVisibility(View.INVISIBLE);
            buttonclasses.setVisibility(View.INVISIBLE);
            buttonpersonal.setVisibility(View.INVISIBLE);

            carousel.setPageCount(6);
            carousel.setImageListener(new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    switch (position)
                    {
                        case 0:
                            imageView.setImageResource(R.drawable.trx);
                            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            break;
                        case 1:
                            imageView.setImageResource(R.drawable.yoga);
                            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            break;
                        case 2:
                            imageView.setImageResource(R.drawable.pilates);
                            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            break;
                        case 3:
                            imageView.setImageResource(R.drawable.zumba);
                            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            break;
                        case 4:
                            imageView.setImageResource(R.drawable.boxing);
                            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            break;
                        default:
                            imageView.setImageResource(R.drawable.tabata);
                            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }
                }
            });

        }
        return root;
    }

}