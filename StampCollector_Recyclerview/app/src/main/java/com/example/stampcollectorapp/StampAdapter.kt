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

import android.content.Context
import com.example.stampcollectorapp.StampData
import androidx.recyclerview.widget.RecyclerView
import com.example.stampcollectorapp.StampAdapter.StampHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.stampcollectorapp.R
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import android.widget.TextView
import java.util.ArrayList

class StampAdapter(
    context: Context?,
    data: ArrayList<StampData?>
) : RecyclerView.Adapter<StampHolder>() {
    private val mStampDataArray: ArrayList<StampData?>
    private val mLayoutInflater: LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StampHolder {
        val view = mLayoutInflater.inflate(R.layout.recycler_row_layout,
            parent, false)
        return StampHolder(view)
    }

    override fun onBindViewHolder(holder: StampHolder, position: Int) {
        val currentStampData = mStampDataArray[position]
        holder.mStampTitleHolder.text = currentStampData!!.stampTitle
        holder.mStampIconHolder.setImageResource(
            currentStampData.stampIcon)
        holder.mStampCounterHolder.text = currentStampData.stampCounter.toString()
    }

    override fun getItemCount(): Int {
        return mStampDataArray.size
    }

    inner class StampHolder(itemView: View) : ViewHolder(itemView) {
        var mStampTitleHolder: TextView
        var mStampCounterHolder: TextView
        var mStampIconHolder: ImageView
        var mAddButton: Button
        var mSubButton: Button
        private var mCounter = 0
        private var mEditStampData: StampData? = null
        private fun changeStampCount(increaseCount: Boolean) {
            // Get the current StampData object by using the
            // getAdapterPosition() method
            mEditStampData = mStampDataArray[adapterPosition]

            //Access the current counter value from StampData object
            mCounter = mEditStampData!!.stampCounter

            //Increment the counter value by one every time
            if (increaseCount) {
                mCounter++
            } else {
                if (mCounter > 0) {
                    mCounter--
                }
            }

            //Update the new counter value in StampData object
            mEditStampData!!.stampCounter = mCounter

            //Update the current object in the ArrayList object too
            mStampDataArray[adapterPosition] = mEditStampData

            //Notify the adapter about the changes
            // made at the current position
            notifyItemChanged(adapterPosition)
        }

        init {
            mStampTitleHolder = itemView.findViewById(R.id.stamp_title)
            mStampIconHolder = itemView.findViewById(R.id.stamp_pic)
            mStampCounterHolder = itemView.findViewById(R.id.stamp_count)
            mAddButton = itemView.findViewById(R.id.add_button)
            mSubButton = itemView.findViewById(R.id.sub_button)

            //Use OnClick Listeners to Handle Add Button
            mAddButton.setOnClickListener { changeStampCount(true) }

            //Use OnClick Listeners to Handle Sub Button
            mSubButton.setOnClickListener { changeStampCount(false) }
        }
    }

    init {
        mLayoutInflater = LayoutInflater.from(context)
        mStampDataArray = data
    }
}