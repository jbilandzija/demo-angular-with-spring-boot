package de.novatec.studentservice.student

import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1/student")
@CrossOrigin(origins = [], allowCredentials = "false", allowedHeaders = ["*"], methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE])
class StudentController(private val studentRepository: StudentRepository) {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/{name}")
    fun getStudentByName(@PathVariable("name") name: String): ResponseEntity<Student> {
        val result = studentRepository.findStudentEntityByName(name)?.toStudent()
        return if (result == null) {
            logger.info { "Get Student by name. Student not found by name: $name" }
            ResponseEntity.notFound().build()
        } else {
            logger.info { "Get Student by name. Student found by name: $name, result: $result" }
            ResponseEntity.ok(result)
        }
    }

    @DeleteMapping("/{name}")
    fun deleteStudentByName(@PathVariable("name") name: String): ResponseEntity<String> {
        val result = studentRepository.findStudentEntityByName(name)
        if (result != null) {
            logger.info { "Delete Student by name. Student deleted by name: $name" }
            studentRepository.delete(result)
        }
        return ResponseEntity.ok(name)
    }

    @PostMapping()
    fun postStudentByName(@RequestBody student: Student): ResponseEntity<Student> {
        val result = studentRepository.findStudentEntityByName(student.name)
        val temp = if (result == null) {
            logger.info { "Create Student by name. New student created by name: $student.name" }
            studentRepository.save(StudentEntity(UUID.randomUUID().toString(), student.name))
        } else {
            logger.info { "Update Student by name. Student updated by name: $student.name" }
            studentRepository.save(StudentEntity(result.id, student.name))
        }
        return ResponseEntity.ok(temp.toStudent())
    }

    @GetMapping("/all")
    fun getAllStudents(): ResponseEntity<List<Student>> {
        logger.info { "Get all students." }
        return ResponseEntity.ok(studentRepository.findAll().map { it.toStudent() })
    }
}
