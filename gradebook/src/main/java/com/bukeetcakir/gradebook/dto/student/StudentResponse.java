package com.bukeetcakir.gradebook.dto.student;

public record StudentResponse(Long id,
                              String fullName,
                              String number,
                              String email,
                              String gsmNumber) {
}
