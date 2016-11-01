package com.example.nikitok.minesweeper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nikitok.minesweeper.adapter.TopAdapter;


public class EndGameDialog extends DialogFragment implements View.OnClickListener {
    private ButtonListener listener;
    public static final int BUTTON_NEW_GAME=0;
    public static final int BUTTON_RESTART_GAME=1;
    public static final int BUTTON_FINISH_GAME=2;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newGame:
                Log.d("mytags", "buttonpressed");
                listener.buttonPressed(BUTTON_NEW_GAME);
                break;
            case R.id.restart:
                listener.buttonPressed(BUTTON_RESTART_GAME);
                break;
            case R.id.finish:
                listener.buttonPressed(BUTTON_FINISH_GAME);
                break;

        }
    }

    public interface ButtonListener{
        void buttonPressed(int button);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ButtonListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getArguments().getString("title"));

        View container= getActivity().getLayoutInflater().inflate(R.layout.endgame_dialog, null);
        builder.setView(container);

        AlertDialog alertDialog= builder.create();
        alertDialog.setCancelable(false);
        final RecyclerView recyclerView = (RecyclerView) container.findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        TopAdapter adapter = new TopAdapter(getActivity());
        Button newGage = (Button) container.findViewById(R.id.newGame);
        Button restart = (Button) container.findViewById(R.id.restart);
        Button finish = (Button) container.findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.buttonPressed(BUTTON_FINISH_GAME);
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.buttonPressed(BUTTON_RESTART_GAME);
            }
        });
        newGage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.buttonPressed(BUTTON_NEW_GAME);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return alertDialog;
    }
}
