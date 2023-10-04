package ru.yusdm.friday.testing.entity

import jakarta.persistence.*
import org.springframework.data.domain.Persistable
import java.util.UUID
import kotlin.jvm.Transient

@Entity
@Table(name = "city")
class City: Persistable<UUID> {

    @Id
    @Column(name = "uid")
    val uid: UUID

    @Column(name = "name")
    val name: String

    @ManyToOne
    @JoinColumn(name = "country_uid")
    private val country: Country

    @Transient
    private var _isNew: Boolean

    constructor(id: UUID, name: String, country: Country) {
        this.uid = id
        this.country = country
        this.name = name
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