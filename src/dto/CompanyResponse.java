package dto;

public record CompanyResponse(

        Long id,

        String name,

        String address,

        String phone,

        String email,

        String userAdmin


) {
}
