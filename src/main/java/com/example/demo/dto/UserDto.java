package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Devon Ravihansa on 9/27/2017.
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserDto {
    private String username;
    @JsonIgnore
    private String password;
    private List<String> roles;
}
