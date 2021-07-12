package es.plexus.android.presentation_layer.feature.heroes.list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.plexus.android.presentation_layer.databinding.ItemHeroBinding
import es.plexus.android.presentation_layer.domain.ResultsVo

class HeroesListAdapter(
    private val data : List<ResultsVo>,
    private val onSelectHero: (ResultsVo) -> Unit
) : RecyclerView.Adapter<HeroesListAdapter.HeroViewHolder>(){

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ),onSelectHero
        )
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class HeroViewHolder(val view: ItemHeroBinding, private val onSelectHero: (ResultsVo) -> Unit) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(hero: ResultsVo) {
            with(view){
                tvHeroNameValue.text = hero.name.trim()
                tvHeroDescriptionValue.text = hero.description.trim()

                Glide.with(root.context)
                    .load(hero.thumbnail.path)
                    .circleCrop()
                    .into(ivHeroPicture)

                root.setOnClickListener {
                    onSelectHero(hero)
                }
            }
        }
    }
}