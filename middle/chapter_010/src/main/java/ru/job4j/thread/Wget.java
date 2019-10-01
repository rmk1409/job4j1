package ru.job4j.thread;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Нужно написать консольную программу аналог wget.
 * <p>
 * Программа должна скачивать файл из сети с ограничением по скорости скачки.
 * например wget url 200
 * wget (ссылка) (скорость в килобайтах в секунту)
 * для того. чтобы ограничить скорость скачки нужно проверять сколько загрузилось за 1 секунду. если объем больше. то нужно выставлять паузу.
 * Для скачивания файлу используйте HttpConnection.java.
 * Тесты писать на этот код не нужно.
 * <p>
 * Created by roman.pogorelov on 22.09.2019
 */
public class Wget {
    private static final Logger LOG = Logger.getLogger(Wget.class.getName());
    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {
        String href = args[0];
        int speed = Integer.parseInt(args[1]);
        new Wget().download(href, speed);
    }


    /**
     * Downloads file from the href with limit speed.
     *
     * @param href  url to download from
     * @param speed max speed per sec
     */
    private void download(String href, int speed) {
        try {
            HttpsURLConnection con = (HttpsURLConnection) new URL(href).openConnection();
            speed *= 1024;
            byte[] buffer = new byte[speed > BUFFER_SIZE ? speed : BUFFER_SIZE];
            InputStream in = con.getInputStream();
            FileOutputStream out = new FileOutputStream("download.file");
            long start = System.currentTimeMillis();
            int downloaded = 0;
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                downloaded += bytesRead;
                if (checkTime(start)) {
                    downloaded = 0;
                    start = System.currentTimeMillis();
                }
                downloaded = checkLimit(speed, downloaded);
            }
        } catch (IOException | InterruptedException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * Checks whether past more than 1 sec.
     *
     * @param start start point
     * @return whether past more than 1 sec
     */
    private boolean checkTime(long start) {
        return System.currentTimeMillis() - start >= 1_000;
    }

    /**
     * Check downloaded limit, if exceeds then 1 pause and reset counter.
     *
     * @param speed      max allowed
     * @param downloaded already downloaded
     * @return allowed downloaded
     * @throws InterruptedException
     */
    private int checkLimit(int speed, int downloaded) throws InterruptedException {
        if (downloaded >= speed) {
            int quantity = (1000 * (downloaded - speed) / speed);
            LOG.info(String.format("Sleep %.3f seconds", quantity / 1000.0));
            Thread.sleep(quantity);
            downloaded = 0;
        }
        return downloaded;
    }
}
