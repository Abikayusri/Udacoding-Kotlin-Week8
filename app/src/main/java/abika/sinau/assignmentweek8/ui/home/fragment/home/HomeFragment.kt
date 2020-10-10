package abika.sinau.assignmentweek8.ui.home.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import abika.sinau.assignmentweek8.R
import abika.sinau.assignmentweek8.data.database.ShopsDatabase
import abika.sinau.assignmentweek8.data.database.shops.ShopsEntity
import abika.sinau.assignmentweek8.utils.extension.gone
import abika.sinau.assignmentweek8.utils.extension.shortToast
import abika.sinau.assignmentweek8.utils.extension.show
import android.app.Dialog
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_form_shops.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var navController: NavController
    private var dialogView: Dialog? = null
    private val TAG = "HomeFragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        attachObserve()
        viewModel.showDataShops()
        fabHome.setOnClickListener {
            showAddDialogs()
        }
    }

    private fun showAddDialogs() {
        val dialog = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_form_shops, null)
        dialog.setView(view)

        view.btnSave.setOnClickListener {
            if (view.etNameItemShops.text.toString().isEmpty() || view.etNamePlaceShops.text.toString().isEmpty() ||
                view.etJumlahItemShops.text.toString().isEmpty() || view.etKeterangan.text.toString().isEmpty()){
                shortToast(requireContext(), "Data tidak boleh ada yang kosong!")

//                Log.d(TAG, "showAddDialogs: ${view.etNameItemShops}, ${view.etNamePlaceShops}, ${view.etJumlahItemShops}, ${view.etKeterangan}, ${getDate()}")
            } else {
                val nameItem = view.findViewById<EditText>(R.id.etNameItemShops)
                val namePlace = view.findViewById<EditText>(R.id.etNamePlaceShops)
                val jumlahItem = view.findViewById<EditText>(R.id.etJumlahItemShops)
                val keterangan = view.findViewById<EditText>(R.id.etKeterangan)

                Log.d(TAG, "showAddDialogs: $nameItem, $namePlace, $jumlahItem, $keterangan, ${getDate()}")
                viewModel.addDataShops(ShopsEntity(
                    null,
                    nameItem.text.toString(),
                    namePlace.text.toString(),
                    jumlahItem.text.toString().toInt(),
                    keterangan.text.toString(),
                    getDate()))

                viewModel.showDataShops()
//                Log.d(TAG, "showAddDialogs: ${view.etNameItemShops}, ${view.etNamePlaceShops}, ${view.etJumlahItemShops}, ${view.etKeterangan}, ${getDate()}")
                Log.d(TAG, "showAddDialogs: ${nameItem.text}, ${namePlace.text}, ${jumlahItem.text}, ${keterangan.text.toString()}, ${getDate()}")
                dialogView?.dismiss()
            }
        }

        view.btnCancel.setOnClickListener {
            dialogView?.dismiss()
        }

        dialogView = dialog.create()
        dialogView?.show()
    }

    private fun attachObserve() {
        viewModel.isError.observe(viewLifecycleOwner, Observer {
            showError(it)
        })

        viewModel.isEmpty.observe(viewLifecycleOwner, Observer {
            showEmpty(it)
        })

//        viewModel.isSuccess.observe(viewLifecycleOwner, Observer {
//            showSuccess(it)
//        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            showLoading(it)
        })

        viewModel.rShowShops.observe(viewLifecycleOwner, Observer {
            showDataShops(it)
        })

        viewModel.rAddShops.observe(viewLifecycleOwner, Observer {
            viewModel.showDataShops()
        })

        viewModel.rActionShops.observe(viewLifecycleOwner, Observer {
            viewModel.showDataShops()
        })
    }

    private fun showDataShops(it: List<ShopsEntity>?) {
//        shortToast(requireContext(), "$it")
        rvHome.adapter = HomeAdapter(it, object : HomeAdapter.OnClickListener {
            override fun onUpdate(item: ShopsEntity?) {
                showUpdateDialogs(item)
            }

            override fun onDelete(item: ShopsEntity?) {
                AlertDialog.Builder(requireContext()).apply {
                    setTitle("Hapus Data")
                    setMessage("Yakin menghapus data?")
                    setPositiveButton("Hapus") { dialog, which ->
                        dialog.dismiss()
                        viewModel.deleteDataShops(item!!)
                    }
                    setNegativeButton("Cancel") { dialog, which ->
                        dialog.dismiss()
                    }
                }.show()
            }
        })
    }

    private fun showUpdateDialogs(item: ShopsEntity?) {
        Log.d(TAG, "showUpdateDialogs: masuk sini 1 $item")
        val dialog = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_form_shops, null)
        dialog.setView(view)

        val btnSave = view.btnSave
        val etNameItem = view.etNameItemShops
        val etNamePlace = view.etNamePlaceShops
        val etJumlah = view.etJumlahItemShops
        val etKeterangan = view.etKeterangan

        btnSave.text = "Update"
        etNameItem.setText(item?.title)
        etNamePlace.setText(item?.place)
        etJumlah.setText(item?.quantity.toString())
        etKeterangan.setText(item?.note)
        btnSave.setOnClickListener {
            if (etNameItem.text.toString().isNullOrEmpty() || etNamePlace.text.toString().isNullOrEmpty() ||
                etJumlah.text.toString().isNullOrEmpty() || etKeterangan.text.toString().isNullOrEmpty()){
                shortToast(requireContext(), "Data tidak boleh ada yang kosong!")
            } else {
                Log.d(TAG, "showUpdateDialogs: masuk sini 2 ${item?.id}, ${etNameItem.text}, ${etNamePlace.text}," +
                        "${etJumlah.text}, ${etKeterangan.text}")
                viewModel.updateDataShops(ShopsEntity(
                    item?.id,
                    etNameItem.text.toString(),
                    etNamePlace.text.toString(),
                    etJumlah.text.toString().toInt(),
                    etKeterangan.text.toString(),
                    getDate()
                ))
                dialogView?.dismiss()
            }
        }

        view.btnCancel.setOnClickListener {
            dialogView?.dismiss()
        }

        dialogView = dialog.create()
        dialogView?.show()
    }

    private fun getDate(): String {
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateInstance()

        return formatter.format(date)
    }

    private fun showLoading(it: Boolean?) {
        if (it == true){
            pbHome.show()
        } else {
            pbHome.gone()
        }
    }

//    private fun showSuccess(it: Boolean?) {
//        if (it == true) shortToast(requireContext(), "Sukses Login")
//    }

    private fun showEmpty(it: String?) {
        shortToast(requireContext(), it)
    }

    private fun showError(it: String?) {
        shortToast(requireContext(), it)
    }
}