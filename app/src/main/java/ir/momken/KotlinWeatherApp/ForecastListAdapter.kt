package ir.momken.KotlinWeatherApp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ir.momken.KotlinWeatherApp.domain.model.Forecast
import ir.momken.KotlinWeatherApp.domain.model.ForecastList
import ir.momken.KotlinWeatherApp.ui.utils.ctx
import org.jetbrains.anko.find

class ForecastListAdapter(val weekForecast: ForecastList,
                          val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder> ()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx)
            .inflate(R.layout.item_forecast, parent, false)

        return ViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, posititon: Int) {
        holder.bindForecast(weekForecast[posititon])
    }

    override fun getItemCount(): Int = weekForecast.size

    class ViewHolder(val view: View, val itemClickListener: OnItemClickListener)
        : RecyclerView.ViewHolder(view){

        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView = view.find<TextView>(R.id.description)
        private val maxTempView = view.find<TextView>(R.id.maxTemp)
        private val minTempView = view.find<TextView>(R.id.minTemp)

        fun bindForecast(forecast: Forecast){
            with(forecast){
                Picasso.get().load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTempView.text = "$high"
                minTempView.text = "$low"
                itemView.setOnClickListener{itemClickListener(this)}
            }
        }
    }

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }
}