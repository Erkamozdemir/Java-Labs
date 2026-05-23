package CS102_Sec3_Asgn6_Ozdemir_Erkam;

import CS102_Sec3_Asgn6_Ozdemir_Erkam.Data_Structures.*;
import CS102_Sec3_Asgn6_Ozdemir_Erkam.Instruction.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Interpreter {
    private LinkedList<Instruction> instructions;
    private Stack<Integer> stack;
    private Queue<Integer> queue;
    private RepeatStack repeatStack;
    private int ip;
    private Scanner userInput;

    public Interpreter(String path) throws FileNotFoundException {
        instructions = new LinkedList<>();
        stack = new Stack<>();
        queue = new Queue<>(16);
        repeatStack = new RepeatStack();
        ip = 0;
        userInput = new Scanner(System.in);
        load(path);
    }

    private void load(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }
            int commentIndex = line.indexOf('#');
            if (commentIndex != -1) {
                line = line.substring(0, commentIndex).trim();
            }
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\s+", 2);
            InstructionType type = InstructionType.valueOf(parts[0].toUpperCase());
            String value = parts.length > 1 ? parts[1].trim() : null;
            instructions.add(new Instruction(type, value));
        }
        scanner.close();
    }

    public void run() {
        ip = 0;
        while (ip < instructions.size()) {
            Instruction instr = instructions.get(ip);
            execute(instr);
            ip++;
        }
    }

    private void execute(Instruction instr) {
        switch (instr.getType()) {

            case PUSH:
                stack.push(instr.getIntValue());
                break;

            case POP:
                stack.pop();
                break;

            case DUP:
                stack.push(stack.peek());
                break;

            case SWAP:
                int swapA = stack.pop();
                int swapB = stack.pop();
                stack.push(swapA);
                stack.push(swapB);
                break;

            case STORE:
                queue.enqueue(stack.peek());
                break;

            case LOAD:
                stack.push(queue.dequeue());
                break;

            case FRONT:
                stack.push(queue.peek());
                break;

            case ADD:
                int addA = stack.pop();
                int addB = stack.pop();
                stack.push(addA + addB);
                break;

            case SUB:
                int subTop = stack.pop();
                int subSecond = stack.pop();
                stack.push(subTop - subSecond);
                break;

            case PRINT:
                System.out.println(stack.peek());
                break;

            case PRINTTEXT:
                String text = instr.getValue();
                System.out.println(text != null ? text : "");
                break;

            case GET:
                System.out.print(instr.getValue());
                int userVal = userInput.nextInt();
                stack.push(userVal);
                break;

            case JUMP:
                ip += instr.getIntValue();
                break;

            case JUMPIFPOS:
                if (stack.peek() > 0) {
                    ip += instr.getIntValue();
                }
                break;

            case JUMPIFNZ:
                if (stack.peek() != 0) {
                    ip += instr.getIntValue();
                }
                break;

            case REPEAT:
                int count = instr.getIntValue();
                if (count > 0) {
                    repeatStack.push(ip + 1, count);
                } else {
                    int nested = 1;
                    int tempIp = ip + 1;
                    while (nested > 0 && tempIp < instructions.size()) {
                        InstructionType type = instructions.get(tempIp).getType();
                        if (type == InstructionType.REPEAT) {
                            nested++;
                        } else if (type == InstructionType.ENDREPEAT) {
                            nested--;
                        }
                        tempIp++;
                    }
                    ip = tempIp - 1;
                }
                break;

            case ENDREPEAT:
                if (!repeatStack.isEmpty()) {
                    if (repeatStack.decrementAndCheck()) {
                        ip = repeatStack.peekStartIp() - 1;
                    }
                }
                break;

            default:
                break;
        }
    }
}
