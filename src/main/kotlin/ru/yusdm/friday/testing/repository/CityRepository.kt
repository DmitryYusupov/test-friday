package ru.yusdm.friday.testing.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.yusdm.friday.testing.entity.City

interface CityRepository: JpaRepository<City, Long>