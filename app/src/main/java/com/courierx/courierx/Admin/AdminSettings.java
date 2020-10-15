package com.courierx.courierx.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.courierx.courierx.AuthScreens.Wrapper;
import com.courierx.courierx.R;
import com.courierx.courierx.Services.FirebaseAuthentication;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminSettings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminSettings extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminSettings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminSettings.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminSettings newInstance(String param1, String param2) {
        AdminSettings fragment = new AdminSettings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_settings, container, false);
        Button logoutBtn = view.findViewById(R.id.admin_logout_button);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication();
                firebaseAuthentication.logOut(getContext());
                Intent intent = new Intent(getContext() , Wrapper.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }

        });
        return view;
    }
}