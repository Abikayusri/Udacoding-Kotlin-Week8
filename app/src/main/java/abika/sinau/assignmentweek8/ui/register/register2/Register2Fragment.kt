package abika.sinau.assignmentweek8.ui.register.register2

import abika.sinau.assignmentweek8.R
import abika.sinau.assignmentweek8.data.database.shops.ShopsDatabase
import abika.sinau.assignmentweek8.data.database.user.UsersEntity
import abika.sinau.assignmentweek8.ui.register.RegisterViewModel
import abika.sinau.assignmentweek8.utils.extension.gone
import abika.sinau.assignmentweek8.utils.extension.shortToast
import abika.sinau.assignmentweek8.utils.extension.show
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_register2.*

class Register2Fragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController
//    private var userData: ShopsDatabase? = null

    private lateinit var viewModel: RegisterViewModel
    private var getName: String? = null
    private var getEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register2, container, false)
    }

    private val TAG = "Register2Fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getName = arguments?.getString("name")
        getEmail = arguments?.getString("email")

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

//        userData = context?.let { ShopsDatabase.getInstanceShops(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnFinish.setOnClickListener(this)
        back.setOnClickListener(this)
        tvHalo.text = "Halo $getName, untuk menlanjutkan register. Silahkan isi data di bawah ini"

        attachObserve()
    }

    private fun attachObserve() {
        viewModel.isError.observe(viewLifecycleOwner, Observer {
            showError(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            showLoading(it)
        })
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {
            pbRegister.show()
        } else {
            pbRegister.gone()
        }
    }

    private fun showError(it: Throwable?) {
        showToast(it.toString())
        Log.d(TAG, "showError: $it")
    }

    private fun showToast(it: String?) {
        shortToast(requireContext(), it)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnFinish -> {
                when {
                    etPassword.text.toString().isEmpty() -> {
                        etPassword.error = "Password harus diisi"
                    }
                    etConfirmPassword.text.toString().isEmpty() -> {
                        etConfirmPassword.error = "Confirmation Password harus diisi"
                    }
                    else -> {
                        if (etPassword.text.toString() == etConfirmPassword.text.toString()) {
                            val bundle = bundleOf(
                                "name" to getName,
                                "email" to getEmail,
                            )
//                            Observable.fromCallable {
//                                userData?.usersDao()?.insertDataUsers(
//                                    UsersEntity(
//                                        null,
//                                        getName,
//                                        getEmail,
//                                        etPassword.text.toString()
//                                    )
//                                )
//                            }
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe({ response ->
//                                    Log.d(TAG, "addUsers 1: $response")
//                                }, { error ->
//                                    // error ketika gagal
//                                    Log.d(TAG, "addUsers 2: $error")
//                                })
                            viewModel.insertUsers(
                                UsersEntity(
                                    null,
                                    getName,
                                    getEmail,
                                    etPassword.text.toString()
                                )
                            )
                            navController.navigate(
                                R.id.action_register2Fragment_to_resultFragment,
                                bundle
                            )
                        } else {
                            Toast.makeText(context, "Password tidak sama", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
            R.id.back -> activity?.onBackPressed()
        }
    }
}