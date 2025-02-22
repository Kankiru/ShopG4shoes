//kiem tra dang nhap
package repository;

import model.NhanVien;


public class Authu {

    public static NhanVien user = null;

    public static void clear() {
        Authu.user = null;
    }

    public static Boolean isLogin() {
        return Authu.user != null;
    }

    public static boolean isManager() {
        return Authu.isLogin() && user.isChucVu();
    }
}
