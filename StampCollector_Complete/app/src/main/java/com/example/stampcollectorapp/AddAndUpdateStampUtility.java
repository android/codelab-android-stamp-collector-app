/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.stampcollectorapp;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddAndUpdateStampUtility {
    private Context mContext;
    private ArrayList<StampData> mStampData;
    private RecyclerView.Adapter mAdapter;

    private EditText mStampNameEdit, mStampCountEdit;
    private boolean mEntryValid;

    public AddAndUpdateStampUtility(Context context,ArrayList<StampData> data,StampAdapter adapter){
        mContext = context;
        mStampData = data;
        mAdapter = adapter;
    }

    public void addOrUpdateStamp(int position){
        final int stampPosition = position;
        View dialogView = LayoutInflater.from(mContext).
                inflate(R.layout.stamp_dialog_layout, null);

        mStampNameEdit = dialogView.
                findViewById(R.id.edittext_stampname);
        mStampCountEdit = dialogView.
                findViewById(R.id.edittext_stampcounter);

        final boolean editing = stampPosition > -1;

        String dialogTitle = editing ? mContext.
                getString(R.string.stamp_update_title) :
                mContext.getString(R.string.stamp_add_title);


        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                .setView(dialogView)
                .setTitle(dialogTitle)
                .setPositiveButton(R.string.save, null)
                .setNegativeButton(R.string.cancel, null);

        final AlertDialog dialog = builder.show();

        if (editing) {
            StampData editStamp = mStampData.get(stampPosition);
            mStampNameEdit.setText(editStamp.getStampTitle());
            mStampCountEdit.setText(
                    String.valueOf(editStamp.getStampCounter()));
        }

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean stampNameValid = !mStampNameEdit.
                                getText().toString().isEmpty();
                        boolean stampCountValid = !mStampCountEdit.
                                getText().toString().isEmpty();

                        mEntryValid = stampNameValid && stampCountValid;

                        if (mEntryValid) {
                            if (editing) {
                                StampData editStamp =
                                        mStampData.get(stampPosition);
                                editStamp.setStampTitle(mStampNameEdit.
                                        getText().toString());
                                editStamp.setStampCounter(
                                        Integer.parseInt(mStampCountEdit
                                                .getText().toString()));
                                mStampData.set(stampPosition, editStamp);

                                mAdapter.notifyItemChanged(stampPosition);
                            } else {
                                StampData newStamp = new StampData();
                                newStamp.setStampTitle(mStampNameEdit.
                                        getText().toString());
                                newStamp.setStampCounter(
                                        Integer.parseInt(mStampCountEdit
                                                .getText().toString()));
                                mStampData.add(newStamp);

                                mAdapter.notifyItemInserted(mStampData.size());

                            }
                            dialog.dismiss();
                        } else {
                            Toast.makeText(mContext,
                                    "Please Enter Valid Data",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
