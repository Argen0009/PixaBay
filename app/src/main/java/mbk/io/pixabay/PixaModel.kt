package mbk.io.pixabay

data class PixaModel (
    val hits:List<ImageModel>
)

data class ImageModel(
    val largeImageURL: String,
)