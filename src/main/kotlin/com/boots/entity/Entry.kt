package com.boots.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "entries")
open class Entry : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long? = null
    var header: String? = null
    var body: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var user: User? = null

    constructor() {}
    constructor(header: String?, body: String?) {
        this.header = header
        this.body = body
    }

    override fun toString(): String {
        return "$id $header $body"
    }
}