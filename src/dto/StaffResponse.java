package dto;

public record StaffResponse(

    long id,

    String name,

    String departmentName,

    String officeName,

    String positionName,

    String status

) {
}
