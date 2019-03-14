package jsonparse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpRequest {

    // to make sure HttpRequest is not called accidentally
    public HttpRequest() {
    }

    private static final String LOG_TAG = "Network connection";

    public static String getJsonResponse(String urlString) {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        String jsonResponse = "";

        if (urlString == null) {
            return null;
        }

        try {

            /**
             * Establish a new network connection using the url
             */
            httpURLConnection = (HttpURLConnection) (new URL(urlString).openConnection());
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.connect();

            // Read input stream and get json response only if the network connection is success
            if (httpURLConnection.getResponseCode() == 200) {
                /** Get the input stream */
                inputStream = httpURLConnection.getInputStream();

                /**
                 * Read the response and assign the response to the jsonResponse string
                 */
                StringBuilder builder = new StringBuilder();
                String line = null;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        builder.append(line);
                    }
                    jsonResponse = builder.toString();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error with getting the json response", e);
                } finally {
                    inputStream.close();
                    httpURLConnection.disconnect();
                }
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with the url", e);
        } catch (ProtocolException e) {
            Log.e(LOG_TAG, "Error with the request method (protocol)", e);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error with the connect() method", e);
        }

        return jsonResponse;
    }


    public static Bitmap getBitmap(String stringUrl) throws IOException {
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            URL imageUrl = new URL(stringUrl);
            inputStream = imageUrl.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error with retrieving image form url", e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return bitmap;
    }
}