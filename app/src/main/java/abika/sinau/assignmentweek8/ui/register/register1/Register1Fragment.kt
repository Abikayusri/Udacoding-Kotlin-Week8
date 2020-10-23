package abika.sinau.assignmentweek8.ui.register.register1

import abika.sinau.assignmentweek8.R
import abika.sinau.assignmentweek8.ui.register.RegisterViewModel
import abika.sinau.assignmentweek8.utils.extension.shortToast
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_register1.*

class Register1Fragment : Fragment(), View.OnClickListener {
    private lateinit var viewModel: RegisterViewModel
    lateinit var navController: NavController
    private var TAG = "Register1Fragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register1, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnNext.setOnClickListener(this)
        back.setOnClickListener(this)

        attachObserve()
    }

    private fun attachObserve() {
        viewModel.isEmpty.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "attachObserve: $it")
            showEmpty(it)
        })

        viewModel.nameEmpty.observe(viewLifecycleOwner, Observer {
            nameEmpty(it)
        })

        viewModel.emailEmpty.observe(viewLifecycleOwner, Observer {
            emailEmpty(it)
        })

        viewModel.emailPattern.observe(viewLifecycleOwner, Observer {
            emailPattern(it)
        })
    }

    private fun emailPattern(it: Boolean?) {
        if (it == true) etEmail.error = "Format email tidak valid"
    }

    private fun emailEmpty(it: Boolean?) {
        if (it == true) etEmail.error = "Email harus diisi!"

    }

    private fun nameEmpty(it: Boolean?) {
        if (it == true) etName.error = "Nama harus diisi!"
    }

    private fun showEmpty(it: Int?) {
        Log.d(TAG, "showEmpty: $it")
        if (it == 1) {
            shortToast(requireContext(), "Data sudah pernah ada")
        } else {
            val bundle =
                bundleOf(
                    "name" to etName.text.toString(),
                    "email" to etEmail.text.toString()
                )
            navController.navigate(
                R.id.action_register1Fragment_to_register2Fragment,
                bundle
            )
        }
    }

    override fun onClick(view: View?) {
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        when (view?.id) {
            R.id.btnNext -> {
                viewModel.checkedExistedUsers(name, email)
//                when {
//                    name.isEmpty() -> {
//                        etName.error = "Nama harus diisi!"
//                    }
//                    email.isEmpty() -> {
//                        etEmail.error = "Email harus diisi"
//                    }
//                    !Patterns.EMAIL_ADDRESS.matcher(etEmail.text).matches() -> {
//                        etEmail.error = "Format email tidak valid"
//                    }
//                    else -> {
//                        Log.d(TAG, "onClick: $name, $email")
//                        viewModel.checkedExistedUsers(name, email)
//                    }
//                }
            }
            R.id.back -> activity?.onBackPressed()
        }
    }
}