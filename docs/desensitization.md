# toString 数据脱敏

---

## 用法

maven pom.xml 中添加 依赖

```xml
<dependency>
    <groupId>com.longfor.gaia.gfs</groupId>
    <artifactId>gfs-parent</artifactId>
    <version>${gfs.version}</version>
</dependency>
``` 

如果需要配置自定义自己的 logger 配置, 请在 classpath 中添加 logback-spring.xml

```xml
<configuration>
    <include resource="logging/logback-defaults.xml"/>
</configuration>
```

## 数据脱敏逻辑

提供两种方案实现数据脱敏:

1. 如果整个object中部分字段要做脱敏, 可以为需要脱敏的类添加 `@Mask` 注解

```java
    @Getter
    @Builder
    static class User {
        @Mask(prefixNoMaskLen = 2, suffixNoMaskLen = 3)
        private String password;

        @Override
        public String toString() {
            return new MaskToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).toString();
        }
    }

    @Getter
    @Builder
    static class Detail {
        @Mask(sensitiveType = SensitiveType.CHINESE_NAME)
        private String name;
        @Mask(sensitiveType = SensitiveType.PASSWORD)
        private String password;
        @Mask(sensitiveType = SensitiveType.EMAIL)
        private String email;
        @Mask(sensitiveType = SensitiveType.MOBILE_PHONE)
        private String mobile;
        @Mask(sensitiveType = SensitiveType.BANK_CARD)
        private String bankcard;
        @Mask(sensitiveType = SensitiveType.ID_CARD)
        private String idcard;
        @Mask(sensitiveType = SensitiveType.FIXED_PHONE)
        private String fixedPhone;
        @Mask(sensitiveType = SensitiveType.ADDRESS)
        private String address;
        @ToStringExclude
        private String ignore;

        @Override
        public String toString() {
            return new MaskToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).toString();
        }
    }
```

这样可以确保 toString 方法输出的信息为脱敏信息
目前列出了几种常用的脱敏格式, 详见 `SensitiveType.java`

也可以使用 
```java

public class Obj {
    private String name;
    
    // setter
    // getter
    
    @Override
    public String toString() {
        new MaskToStringBuilder(obj, ToStringStyle.SIMPLE_STYLE).toString();
    }
}
```
对某个class类进行脱敏


2. 另外可以支持某个字符串实现脱敏

`DesensitizedUtils` 提供了一组脱敏方法: 

```java
/**
 * 对字符串按指定类型脱敏
 * @param origin 原始字符串
 * @param sensitiveType 脱敏类型
 * @return
 */
public static String maskValue(String origin, SensitiveType sensitiveType) 

/**
 * 对字符串进行脱敏操作
 * @param origin 原始字符串
 * @param prefixNoMaskLen 左侧需要保留几位明文字段
 * @param suffixNoMaskLen 右侧需要保留几位明文字段
 * @param maskStr 用于遮罩的字符串, 如'*'
 * @return
 */
public static String maskValue(String origin, int prefixNoMaskLen, int suffixNoMaskLen, String maskStr)
```

脱敏效果如下:

```java
public class DesensitizedUtilsTest {

    @Test(description = "mask by prefix and suffix length")
    public void maskByPrefixAndSuffixLength() {
        assertEquals(DesensitizedUtils.maskValue(null, 1, 1, "*"), null);
        assertEquals(DesensitizedUtils.maskValue("abc", 1, 1, "*"), "a*c");
        assertEquals(DesensitizedUtils.maskValue("abc", 0, 1, "*"), "**c");
        assertEquals(DesensitizedUtils.maskValue("abc", 1, 0, "*"), "a**");
        assertEquals(DesensitizedUtils.maskValue("abcd", 2, 1, "*"), "ab*d");
        assertEquals(DesensitizedUtils.maskValue("abcd", 1, 2, "*"), "a*cd");
        assertEquals(DesensitizedUtils.maskValue("abc", 2, 2, "*"), "abc");
    }

    @Test(description = "mask chinese name")
    public void maskChineseName() {
        assertEquals(DesensitizedUtils.maskChineseName(null), null);
        assertEquals(DesensitizedUtils.maskChineseName("单弘昊"), "单*昊");
        assertEquals(DesensitizedUtils.maskChineseName("徐磊"), "徐*");
    }

    @Test(description = "mask id card num")
    public void maskIdCardNum() {
        assertEquals(DesensitizedUtils.maskIdCardNum(null), null);
        assertEquals(DesensitizedUtils.maskIdCardNum("340304200001010839"), "340304********0839");
        assertEquals(DesensitizedUtils.maskIdCardNum("34030420000101083X"), "340304********083X");
    }

    @Test(description = "mask email")
    public void maskEmail() {
        assertEquals(DesensitizedUtils.maskEmail(null), null);
        assertEquals(DesensitizedUtils.maskEmail("shanhonghao@longfor.com"), "s**********@longfor.com");
    }

    @Test(description = "mask fixed phone")
    public void maskFixedPhone() {
        assertEquals(DesensitizedUtils.maskFixedPhone(null), null);
        assertEquals(DesensitizedUtils.maskFixedPhone("62580000"), "****0000");
    }

    @Test(description = "mask mobile phone")
    public void maskMobilePhone() {
        assertEquals(DesensitizedUtils.maskMobilePhone(null), null);
        assertEquals(DesensitizedUtils.maskMobilePhone("18616354707"), "186****4707");
    }

    @Test(description = "mask bank card")
    public void maskBankCard() {
        assertEquals(DesensitizedUtils.maskBankCard(null), null);
        assertEquals(DesensitizedUtils.maskBankCard("6222023202000677123"), "622202*********7123");
    }

    @Test(description = "mask address")
    public void maskAddress() {
        assertEquals(DesensitizedUtils.maskAddress(null), null);
        assertEquals(DesensitizedUtils.maskAddress("上海市普陀区新村路2000号"), "上海市普陀区********");
    }

    @Test(description = "mask password")
    public void maskPassword() {
        assertEquals(DesensitizedUtils.maskPassword(null), null);
        assertEquals(DesensitizedUtils.maskPassword("123"), "******");
        assertEquals(DesensitizedUtils.maskPassword("1234567890"), "******");
    }

}
```
