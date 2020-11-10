package com.example.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gymapp.ui.login.LoginActivity;

public class CitySelectFragment extends Fragment {
    public static final String CITY = "";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_select, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_proceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = "";
                RadioGroup group = getView().findViewById(R.id.rbGroup);
                int selection = group.getCheckedRadioButtonId();

                if(selection == R.id.rbFamagusta)
                {
                    city = "Famagusta";
                }
                else if(selection == R.id.rbLarnaca)
                {
                    city = "Larnaca";
                }
                else if(selection == R.id.rbLimassol)
                {
                    city = "Limassol";
                }
                else if(selection == R.id.rbPafos)
                {
                    city = "Pafos";
                }
                else if(selection == R.id.rbNicosia)
                {
                    city = "Nicosia";
                }
                EditText et = getView().findViewById(R.id.textView2);
                et.setText(city);
//                Intent in = new Intent(getContext(), GymSelectActivity.class);
//                in.putExtra(CITY, city);
//                startActivity(in);
            }
        });
    }
}