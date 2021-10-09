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
import com.example.stampcollectorapp.StampAdapter.StampHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class StampAdapter(
    context: Context,
    data: ArrayList<StampData?>
) : RecyclerView.Adapter<StampHolder?>() {
    private val mStampDataArray: ArrayList<StampData?>
    private val mAddAndUpdateStampUtilityAd: AddAndUpdateStampUtility

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StampHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_layout,
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

    override fun getItemCount(): Int = mStampDataArray.size

    inner class StampHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mStampTitleHolder: TextView = itemView.findViewById(R.id.stamp_title)
        var mStampCounterHolder: TextView = itemView.findViewById(R.id.stamp_count)
        var mStampIconHolder: ImageView = itemView.findViewById(R.id.stamp_pic)
        private var mAddButton: Button = itemView.findViewById(R.id.add_button)
        private var mSubButton: Button = itemView.findViewById(R.id.sub_button)
        private var mCounter = 0
        private var mEditStampData: StampData? = null
        private fun changeStampCount(increaseCount: Boolean) {
            // Get the current StampData object by using the
            // getAdapterPosition() method
            mEditStampData = mStampDataArray[absoluteAdapterPosition]

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
            mStampDataArray[absoluteAdapterPosition] = mEditStampData

            //Notify the adapter about the changes
            // made at the current position
            notifyItemChanged(absoluteAdapterPosition)
        }

        init {

            //Use OnClick Listeners to Handle Add Button
            mAddButton.setOnClickListener { changeStampCount(true) }

            //Use OnClick Listeners to Handle Sub Button
            mSubButton.setOnClickListener { changeStampCount(false) }
            itemView.setOnClickListener {
                mAddAndUpdateStampUtilityAd.addOrUpdateStamp(absoluteAdapterPosition)
            }
        }
    }

    init {
        LayoutInflater.from(context)
        mStampDataArray = data
        mAddAndUpdateStampUtilityAd = AddAndUpdateStampUtility(context, mStampDataArray, this)
    }
}