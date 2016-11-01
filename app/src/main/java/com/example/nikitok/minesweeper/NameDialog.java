package com.example.nikitok.minesweeper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class NameDialog extends DialogFragment {
    private NameSetListener listener;

    public interface NameSetListener{
        void nameEntered(String name);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (NameSetListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener= null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setTitle("Enter your name");
        View container = inflater.inflate(R.layout.model_dialog, null);

        final EditText edName= (EditText) container.findViewById(R.id.name);
        builder.setView(container);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.nameEntered(edName.getText().toString());
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                final Button button = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                button.setEnabled(false);
                ((AlertDialog) dialog).setCancelable(false);
                edName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(s.length()!=0){
                            button.setEnabled(true);

                        }
                        else{
                            button.setEnabled(false);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


            }
        });


        return alertDialog;
    }
}
