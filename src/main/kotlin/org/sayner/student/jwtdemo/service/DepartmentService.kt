package org.sayner.student.jwtdemo.service

import org.sayner.student.jwtdemo.dto.DepartmentDto
import org.sayner.student.jwtdemo.model.Department
import org.sayner.student.jwtdemo.repository.DepartmentRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DepartmentService(
        private val departmentRepository: DepartmentRepository
) {
    private val logger = LoggerFactory.getLogger(DepartmentService::class.java)

    fun findAll(): List<Department> = departmentRepository.findAll()

    fun create(departmentDto: DepartmentDto): Department {
        val department = Department(
                null,
                departmentDto.name
//                ,
//                emptySet()
        )
        logger.info("Saving new department $department")
        return departmentRepository.save(department)
    }

    fun update(id: Int, departmentDto: DepartmentDto): Department {
        val optional = departmentRepository.findById(id)
        if (optional.isPresent) {
            val department = optional.get()
            department.name = departmentDto.name
            logger.info("Updating department $department")
            return departmentRepository.save(department)
        }
        logger.error("Department with id=$id not found")
        throw IllegalStateException("I can't find department with id=$id")
    }

    fun delete(id: Int) {
        departmentRepository.deleteById(id)
        logger.info("Department $id deleted")
    }
}
