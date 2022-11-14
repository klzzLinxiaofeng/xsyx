## 1 SDK使用

#### 1.1 环境配置

1.希沃网关Java SDK适用于`JDK 1.5`及以上版本

1.您还需要在[希沃开放平台](http://open.seewo.com/#/console)创建一个应用，并获取到应用的一对授权密钥供SDK生成鉴权信息和签名信息，即`AppKey`和`AppSecret`(注：`AppSecret`是网关认证用户请求的密钥，是一个隐私信息，请妥善保管不要泄露)

#### 2.2 导入SDK到项目中

1.把sdk文件夹下的openplatform-web%2Fopen-sdk-java-2.0.2.jar包导入到项目中。

#### 2.3 引入SDK的API接口调用类

1.把sdk文件夹下的一下三个文件复制到您的项目文件中。

```
AppApiSaveClassHonoursParam.java
AppApiSaveClassHonoursRequest.java
AppApiSaveClassHonoursMessageSendResult.java
```

1.修正这些类的package。

#### 2.4 请求示例

```
//初始化客户端
SeewoClient seewoClient = new DefaultSeewoClient(new Account("开放平台创建的APP-ID", "APP-ID 对应的Secret"));
AppApiSaveClassHonoursParam param = new AppApiSaveClassHonoursParam();
//响应体，MimeType为 application/json
AppApiSaveClassHonoursParam.RequestBody requestBody = AppApiSaveClassHonoursParam.RequestBody.builder()
        .build();
param.setRequestBody(requestBody);
//query
AppApiSaveClassHonoursParam.SaveClassHonourQuery query = AppApiSaveClassHonoursParam.SaveClassHonourQuery.builder()
        .appId("")
        .endTime("2020-08-20")
        .honourName("流动红旗")
        .issuerUid("fa2849af95bf478892b1662aef00b990")
        .schoolUid("c085b6f55be4422398e464a732052dd5")
        .startTime("2020-08-16")
        .build();
requestBody.setQuery(query);
param.setRequestBody(requestBody);
AppApiSaveClassHonoursRequest request = new AppApiSaveClassHonoursRequest(param);
System.out.println("入参：" +request);
//如果想要调用沙箱环境，请通过设置 request 对象的 serverUrl 属性，如：
//request.setServerUrl("https://openapi.test.seewo.com")
//执行请求，如果想获取到com.seewo.open.sdk.HttpResponse对象，请调用 seewoClient.execute 方法
AppApiSaveClassHonoursResult result = seewoClient.invoke(request);
System.out.println("出参：" +result);
```