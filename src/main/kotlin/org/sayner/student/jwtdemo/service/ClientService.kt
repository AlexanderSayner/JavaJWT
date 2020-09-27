package org.sayner.student.jwtdemo.service

import org.sayner.student.jwtdemo.dto.ClientDto
import org.sayner.student.jwtdemo.model.Client
import org.sayner.student.jwtdemo.repository.ClientRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ClientService(
        private val clientRepository: ClientRepository
) {
    private val logger = LoggerFactory.getLogger(ClientService::class.java)

    fun findAll(): List<Client> = clientRepository.findAll()

    fun create(clientDto: ClientDto): Client {
        val client = Client(
                null,
                clientDto.username,
                clientDto.state,
                clientDto.email,
                emptySet()
        )
        logger.info("Saving new client $clientDto")
        return clientRepository.save(client)
    }

    fun update(id: Int, clientDto: ClientDto): Client {
        val optional = clientRepository.findById(id)
        if (optional.isPresent) {
            val client = optional.get()
            client.username = clientDto.username
            client.state = clientDto.state
            client.email = clientDto.email
            logger.info("Updating client ${client.id}")
            return clientRepository.save(client)
        }
        logger.error("Client with id=$id not found")
        throw IllegalStateException("I can't find client with id=$id")
    }

    fun delete(id: Int) {
        clientRepository.deleteById(id)
        logger.info("Client $id deleted")
    }
}
