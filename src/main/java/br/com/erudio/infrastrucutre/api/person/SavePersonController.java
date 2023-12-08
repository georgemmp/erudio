package br.com.erudio.infrastrucutre.api.person;

import br.com.erudio.infrastrucutre.adapter.person.SavePersonAdapter;
import br.com.erudio.infrastrucutre.dtos.person.PersonRequestDTO;
import br.com.erudio.infrastrucutre.dtos.person.PersonResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/people")
@Tag(name = "People", description = "Endpoints for managing people")
public class SavePersonController {

    @Autowired
    private SavePersonAdapter adapter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save person", description = "Save person", tags = {"People"})
    @ApiResponses({
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = PersonResponseDTO.class))),
            @ApiResponse(description = "Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
    })
    public PersonResponseDTO execute(@Valid @RequestBody PersonRequestDTO dto) {
        return this.adapter.execute(dto);
    }
}
