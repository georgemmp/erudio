package br.com.erudio.infrastrucutre.api.auth;

import br.com.erudio.infrastrucutre.adapter.auth.SignInAdapter;
import br.com.erudio.infrastrucutre.dtos.auth.AuthenticationDTO;
import br.com.erudio.infrastrucutre.dtos.auth.TokenDTO;
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
@RequestMapping(value = "/api/v1/auth")
@Tag(name = "Authentication", description = "Endpoints for authentication")
public class SignInController {

    @Autowired
    private SignInAdapter adapter;

    @PostMapping(value = "/signin")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Authenticate user and return token", tags = {"Authentication"})
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TokenDTO.class))),
            @ApiResponse(description = "Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
    })
    public TokenDTO execute(@Valid @RequestBody AuthenticationDTO dto) {
        return this.adapter.execute(dto);
    }
}
