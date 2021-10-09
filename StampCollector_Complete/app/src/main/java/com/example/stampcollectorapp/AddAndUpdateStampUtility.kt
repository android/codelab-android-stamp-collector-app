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
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

class AddAndUpdateStampUtility(
    private val mContext: Context,
    private val mStampData: ArrayList<StampData?>,
    adapter: StampAdapter
) {
    private val mAdapter: StampAdapter = adapter
    private lateinit var mStampNameEdit: EditText
    private lateinit var mStampCountEdit: EditText
    private var mEntryValid = false

    fun addOrUpdateStamp(position: Int) {
        val dialogView =
            LayoutInflater.from(mContext).inflate(R.layout.stamp_dialog_layout, null)
        mStampNameEdit = dialogView.findViewById(R.id.edittext_stampname)
        mStampCountEdit = dialogView.findViewById(R.id.edittext_stampcounter)
        val editing = position > -1
        val dialogTitle =
            if (editing) mContext.getString(R.string.stamp_update_title) else mContext.getString(
                R.string.stamp_add_title)
        val dialog = MaterialAlertDialogBuilder(mContext)
            .setView(dialogView)
            .setTitle(dialogTitle)
            .setPositiveButton(R.string.save, null)
            .setNegativeButton(R.string.cancel, null)
            .show()
        if (editing) {
            val editStamp = mStampData[position]
            mStampNameEdit.setText(editStamp!!.stampTitle)
            mStampCountEdit.setText(editStamp!!.stampCounter)
        }
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val stampNameValid = mStampNameEdit.text.toString().isNotEmpty()
            val stampCountValid = mStampCountEdit.text.toString().isNotEmpty()
            mEntryValid = stampNameValid && stampCountValid
            if (mEntryValid) {
                if (editing) {
                    val editStamp = mStampData[position]
                    editStamp!!.stampTitle = mStampNameEdit.text.toString()
                    editStamp!!.stampCounter = mStampCountEdit
                        .text.toString().toInt()
                    mStampData[position] = editStamp
                    mAdapter.notifyItemChanged(position)
                } else {
                    val newStamp = StampData()
                    newStamp.stampTitle = mStampNameEdit.text.toString()
                    newStamp.stampCounter = mStampCountEdit
                        .text.toString().toInt()
                    mStampData.add(newStamp)
                    mAdapter.notifyItemInserted(mStampData.size)
                }
                dialog.dismiss()
            } else {
                Toast.makeText(mContext,
                    "Please Enter Valid Data",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

}