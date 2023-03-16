import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

class CatholicChantDownloader {
    static final String URL_SHEET = "https://maria.catholic.or.kr/files/mp3/sungga/img/2012/2012040";
    static final String URL_MP3_1 = "https://maria.catholic.or.kr/musicfiles/mp3/2004090";
    static final String URL_MP3_2 = "https://maria.catholic.or.kr/musicfiles/mp3/2010060";

    public static void main(String args[]) {
        downloadSheet();
        downloadMp3();
    }

    static void downloadSheet() {
        new File("Sheet").mkdirs();
        for (int i = 1; i < 530; i++) {
            String number = padLeftZeros(String.valueOf(i), 3);
            try {
                String sheetUrl = getSheetUrl(number);
                String sheetFile  = getSheetFile(number);
                download(sheetUrl, sheetFile);
            } catch (Exception e) {
            }
        }
    }

    static void downloadMp3() {
        new File("Mp3").mkdirs();
        for (int i = 1; i < 401; i++) {
            String number = padLeftZeros(String.valueOf(i), 3);
            try{
                String mp3Url = getMp3Url1(number);
                String mp3File  = getMp3File(number);
                download(mp3Url, mp3File);
            } catch (Exception e) {
            }
        }
        for (int i = 401; i < 530; i++) {
            String number = padLeftZeros(String.valueOf(i), 3);
            try{
                String mp3Url = getMp3Url2(number);
                String mp3File  = getMp3File(number);
                download(mp3Url, mp3File);
            } catch (Exception e) {
            }
        }
    }

    static String getSheetUrl(String number) {
        return URL_SHEET + number + ".jpg";
    }

    static String getMp3Url1(String number) {
        return URL_MP3_1 + number + ".mp3";
    }

    static String getMp3Url2(String number) {
        return URL_MP3_2 + number + ".mp3";
    }

    static String getSheetFile(String number) throws Exception {
        return "Sheet/" + number + ".jpg";
    }

    static String getMp3File(String number) throws Exception {
        return "Mp3/" + number + ".mp3";
    }

    static void download(String url, String fileName) throws Exception {
        // download
        InputStream in = new BufferedInputStream(new URL(url).openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();

        // write to file
        FileOutputStream fs = new FileOutputStream(fileName);
        fs.write(out.toByteArray());
        fs.close();
    }

    static String padLeftZeros(String inputString, int length) {
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
}