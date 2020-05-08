package com.xm.httpapi.BaseApi;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.xm.httpapi.BaseApiStatus.ErrorType;
import com.xm.httpapi.BaseApiStatus.ServerException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

/**
 *
 */

public class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String response = value.string();
            Log.i("CustomGsonResponse", "convert: " + response);
            JSONObject mJSONObject = null;
            try {
                mJSONObject = new JSONObject(response);
            } catch (JSONException mE) {
                mE.printStackTrace();
            }
            int code = mJSONObject.optInt("code", -1);
            if (code == ErrorType.SUCCESS.getCode()) {
                MediaType mediaType = value.contentType();
                Charset charset = mediaType != null ? mediaType.charset(UTF_8) : UTF_8;
                InputStream inputStream = new ByteArrayInputStream(response.getBytes());
                JsonReader jsonReader = gson.newJsonReader(new InputStreamReader(inputStream, charset));
                T result = adapter.read(jsonReader);
                if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
                return result;
            } else {
                String message = mJSONObject.optString("message");
                value.close();
                throw new ServerException(code, message);
            }

        } finally {
            value.close();
        }
    }
}
