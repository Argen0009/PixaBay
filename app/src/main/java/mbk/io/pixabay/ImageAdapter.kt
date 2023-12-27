package mbk.io.pixabay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import mbk.io.pixabay.databinding.ItemImageBinding

class ImageAdapter(private val list: MutableList<ImageModel>) : Adapter<ImageAdapter.ImageViewHorder>() {

    inner class ImageViewHorder(val binding: ItemImageBinding) : ViewHolder(binding.root) {
        fun onBind(image: ImageModel) {
            binding.pixaImage.load(image.largeImageURL)
        }
    }

    fun addNewImages(list: ArrayList<ImageModel>){
       this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHorder {
        return ImageViewHorder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHorder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}