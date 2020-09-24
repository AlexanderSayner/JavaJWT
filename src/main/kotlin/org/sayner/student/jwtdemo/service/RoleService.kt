package org.sayner.student.jwtdemo.service

import org.sayner.student.jwtdemo.dto.RoleDto
import org.sayner.student.jwtdemo.model.Role
import org.sayner.student.jwtdemo.repository.RoleRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository) {
    private val logger = LoggerFactory.getLogger(RoleService::class.java)

    fun findAll(): List<Role> = roleRepository.findAll()

    fun create(roleDto: RoleDto): Role {
        val newRole = Role(
                null,
                roleDto.role,
                roleDto.skillLevel,
                emptySet()
        )
        logger.info("Saving new role $newRole")
        return roleRepository.save(newRole)
    }

    fun update(id: Int, roleDto: RoleDto): Role {
        val optional = roleRepository.findById(id)
        if (optional.isPresent) {
            val role = optional.get()
            role.role = roleDto.role
            role.skillLevel = roleDto.skillLevel
            logger.info("Updating role $role")
            return roleRepository.save(role)
        }
        logger.error("Role with id=$id not found")
        throw IllegalStateException("I can't find role with id=$id")
    }

    fun delete(id: Int) {
        roleRepository.deleteById(id)
        logger.info("Role $id deleted")
    }
}
