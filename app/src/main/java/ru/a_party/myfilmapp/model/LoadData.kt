package ru.a_party.myfilmapp.model

interface LoadData {
    fun loadLocal():List<Section>;
    fun loadFromServer():List<Section>
    fun loadFilmsBySection(section:Section):List<Film>
}

class LoadDataImpl():LoadData{
    override fun loadLocal(): List<Section> {
        return listOf(
            Section("Новинки"),
            Section("В топе"),
            Section("Для детей"),
            Section("Скоро"))
    }

    override fun loadFromServer(): List<Section> {
        return listOf(
            Section("Новинки"),
            Section("В топе"),
            Section("Для детей"),
            Section("Скоро"))
    }

    override fun loadFilmsBySection(section: Section): List<Film> {
        return listOf(
            Film("Терминатор",1985),
            Film("Чужие",1979)
        )
    }

}