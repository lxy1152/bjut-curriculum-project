import base64

from tencentcloud.common import credential
from tencentcloud.common.profile.client_profile import ClientProfile
from tencentcloud.common.profile.http_profile import HttpProfile
from tencentcloud.common.exception.tencent_cloud_sdk_exception import TencentCloudSDKException
from tencentcloud.ocr.v20181119 import ocr_client, models


def tencent_ocr(base64):
    try:
        cred = credential.Credential("your_app_id", "your_key")
        httpProfile = HttpProfile()
        httpProfile.endpoint = "ocr.tencentcloudapi.com"

        clientProfile = ClientProfile()
        clientProfile.httpProfile = httpProfile
        client = ocr_client.OcrClient(cred, "ap-guangzhou", clientProfile)

        req = models.GeneralBasicOCRRequest()
        # req.ImageUrl = "https://mc.qcloudimg.com/static/img/6d4f1676deba26377d4303a462ca5074/image.png"
        params = '{"ImageBase64":"' + base64 + '"}'
        req.from_json_string(params)
        resp = client.GeneralBasicOCR(req)
        # print(resp.to_json_string())
        return resp.to_json_string()
    except TencentCloudSDKException as err:
        print(err)
