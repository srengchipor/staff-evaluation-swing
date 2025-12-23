package dto;

public record UserResponse(

        Long id,

        String username,

        String company,

        String description,

        String status

) {
}
