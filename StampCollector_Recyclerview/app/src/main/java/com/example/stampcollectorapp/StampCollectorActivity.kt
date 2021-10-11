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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class StampCollectorActivity : AppCompatActivity() {
    private lateinit var mStampTitle: Array<String?>
    private var mStampIcon: TypedArray? = null
    private lateinit var mStampCounter: IntArray
    private lateinit var mStampData: ArrayList<StampData?>
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: StampAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stamp_collector)

        mStampTitle = resources.getStringArray(R.array.stamp_title_array)
        mStampCounter = resources.getIntArray(R.array.stamp_counter_array)
        mStampIcon = resources.obtainTypedArray(R.array.stamp_icon_array)

        setupData(mStampTitle, mStampIcon, mStampCounter)

        mAdapter = StampAdapter(this, mStampData)
        setUpRecyclerView()
    }

    private fun setupData(title: Array<String?>, icon: TypedArray?, count: IntArray) {
        mStampData = ArrayList()

        for (i in title.indices) {
            val instance = StampData()
            instance.stampTitle = title[i]
            instance.stampIcon = icon!!.getResourceId(i, 0)
            instance.stampCounter = count[i]
            mStampData.add(instance)
        }
    }

    private fun setUpRecyclerView() {

        //Initialize RecyclerView object
        mRecyclerView = findViewById(R.id.mRecyclerView)

        //Set up a line after each row, so it looks like a list
        mRecyclerView.addItemDecoration(DividerItemDecoration(
            this, DividerItemDecoration.VERTICAL))

        //Set up the LayoutManager for RecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        //Attach adapter object with RecyclerView
        mRecyclerView.adapter = mAdapter
    }
}