package com.example.runningtrackerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.runningtrackerapp.R
import com.example.runningtrackerapp.db.Run
import com.example.runningtrackerapp.other.TrackingUtility
import kotlinx.android.synthetic.main.item_run.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class RunAdapter :RecyclerView.Adapter<RunAdapter.RunViewHolder> (){
    inner class  RunViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
        val diffCallBack = object : DiffUtil.ItemCallback<Run>(){
            override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
                return oldItem.id ==newItem.id
            }

            override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
return oldItem.hashCode() == newItem.hashCode()
            }

        }
    val differ =AsyncListDiffer(this , diffCallBack)
    fun submitList(list: List<Run>) = differ.submitList(list)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
 return RunViewHolder(
     LayoutInflater.from(parent.context).inflate(
         R.layout.item_run,
         parent,
         false
     )
 )

    }

    override fun getItemCount(): Int {
return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
val run =differ.currentList[position]
        holder.itemView.apply {

            Glide.with(this).load(run.img).into(ivRunImage)
            val calendar = Calendar.getInstance().apply {
                timeInMillis = run.timestamp
            }
            val dateFormate = SimpleDateFormat("dd.MM.yy",Locale.getDefault())
            tvDate.text=dateFormate.format(calendar.time)
            val avgSpeed = "${run.avgSpeedInKMH}KM/h"
            tvAvgSpeed.text=avgSpeed
            val distance = "${run.distanceInMeters /1000f}Km"
            tvDistance.text= distance
            tvTime.text=TrackingUtility.getFormatedStopWatchTime(run.timeInMillis)
            val caloriesBurned = " ${ run.caloriesBurned}kcal"
            tvCalories.text=caloriesBurned

        }
    }
}