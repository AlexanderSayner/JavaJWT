package org.sayner.student.jwtdemo.service

import org.sayner.student.jwtdemo.dto.StateDto
import org.sayner.student.jwtdemo.dto.id.StateIdDto
import org.sayner.student.jwtdemo.model.State
import org.sayner.student.jwtdemo.repository.ProjectRepository
import org.sayner.student.jwtdemo.repository.StateRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.stream.Collectors

@Service
class StateService(
        private val projectRepository: ProjectRepository,
        private val stateRepository: StateRepository
) {
    private val logger = LoggerFactory.getLogger(StateService::class.java)

    fun findAll(): List<State> = stateRepository.findAll()

    fun create(projectId: Int, stateDto: StateDto): State {
        val optional = projectRepository.findById(projectId)
        if (optional.isEmpty) {
            throw IllegalStateException("Нет проекта с id $projectId")
        }
        val state = State(
                null,
                stateDto.state,
                stateDto.startDate,
                stateDto.endDate,
                optional.get()
        )
        logger.info("Saving new state $stateDto")
        return stateRepository.save(state)
    }

    fun update(id: Int, stateDto: StateDto): State {
        val optional = stateRepository.findById(id)
        if (optional.isPresent) {
            val state = optional.get()
            state.state = stateDto.state
            state.startDate = stateDto.startDate
            state.endDate = stateDto.endDate
            logger.info("Updating state ${state.id}")
            return stateRepository.save(state)
        }
        logger.error("State with id=$id not found")
        throw IllegalStateException("I can't find state with id=$id")
    }

    fun delete(id: Int) {
        stateRepository.deleteById(id)
        logger.info("State $id deleted")
    }

    fun getProjectStates(projectId: Int): List<StateIdDto> {
        logger.info("Getting project states")
        val opt = projectRepository.findById(projectId)
        if (opt.isPresent) {
            return opt.get().states
                    .stream()
                    .map { entity ->
                        StateIdDto(
                                entity.id ?: -1,
                                entity.state ?: "none",
                                entity.startDate ?: LocalDate.EPOCH,
                                entity.endDate ?: LocalDate.EPOCH)
                    }
                    .collect(Collectors.toList())
        }
        throw IllegalStateException("I can't find project with id=$projectId")
    }
}
