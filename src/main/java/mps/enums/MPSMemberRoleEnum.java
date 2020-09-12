package mps.enums;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum MPSMemberRoleEnum {

    PM_USER("High_Privileged"),
    MINISTER_USER("Less_Privileged"),
    MP_USER("Privileged"),
    COMMISSIONER_USER("Arrest_Privileged"),
    SPECIAL_COMMISSIONER_USER("Special_Arrest_Privileged"),
    ADMIN_USER("Member_Data_Create_Privileged");

	private final String roleAssigned;

    @Override
    public String toString(){
        return roleAssigned;
    }

    public static Stream<MPSMemberRoleEnum> stream() {
        return Stream.of(MPSMemberRoleEnum.values());
    }
}
