package com.dicoding.picodiploma.besti.view.home.ui.camera

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.besti.databinding.FragmentCameraBinding
import java.io.File
import java.util.concurrent.ExecutorService

typealias CornersListener = () -> Unit

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null
    private var imageAnalyzer: ImageAnalysis? = null
    private var camera: Camera? = null

    private lateinit var safeContext: Context

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        safeContext = context
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(CameraViewModel::class.java)

        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}