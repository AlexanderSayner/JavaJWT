package org.sayner.student.jwtdemo.repository

import org.sayner.student.jwtdemo.model.State
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StateRepository :JpaRepository<State,Int>
