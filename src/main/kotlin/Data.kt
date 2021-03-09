class SongDAO{
    val songs: List<Song> = listOf(
        Song("Metallica", "Unforgiven"),
        Song("ACDC", "Big Gun"),
        Song("zz top", "La Grange")
    )
    fun getAllSongs(): List<Song> = songs
    fun getAuthorBySong(song: String) = songs.find{it.Author == song}
}
class AlbumDAO{
    val albums: List<Album> = listOf(
        Album("Metallica", "Apocalyptica", 1996),
        Album("ACDC", "Last Action Hero", 1993),
        Album("zz top", "Tres Hombres", 1973)
    )
    fun getNameByAuthor(Author: String) = albums.find{it.Author == Author}
}