import time

import ocr
import json
from translate import Translator

translator = Translator("your_app_id", "your_key")


def translation(base64):
    tencent_rep = ocr.tencent_ocr(base64)
    tencent_rep_json = json.loads(tencent_rep)
    res = []
    count = 1
    for item in tencent_rep_json['TextDetections']:
        d = {
            'Text': translator.baidu_translate(item['DetectedText']),
            'Direction': item['Polygon']
        }
        res.append(d)
        count += 1
        if count % 10 == 0:
            time.sleep(1)
    return res
