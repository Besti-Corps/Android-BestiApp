package com.dicoding.picodiploma.besti.helper

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.picodiploma.besti.database.Feedback

class FeedbackDiffCallback(
    private val mOldNoteList: List<Feedback>,
    private val mNewNoteList: List<Feedback>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }
    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].id == mNewNoteList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldNoteList[oldItemPosition]
        val newEmployee = mNewNoteList[newItemPosition]
        return oldEmployee.name == newEmployee.name && oldEmployee.feedback == newEmployee.feedback
    }
}