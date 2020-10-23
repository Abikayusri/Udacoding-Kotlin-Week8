package abika.sinau.assignmentweek8.ui.boarding.fragment

import abika.sinau.assignmentweek8.R
import abika.sinau.assignmentweek8.data.preferences.SessionManager
import abika.sinau.assignmentweek8.utils.extension.shortToast
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register1.*
import kotlinx.android.synthetic.main.fragment_register2.*

class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var viewModel: LoginViewModel
    private val TAG = "LoginFragment"
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        btnLoginMain.setOnClickListener(this)
        btnRegisterMain.setOnClickListener(this)

        attachObserve()
    }

    private fun attachObserve() {
        viewModel.isError.observe(viewLifecycleOwner, Observer {
            showError(it)
        })

        viewModel.isLogin.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "attachObserve: $it")
            showLogin(it)
        })

        viewModel.emailEmpty.observe(viewLifecycleOwner, Observer {
            emailEmpty(it)
        })

        viewModel.emailPattern.observe(viewLifecycleOwner, Observer {
            emailPattern(it)
        })

        viewModel.passwordEmpty.observe(viewLifecycleOwner, Observer {
            showPasswordEmpty(it)
        })
    }


    private fun emailPattern(it: Boolean?) {
        if (it == true) etEmailMain.error = "Format email tidak valid"
    }

    private fun emailEmpty(it: Boolean?) {
        if (it == true) etEmailMain.error = "Email harus diisi!"

    }

    private fun showPasswordEmpty(it: Boolean?) {
        if (it == true) etPasswordMain.error = "Password tidak boleh kosong!"
    }

    private fun showLogin(it: Int?) {
        Log.d(TAG, "showLogin: $it")
        if (it == 1) {
            val session = SessionManager(requireContext())
            val email = etEmailMain.text.toString()
            Log.d(TAG, "showLogin: $email, ${etEmailMain.text}")
            session.email = email
            session.isLogin = true
            shortToast(requireContext(), "Login Berhasil")
            navController.navigate(
                R.id.action_loginFragment_to_homeActivity
            )
            activity?.finish()
        } else {
            shortToast(requireContext(), "Akunmu belum terdaftar. Silahkan daftar terlebih dahulu!")
        }
    }

    private fun showError(it: String?) {
        shortToast(requireContext(), it)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            /** Untuk login **/
            R.id.btnLoginMain -> {
                val email = etEmailMain.text.toString()
                val password = etPasswordMain.text.toString()

                viewModel.loginUsers(email, password)

//                when {
//                    email.isEmpty() -> {
//                        etEmailMain.error = "Email tidak boleh kosong"
//                    }
//                    password.isEmpty() -> {
//                        etPasswordMain.error = "Password tidak boleh kosong"
//                    }
//                    else -> {
//                        Log.d(TAG, "onClick: $email, $password")
//                        viewModel.loginUsers(email, password)
//                    }
//                }
            }

            /** Untuk register **/
            R.id.btnRegisterMain -> navController.navigate(
                R.id.action_loginFragment_to_register1Fragment
            )
        }
    }
}