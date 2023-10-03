package ru.yusdm.friday.testing.entity

import jakarta.persistence.*
import org.springframework.data.domain.Persistable
import kotlin.jvm.Transient

@Entity
@Table(name = "country")
class Country: Persistable<Long> {

    @Id
    @Column(name = "uid")
    val uid: Long

    @Column(name = "name")
    var name: String

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = [CascadeType.PERSIST])
    val cities: MutableList<City>?

    @Transient
    private var _isNew: Boolean

    constructor(id: Long, name: String, cities: MutableList<City>?) {
        this.uid = id
        this.name = name
        this.cities = cities
        this._isNew = true
    }

    override fun getId() = this.uid

    override fun isNew() = this._isNew

    @PostPersist
    @PostLoad
    private fun setPersisted() {
        this._isNew = false
    }
}