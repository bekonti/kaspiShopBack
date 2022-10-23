package kz.kbtu.kaspishopback.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.kbtu.kaspishopback.domain.KsProduct;
import kz.kbtu.kaspishopback.domain.KsRole;
import kz.kbtu.kaspishopback.domain.KsUser;
import kz.kbtu.kaspishopback.service.ProductService;
import kz.kbtu.kaspishopback.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/users")
    public ResponseEntity<List<KsUser>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<KsUser> saveUser(@RequestBody KsUser user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<KsRole> saveRole(@RequestBody KsRole role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm role) {
        userService.addRoleToUser(role.getUsername(), role.getRolename());
        return ResponseEntity.ok().build();
    }

    @GetMapping("token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                KsUser user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(username)
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(KsRole::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                String error = exception.getMessage();
                response.setHeader("error", error);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("error_message", error);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), errorMap);
            }
        }

    }


    @GetMapping("/product/list")
    public ResponseEntity<List<KsProduct>> productList() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @GetMapping("/product/detail")
    public ResponseEntity<?> productDetail() {
        return null;
    }
}


@Data
class RoleToUserForm {
    private String username;
    private String rolename;
}