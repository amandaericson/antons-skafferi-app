package se.grupp1.antonsskafferi.lib;

public class StringFormatter {

    static public final String date_format = "yyyy-MM-dd";

    static public final String time_format = "HH:mm";

    public static String formatTime(String in) {
        if(in.length() == 5) return in;

        int index = in.indexOf(':');

        //Lägg till en 0 i början
        if(index < 2) {
            in = '0' + in;
            ++index;
        }

        //Lägg till en 0 i slutet
        if(in.length() < 5) {
            String end = in.substring(index + 1);
            String begin = in.substring(0, index + 1);
            in = begin + '0' + end;
        }

        return in;
    }

    public static String formatDate(String in) {
        if(in.length() == 10) return in;

        String[] arr = in.split("-");
        String out = "";

        for(int i = 1; i < arr.length; i++) {
            if(arr[i].length() == 1) {
                arr[i] = '0' + arr[i];
            }
        }

        for(int i = 0; i < arr.length; i++) {
            out = out + arr[i];
            if(i != arr.length - 1) {
                out = out + '-';
            }
        }

        return out;
    }

    public static boolean isValidEmail(String in) {
        int atIndex = in.indexOf('@');
        int dotIndex = in.indexOf('.');

        return atIndex  != -1                           //Kolla att @ existerar
                && dotIndex != -1                       //Kolla att . existerar
                && atIndex == in.lastIndexOf('@')   //Kolla att bara ett @ existerar
                && atIndex + 1 < dotIndex               //Kolla att @ ligger före .
                && dotIndex + 1 != in.length()          //Kolla att . inte är sista elementet
                && atIndex != 0;                        //Kolla att @ inte är första elementet
    }
}
