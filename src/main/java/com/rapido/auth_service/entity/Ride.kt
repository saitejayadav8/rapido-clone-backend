package com.rapido.ride_service.entity

import jakarta.persistence.*

@Entity
@Table(name = "rides")
class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    private val riderEmail: String? = null

    private val driverEmail: String? = null

    private val status: String? = null // Getters and Setters
}