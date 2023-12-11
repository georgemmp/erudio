package br.com.erudio.infrastrucutre.api.person;

import br.com.erudio.infrastrucutre.adapter.person.FindAllPersonAdapter;
import br.com.erudio.infrastrucutre.dtos.person.PersonResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.data.domain.Sort.Direction.*;

@RestController
@RequestMapping(value = "/api/v1/people")
@Tag(name = "People", description = "Endpoints for managing people")
public class FindAllPersonController {

    @Autowired
    private FindAllPersonAdapter adapter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all people", description = "Find all people", tags = {"People"})
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonResponseDTO.class)))
            }),
            @ApiResponse(description = "Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
    })
    public ResponseEntity<Page<PersonResponseDTO>> execute(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        Direction sort = direction.equalsIgnoreCase("desc") ? DESC : ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sort, "firstName"));
        return ResponseEntity.ok(this.adapter.execute(pageable));
    }
}
