public class complexNumberMultiply {
    public String complexNumberMultiply(String a, String b) {
        char[] inputa = a.toCharArray();
        char[] inputb = b.toCharArray();
        int[] numa = getNum(inputa);
        int[] numb = getNum(inputb);
        int real = numa[0] * numb[0] - numa[1] * numb[1];
        int image = numa[0] * numb[1] + numa[1] * numb[0];
        return String.valueOf(real) + '+' + String.valueOf(image) + 'i';
    }
    private int[] getNum(char[] input){
        int index = 0;
        int i = 0;
        int r = 0;
        boolean neg = false;
        if (input[index] == '-') {
            neg = true;
            index++;
        }
        StringBuilder sb = new StringBuilder();
        while (index < input.length && Character.isDigit(input[index])) {
            sb.append(input[index]);
            index++;
        }
        r = Integer.parseInt(sb.toString());
        if (neg == true) {
            r = - r;
        }
        neg = false;
        while (index < input.length && !Character.isDigit(input[index])) {
            if (input[index] == '-') {
                neg = true;
            }
            index++;
        }
        sb = new StringBuilder();
        while (index < input.length && Character.isDigit(input[index])) {
            sb.append(input[index]);
            index++;
        }
        i = Integer.parseInt(sb.toString());
        if (neg == true) {
            i = - i;
        }
        int[] result = new int[2];
        result[0] = r;
        result[1] = i;
        return result;
    }
}