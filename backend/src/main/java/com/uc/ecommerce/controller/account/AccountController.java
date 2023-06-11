package com.uc.ecommerce.controller.account;


import com.uc.ecommerce.core.security.SecurityContextUtil;
import com.uc.ecommerce.core.security.annotation.IsAuthenticated;
import com.uc.ecommerce.core.security.annotation.PermitAll;
import com.uc.ecommerce.service.imp.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @IsAuthenticated
    @Operation(
            summary = "Gets current User Details",
            description = "Returns current user information, return 401 if not authenticated"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "current user information", content = @Content),
                    @ApiResponse(responseCode = "401", description = "not authenticated", content = @Content)
            }
    )
    @GetMapping
    public MeResponse me() {
        return SecurityContextUtil.getMe();
    }

    @Operation(
            summary = "Logs User into the System",
            description = "a successful login"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully login", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Access to the relevant resource is prohibited", content = @Content),
            }
    )
    @PostMapping("/login")
    @PermitAll
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {

        return accountService.login(loginRequest);
    }

    @GetMapping("/logout")
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        accountService.logout(request, response);
    }
    @GetMapping("/active/{id}")
    public void activeUser(@PathVariable Long id,@RequestParam String code){
        accountService.active(id,code);
    }

}
