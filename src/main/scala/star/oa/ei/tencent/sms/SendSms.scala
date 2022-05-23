package star.oa.ei.tencent.sms

import com.tencentcloudapi.common.Credential
import com.tencentcloudapi.common.exception.TencentCloudSDKException
import com.tencentcloudapi.common.profile.ClientProfile
import com.tencentcloudapi.common.profile.HttpProfile
import com.tencentcloudapi.sms.v20210111.SmsClient
import com.tencentcloudapi.sms.v20210111.models.{DescribeSmsTemplateListRequest, DescribeSmsTemplateListResponse}

case class SmsConfig(
                      //(sdkAppID: String,
                      // appKey: String,
                      secretId: String,
                      secretKey: String //,
                      //endpoint: String
                    ) {
  lazy val credential: Credential = new Credential(secretId, secretKey)
  lazy val httpProfile: HttpProfile = {
    val profile = new HttpProfile
    profile.setEndpoint("sms.tencentcloudapi.com")
    profile
  }
  lazy val clientProfile: ClientProfile = {
    val profile = new ClientProfile()
    profile.setHttpProfile(httpProfile)
    profile
  }
  lazy val client: SmsClient = new SmsClient(credential, "ap-nanjing", clientProfile)
}

case class SmsTemplate
(TemplateId: Long,
 International: Long,
 StatusCode: Long,
 ReviewReply: String,
 TemplateName: String,
 CreateTeime: Long,
 TemplateContent: String)

case class SendSms(config: SmsConfig) {
  def listTemplate: List[SmsTemplate] = {
    // 实例化一个请求对象,每个接口都会对应一个request对象
    val req = new DescribeSmsTemplateListRequest()
    req.setInternational(0L)
    // 返回的resp是一个DescribeSmsTemplateListResponse的实例，与请求对象对应
    val resp: DescribeSmsTemplateListResponse =
      config.client.DescribeSmsTemplateList(req)
    // 输出json格式的字符串回包
    //          println(DescribeSmsTemplateListResponse.toJsonString(resp))
    resp.getDescribeTemplateStatusSet.toList.map(t =>
      SmsTemplate(t.getTemplateId, t.getInternational, t.getStatusCode, t.getReviewReply,
        t.getTemplateName, t.getCreateTime, t.getTemplateContent)
    )
  }
}

object SendSmsTest {
  def main(args: Array[String]): Unit = {
    val config = SmsConfig("", "")
    val sendSms = SendSms(config)
    println(sendSms.listTemplate)
  }
}