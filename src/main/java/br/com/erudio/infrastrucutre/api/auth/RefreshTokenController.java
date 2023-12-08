package br.com.erudio.infrastrucutre.api.auth;

import br.com.erudio.infrastrucutre.adapter.auth.RefreshTokenAdapter;
import br.com.erudio.infrastrucutre.dtos.auth.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
@Tag(name = "Authentication", description = "Endpoints for authentication")
public class RefreshTokenController {

    @Autowired
    private RefreshTokenAdapter adapter;

    @PutMapping(value = "/refresh-token/{userName}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Refresh token", tags = {"Authentication"})
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TokenDTO.class))),
            @ApiResponse(description = "Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
    })
    public TokenDTO execute(@PathVariable String userName, @RequestHeader(value = "Authorization") String refreshToken) {
        return this.adapter.execute(userName, refreshToken);
    }
}
