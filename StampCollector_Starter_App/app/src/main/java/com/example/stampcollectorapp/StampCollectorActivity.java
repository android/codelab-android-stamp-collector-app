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

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class StampCollectorActivity extends AppCompatActivity {

    private String mStampTitle[];

    private TypedArray mStampIcon;

    private int mStampCounter[];

    private ArrayList<StampData> mStampData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_collector);

        mStampTitle = getResources().
                getStringArray(R.array.stamp_title_array);

        mStampCounter = getResources().
                getIntArray(R.array.stamp_counter_array);

        mStampIcon = getResources().
                obtainTypedArray(R.array.stamp_icon_array);

        setupData(mStampTitle,mStampIcon,mStampCounter);
    }


    public void setupData(String title[],TypedArray icon,int count[]){
        mStampData = new ArrayList<StampData>();

        for (int i=0; i < title.length; i++){
            StampData instance = new StampData();
            instance.setStampTitle(title[i]);
            instance.setStampIcon(icon.getResourceId(i,0));
            instance.setStampCounter(count[i]);
            mStampData.add(instance);
        }
    }
}
