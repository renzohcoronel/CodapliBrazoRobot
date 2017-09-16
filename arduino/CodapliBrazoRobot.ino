#include <Servo.h>

char dato;
Servo hombro, codo, pinza, base;
int phombro, pcodo, ppinza, pbase, contador=0;

const int PIN_HOMBRO = 13;
const int ANG_MIN_HOMBRO=10;
const int POSICION_INICIAL_HOMBRO=86;
const int ANG_MAX_HOMBRO=120;

const int PIN_CODO = 12;
const int ANG_MIN_CODO=10;
const int POSICION_INICIAL_CODO=86;
const int ANG_MAX_CODO=120;

const int PIN_PINZA = 10;
const int ANG_MIN_PINZA=10;
const int POSICION_INICIAL_PINZA=86;
const int ANG_MAX_PINZA=120;

const int PIN_BASE = 9;
const int ANG_MIN_BASE=20;
const int POSICION_INICIAL_BASE=86;
const int ANG_MAX_BASE=100;

const int PASO_ANGULO = 1;

void setup()
{
  hombro.attach(PIN_HOMBRO);
  codo.attach(PIN_CODO);
  pinza.attach(PIN_PINZA);
  base.attach(PIN_BASE);
  inicializarServos();
  Serial.begin(9600);
}

void inicializarServos(){
     // Leer posicion inicial
    phombro=hombro.read();
    pcodo=codo.read();
    ppinza=pinza.read();
    pbase=base.read();
    //Escribir posicion inicial
    hombro.write(POSICION_INICIAL_HOMBRO);
    codo.write(POSICION_INICIAL_CODO);
    pinza.write(POSICION_INICIAL_PINZA);
    base.write(POSICION_INICIAL_BASE);
}

void loop()
{

  if (Serial.available() > 0){
      dato=Serial.read();
      switch(dato)
      {
        //Hombro -------------------------------------------
        case 'w': 
        {
        Serial.println("Hombro");
        Serial.println(phombro);
          if(phombro>ANG_MAX_HOMBRO){
                Serial.println("No hago nada");
                break;
            } else {
                  phombro=phombro+PASO_ANGULO;
                  hombro.write(phombro);
                  }           

          break;  
        }
        case 's': 
        {
        Serial.println("Hombro");
        Serial.println(phombro);
          if(phombro<ANG_MIN_HOMBRO){
                  Serial.println("No hago nada");
                  break;
                } else {
                  phombro=phombro-PASO_ANGULO;
                  hombro.write(phombro);

                  }
              
          break;  
        }
      // Codo -------------------------------------------
      case 'q': 
        {
        Serial.println("Codo");
        Serial.println(pcodo);
          if(pcodo>ANG_MAX_CODO){
                Serial.println("No hago nada");
                break;
            } else {
                  pcodo=pcodo+PASO_ANGULO;
                  codo.write(pcodo);
                  }           

          break;  
        }
        case 'e':
        {
          Serial.println("Codo");
          Serial.println(pcodo);
          if(pcodo<ANG_MIN_CODO){
                  Serial.println("No hago nada");
                  break;
                } else {
                  pcodo=pcodo-PASO_ANGULO;
                  codo.write(pcodo);
                  }
              
          break;  
        }
        // Pinza -------------------------------------------
        case 'p': 
        {
        Serial.println("Pinsa");
        Serial.println(ppinza);
          if(ppinza>ANG_MAX_PINZA){
                Serial.println("No hago nada");
                break;
            } else {
                  ppinza=ppinza+PASO_ANGULO;
                  pinza.write(ppinza);
                  }           

          break;  
        }    
        case 'o': 
        {
          if(ppinza<ANG_MIN_PINZA){
                  Serial.println("No hago nada");
                  break;
                } else {
                  ppinza=ppinza-PASO_ANGULO;
                  pinza.write(ppinza);
                  }
              
          break;  
        }
        //Base -------------------------------------------
        case 'a': 
        {
        Serial.println("Base");
        Serial.println(pbase);
          if(pbase>ANG_MAX_BASE){
                Serial.println("No hago nada");
                break;
            } else {
                  pbase=pbase+PASO_ANGULO;
                  base.write(pbase);
                  }           

          break;  
        } 
        case 'd': 
        {
          Serial.println("Base");
          Serial.println(pbase);
          if(pbase<ANG_MIN_BASE){
                  Serial.println("No hago nada");
                  break;
                } else {
                  pbase=pbase-PASO_ANGULO;
                  base.write(pbase);
                  }
              
          break;  
        }
        default: {
          Serial.println("Caracter no valido");
          break;
        }
      }
  }
   delay(100);
}
