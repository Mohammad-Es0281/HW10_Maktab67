package ir.es.mohammad.netflix

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.textfield.TextInputEditText
import ir.es.mohammad.netflix.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var takePictureActivityResultLauncher: ActivityResultLauncher<Void>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)

        with(binding) {
            btnAddPic.setOnClickListener {
                takePictureActivityResultLauncher.launch(null)
            }

            editTextFullName.setOnFocusChangeListener { it, hasFocus ->
                it as TextInputEditText
                if (!hasFocus) {
                    if (it.text.toString().isBlank()) {
                        it.error = "Full name can't be empty"
                        btnRegister.isEnabled = false
                    } else if (editTextEmail.text.toString().isNotBlank())
                        btnRegister.isEnabled = true
                }
            }

            editTextEmail.setOnFocusChangeListener { it, hasFocus ->
                it as TextInputEditText
                if (!hasFocus) {
                    if (it.text.toString().isBlank()) {
                        it.error = "Email can't be empty"
                        btnRegister.isEnabled = false
                    } else if (editTextFullName.text.toString().isNotBlank())
                        btnRegister.isEnabled = true
                }
            }

            btnRegister.setOnClickListener {
                User.apply {
                    registered = true
                    profilePicId = imgProfilePic.drawable
                    fullName = editTextFullName.text.toString()
                    email = editTextEmail.text.toString()
                    username = editTextUsername.text?.toString() ?: ""
                    phoneNumber = editTextPhoneNumber.text?.toString() ?: ""
                }
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<ProfileFragment>(R.id.fragContainerProfile)
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        createTakePictureIntentActivityResultLauncher()
    }

    private fun createTakePictureIntentActivityResultLauncher() {
        takePictureActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
                binding.imgProfilePic.setImageBitmap(it)
            }
    }
}