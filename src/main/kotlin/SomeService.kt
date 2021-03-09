class SomeService {
    fun SomeFun(): List<AlbumSongs> = SongDAO().getAllSongs().map{it ->
            AlbumSongs(
                it.Author,
                it.title,
                AlbumDAO().getNameByAuthor(it.Author)!!.name,
                AlbumDAO().getNameByAuthor(it.Author)!!.pubYear
            )
        }
    fun SortFun() = SomeFun().sortedBy { it.Author }
    fun MapFun() = SomeFun().groupBy { it.name  }
    fun CountFun() = SomeFun().count { it.pubYear <= 1980}
}