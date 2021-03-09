class SomeService {
    fun aaa(): List<AlbumSongs> {
        SongDAO().getAllSongs().map{it ->
            AlbumSongs(
                it.Author,
                it.title,
                AlbumDAO().getAuthorByName(it.title)!!.pubYear
            )
        }
    }
}