package com.dongql.deploy;

import com.dongql.deploy.bean.NginxUpStream;
import com.dongql.deploy.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.dongql.deploy.NginxConfTest.getNginxUpStream;
import static org.junit.Assert.assertEquals;

/**
 * Created by dongqilin on 2017/3/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppNodeTest {

    @Test
    public void load() {
    }

    @Test
    public void update() throws IOException {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        List<NginxUpStream> upStreams = new ArrayList<>();
        upStreams.add(getNginxUpStream("erp2"));
//        upStreams.add(getNginxUpStream("wechat2"));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(upStreams);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.getBytes());
        Request request = new Request.Builder()
                .url("http://localhost:8000/update") //这里也不能写update.html，这样会报406
                .post(body)
                .build();
        Call call = mOkHttpClient.newCall(request);
        Response response = call.execute();
        String str = response.body().string();
        Result result = mapper.readValue(str, Result.class);
        assertEquals(result.getMessage(), result.getCode(), 0);
    }


}
