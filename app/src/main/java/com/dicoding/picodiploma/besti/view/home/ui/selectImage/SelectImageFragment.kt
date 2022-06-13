package com.dicoding.picodiploma.besti.view.home.ui.selectImage

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.besti.PreferenceHelper
import com.dicoding.picodiploma.besti.PreferenceHelper.Companion.ACCURACY
import com.dicoding.picodiploma.besti.PreferenceHelper.Companion.LABEL
import com.dicoding.picodiploma.besti.PreferenceHelper.Companion.TYPE
import com.dicoding.picodiploma.besti.databinding.FragmentSelectImageBinding
import com.dicoding.picodiploma.besti.reduceFileImage
import com.dicoding.picodiploma.besti.rotateBitmap
import com.dicoding.picodiploma.besti.view.camera.CameraActivity
import com.dicoding.picodiploma.besti.view.result.ResultActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class SelectImageFragment : Fragment() {

    private var _binding: FragmentSelectImageBinding? = null
    private lateinit var preferenceHelper: PreferenceHelper

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var getFile: File? = null
    private val viewModel: SelectImageViewModel by viewModels()

    companion object {
        const val CAMERA_X_RESULT = 200
    }

    private var activityResultLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()) {result ->
            var allAreGranted = true
            for(b in result.values) {
                allAreGranted = allAreGranted && b
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
//        val selectImageViewModel =
//            ViewModelProvider(this).get(SelectImageViewModel::class.java)

        preferenceHelper = PreferenceHelper(requireContext())

        _binding = FragmentSelectImageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding!!.captureImage.setOnClickListener {
            val appPerms = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
            activityResultLauncher.launch(appPerms)
            startCameraX()
        }

        _binding!!.next.setOnClickListener {
            uploadImage()
        }
        return root
    }

    private fun uploadImage() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val filePath = file.path
            val bitmap = BitmapFactory.decodeFile(filePath)
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "image",
                file.name,
                requestImageFile
            )

            viewModel.setImage(imageMultipart)
            viewModel.getPredict().observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    val accuracy = it.predict
                    val type = it.type
                    val label = it.predict
                    preferenceHelper.put(ACCURACY, accuracy[0].accuracy.toString())
                    preferenceHelper.put(TYPE, type)
                    preferenceHelper.put(LABEL, label[0].label)
//                    Toast.makeText(activity, "ACCURACY" + preferenceHelper.getString(ACCURACY), Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, ResultActivity::class.java)
                    intent.putExtra("BITMAP", filePath)
                    startActivity(intent)
                }
            })

        } else {
//            Toast.makeText(activity, "Silakan masukkan berkas gambar terlebih dahulu.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startCameraX() {
        val intent = Intent(activity, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("image") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            getFile = myFile
            val result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                isBackCamera
            )

            _binding?.previewImageView?.setImageBitmap(result)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}