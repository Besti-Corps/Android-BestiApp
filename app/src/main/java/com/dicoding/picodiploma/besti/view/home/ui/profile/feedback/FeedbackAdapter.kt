package com.dicoding.picodiploma.besti.view.home.ui.profile.feedback

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.besti.database.Feedback
import com.dicoding.picodiploma.besti.databinding.ItemFeedbackBinding
import com.dicoding.picodiploma.besti.helper.FeedbackDiffCallback

class FeedbackAdapter : RecyclerView.Adapter<FeedbackAdapter.NoteViewHolder>() {
    private val listNotes = ArrayList<Feedback>()
    fun setListNotes(listNotes: List<Feedback>) {
        val diffCallback = FeedbackDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            ItemFeedbackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    inner class NoteViewHolder(private val binding: ItemFeedbackBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(feedback: Feedback) {
            with(binding) {
                tvItemTitle.text = feedback.name
                tvItemDate.text = feedback.date
                tvItemDescription.text = feedback.feedback
                cvItemNote.setOnClickListener {
                    val intent = Intent(it.context, FeedbackActivity::class.java)
                    intent.putExtra(FeedbackActivity.EXTRA_NOTE, feedback)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}