package org.pablodeafsapps.rickandmortypedia.character.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Character
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.pablodeafsapps.rickandmortypedia.R

class CharactersAdapter(
    private val data: MutableList<Character> = mutableListOf()
) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val rootView: View = LayoutInflater.from(parent.context).inflate(R.layout.characters_adapter_row, parent, false)
        return CharactersViewHolder(itemView = rootView)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<Character>) {
//        data.clear()
        data.addAll(newData.toMutableList())
        notifyDataSetChanged()
    }

    inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgProfile: ImageView by lazy { itemView.findViewById(R.id.img_profile) }
        private val tvName: TextView by lazy { itemView.findViewById(R.id.tv_name) }
        private val tvExtras: TextView by lazy { itemView.findViewById(R.id.tv_extras) }

        fun bindData(character: Character) {
            Glide.with(itemView.context).load(character.image).centerCrop().into(imgProfile);
            tvName.text = character.name
            tvExtras.text = "species: ${character.species}, status: ${character.status}"
        }

    }

}
