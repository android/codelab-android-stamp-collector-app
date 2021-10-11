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
package com.example.stampcollectorapp

data class StampData(
    private var mStampTitle: String? = null,
    private var mStampCounter: Int = 0,
    private var mStampIcon: Int = 0,
) {


    fun StampData() {
        mStampIcon = R.drawable.general_stamp
    }

    fun getStampTitle(): String? {
        return mStampTitle
    }

    fun setStampTitle(mStampTitle: String?) {
        this.mStampTitle = mStampTitle
    }

    fun getStampCounter(): Int {
        return mStampCounter
    }

    fun setStampCounter(mStampCounter: Int) {
        this.mStampCounter = mStampCounter
    }

    fun getStampIcon(): Int {
        return mStampIcon
    }

    fun setStampIcon(mStampIcon: Int) {
        this.mStampIcon = mStampIcon
    }

}