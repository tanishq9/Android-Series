package com.boss.viewpagertablayout.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boss.viewpagertablayout.Adapter.GeneralAdapter;
import com.boss.viewpagertablayout.R;

import java.util.ArrayList;

public class GeneralFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<String> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add("G" + i);
        }
    }

    public GeneralFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general, container, false);
        recyclerView = view.findViewById(R.id.rv);
        GeneralAdapter generalAdapter = new GeneralAdapter(list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(generalAdapter);

        return view;
    }

}
