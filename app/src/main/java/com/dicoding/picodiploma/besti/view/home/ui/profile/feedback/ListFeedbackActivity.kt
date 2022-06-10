package com.dicoding.picodiploma.besti.view.home.ui.profile.feedback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.besti.R
import com.dicoding.picodiploma.besti.ViewModelFactorys
import com.dicoding.picodiploma.besti.databinding.ActivityListFeedbackBinding

class ListFeedbackActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityListFeedbackBinding? = null
    private val binding get() = _activityMainBinding
    private lateinit var adapter: FeedbackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityMainBinding = ActivityListFeedbackBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val mainViewModel = obtainViewModel(this@ListFeedbackActivity)
        mainViewModel.getAllNotes().observe(this, { noteList ->
            if (noteList != null) {
                adapter.setListNotes(noteList)
            }
        })

        adapter = FeedbackAdapter()
        binding?.rvNotes?.layoutManager = LinearLayoutManager(this)
        binding?.rvNotes?.setHasFixedSize(true)
        binding?.rvNotes?.adapter = adapter

        binding?.fabAdd?.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(this@ListFeedbackActivity, FeedbackActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): ListFeedbackViewModel {
        val factory = ViewModelFactorys.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(ListFeedbackViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }
}