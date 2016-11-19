package androidkejar.app.sunshine;

/**
 * Created by alodokter-it on 12/11/16.
 */

public class BaseApp {
    static class Dates {
        private static final String[] listDay = new String[]{"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu"};
        private static final String[] listMonth = new String[]{"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agt", "Sep", "Okt", "Nov", "Des"};

        public static String[] getListDay() {
            return listDay;
        }

        public static String[] getListMonth() {
            return listMonth;
        }
    }
}
