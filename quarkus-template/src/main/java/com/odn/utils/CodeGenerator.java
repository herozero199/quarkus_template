package com.odn.utils;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.util.Random;

@ApplicationScoped
public class CodeGenerator {
    /* Hệ cơ số 36: 0..9 + A..Z (10 ký tự số + 26 ký tự chữ cái)
    Nguyên tắc sinh:
        - Số giây từ 01/01/2024 00:00:00 tới hiện tại -> Leftpad 0 thành 9 ký tự
        - Đảo ngược lại
        - Chuyển thành chuỗi 6 ký tự hệ cơ số 36. 36^6 = 2,176,782,336 -> cover số có 9 chữ số (cover đc 30 năm theo quy tắc sinh mã)
        - Thêm 1 ký tự random vào đầu
        - output: 7 ký tự
    */
    public String genUniqueCodeFromEpoch() {
        long zeroPoint = 1704042000; // 01/01/2024 00:00:00
        long currentTime = Instant.now().toEpochMilli();

        if (currentTime < zeroPoint) {
            return "";
        }

        // Đảo ngược
        String sourceNumStr = String.valueOf(currentTime - zeroPoint);
        sourceNumStr = padLeftZeros(sourceNumStr, 9);
        sourceNumStr = reverseString(sourceNumStr);
        long sourceNum = Long.parseLong(sourceNumStr);

        String code = convertNumberToCode36(sourceNum, 6);

        long randomCharCode = new Random().nextLong();
        randomCharCode = randomCharCode >= 0 ? randomCharCode : Math.abs(randomCharCode);
        String randomChar = convertNumberToCode36(randomCharCode, 1);

        return randomChar + code;
    }

    /* Hệ cơ số 36: 0..9 + A..Z (10 ký tự số + 26 ký tự chữ cái)
    Nguyên tắc sinh:
        - Leftpad 0 thành 10 ký tự
        - Đảo ngược lại
        - Chuyển thành chuỗi 6 ký tự hệ cơ số 36. 36^7 = 78,364,164,096 -> cover số có 10 chữ số
        - output: 7 ký tự
    */
    public static String genUniqueCodeFromNumber(long inputNum) {
        if (inputNum < 0) {
            return "";
        }

        // Đảo ngược
        String sourceNumStr = String.valueOf(inputNum);
        sourceNumStr = padLeftZeros(sourceNumStr, 10);
        sourceNumStr = reverseString(sourceNumStr);
        long sourceNum = Long.parseLong(sourceNumStr);

        String code = convertNumberToCode36(sourceNum, 7);

        return code;
    }

    public static String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }

    public static String reverseString(String originalStr) {
        String reversedStr = "";

        for (int i = 0; i < originalStr.length(); i++) {
            reversedStr = originalStr.charAt(i) + reversedStr;
        }

        return reversedStr;
    }

    public static String convertNumberToCode36(long inputNum, int codeLength) {
        // 0..9; A = 10; B = 11; C = 12...
        String output = "";

        if (inputNum < 1) {
            return "";
        }

        long diff = 55; // A = chr(65) = value 10
        long total_char = 36; // Bảng chữ cái tiếng Anh có 26 ký tự + 10 ký tự số

        long so_bi_chia = inputNum;
        long so_chia = total_char;
        long thuong;
        long so_du;
        long char_code;

        for (int i = 0; i < codeLength; i++ ) {
            thuong = so_bi_chia / so_chia;
            so_du = so_bi_chia % so_chia;
            char_code = diff + so_du;
            output = ((so_du >= 0 && so_du <= 9) ? String.valueOf(so_du) : (char) char_code) + output;
            so_bi_chia = thuong;
        }

        return output;
    }
}
