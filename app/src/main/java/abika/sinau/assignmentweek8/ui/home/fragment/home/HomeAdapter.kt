package abika.sinau.assignmentweek8.ui.home.fragment.home

import abika.sinau.assignmentweek8.R
import abika.sinau.assignmentweek8.data.database.shops.ShopsEntity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_shops.view.*

/**
 * Created by Abika Chairul Yusri on 04/10/2020
 * Bismillahirrahmanirrahim
 */
class HomeAdapter(private val data: List<ShopsEntity>?, private val itemClick: OnClickListener) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shops, parent, false)

        return HomeViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = data?.get(position)

        holder.bindItem(item)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class HomeViewHolder (private val view: View, private val itemClick: OnClickListener) : RecyclerView.ViewHolder(view) {

        fun bindItem(item: ShopsEntity?){
            view.tv_item_name_shops.text = item?.title
            view.tv_item_place_shops.text = item?.place
            view.tv_item_kuantiti_shops.text = item?.quantity.toString()
            view.tv_item_date.text = item?.date
            view.tv_item_keterangan_shops.text = item?.note

            view.btnEdit.setOnClickListener {
                itemClick.onUpdate(item)
            }

            view.btnDelete.setOnClickListener {
                itemClick.onDelete(item)
            }
        }
    }

    interface OnClickListener {
        fun onUpdate(item: ShopsEntity?)
        fun onDelete(item: ShopsEntity?)
    }
}