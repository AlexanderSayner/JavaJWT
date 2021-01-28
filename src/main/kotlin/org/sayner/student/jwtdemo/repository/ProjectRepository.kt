package org.sayner.student.jwtdemo.repository

import org.sayner.student.jwtdemo.model.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project, Int>
