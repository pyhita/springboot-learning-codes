package com.yangtao.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 * @Author: kante_yang
 * @Date: 2023/12/19
 */
public class MyYamlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private ObjectMapper objectMapper;

    public MyYamlHttpMessageConverter() {
        // 配置这个message converter支持的类型
        super(new MediaType("text", "yaml", Charset.forName("utf-8")));
        YAMLFactory yamlFactory = new YAMLFactory()
            .disable(Feature.WRITE_DOC_START_MARKER);
        objectMapper = new ObjectMapper(yamlFactory);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // 支持序列化那种类型，对象类型都支持

        return true;
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
        throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage)
        throws IOException, HttpMessageNotWritableException {
        try (OutputStream body = outputMessage.getBody()) {
            objectMapper.writeValue(body, o);
        }
    }
}
