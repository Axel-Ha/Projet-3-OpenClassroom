package com.chatop.api.controller;

import com.chatop.api.services.PictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.chatop.api.configuration.SwaggerConfig.NAME_SECURITY_REQUIREMENT;

@Controller
@RequestMapping("/api")
@RestController
@SecurityRequirement(name=NAME_SECURITY_REQUIREMENT)
public class PictureController {

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/pictures/{filename}")
    @Operation(
            summary = "Return the corresponding picture of the rental ",
            description = "Get the picture according to the rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the picture"),
            @ApiResponse(responseCode = "404", description = "The picture does not exist on the server"),
    })
    public ResponseEntity<Resource> serverFile(@PathVariable String filename) {
        try {
            Resource resource = pictureService.getPicture(filename);
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("File not found or not readable");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error serving file", e);
        }
    }
}
