package request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpHeaderTest {

    @DisplayName("getLength test")
    @Test
    void test1() {
        // key, value 모두 존재
        // key 존재하지 않음, value 존재
        // key 존재, value 존재하지 않음.
        // key 존재, value delimiter 뒤에 value 값이 없는 경우.  "key:value,"
        // : 이 존재 유무
        // value 에 ' ' 들어간경우.
//
//        HttpHeader.Builder httpHeaderBuilder = new HttpHeader.Builder();
//
//        HttpHeader httpHeader = httpHeaderBuilder.append("testKey:testValue").build();
//
//        Assertions.assertThat(httpHeader.getContentLength()).isSameAs(0);
        HttpHeader.Builder httpHeaderBuilder = new HttpHeader.Builder();

        HttpHeader httpHeader = httpHeaderBuilder.append("testKey:testvalue").build();

        Assertions.assertThat(httpHeader.getContentLength()).isSameAs(0);
    }
}