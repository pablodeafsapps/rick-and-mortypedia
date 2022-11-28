package org.pablodeafsapps.rickandmortypedia.episode.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.pablodeafsapps.rickandmortypedia.R
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episode

class EpisodesAdapter(
    private val data: MutableList<Episode> = mutableListOf()
) : RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val rootView: View = LayoutInflater.from(parent.context).inflate(R.layout.episodes_adapter_row, parent, false)
        return EpisodesViewHolder(itemView = rootView)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<Episode>) {
        data.clear()
        data.addAll(newData.toMutableList())
        notifyDataSetChanged()
    }

    inner class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvName: TextView by lazy { itemView.findViewById(R.id.tv_name) }
        private val tvEpisode: TextView by lazy { itemView.findViewById(R.id.tv_episode) }
        private val tvAirDate: TextView by lazy { itemView.findViewById(R.id.tv_air_date) }

        fun bindData(episode: Episode) {
            tvName.text = episode.name
            tvEpisode.text = episode.episode
            tvAirDate.text = episode.airDate
        }

    }

}
