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


public class StampData {
    private String mStampTitle;
    private int mStampCounter;
    private int mStampIcon;

    public StampData(){
        mStampIcon = R.drawable.general_stamp;
    }

    public String getStampTitle() {
        return mStampTitle;
    }

    public void setStampTitle(String mStampTitle) {
        this.mStampTitle = mStampTitle;
    }

    public int getStampCounter() {
        return mStampCounter;
    }

    public void setStampCounter(int mStampCounter) {
        this.mStampCounter = mStampCounter;
    }

    public int getStampIcon() {
        return mStampIcon;
    }

    public void setStampIcon(int mStampIcon) {
        this.mStampIcon = mStampIcon;
    }
}
