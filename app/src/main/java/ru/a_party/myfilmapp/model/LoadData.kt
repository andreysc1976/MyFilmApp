package ru.a_party.myfilmapp.model

interface LoadData {
    fun loadLocal():List<Section>;
    fun loadFromServer():List<Section>
    fun loadFilmsBySection(section:Section)

}

class LoadDataImpl(val onLoadSection: OnLoadSection):LoadData{
    override fun loadLocal(): List<Section> {
        return listOf(
            Section("Популярные","https://api.themoviedb.org/3/movie/popular"),
            Section("Сейчас в кино",    "https://api.themoviedb.org/3/movie/now_playing"),
            Section("С высоким рейтингом","https://api.themoviedb.org/3/movie/top_rated"))
    }

    override fun loadFromServer(): List<Section> {
        return listOf(
            Section("Популярные","https://api.themoviedb.org/3/movie/popular"),
            Section("Сейчас в кино",    "https://api.themoviedb.org/3/movie/now_playing"),
            Section("С высоким рейтингом","https://api.themoviedb.org/3/movie/top_rated"))
    }

    override fun loadFilmsBySection(section: Section){
        val dataLoader:LoadSectionData = LoadSectionData(section.uri,1)
        dataLoader.setOnLoadDataUpdater(object : LoadSectionData.OnLoadDataUpdater
        {
            override fun onLoadData(data: SectionDataClass) {
                onLoadSection?.onLoadSection(data.results)
            }

        })
        dataLoader.start()
    }


    interface OnLoadSection{
        fun onLoadSection(films:Array<MovieListResultObject>)
    }

}