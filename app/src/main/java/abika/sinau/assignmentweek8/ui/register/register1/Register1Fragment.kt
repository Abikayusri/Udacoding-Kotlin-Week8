package abika.sinau.assignmentweek8.ui.register.register1

import abika.sinau.assignmentweek8.R
import abika.sinau.assignmentweek8.ui.register.RegisterViewModel
import abika.sinau.assignmentweek8.utils.extension.shortToast
import android.os.Bundle
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
            showSuccess(it)
        })
    }

    private fun showSuccess(it: Int?) {
        if (it == 1) {
            shortToast(requireContext(), "Lanjut gan")
            val bundle =
                bundleOf(
                    "name" to etName.text.toString(),
                    "email" to etEmail.text.toString()
                )
            navController.navigate(
                R.id.action_register1Fragment_to_register2Fragment,
                bundle
            )
        } else {
            shortToast(requireContext(), "Data udah pernah ada gan")
        }
    }

    override fun onClick(view: View?) {
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        when (view?.id) {
            R.id.btnNext -> {
                when {
                    name.isEmpty() -> {
                        etName.error = "Nama harus diisi!"
                    }
                    email.isEmpty() -> {
                        etEmail.error = "Email harus diisi"
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(etEmail.text).matches() -> {
                        etEmail.error = "Format email tidak valid"
                    }
                    else -> {
                        viewModel.checkedExistedUsers(name, email)
                    }
                }
            }
            R.id.back -> activity?.onBackPressed()
        }
    }
}