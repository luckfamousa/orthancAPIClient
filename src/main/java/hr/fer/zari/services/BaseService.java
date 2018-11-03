package hr.fer.zari.services;

import hr.fer.zari.OrthancException;
import hr.fer.zari.OrthancService;
import okhttp3.ResponseBody;
import retrofit2.Call;

import java.io.*;
import java.util.stream.Collectors;

/**
 * Created by eugen on 13/05/2017.
 */
public abstract class BaseService {

    protected OrthancService service;

    public BaseService(OrthancService service) {
        this.service = service;
    }

    protected <T> T checkResponse(Call<T> call) throws IOException, OrthancException {
        retrofit2.Response response = call.execute();
        if (response.isSuccessful()) {
            return (T) response.body();
        } else {                 
          
            String body = "";
            ResponseBody reb = response.errorBody();
            if (reb != null) {
              BufferedReader r = new BufferedReader(reb.charStream());              
              body = r.lines().collect(Collectors.joining("\n"));
            }
          
            throw new OrthancException(String.format("%s (%s)\n%s", response.message(), response.code(), response.toString()));
        }
    }

    protected boolean writeResponseBodyToDisk(ResponseBody body, String file) {
        try {
            File downloadFile = new File(file);
            downloadFile.getParentFile().mkdirs();
//            FileWriter writer = new FileWriter(downloadFile);
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];
//                long fileSize = body.contentLength();
//                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(downloadFile);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
//                    fileSizeDownloaded += read;
//                    SystemInfo.out.println("file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
