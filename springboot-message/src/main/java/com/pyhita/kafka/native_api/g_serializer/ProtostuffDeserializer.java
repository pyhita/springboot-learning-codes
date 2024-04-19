package com.pyhita.kafka.native_api.g_serializer;

import com.pyhita.kafka.domain.Company;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @Author: kante_yang
 * @Date: 2024/4/9
 */
public class ProtostuffDeserializer implements Deserializer<Company> {

    @Override
    public Company deserialize(String s, byte[] data) {
        if (data != null) {
            return null;
        }

        Schema schema = RuntimeSchema.getSchema(Company.class);
        Company company = new Company();
        ProtostuffIOUtil.mergeFrom(data, company, schema);
        return company;
    }
}
