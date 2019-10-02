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
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(val weekForecast: ForecastList,
                          val itemClickListener: (Forecast) -> Unit) :
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

    class ViewHolder(view: View, val itemClickListener: (Forecast) -> Unit)
        : RecyclerView.ViewHolder(view){

        fun bindForecast(forecast: Forecast){
            with(forecast){
                Picasso.get().load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemp.text = "$high"
                itemView.minTemp.text = "$low"
                itemView.setOnClickListener{itemClickListener(this)}
            }
        }
    }

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }
}