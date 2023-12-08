package br.com.erudio.infrastrucutre.api.user;

import br.com.erudio.infrastrucutre.adapter.user.SaveUserAdapter;
import br.com.erudio.infrastrucutre.dtos.user.UserRequestDTO;
import br.com.erudio.infrastrucutre.dtos.user.UserResponseDTO;
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
@RequestMapping(value = "/api/v1/users")
@Tag(name = "User", description = "Endpoints for user")
public class SaveUserController {

    @Autowired
    private SaveUserAdapter adapter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create user", tags = {"User"})
    @ApiResponses({
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
    })
    public UserResponseDTO execute(@Valid @RequestBody UserRequestDTO dto) {
        return this.adapter.execute(dto);
    }
}
