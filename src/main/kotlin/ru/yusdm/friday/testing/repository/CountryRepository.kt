package ru.yusdm.friday.testing.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.yusdm.friday.testing.entity.Country
import java.util.*

interface CountryRepository : JpaRepository<Country, UUID> {
    @Query("SELECT c.uid FROM Country c")
    fun getAllIds(): List<UUID>

    @Modifying
    @Query("INSERT INTO COUNTRY (uid, name) VALUES (:#{#country.id}, :#{#country.name})", nativeQuery = true)
    fun saveNative(@Param("country") country: Country)

    @Modifying
    @Query("UPDATE COUNTRY SET name = :name WHERE uid = :id", nativeQuery = true)
    fun updateNameNative(@Param("id") id: UUID, @Param("name") name: String)

    @Query("""
            select *
            from country c
            where c.uid = (select country_id
                           from (select cn.uid   as country_id,
                                        COUNT(1) as count
                                 from country cn
                                          inner join city ct on
                                     cn.uid = ct.country_uid
                                 group by cn.uid
                                 order by count desc
                                 limit 1) t)    
                                 """, nativeQuery = true)
    fun getCountryWithMaxNumberOfCities(): Country

    @Modifying
    @Query("DELETE FROM COUNTRY WHERE uid = :id", nativeQuery = true)
    fun deleteByUid(@Param("id") id: UUID)
}