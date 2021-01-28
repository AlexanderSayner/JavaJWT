package org.sayner.student.jwtdemo.service

import org.sayner.student.jwtdemo.dto.ProjectDto
import org.sayner.student.jwtdemo.model.Project
import org.sayner.student.jwtdemo.repository.ProjectRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository
) {
    private val logger = LoggerFactory.getLogger(ProjectService::class.java)

    fun findAll(): List<Project> = projectRepository.findAll()

    fun create(projectDto: ProjectDto): Project {
        val project = Project(
            null,
            projectDto.name,
            projectDto.open_date,
            projectDto.release_date,
            emptySet()
        )
        logger.info("Saving new project $project")
        return projectRepository.save(project)
    }

    fun update(id: Int, projectDto: ProjectDto): Project {
        val optional = projectRepository.findById(id)
        if (optional.isPresent) {
            val project = optional.get()
            project.name = projectDto.name
            project.open_date = projectDto.open_date
            project.release_date = projectDto.release_date
            logger.info("Updating project $projectDto")
            return projectRepository.save(project)
        }
        logger.error("Project with id=$id not found")
        throw IllegalStateException("I can't find project with id=$id")
    }

    fun delete(id: Int) {
        projectRepository.deleteById(id)
        logger.info("Project $id deleted")
    }
}
