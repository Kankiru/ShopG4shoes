package repository;

public class Validated {

    public static Boolean isEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static Boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        String repex = "^\\w+@\\w+(\\.\\w+){1,2}$";
        return email.matches(repex);
    }

    public static Boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }

        String repex = "^[0-9]{9}$";
        return phoneNumber.matches(repex);
    }

    public static Boolean isPassword(char[] pass) {
        String passString = new String(pass).trim();
        return passString.matches("^[a-zA-Z0-9]{6,15}$");
    }

    public static boolean isNumericDouble(String numeric) {
        if (numeric == null) {
            return false;
        }
        String regex = "^[+]?(?!0$)[0-9]*\\.?[0-9]+$";
        return numeric.matches(regex);
    }

    public static boolean isNumericInt(String numeric) {
        if (numeric == null) {
            return false;
        }
        String regex = "^[+]?[0-9]+$";
        return numeric.matches(regex);
    }

    public static boolean isValidName(String name) {
        // Sử dụng regex để kiểm tra xem chuỗi chỉ chứa chữ cái hay không
        return name.matches("[a-zA-Z]+");
    }
}
