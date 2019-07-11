package pl.babel.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
class TodoServlet {
    private final Logger logger = LoggerFactory.getLogger( TodoServlet.class );

    private TodoRepository repository;

    private TodoServlet(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<Todo>> findAllTodos() {
        logger.info( "Got request" );
        return ResponseEntity.ok( repository.findAll() );
    }

    @PutMapping("/{id}")
    ResponseEntity<Todo> toggleTodo(@PathVariable Integer id) {
        logger.info( "Put request" );
        var todo = repository.findById( id );
        todo.ifPresent( todo1 ->
        {
            todo1.setDone( !todo1.getDone() );
            repository.save( todo1 );
        } );
        return todo.map( ResponseEntity::ok ).orElse( ResponseEntity.notFound().build() );
    }

    @PostMapping
    ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
        logger.info( "Post request" );
        return ResponseEntity.ok( repository.save( todo ) );
    }

}