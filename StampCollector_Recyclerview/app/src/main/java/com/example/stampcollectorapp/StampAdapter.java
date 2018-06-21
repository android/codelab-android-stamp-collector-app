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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StampAdapter extends
        RecyclerView.Adapter<StampAdapter.StampHolder> {

    private ArrayList<StampData> mStampDataArray;
    private LayoutInflater mLayoutInflater;

    public StampAdapter(Context context,
                        ArrayList<StampData> data){

        mLayoutInflater = LayoutInflater.from(context);
        mStampDataArray = data;
    }

    @Override
    public StampAdapter.StampHolder onCreateViewHolder
            (ViewGroup parent, int viewType) {

        View view = mLayoutInflater.
                inflate(R.layout.recycler_row_layout,
                        parent,false);

        return new StampHolder(view);
    }

    @Override
    public void onBindViewHolder
            (StampAdapter.StampHolder holder, int position) {

        StampData currentStampData = mStampDataArray.get(position);

        holder.mStampTitleHolder.setText(
                currentStampData.getStampTitle());
        holder.mStampIconHolder.setImageResource(
                currentStampData.getStampIcon());
        holder.mStampCounterHolder.setText(
                String.valueOf(currentStampData.getStampCounter()));
    }

    @Override
    public int getItemCount() {
        return mStampDataArray.size();
    }

    public class StampHolder extends RecyclerView.ViewHolder {

        TextView mStampTitleHolder;
        TextView mStampCounterHolder;
        ImageView mStampIconHolder;

        Button mAddButton;
        Button mSubButton;

        private int mCounter;

        private StampData mEditStampData;

        public StampHolder(View itemView) {
            super(itemView);

            mStampTitleHolder = itemView.
                    findViewById(R.id.stamp_title);

            mStampIconHolder = itemView.
                    findViewById(R.id.stamp_pic);

            mStampCounterHolder = itemView.
                    findViewById(R.id.stamp_count);

            mAddButton = itemView.
                    findViewById(R.id.add_button);

            mSubButton = itemView.
                    findViewById(R.id.sub_button);

            //Use OnClick Listeners to Handle Add Button
            mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeStampCount(true);
                }
            });

            //Use OnClick Listeners to Handle Sub Button
            mSubButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeStampCount(false);
                }
            });

        }

        private void changeStampCount (boolean increaseCount)
        {
            // Get the current StampData object by using the
            // getAdapterPosition() method
            mEditStampData = mStampDataArray.
                    get(getAdapterPosition());

            //Access the current counter value from StampData object
            mCounter = mEditStampData.getStampCounter();

            //Increment the counter value by one every time
            if (increaseCount) {
                mCounter++;
            }
            else {
                if (mCounter > 0){
                    mCounter--;
                }
            }

            //Update the new counter value in StampData object
            mEditStampData.setStampCounter(mCounter);

            //Update the current object in the ArrayList object too
            mStampDataArray.set(getAdapterPosition(), mEditStampData);

            //Notify the adapter about the changes
            // made at the current position
            notifyItemChanged(getAdapterPosition());
        }
    }
}
