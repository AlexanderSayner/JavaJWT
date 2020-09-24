package org.sayner.student.jwtdemo.repository

import org.sayner.student.jwtdemo.model.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReportRepository :JpaRepository<Report,Int>
