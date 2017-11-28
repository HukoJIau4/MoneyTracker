package com.example.lexel.moneytracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ActionMode;
import android.view.View;

import java.util.zip.Deflater;



@SuppressLint("ValidFragment")
class ConfirmationDialog extends DialogFragment {



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setTitle(R.string.app_name);
        builder.setMessage(getString(R.string.confirm_remove));
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
               ItemsFragment.removeSelectedItems();
            }



        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                ItemsFragment.actionMode.finish();

                ItemsFragment.actionMode = null;

                ItemsFragment.clearSelectedItems();

            }
        });
        return builder.create();
    }

}
