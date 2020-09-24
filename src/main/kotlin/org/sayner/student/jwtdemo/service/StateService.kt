package org.sayner.student.jwtdemo.service

import org.sayner.student.jwtdemo.dto.StateDto
import org.sayner.student.jwtdemo.model.State
import org.sayner.student.jwtdemo.repository.StateRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StateService(
        private val stateRepository: StateRepository
) {
    private val logger = LoggerFactory.getLogger(StateService::class.java)

    fun findAll(): List<State> = stateRepository.findAll()

    fun create(stateDto: StateDto): State {
        val project = State(
                null,
                stateDto.state,
                stateDto.startDate,
                stateDto.endDate,
                null
        )
        logger.info("Saving new state $project")
        return stateRepository.save(project)
    }

    fun update(id: Int, stateDto: StateDto): State {
        val optional = stateRepository.findById(id)
        if (optional.isPresent) {
            val state = optional.get()
            state.state = stateDto.state
            state.startDate = stateDto.startDate
            state.endDate = stateDto.endDate
            logger.info("Updating state $state")
            return stateRepository.save(state)
        }
        logger.error("State with id=$id not found")
        throw IllegalStateException("I can't find state with id=$id")
    }

    fun delete(id: Int) {
        stateRepository.deleteById(id)
        logger.info("State $id deleted")
    }
}
