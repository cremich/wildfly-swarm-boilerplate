package de.onkelpixel.example.wildflyswarm.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GsonMessageBodyHandler implements MessageBodyWriter, MessageBodyReader {

    private static final String UTF_8 = "UTF-8";

    private Gson gson;

    private Gson getGson() {
        if (gson == null) {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();
        }
        return gson;
    }

    @Override
    public boolean isReadable(final Class type, final Type genericType,
            final Annotation[] annotations, final MediaType mediaType) {

        return true;
    }

    @Override
    public Object readFrom(final Class type, final Type genericType, final Annotation[] annotations,
            final MediaType mediaType, final MultivaluedMap httpHeaders,
            final InputStream entityStream) throws IOException, WebApplicationException {

        final InputStreamReader streamReader = new InputStreamReader(entityStream, UTF_8);
        try {
            final Type jsonType;
            if (type.equals(genericType)) {
                jsonType = type;
            } else {
                jsonType = genericType;
            }
            return getGson().fromJson(streamReader, jsonType);
        } finally {
            streamReader.close();
        }
    }

    @Override
    public boolean isWriteable(final Class type, final Type genericType, final Annotation[] annotations,
            final MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(final Object object, final Class type, final Type genericType, final Annotation[] annotations,
            final MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(final Object object, final Class type, final Type genericType, final Annotation[] annotations,
            final MediaType mediaType, final MultivaluedMap httpHeaders,
            final OutputStream entityStream) throws IOException, WebApplicationException {
        final OutputStreamWriter writer = new OutputStreamWriter(entityStream, UTF_8);
        try {
            final Type jsonType;
            if (type.equals(genericType)) {
                jsonType = type;
            } else {
                jsonType = genericType;
            }
            getGson().toJson(object, jsonType, writer);
        } finally {
            writer.close();
        }
    }
}
