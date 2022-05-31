package com.dicoding.picodiploma.besti.view.home.ui.selectImage

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.besti.databinding.FragmentCameraBinding
import com.dicoding.picodiploma.besti.view.result.ResultActivity
import com.dicoding.picodiploma.besti.view.camera.CameraActivity
import java.io.File

class SelectImageFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var currentPhotoPath: String

    private var getFile: File? = null

    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    private var activityResultLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()) {result ->
            var allAreGranted = true
            for(b in result.values) {
                allAreGranted = allAreGranted && b
            }

            if(allAreGranted) {

            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(SelectImageViewModel::class.java)

        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding!!.captureImage.setOnClickListener {
            val appPerms = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
            activityResultLauncher.launch(appPerms)
            val intent = Intent (activity, CameraActivity::class.java)
            activity?.startActivity(intent)
        }

        _binding!!.next.setOnClickListener {
            val intent = Intent(activity, ResultActivity::class.java)
            activity?.startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}