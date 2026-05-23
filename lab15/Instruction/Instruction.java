package CS102_Sec3_Asgn6_Ozdemir_Erkam.Instruction;

public class Instruction {
    private InstructionType type;
    private String value;

    public Instruction(InstructionType type, String value) {
        this.type = type;
        this.value = value;
    }

    public InstructionType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getIntValue() {
        return Integer.parseInt(value);
    }
}
