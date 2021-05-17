package com.boots.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "files")
open class FileDB : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long? = null
    var name: String? = null
    var type: String? = null

    @Lob
    lateinit var data: ByteArray

    @ManyToOne(fetch = FetchType.LAZY)
    var myUser: MyUser? = null

    constructor() {}
    constructor(name: String?, type: String?, data: ByteArray) {
        this.name = name
        this.type = type
        this.data = data
    }

    override fun toString(): String {
        return "$id $name $type"
    }
}