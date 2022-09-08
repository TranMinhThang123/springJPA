package com.traminhthang.dataJpa.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@AttributeOverrides() bao gom nhieu @AttributeOverride(con khac nhau dat trong dau {})
//moi @AttributeOverride() co 2 thuoc tinh la name va column
//name chi ra ten thuoc tinh trong code
//column chi ra cot tuong tung cua thuoc tinh do trong
@AttributeOverrides({
        @AttributeOverride(
                name = "name",
                column = @Column(name = "guardian_name")
        ),
        @AttributeOverride(
                name = "email",
                column = @Column(name = "guardian_email")
        ),
        @AttributeOverride(
                name = "mobile",
                column = @Column(name = "guardian_mobile")
        )
}
)
public class Guardian {
    private String name;
    private String email;
    private String mobile;
}
