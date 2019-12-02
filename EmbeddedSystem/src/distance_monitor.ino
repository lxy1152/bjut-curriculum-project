// 引入lcd与超声头文件 
// #include <LiquidCrystal.h> 
#include <Ultrasonic.h>

// 超声引脚
const int ULTRASONIC_PIN = 7;
// 蜂鸣器引脚
const int BUZZER_PIN = 8;
// lcd引脚， rs是寄存器选择端， en是使能端， d4-d7为4个数据端
//const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;


// 声明超声变量
Ultrasonic ultrasonic(ULTRASONIC_PIN);
// 保存超声测量的距离
long distance;
// 声明lcd变量
//LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

void setup() {
  // 启动串口与设置引脚
  Serial.begin(9600);
  pinMode(BUZZER_PIN, OUTPUT);
// 启动lcd， 设置为2行16列
//  lcd.begin(16, 2);
//  lcd.print("H");
//  delay(1000);
//  lcd.clear();
}

void loop() {
  // 超声测量距离
  distance = ultrasonic.MeasureInCentimeters();
  // 在串口监视器中输出
//  Serial.print("The distance is:");
//  Serial.print(distance, DEC);
//  Serial.print("cm");
//  Serial.println();
  // 对距离进行判断，不同的距离，有不同的提示音
  if(distance < 5){
    // 589对应D调高音do的频率，播放持续时间200毫秒
    tone(BUZZER_PIN, 589);
    delay(200);
    Serial.print("当前距离过近！请立刻减速！当前距离为：");
    Serial.print(distance);
    Serial.println("cm");
  }
  else if (distance < 10){
    // 882对应A调高音do的频率，播放持续时间200毫秒
    tone(BUZZER_PIN, 882);
    delay(200);
    Serial.print("请注意您的车距并立即减速！当前距离为：");
    Serial.print(distance);
    Serial.println("cm");
  }
  else if (distance < 20){
    // 525对应C调高音do的频率，播放持续时间400毫秒
    tone(BUZZER_PIN, 525);
    delay(400);
    Serial.print("请注意您的车距并适当减速！当前距离为：");
    Serial.print(distance);
    Serial.println("cm");
  }
  else{
    Serial.print("请保持当前车距！当前距离为:");
    Serial.print(distance);
    Serial.println("cm");
  }
  // 停止当前音符，进入下一个音符
  noTone(BUZZER_PIN);
//  lcd.print("Hello World");
  // 超声延时150毫秒
  delay(150);
}
