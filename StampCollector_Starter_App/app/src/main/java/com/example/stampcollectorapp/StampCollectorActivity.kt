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

import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class StampCollectorActivity : AppCompatActivity() {

    private lateinit var mStampTitle: Array<String?>

    private var mStampIcon: TypedArray? = null

    private lateinit var mStampCounter: IntArray

    private var mStampData: ArrayList<StampData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stamp_collector)

        mStampTitle = resources.getStringArray(R.array.stamp_title_array)

        mStampCounter = resources.getIntArray(R.array.stamp_counter_array)

        mStampIcon = resources.obtainTypedArray(R.array.stamp_icon_array)

        setupData(mStampTitle, mStampIcon, mStampCounter)
    }

    private fun setupData(title: Array<String?>, icon: TypedArray?, count: IntArray) {
        mStampData = ArrayList<StampData>()

        for (i in title.indices) {
            val instance = StampData()
            instance.setStampTitle(title[i])
            instance.setStampIcon(icon!!.getResourceId(i, 0))
            instance.setStampCounter(count[i])
            mStampData!!.add(instance)
        }
    }
}