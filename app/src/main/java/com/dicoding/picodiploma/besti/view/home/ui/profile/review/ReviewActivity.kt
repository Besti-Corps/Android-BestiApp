package com.dicoding.picodiploma.besti.view.home.ui.profile.review

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dicoding.picodiploma.besti.PreferenceHelper
import com.dicoding.picodiploma.besti.PreferenceHelper.Companion.PREF_TOKEN
import com.dicoding.picodiploma.besti.R
import com.dicoding.picodiploma.besti.databinding.ActivityReviewBinding
import com.dicoding.picodiploma.besti.dataclass.*
import com.google.android.material.snackbar.Snackbar

class ReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewBinding
    private lateinit var preferenceHelper: PreferenceHelper
    private lateinit var adapter: ReviewAdapter
    private lateinit var reviewViewModel: ReviewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceHelper = PreferenceHelper(this)
        adapter = ReviewAdapter()

        val swipe : SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipe.setOnRefreshListener {
            reviewViewModel.ListReview(preferenceHelper.getString(PREF_TOKEN).toString())

            reviewViewModel.getReview().observe(this, Observer<ArrayList<listReviewItem>> {
                if (it != null) {
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                }
            })
            adapter.notifyDataSetChanged()
            swipe.isRefreshing = false
        }

        supportActionBar?.hide()

        reviewViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ReviewViewModel::class.java)

        reviewViewModel.ListReview(preferenceHelper.getString(PREF_TOKEN).toString())

        binding.apply {
            rvReview.layoutManager = LinearLayoutManager(this@ReviewActivity)
            rvReview.setHasFixedSize(true)
            rvReview.adapter = adapter
        }

        reviewViewModel.getReview().observe(this, Observer<ArrayList<listReviewItem>> {
            if (it != null) {
                adapter.setList(it)
            }
        })

        reviewViewModel.postReview().observe(this, Observer<PostReviewResponse> {
            if (it != null) {
//                Toast.makeText(applicationContext, it.status, Toast.LENGTH_LONG).show()
            }
            if(it == null){
//                Toast.makeText(applicationContext, "Failed to create User", Toast.LENGTH_LONG).show()
            }
        })

        reviewViewModel.snackbarText.observe(this, {
            it.getContentIfNotHandled()?.let { snackBarText ->
                Snackbar.make(
                    window.decorView.rootView,
                    snackBarText,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        binding.btnSend.setOnClickListener {
            val review = binding.edReview.text.toString()
            when {
                review.isEmpty() -> {
                    binding.edReview.error = "Masukkan review"
                }
                else -> {
                    reviewViewModel.PostReview(preferenceHelper.getString(PREF_TOKEN).toString(),review)
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(it.windowToken, 0)
                }
            }
        }

        reviewViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        reviewViewModel.snackbarText.observe(this, {
            it.getContentIfNotHandled()?.let { snackBarText ->
                Snackbar.make(
                    window.decorView.rootView,
                    snackBarText,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}