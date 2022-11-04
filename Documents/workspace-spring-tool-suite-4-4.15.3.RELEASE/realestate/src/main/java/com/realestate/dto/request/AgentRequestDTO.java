package com.realestate.dto.request;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AgentRequestDTO {

   
    @NotEmpty(message = "This field cannot be left blank.")
    @Size(max = 20)
    @NotNull(message = "Please enter agent's first name")
    private String firstName;

    @NotEmpty(message = "This field cannot be left blank.")
    @Size(max = 20)
    @NotNull(message = "Please enter agent's last name")  
    private String lastName;

    @NotEmpty(message = "This field cannot be left blank.")
    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please enter valid phone number")
    @Size(min = 14, max = 14)
    @NotNull(message = "Please enter your phone number")   
    private String phoneNumber;

    @NotEmpty(message = "This field cannot be left blank.")
    @Email
    @Size(min = 8, max = 120)
    @NotNull(message = "Please enter agent's email")  
    private String email;

    private List<PropertyRequestDTO> properties;

    private AgentImageRequestDTO agentImage;
}