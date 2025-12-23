package dto;

public record CompanyResponse(

        Long id,

        String name,

        String address,

        String email,

        String phone,

        String userAdmin,

        String status


) {
}
