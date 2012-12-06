#include <Servo.h> 
#define cbi(sfr, bit) (_SFR_BYTE(sfr) &= ~_BV(bit))
#define sbi(sfr, bit) (_SFR_BYTE(sfr) |= _BV(bit))

Servo push;
Servo turn;
double zero;
int num_reads = 20000;  //how many measurements to take and average
double tare_factor = 0;
enum bottles {RUM = 2, TEQUILA = 3, GIN = 4, COKE = 8, SPRITE = 11, TONIC = 12};
enum debug_modes {NO_DEBUG=0, SCALE=1, POUR_SINGLE=2, TEST_ALL=3, TURN=4, SIT = 5};
int pinList[] = {RUM,TEQUILA,GIN,COKE,SPRITE,TONIC};
int PUSH_PIN = 9;
int TURN_PIN = 10;
int debug_mode = NO_DEBUG;
int debug_bottle = TEQUILA;
boolean NO_SCALE = false; //testing away from the scale, i.e. raw arduino


char incomingChar;
String messageBuffer = String();



void setup() 
{ 
  Serial.begin(9600);
  for (int i = 0; i < 6; i++) { 
    pinMode(pinList[i], OUTPUT);
    digitalWrite(pinList[i],LOW);
  }
  pinMode(8, OUTPUT);
  initialize_scale();
  
  //oversample the arduino!!
  cbi(ADCSRA,ADPS0);
  cbi(ADCSRA,ADPS1);
  sbi(ADCSRA,ADPS2);
  push.attach(PUSH_PIN);
  turn.attach(TURN_PIN);
  turn.write(0);
  push.write(0);
  initialize_scale();
} 


boolean parseCharacter(char aChar){
  if(incomingChar == '\n')
  { //start of a new message
    if(messageBuffer == "x")
    {
      initialize_scale();
      Serial.println("initializing");
    }
    else if(messageBuffer == "push"){
      //push cup                              // in steps of 1 degree 
      for(int i =60; i <160; i++)
      {
            push.write(i);              // tell servo to go to position in variable 'pos' 
            delay(15);                       // waits 15ms for the servo to reach the position 
      }
      delay(100);
      for(int i =160; i >40; i--)
      {
            push.write(i);              // tell servo to go to position in variable 'pos' 
            delay(15);                       // waits 15ms for the servo to reach the position 
      }
      delay(300);
      Serial.println("Pushed the cup!");
    }
    else if(messageBuffer =="turnTowards"){

       for(int pos = 0; pos < 200; pos += 1)  // goes from 0 degrees to 180 degrees 
       {                                  // in steps of 1 degree 
          turn.write(pos);              // tell servo to go to position in variable 'pos' 
          delay(20);                       // waits 15ms for the servo to reach the position 
       }
       delay(1000);
      Serial.println("Turned towards scale");
    }
    else if(messageBuffer =="turnAway"){
       for(int pos = 200; pos > 0; pos -= 1)  // goes from 0 degrees to 180 degrees 
       {                                  // in steps of 1 degree 
          turn.write(pos);              // tell servo to go to position in variable 'pos' 
          delay(20);                       // waits 15ms for the servo to reach the position 
       }
      delay(1000);
      Serial.println("Turned away scale");
    }
    else{
      
        int lastParen = messageBuffer.indexOf(')');
        int firstParen = messageBuffer.indexOf('(');
        if( lastParen <0 || firstParen != 0 ){
            Serial.println("ERROR:'" + messageBuffer + "'");
            delay(100);

            messageBuffer = String(); //start new message
            return false; //error, no comma found
        }

        String firstInt = messageBuffer.substring(1,messageBuffer.indexOf(','));
        String secondInt = messageBuffer.substring(messageBuffer.indexOf(',')+1,messageBuffer.length()-1);
        int handle_num = str_to_int(firstInt);
        int pour_amt = str_to_int(secondInt);
        int pin_to_write = pinList[handle_num];
        if(pour_amt < 0)
        {
          Serial.println("ERROR_POUR_AMT '" + secondInt + "'");     
        }
        else{
          if(NO_SCALE){
            digitalWrite(pin_to_write, HIGH);
            delay(5000);
            digitalWrite(pin_to_write, LOW);
          }
          else{
            pour(pin_to_write, pour_amt );
          }
          Serial.println("poured:" + String(pour_amt) + " from:" + String(handle_num));
        }
      }
    messageBuffer = String();
    return true;
  }
  else{
    messageBuffer.concat(incomingChar);
    return true;  
  }
}


void loop() {
  switch (debug_mode) {
    case SCALE:
      Serial.println(get_weight());
      break;
     
    case TURN:
       for(int pos = 0; pos < 200; pos += 1)  // goes from 0 degrees to 180 degrees 
       {                                  // in steps of 1 degree 
          turn.write(pos);              // tell servo to go to position in variable 'pos' 
          delay(20);                       // waits 15ms for the servo to reach the position 
       }
       for (int i=0; i<10; i++) {
         Serial.println(get_weight());
       }
       delay(3000);
       for(int pos = 200; pos > 0; pos -= 1)  // goes from 0 degrees to 180 degrees 
       {                                  // in steps of 1 degree 
          turn.write(pos);              // tell servo to go to position in variable 'pos' 
          delay(20);                       // waits 15ms for the servo to reach the position 
       }
       delay(3000);
      break;
    case POUR_SINGLE:
      pour(debug_bottle,50);
      debug_mode = SIT;
      delay(5000);
      break;
      
    case TEST_ALL:
      pour_all();
      break;
    
    case NO_DEBUG: //live
      if (Serial.available() > 0) {
        incomingChar = Serial.read();
        parseCharacter(incomingChar);
      }
     default:
       break;
  }
}

void pour_all() {
  if(NO_SCALE){   

      for (int i = 0; i < 6; i++) { 
        digitalWrite(pinList[i], LOW);
      }
      delay(500);
      for (int i = 0; i < 6; i++) { 
        digitalWrite(pinList[i], HIGH);
      }
      delay(500);
      for (int i = 0; i < 6; i++) { 
        digitalWrite(pinList[i], LOW);
      }
      Serial.write("WTF\n");
  }
  else{
      pour(TEQUILA,50);
      pour(RUM,50);
      pour(GIN,50);
      pour(COKE,50);
      pour(SPRITE,50);
      pour(TONIC,50);
  }
}

//converts a string to an integer
int str_to_int(String content) {
  char charBuf[content.length()+1];
  content.toCharArray(charBuf, content.length()+1);
  return atoi(charBuf);
}

//clear any lingering bits from the serial port
void flush_serial() {
    while (Serial.available() != 0) {
      Serial.read();
    }  
}


//From "bottle" pour "grams" of liquid
void pour(int bottle,int grams) {
  tare();
  double weight = 0;  
  digitalWrite(bottle, HIGH);
  while (weight < grams) {
    weight = get_weight();
  }
  digitalWrite(bottle, LOW);
}




//zeros the scale absolutely. for robustness, zeros voltage. only used initially.
void initialize_scale() {
   delay(50);
   zero = get_scale_voltage();                   //set new scale zero voltage
   tare();                                           //set new scale zero weight
}

//sets current scale weight to 0 grams
void tare() {
 delay(50);
 tare_factor = get_raw_weight(); 
}

//gives the weight on the scale in grams (at the appropriate zero)
double get_weight() {
 return get_raw_weight() - tare_factor;  
}



//gets weight on the scale
double get_raw_weight() {
  return 1.1484 * (get_scale_voltage() - zero);
}

//gets an averaged reading for the voltage across the wheatstone bridge
double get_scale_voltage() {
  double output = 0;
  for (int i = 0; i<num_reads ; i++) {
    output += analogRead(A0);
 } 
 output /= num_reads;
 return output;
}
